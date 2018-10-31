package Graph;
import java.util.*;

// course schedule
/*
 * <a, b> means a depends on b
 * step1: construct the graph
 * step2: topological sort by using DFS
 */
public class Graph02 {
	public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        return isValid(numCourses, graph);
    }
	
	private static boolean isValid(int numCourses, Map<Integer, List<Integer>> graph) {
		Set<Integer> visited = new HashSet<>();
		Set<Integer> visiting = new HashSet<>();
		for (int i = 0; i < numCourses; i++) {
			if (!dfs(i, graph, visited, visiting)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean dfs(int vertex, Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<Integer> visiting) {
		if (visited.contains(vertex)) {
			return true;
		}
		if (visiting.contains(vertex)) {
			return false;
		}
		visiting.add(vertex);
		List<Integer> dependencies = graph.get(vertex);
		for (Integer dependency : dependencies) {
			if (!dfs(dependency, graph, visited, visiting)) {
				return false;
			}
		}
		visiting.remove(vertex);
		return true;
	}
	
	private static Map<Integer, List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		for (int i = 0; i < numCourses; i++) {
			graph.put(i, new ArrayList<>());
		}
		for (int[] pre : prerequisites) {
			int u = pre[0];   
			int v = pre[1]; // v depends on u
			graph.get(u).add(v);
		}
		return graph;
	}
}
