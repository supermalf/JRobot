-- loadMap.lua, Loads a map and return it to Java
-- $Id: loadMap.lua,v 1.1 2005/06/19 08:47:40 savio Exp $

dofile(script)

table = luajava.newInstance("java.util.HashMap")
nprops = entry.nprops
height = entry.height
width  = entry.width
n = entry.n

if proper ~= nil and proper <= nprops then
   map = entry.maps[proper]
   propname = entry.propname[proper]
   for i=1,width do
      for j=1,height do
	 table:put(tostring(i).."-"..tostring(j),tostring(map[i][j]));
      end
   end
end

