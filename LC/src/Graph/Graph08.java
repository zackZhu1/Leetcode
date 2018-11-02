package Graph;

import java.util.*;
import java.util.Map.Entry;

// Minimum time to finish all the tasks
public class Graph08 {
	public static int getMinimumTime(Map<Integer, List<Integer>> graph) {
		Map<Integer, Integer> indegrees = getIndegrees(graph);
		// return topologicalSort(graph, indegrees);
		return topologicalSort2(getDependcies(graph));
	}
	
	// solution1: topological sort by BFS => get the number of levels.
	private static int topologicalSort(Map<Integer, List<Integer>> graph, Map<Integer, Integer> indegrees) {
		Queue<Integer> queue = new LinkedList<>();
		for (Map.Entry<Integer, Integer> entry : indegrees.entrySet()) {
			if (entry.getValue() == 0) {
				queue.offer(entry.getKey());
			}
		}
		
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			level++;
			for (int i = 0; i < size; i++) {
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
		}
		return level;
	}
	
	private static Map<Integer, Integer> getIndegrees(Map<Integer, List<Integer>> graph) {
		Map<Integer, Integer> indegree = new HashMap<>();
		for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
			indegree.putIfAbsent(entry.getKey(), 0);
			List<Integer> neis = entry.getValue();
			for (Integer nei : neis) {
				indegree.put(nei, indegree.getOrDefault(nei, 0) + 1);
			}
		}
		return indegree;
	}
	
	// solution2: DFS + memorization
	public static int topologicalSort2(Map<Integer, List<Integer>> dependencies) {
		int max = 0;
		Map<Integer, Integer> visited = new HashMap<>();
		for (Map.Entry<Integer, List<Integer>> entry : dependencies.entrySet()) {
			int minimumTime = dfs(entry.getKey(), dependencies, visited);
			max = Math.max(max, minimumTime);
		}
		return max;
	}
	
	private static int dfs(Integer vertex, Map<Integer, List<Integer>> dependenciesGraph, Map<Integer, Integer> visited) {
		List<Integer> dependencies = dependenciesGraph.get(vertex);
		// early return
		if (visited.containsKey(vertex)) {
			return visited.get(vertex);
		}
		// base case
		if (dependencies.size() == 0) {
			return 1;
		}
		// recursion rule
		int max = 0;
		for (Integer dependency : dependencies) {
			int cur = 1;
			cur += dfs(dependency, dependenciesGraph, visited);
			max = Math.max(max, cur);
		}
		visited.put(vertex, max);
		return max;
	}
	
	private static Map<Integer, List<Integer>> getDependcies(Map<Integer, List<Integer>> graph) {
		Map<Integer, List<Integer>> dependencies = new HashMap<>();
		for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
			dependencies.putIfAbsent(entry.getKey(), new ArrayList<>());
			List<Integer> neis = entry.getValue();
			for (Integer nei : neis) {
				if (!dependencies.containsKey(nei)) {
					dependencies.put(nei, new ArrayList<>());
				}
				dependencies.get(nei).add(entry.getKey());
			}
		}
		return dependencies;
	}
	
	public static void main(String[] args) {
		// input: Map<Integer, List<Integer>> graph
//		Map<Integer, List<Integer>> graph = new HashMap<>();
//		List<Integer> list1 = new ArrayList<>();
//		list1.add(2); list1.add(4);
//		List<Integer> list2 = new ArrayList<>();
//		List<Integer> list3 = new ArrayList<>();
//		list3.add(2); list3.add(4);
//		List<Integer> list4 = new ArrayList<>();
//		graph.put(1, list1);
//		graph.put(2, list2);
//		graph.put(3, list3);
//		graph.put(4, list4);
		
		Map<Integer, List<Integer>> graph = new HashMap<>();
		List<Integer> list1 = new ArrayList<>();
		list1.add(2); list1.add(4);
		List<Integer> list2 = new ArrayList<>();
		list2.add(5);
		List<Integer> list3 = new ArrayList<>();
		list3.add(2); list3.add(4);
		List<Integer> list4 = new ArrayList<>();
		list4.add(5);
		List<Integer> list5 = new ArrayList<>();
		graph.put(1, list1);
		graph.put(2, list2);
		graph.put(3, list3);
		graph.put(4, list4);
		graph.put(5, list5);
		System.out.println(getMinimumTime(graph));
	}
}
