import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BreadthFirstSearch {
    private static final int EDGE_WEIGHT = 6;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numQueries = sc.nextInt();
        for (int i = 0; i < numQueries; i++) 
		{
            int numNodes = sc.nextInt();
            int numEdges = sc.nextInt();
            Node [] node = new Node[numNodes + 1]; 
            node[0] = null;
            for (int j = 1; j <= numNodes; j++) {
                node[j] = new Node(j);
            }
            for (int j = 0; j < numEdges; j++) {
                int n1 = sc.nextInt();
                int n2 = sc.nextInt();
                node[n1].addNeighbor(node[n2]);
            }
            int start = sc.nextInt();
            findDistances(node[start]);
            for (int j = 1; j <= numNodes; j++) {
                if (j != start) {
                    System.out.print(node[j].distance + " ");
                }
            }
            System.out.println();
        }
        sc.close();
    }
    private static void findDistances(Node start) {
        if (start == null) {
            return;
        }
        ArrayDeque<Node> deque = new ArrayDeque<>(); 
        start.distance = 0;
        deque.add(start);
        while (!deque.isEmpty()) {
            Node curr = deque.remove();
            for (Node neighbor : curr.neighbors) {
                if (neighbor.distance == -1) { 
                    neighbor.distance = curr.distance + EDGE_WEIGHT;
                    deque.add(neighbor);
                }
            }
        }
    }
}
class Node {
        public final int id; 
        public int distance;
        public HashSet<Node> neighbors;
        public Node (int id) {
            this.id   = id;
            distance  = -1;
            neighbors = new HashSet<>();
        }
        public void addNeighbor(Node neighbor) {
            neighbors.add(neighbor);
            neighbor.neighbors.add(this);
        }
        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            } else if (other == null || !(other instanceof Node)) {
                return false;
            }
            Node otherNode = (Node) other;
            return this.id == otherNode.id;
        }
        @Override
        public int hashCode() {
            return id; // simple and effective
        }
}
