package Graph;
import java.util.*;

//Minimum time to finish all the tasks, each of the task takes different amount of time
public class Graph09 {
	public static int getMinimumTime(Map<Integer, List<Integer>> graph, Map<Integer, Integer> time) {
		return topologicalSort(getDependencies(graph), time);
	}
	
	private static int topologicalSort(Map<Integer, List<Integer>> dependencies, Map<Integer, Integer> time) {
		int max = 0;
		Map<Integer, Integer> visited = new HashMap<>();
		for (Map.Entry<Integer, List<Integer>> entry : dependencies.entrySet()) {
			int minimumTime = dfs(entry.getKey(), dependencies, visited, time);
			System.out.println(entry.getKey() + ": " +  minimumTime);
			max = Math.max(max, minimumTime);
		}
		return max;
	}
	
	private static int dfs(Integer vertex, Map<Integer, List<Integer>> dependenciesGraph, Map<Integer, Integer> visited, Map<Integer, Integer> time) {
		List<Integer> dependencies = dependenciesGraph.get(vertex);
		if (visited.containsKey(vertex)) {
			return visited.get(vertex);
		}
		
		// base case
		if (dependencies.size() == 0) {
			return time.get(vertex);
		}
		// recursion rule
		int max = 0;
		for (Integer dependency : dependencies) {
			int cur = time.get(vertex); 
			cur += dfs(dependency, dependenciesGraph, visited, time);
			max = Math.max(max, cur);
		}
		visited.put(vertex, max);
		return max;
	}
	
	private static Map<Integer, List<Integer>> getDependencies(Map<Integer, List<Integer>> graph) {
		Map<Integer, List<Integer>> dependenices = new HashMap<>();
		for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
			dependenices.putIfAbsent(entry.getKey(), new ArrayList<>());
			List<Integer> neis = entry.getValue();
			for (Integer nei : neis) {
				if (!dependenices.containsKey(nei)) {
					dependenices.put(nei, new ArrayList<>());
				}
				dependenices.get(nei).add(entry.getKey());
			}
		}
		return dependenices;
	}
	
	public static void main(String[] args) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		List<Integer> list1 = new ArrayList<>();
		list1.add(2); list1.add(4); list1.add(5);
		List<Integer> list2 = new ArrayList<>();
		List<Integer> list3 = new ArrayList<>();
		list3.add(2); list3.add(4);
		List<Integer> list4 = new ArrayList<>();
		List<Integer> list5 = new ArrayList<>();
		graph.put(1, list1);
		graph.put(2, list2);
		graph.put(3, list3);
		graph.put(4, list4);
		graph.put(5, list5);
		Map<Integer, Integer> time = new HashMap<>();
		time.put(1,	2);
		time.put(2, 4);
		time.put(3, 3);
		time.put(4, 1);
		time.put(5, 2);
		System.out.println(getMinimumTime(graph, time));
	}
}
