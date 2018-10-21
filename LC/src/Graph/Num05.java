package Graph;

import java.util.*;

import Graph.Num02.Node;

public class Num05 {
	static class Node {
		Node(Integer value) {
			this.value = value;
		}
		Integer value;
		List<Node> neighbors = new ArrayList<>();
	}
	
	static class BFSImpl {
		private Map<Node, Integer> visited; // mark visited, and level
		private Queue<Node> queue;
		
		public BFSImpl(Node init) {
			visited = new HashMap<>();
			queue = new ArrayDeque<>();
			visited.put(init, 0);
			queue.offer(init);
		}
		
		public boolean hasNodesToExplore() {
			return !queue.isEmpty();
		}
		
		public int nextRound(BFSImpl reverse) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node cur = queue.poll();
				for (Node nei : cur.neighbors) {
					// check if it is overlapped with the reverse BFS explored
					if (reverse.visited.containsKey(nei)) {
						return reverse.visited.get(nei) + visited.get(cur) + 1;
					}
					
					// generate from A for all the neighbors
					if (!visited.containsKey(nei)) {
						visited.put(nei, visited.get(cur) + 1);
					}
				}
			}
			return -1;
		}
	}
	
	public int biDirectionalBFS(Node init, Node goal) {
		BFSImpl bfsA = new BFSImpl(init);
		BFSImpl bfsB = new BFSImpl(goal);
		while (bfsA.hasNodesToExplore() && bfsB.hasNodesToExplore()) {
			int shortest = bfsA.nextRound(bfsB);
			if (shortest != -1) {
				return shortest;
			}
			shortest = bfsB.nextRound(bfsA);
			if (shortest != -1) {
				return shortest;
			}
		}
		return -1;
	}
}
