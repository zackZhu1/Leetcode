package Graph;
import java.util.*;

// find any shortest path from init node to goal node
public class Num01 {
	static class Node {
		Integer node;
		List<Node> neighbors = new ArrayList<>();
	}
	
	static Map<Node, Node> bfs(Node init, Node goal) {
		// mark visited + record parent node
		Map<Node, Node> prevMap = new HashMap<>();
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(init);
		prevMap.put(init, null);
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			for (Node nei : cur.neighbors) {
				// when generate a node
				if (prevMap.containsKey(nei)) continue;
				prevMap.put(nei, cur);
				if (nei == goal) return prevMap;
				queue.offer(nei);
			}
		}
		return null;
	}
	
	static List<Node> getPaths(Map<Node, Node> prevMap, Node init, Node goal) {
		List<Node> path = new ArrayList<>();
		path.add(goal);
		Node cur = goal;
		while (cur != init) {
			cur = prevMap.get(cur); // find the parent
			path.add(cur);
		}
		Collections.reverse(path);
		return path;
	}
	
	public static void main(String[] args) {
		
	}
	
}
