package Graph;
import java.util.*;

/*
	A N C
	B N C 
	C N D 
	A S D  ⇔ D A N 
 */
/* A N C: (A->C)  means A depends on C
 * A: [C]
 * topological sort by using DFS
 */
public class Graph03 {
	public static boolean isValid(List<List<Character>> input) {
		// construct dependency graph
		Map<Character, List<Character>> graph = buildGraph(input);
		return hasCycle(graph);
	}
	
	// DFS22
	private static boolean hasCycle(Map<Character, List<Character>> graph) {
		Set<Character> visited = new HashSet<>();
		Set<Character> visiting = new HashSet<>();
		for (Map.Entry<Character, List<Character>> entry : graph.entrySet()) {
			if (visited.contains(entry.getKey())) {
				continue;
			}
			if (!dfs(graph, entry.getKey(), visited, visiting)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean dfs(Map<Character, List<Character>> graph, char key, Set<Character> visited, Set<Character> visiting) {
		if (visited.contains(key)) {
			return true;
		}
		if (visiting.contains(key)) {
			return false;
		}
		visiting.add(key);
		List<Character> neis = graph.get(key);
		for (Character nei : neis) {
			if (!dfs(graph, nei, visited, visiting)) {
				return false;
			}
		}
		visiting.remove(key);
		return true;
	}
	
	private static Map<Character, List<Character>> buildGraph(List<List<Character>> input) {
		Map<Character, List<Character>> map = new HashMap<>();
		for (List<Character> list : input) {
			char u = list.get(0);
			char v = list.get(2);
			if (!map.containsKey(u)) {
				map.put(u, new ArrayList<>());
			} 
			if (!map.containsKey(v)) {
				map.put(v, new ArrayList<>());
			}
			char direction = list.get(1);
			if (direction == 'N') { // u depends on v
				map.get(u).add(v);
			} else {
				map.get(v).add(u);
			}
		}
		return map;
	}
	
	public static void main(String[] args) {
		List<List<Character>> input = new ArrayList<>();
		List<Character> l1 = new ArrayList<>();
		l1.add('A'); l1.add('N'); l1.add('C');
		List<Character> l2 = new ArrayList<>();
		l2.add('B'); l2.add('N'); l2.add('C'); 
		List<Character> l3 = new ArrayList<>();
		l3.add('C'); l3.add('N'); l3.add('D'); 
		List<Character> l4 = new ArrayList<>();
		l4.add('D'); l4.add('S'); l4.add('A');
		input.add(l1); input.add(l2); input.add(l3); input.add(l4);
		System.out.println(isValid(input));
	}
}
