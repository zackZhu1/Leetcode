package Graph;
import java.util.*;

// Determine if the directed graph has only one valid topological order
// topological sort by BFS
public class Graph04 {
	public static boolean isValid(Map<Integer, List<Integer>> graph) {
		Map<Integer, Integer> indegrees = getIndegrees(graph);
		return topologicalSort(graph, indegrees);
	}
	
	private static boolean topologicalSort(Map<Integer, List<Integer>> graph, Map<Integer, Integer> indegrees) {
		Queue<Integer> queue = new LinkedList<>();
		for (Map.Entry<Integer, Integer> entry : indegrees.entrySet()) {
			if (entry.getValue() == 0) {
				queue.offer(entry.getKey());
			}
		}
		
		while (!queue.isEmpty()) {
			if (queue.size() > 1) {
				return false;
			}
			int curVertex = queue.poll();
			List<Integer> neighbors = graph.get(curVertex);
			for (Integer nei : neighbors) {
				int indegree = indegrees.get(nei) - 1;
				indegrees.put(nei, indegree);
				if (indegree == 0) {
					queue.offer(nei);
				}
			}
		}
		return true;
	}
	
	private static Map<Integer, Integer> getIndegrees(Map<Integer, List<Integer>> graph) {
		Map<Integer, Integer> indegrees = new HashMap<>();
		for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
			int vertex = entry.getKey();
			if (!indegrees.containsKey(vertex)) {
				indegrees.put(vertex, 0);
			}
			for (Integer nei : entry.getValue()) {
				if (!indegrees.containsKey(nei)) {
					indegrees.put(nei, 0);
				}
				indegrees.put(nei, indegrees.get(nei) + 1);
			}
		}
		return indegrees;
	}
	
	public static void main(String[] args) {
		// test case: 1 -> 2 -> 3
		//                   -> 4
		Map<Integer, List<Integer>> graph = new HashMap<>();
		List<Integer> list1 = new ArrayList<>();
		list1.add(2);
		List<Integer> list2 = new ArrayList<>();
		list2.add(3); list2.add(4);
		graph.put(1, list1);
		graph.put(2, list2);
		graph.put(3, new ArrayList<>());
		graph.put(4, new ArrayList<>());
		System.out.println(isValid(graph));
	}
}
