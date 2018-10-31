package Graph;

import java.util.*;

// return the lexicographically smallest topological order
public class Graph05 {
	public static List<Integer> isValid(Map<Integer, List<Integer>> graph) {
		Map<Integer, Integer> indegrees = getIndegrees(graph);
		List<Integer> order = new ArrayList<>();
		return topologicalSort(graph, indegrees, order);
	}
	
	private static List<Integer> topologicalSort(Map<Integer, List<Integer>> graph, Map<Integer, Integer> indegrees, List<Integer> order) {
		PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
			
		});
		for (Map.Entry<Integer, Integer> entry : indegrees.entrySet()) {
			if (entry.getValue() == 0) {
				heap.offer(entry.getKey());
			}
		}
		
		while (!heap.isEmpty()) {
			int curVertex = heap.poll();
			order.add(curVertex);
			List<Integer> neighbors = graph.get(curVertex);
			for (Integer nei : neighbors) {
				int indegree = indegrees.get(nei) - 1;
				indegrees.put(nei, indegree);
				if (indegree == 0) {
					heap.offer(nei);
				}
			}
		}
		return order;
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
		Map<Integer, List<Integer>> graph = new HashMap<>();
		List<Integer> list1 = new ArrayList<>();
		list1.add(2); list1.add(4);
		List<Integer> list2 = new ArrayList<>();
		list2.add(4);
		List<Integer> list3 = new ArrayList<>();
		list3.add(2); list3.add(4);
		List<Integer> list4 = new ArrayList<>();
		graph.put(1, list1); graph.put(2, list2); graph.put(3, list3); graph.put(4, list4); 
		System.out.println(isValid(graph));
	}
}
