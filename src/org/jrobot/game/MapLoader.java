package org.jrobot.game;

import org.jrobot.game.proper.*;
import org.jrobot.log.*;

import org.keplerproject.luajava.*;

import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Map loader
 *
 * @author savio
 * @version $Id: MapLoader.java,v 1.15 2005/07/04 05:01:34 savio Exp $
 */

/* XXX 
 * rever ordem de height e width que ficou confuso
 *
 * CUIDADO: (XXX FIXME)
 * essa classe nao diferencia propriedades dinamicas de estaticas.
 * todas as propriedades carregadas sao interpretadas pela interface
 * Proper. 
 */
public class MapLoader extends Map {

    /**
     * Table used to store lua matrices
     */
    private static HashMap table;

    /**
     * LuaState, a stack to talk to lua
     */
    private static LuaState L;

    /**
     * File descriptor
     */
    private static File file;

    /**
     * Property names
     */
    private String propname;

    /**
     * Map matrix
     */
    private float[][] mat;

    /**
     * Maximum number of new properties created by user
     */
    private static int MAX_PROP = 10;

    /**
     * Property current index
     */
    static int k;

    /**
     * Class constructor
     *
     * @param mapFile     lua map path
     * @param bindedProps name of properties to be loaded (null accepted)
     * @see Proper
     */
    public MapLoader(String mapFile, String[] bindedProps) {

        super();

        if (bindedProps != null) {
            ndprops = bindedProps.length;
        }

        /* Loads static properties */
        loadMapFile(mapFile);

        /* Loads dynamic properties */
        for (int i = 0; i < ndprops; i++, k++) {
            try {
                props[k - 1] = (Proper) Class.forName("org.jrobot.game.proper." +
                        bindedProps[i]).newInstance();
                props[k - 1].setSize(height, width);
                props[k - 1].setName(bindedProps[i]);
                Log.game("Dynamic Property loaded: " + bindedProps[i]);
            } catch (Exception e) {
                Log.game("Error : Dynamic property " + bindedProps[i] +
                        " doesnt exist!");
                Log.debug("Could not load dynamic property : "
                        + bindedProps[i] + " :" + e);
                k--;
            }
        }
        /** make log entry with load status */
        Log.game((k - 1) + "/" + (nprops + ndprops) + " props loaded, " + (k - nprops - 1) +
                " dynamics. map size: [" + height + "," + width + "].");

        /** set nprops to total number of properties found */
        nprops = k - 1;

        robotPosition = new int[width][height];
        flagPosition  = new int[width][height];
    }

    /**
     * Loads a map and store data.
     *
     * Programmers note: this method is lame and ugly. This code
     * was leeched from JRobot-MapEditor project. XXX
     *
     * TODO: implement a better algorithm
     *
     * @param _mapFile Lua map path
     */
    private void loadMapFile(String _mapFile) {

        /** XXX mudar o nome das variaveis pra alguma coisa mais clara */
        Float K;      /** Counter */
        Integer I;      /** Counter */
        Integer J;      /** Counter */
        String string; /** Temp string */
        Object obj;    /** Temp object */
        int i;      /** Counter */
        int j;      /** Counter */
        int p;      /** Counter */
        byte[] data;

        mapFile = _mapFile;

        mapFile = GameUtils.getMapPath() + mapFile;
        file = new File(mapFile);
        if (!file.exists()) {
            if (GameUtils.consoleMode() != true) {
                javax.swing.JOptionPane.showMessageDialog(null,
                        "Map file not found : " + mapFile);
            } else {
                System.out.println("Map file not found");
            }
            GameUtils.handleExit();
        }

        /* 
         * Call it once with no property to get map properties and
         * alloc a Proper vector of exact size.
         **/
        if (doScript(0) == false) {
            return;
        }

        /**
         * Alloc Proper vector
         */
        props = new Proper[nprops + ndprops];
        
        /*
         * Vector index 0 is not used to make our job easier, since in
         * Lua the default first position is set to 1.
         */
        mat = new float[height][width];
        propname = new String();

        /* Convert HashMap to float matrix */
        for (p = 1, k = 1; p <= nprops; p++, k++) {

            if (doScript(p) == false) {
                return;
            }

            for (i = 0; i < height; i++) {
                I = new Integer(i + 1);
                for (j = 0; j < width; j++) {
                    J = new Integer(j + 1);

                    obj = (I.toString() + "-" + J.toString());
                    string = (String) table.get(obj);
                    if (string == null) {
                        Log.game("Error loading map. Check log for details.");
                        Log.warning("Error fetching map from script : [" +
                                string + "," + mapFile + "]");
                        return;
                    }
                    K = new Float(string);
                    mat[i][j] = K.floatValue();
                }
            }

            /**
             * Loads static properties found on lua map.
             */
            try {
                props[k - 1] = (Proper) Class.forName("org.jrobot.game.proper." +
                        propname).newInstance();
                props[k - 1].setMap(mat,width,height);
                props[k - 1].setSize(height, width);
                props[k - 1].setName(propname);
                Log.game("Static Property loaded: " + propname);
            } catch (Exception e) {
                Log.game("Error : Static property " + propname + " doesnt exist!");
                Log.debug("Could not load static property : "
                        + propname + " :" + e);
                k--;
            }
        }
        /** make log entry with load status XXX*/
        // Log.game((k-1)+"/"+nprops+" props loaded, map size: ["+ height
        // +","+ width +"].");
    }

    /**
     * Executes a lua script and fetch one map layer/property
     *
     * @param proper Index of the property to be loaded. If this index is 0,
     *               doScript() returns the number of properties this map has.
     */

    public boolean doScript(int proper) {
        int err;

        if (proper > nprops) {
            return false;
        }
        
        /* Get things rolling with Lua */

        L = LuaStateFactory.newLuaState();
        L.openBasicLibraries();

        L.newTable();

        L.pushString(mapFile);
        L.setGlobal("script");

        if (proper != 0) {
            L.pushNumber(proper);
            L.setGlobal("proper");
        }
        /* Load the script */
        err = L.doFile(GameUtils.getScriptPath() + "loadMap.lua");

        /**
         * the following code block is distributed under MIT license
         * by LuaJava 1.0b4 (http://www.keplerproject.org/luajava).
         *
         * Copyright (C) 2004 Kepler Project
         */
        if (err != 0) {
            switch (err) {
                case 1:
                    System.err.println("Runtime error. " + L.toString(-1));
                    break;
                case 2:
                    System.err.println("File not found. " + L.toString(-1));
                    break;
                case 3:
                    System.err.println("Syntax error. " + L.toString(-1));
                    break;
                case 4:
                    System.err.println("Memory error. " + L.toString(-1));
                    break;
                default :
                    System.err.println("Error. " + L.toString(-1));
                    break;
            }
        }
        /* Fetch variables from Lua when called for the first time */
        try {
            if (proper == 0) {
                height = (int) L.getLuaObject("height").getNumber();
                width = (int) L.getLuaObject("width").getNumber();
                nprops = (int) L.getLuaObject("nprops").getNumber();
                n = (int) L.getLuaObject("n").getNumber();
            }
            if (proper > 0) {
                table = (HashMap) L.getLuaObject("table").getObject();
                propname = (String) L.getLuaObject("propname").getString();
            }
        } catch (LuaException e) {
            Log.warning("Lua exception received, game could not start : " + e);
            return false;
        }
        L.close();

        return true;
    }

    /**
     * Load property to props list.
     *
     * @param idx  Property index
     * @param name Property name
     * @deprecated Since MapLoader.java CVS-1.4 revision due to the difference
     *             between loading dynamic and static properties.
     */
    private void doLoad(int idx, String name) throws Exception {
        return;
    }
}
