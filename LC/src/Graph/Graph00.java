package Graph;
import java.util.*;

// topological sort by using DFS
public class Graph00 {
	enum State {
		UNVISITED, VISITING, VISITED;
	}
	
	static class Vertex {
		Vertex(int value) {
			this.value = value;
		}
		int value;
		State state = State.UNVISITED;
		List<Vertex> dependencies = new ArrayList<>();
	}
	
	public static List<Vertex> topOrder(List<Vertex> graph) throws Exception {
		List<Vertex> order = new ArrayList<>();
		for (Vertex v : graph) {
			System.out.println("new subgraph: " + v.value);
			dfs(v, order);
			System.out.println();
		}
		return order;
	}
	
	private static void dfs(Vertex v, List<Vertex> order) throws Exception {
		if (v.state == State.VISITED) {
			return;
		}
		if (v.state == State.VISITING) {
			throw new Exception();
		}
		
		v.state = State.VISITING;
		for (Vertex n : v.dependencies) {
			dfs(n, order);
		}
		v.state = State.VISITED;
		order.add(v);
		System.out.print(v.value + " ");
	}
	
	public static void main(String[] args) throws Exception {
		// test case1:
		Vertex v7 = new Vertex(7);
		Vertex v5 = new Vertex(5);
		Vertex v3 = new Vertex(3);
		Vertex v11 = new Vertex(11);
		Vertex v8 = new Vertex(8);
		Vertex v2 = new Vertex(2);
		Vertex v9 = new Vertex(9);
		Vertex v10 = new Vertex(10);
		v11.dependencies.add(v7); v11.dependencies.add(v5);
		v8.dependencies.add(v7); v8.dependencies.add(v3);
		v2.dependencies.add(v11);
		v9.dependencies.add(v11); v9.dependencies.add(v8);
		v10.dependencies.add(v11); v10.dependencies.add(v3);
		List<Vertex> graph = new ArrayList<>();
		graph.add(v11); graph.add(v8); graph.add(v2); graph.add(v9); 
		graph.add(v10); graph.add(v7); graph.add(v5); graph.add(v3);
		List<Vertex> order = topOrder(graph);
		for (Vertex v : order) {
			System.out.print(v.value + " ");
		}
	}
}
