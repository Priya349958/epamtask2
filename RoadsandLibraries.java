import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
public class Solution{
    public static long roadsAndLibraries(int n, long cl, long cr, ArrayList<TreeSet<Integer>> adj) {
if (cl <= cr) 
{ 
    return (cl * n); 
}
        long cost = 0;
        boolean[] visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                long tempCost = cl;
                Stack<Integer> stack = new Stack<>();
                stack.push(i);
                visit[i] = true;
                while (!stack.empty()) {
                    int u = stack.pop();
                    Iterator<Integer> it = adj.get(u).iterator();
                    while (it.hasNext()) {
                        int v = it.next();
                        if (!visit[v]) {
                            stack.push(v);
                            visit[v] = true;
                            tempCost += cr;
                        }
                    }
                }
                cost += tempCost;
            }
        }
        return cost;
    }
public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = sc.nextInt();
            int m = sc.nextInt();
            long cl = sc.nextLong();
            long cr = sc.nextLong();
            ArrayList<TreeSet<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) { adj.add(new TreeSet<Integer>()); }
            for(int a1 = 0; a1 < m; a1++){
                int u = sc.nextInt();
                u--;
                int v = sc.nextInt();
                v--;
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            System.out.println(Solution.roadsAndLibraries(n, cl, cr, adj));
        }
    }    
}