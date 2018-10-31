package Graph;

import java.util.*;

// determine a list is one of the topological order.
public class Graph06 {
	public static boolean isValid(Map<Integer, List<Integer>> graph, List<Integer> order) {
		Map<Integer, Integer> indegrees = getIndegrees(graph);
		return topologicalSort2(graph, indegrees, order);
	}
	
	// solution1: use set to store all the candidates whose indegree is 0.
	private static boolean topologicalSort1(Map<Integer, List<Integer>> graph, Map<Integer, Integer> indegrees, List<Integer> order) {
		Set<Integer> set = new HashSet<>();
		for (Map.Entry<Integer, Integer> entry : indegrees.entrySet()) {
			if (entry.getValue() == 0) {
				set.add(entry.getKey());
			}
		}
		
		int index = 0; // index of the order list
		while (!set.isEmpty()) {
			int curVertex = order.get(index);
			if (!set.contains(curVertex)) {
				return false;
			}
			index++;
			set.remove(curVertex); // expand the vertex in the list 
			List<Integer> neighbors = graph.get(curVertex);
			for (Integer nei : neighbors) {
				int indegree = indegrees.get(nei) - 1;
				indegrees.put(nei, indegree);
				if (indegree == 0) {
					set.add(nei);
				}
			}
		}
		return true;
	}
	
	// solution2: instead of checking whether the element exists in hashSet, we can just check whether the indegree of this element is 0.
	private static boolean topologicalSort2(Map<Integer, List<Integer>> graph, Map<Integer, Integer> indegrees, List<Integer> order) {
		for (Integer ele : order) {
			if (indegrees.get(ele) != 0) {
				return false;
			}
			List<Integer> neis = graph.get(ele);
			for (Integer nei : neis) {
				indegrees.put(nei, indegrees.get(nei) - 1);
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
		Map<Integer, List<Integer>> graph = new HashMap<>();
		List<Integer> list1 = new ArrayList<>();
		list1.add(2); list1.add(4);
		List<Integer> list2 = new ArrayList<>();
		list2.add(4);
		List<Integer> list3 = new ArrayList<>();
		list3.add(2); list3.add(4);
		List<Integer> list4 = new ArrayList<>();
		graph.put(1, list1); graph.put(2, list2); graph.put(3, list3); graph.put(4, list4); 
		List<Integer> order = new ArrayList<>();
		// order.add(3); order.add(4); order.add(2); order.add(1); // false
		order.add(3); order.add(1); order.add(2); order.add(4); // true
		System.out.println(isValid(graph, order));
	}
}
