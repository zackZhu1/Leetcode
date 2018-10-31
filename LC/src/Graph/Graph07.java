package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Find all possible topological orders 
// => 满足一定条件的permutation
public class Graph07 {
	static class Vertex {
		int key;
		List<Vertex> neighbors;
		public Vertex(int key) {
			this.key = key;
			this.neighbors = new ArrayList<>();
		}
	}
	
	public static List<List<Vertex>> getAllTopOrders(List<Vertex> graph) {
		List<List<Vertex>> topoOrders = new ArrayList<>();
		if (graph == null || graph.isEmpty()) {
			return topoOrders;
		}
	
		Map<Vertex, Integer> indegree = getIndegrees(graph);
		Vertex[] permutation = graph.toArray(new Vertex[graph.size()]);
		permutate(permutation, 0, indegree, topoOrders);
		return topoOrders;
	}
	
	private static void permutate(Vertex[] permutation, int index, Map<Vertex, Integer> indegree, List<List<Vertex>> topoOrders) {
		// base case 
		if (index == permutation.length) {
			topoOrders.add(new ArrayList<Vertex>(Arrays.asList(permutation)));
			return;
		}
		// recursive rule
		for (int i = index; i < permutation.length; i++) {
			Vertex candidate = permutation[i];
			if (indegree.get(candidate) == 0) {
				update(candidate, indegree);
				swap(permutation, index, i);
				permutate(permutation, index + 1, indegree, topoOrders);
				swap(permutation, index, i);
				recover(candidate, indegree);
			}
		}
	}
	
	private static void swap(Vertex[] permutation, int left, int right) {
		Vertex tmp = permutation[left];
		permutation[left] = permutation[right];
		permutation[right] = tmp;
	}
	
	private static void update(Vertex candidate, Map<Vertex, Integer> indegree) {
		for (Vertex nei : candidate.neighbors) {
			indegree.put(nei, indegree.get(nei) - 1);
		}
	}
	
	private static void recover(Vertex candidate, Map<Vertex, Integer> indegree) {
		for (Vertex nei : candidate.neighbors) {
			indegree.put(nei, indegree.get(nei) + 1);
		}
	}
	
	private static Map<Vertex, Integer> getIndegrees(List<Vertex> graph) {
		Map<Vertex, Integer> indegree = new HashMap<>();
		for (Vertex v : graph) {
			indegree.putIfAbsent(v, 0);
			for (Vertex nei : v.neighbors) {
				indegree.put(nei, indegree.getOrDefault(nei, 0) + 1);
			}
		}
		return indegree;
	}
	
	public static void main(String[] args) {
		List<Vertex> graph = new ArrayList<>();
		List<List<Vertex>> allOrders = getAllTopOrders(graph);
	}
}
