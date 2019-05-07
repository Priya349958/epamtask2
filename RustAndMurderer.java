import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
public class RustAndMurderer {
  public static void main(String[] args) throws Exception {
    final StringBuffer stb = new StringBuffer();
    final BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
    for(byte t = Byte.parseByte(bufr.readLine()); t > 0; --t){
      String[] line = bufr.readLine().split(" ");
      final int n = Integer.parseInt(line[0]);
      final List<Set<Integer>> roads = new ArrayList<Set<Integer>>(n);
      for(int i = 0; i < n; ++i){
        roads.add(new HashSet<Integer>());
      }
      for(short m = Short.parseShort(line[1]); m > 0; --m){
        line = bufr.readLine().split(" ");
        final int X = Integer.parseInt(line[0]) - 1;
        final int Y = Integer.parseInt(line[1]) - 1;
        roads.get(X).add(Y);
        roads.get(Y).add(X);
      }
      final int s = Integer.parseInt(bufr.readLine()) - 1;
      final int[] minD = getEdgelessminD(roads, s, n);   
      //Print output
      for(int i = 0; i < s; stb.append(minD[i++] + " ")){}
      for(int i = s; ++i < n; stb.append(minD[i] + " ")){}
      stb.append("\n");
    }
    System.out.print(stb);
  }
  private static int[] getEdgelessminD(final List<Set<Integer>> roads, final int origin, final int n){
    final int[] d = new int[n];
    for(int i = 0; i < n; d[i++] = -1){}
    final List<Integer> unvisited = new LinkedList<Integer>();
    for(int i = 0; i < origin; unvisited.add(i++)){}
    for(int i = origin; ++i < n; unvisited.add(i)){}
    
    //Find min d
    final Queue<Integer> queue = new LinkedList<Integer>();
    queue.add(origin);
    do {
      final int city = queue.poll();
      final int distance = ++d[city];
      final Set<Integer> cityRoads = roads.get(city);
      for(Iterator<Integer> it = unvisited.iterator(); it.hasNext();){
        final int unvisitedCity = it.next();
        if(!cityRoads.contains(unvisitedCity)){
          d[unvisitedCity] = distance;
          it.remove();
          queue.add(unvisitedCity);
        }
      }
    } while (!queue.isEmpty());   
    return d;
  }
}