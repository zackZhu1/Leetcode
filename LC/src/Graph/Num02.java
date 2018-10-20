package Graph;

import java.util.*;

import Graph.Num01.Node;


// find all shortest path from init node to goal node
public class Num02 {
	static class Node {
		Node(Integer value) {
			this.value = value;
		}
		Integer value;
		List<Node> neighbors = new ArrayList<>();
	}
	
	static Map<Node, List<Node>> bfs(Node init, Node goal) {
		// record all the parents
		Map<Node, List<Node>> prevMap = new HashMap<>();
		// mark visited + recod the level of the node
		Map<Node, Integer> levels = new HashMap<>();
		
		Queue<Node> queue = new ArrayDeque<>();
		
		queue.offer(init);
		prevMap.put(init, new ArrayList<>());
		levels.put(init, 0);
		int level = 0;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node cur = queue.poll();
				for (Node nei : cur.neighbors) {
					// when generating nodes
					// case1: first time generate this node
					if (!levels.containsKey(nei)) {
						levels.put(nei, level + 1);
						prevMap.put(nei, new ArrayList<>());
						prevMap.get(nei).add(cur);
						queue.offer(nei);
					}
					// case2: include all the parent nodes in the shortest path
					else if (level + 1 == levels.get(nei)) {
						prevMap.get(nei).add(cur);
					}
				}
			}
			
			if (levels.containsKey(goal)) {
				return prevMap;
			}
			
			level++;
		}
		return null;
	}
	
	static List<List<Node>> allShortestPaths(Map<Node, List<Node>> prevMap, Node goal) {
		List<List<Node>> allShortestPaths = new ArrayList<>();
		List<Node> path = new ArrayList<>();
		dfs(prevMap, goal, path, allShortestPaths);
		return allShortestPaths;
	}
	
	static void dfs(Map<Node, List<Node>> prevMap, Node cur, List<Node> path, List<List<Node>> allPaths) {
		path.add(cur);
		if (prevMap.get(cur).isEmpty()) {
			List<Node> curPath = new ArrayList<>(path);
			Collections.reverse(curPath);
			allPaths.add(curPath);
		} else {
			for (Node nei : prevMap.get(cur)) {
				dfs(prevMap, nei, path, allPaths);
			}
		}
		path.remove(path.size() - 1);
	}
	
	public static void main(String[] args) {
		// test case 
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		node1.neighbors.add(node2); node1.neighbors.add(node4);
		node2.neighbors.add(node3);
		node3.neighbors.add(node1);
		node4.neighbors.add(node3); node4.neighbors.add(node5);
		node5.neighbors.add(node3);

		Map<Node, List<Node>> prevMap = bfs(node1, node3);

		List<List<Node>> allPaths = allShortestPaths(prevMap, node3);
		for (List<Node> list : allPaths) {
			for (Node node : list) {
				System.out.print(node.value + " ");
			}
			System.out.println();
		}
	}
}
