package Graph;
import java.util.*;

// reverse a graph
/*
 * 遍历图 每次将边反向即可
 * https://www.geeksforgeeks.org/transpose-graph/
 */
public class Graph01 {
	public static void reverseGraph(Map<Integer, List<Integer>> graph, Map<Integer, List<Integer>> reversedGraph) {
		for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
			int u = entry.getKey();
			if (!reversedGraph.containsKey(u)) {
				reversedGraph.put(u, new ArrayList<>());
			}
			List<Integer> neis = entry.getValue();
			for (int i = 0; i < neis.size(); i++) {
				int nei = neis.get(i);
				if (!reversedGraph.containsKey(nei)) {
					reversedGraph.put(nei, new ArrayList<>());
				}
				reversedGraph.get(nei).add(u);
			}
		}
	}
	
	public static void printEdges(Map<Integer, List<Integer>> graph) {
		for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
			System.out.println(entry.getKey() + "-->");
			for (Integer nei : entry.getValue()) {
				System.out.print(nei + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// input graph: Map<Integer, List<Integer>> 
		Map<Integer, List<Integer>> graph = new HashMap<>();
		Map<Integer, List<Integer>> reversedGraph = new HashMap<>();
		List<Integer> l0 = new ArrayList<>();
		List<Integer> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();
		List<Integer> l3 = new ArrayList<>();
		List<Integer> l4 = new ArrayList<>();
		l0.add(1); l0.add(4); l0.add(3);
		l2.add(0);
		l3.add(2);
		l4.add(1); l4.add(3);
		graph.put(0, l0); graph.put(1, l1); graph.put(2, l2); graph.put(3, l3); graph.put(4, l4);
		reverseGraph(graph, reversedGraph);
		printEdges(reversedGraph);
	}
}
