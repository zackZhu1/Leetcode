package Interview;

import java.util.*;

/*
inputArr = [
 {
   "parent_id": null,
   "id": 2,
   "value": 'C'
 },
   {
   "parent_id": 1,
   "id": 4,
   "value": 'F'
 },
   {
   "parent_id": 3,
   "id": 5,
   "value": 'I'
 },
 {
   "parent_id": null,
   "id": 1,
   "value": 'A'
 },
   {
   "parent_id": 4,
   "id": 6,
   "value": 'X'
 },
   {
   "parent_id": 4,
   "id": 7,
   "value": 'E'
 },
   {
   "parent_id": 5,
   "id": 8,
   "value": 'N'
 },
   {
   "parent_id": null,
   "id": 3,
   "value": 'B'
 },
   {
   "parent_id": 1,
   "id": 10,
   "value": 'G'
 },
   {
   "parent_id": 8,
   "id": 9,
   "value": 'O'}]
Expected Output:
> pretty_print(inputArr)
A
    F
        X
        E
    G
C
B
    I
        N
            O
            
 */

class Elem {
	Elem(int parentId, int id, char value) {
		this.parentId = parentId;
		this.id = id;
		this.value = value;
	}
	int id;
	int parentId; // use -1 to represent null
	char value;
}
public class Num01 {
	public void pretty_print(Elem[] inputArr) {
		Map<Integer, Character> idToCharacter = new HashMap<>();
		Map<Integer, List<Integer>> graph = new HashMap<>();
		Set<Integer> roots = new HashSet<>();
		
		// step1: build graph
		buildGraph(inputArr, idToCharacter, graph, roots);
		
		// step2: for each all the root nodes
		for (Integer root : roots) {
			dfs(idToCharacter, graph, root, 0);
		}
	}
	
	private void dfs(Map<Integer, Character> idToCharacter, Map<Integer, List<Integer>> graph, int curId, int numberOfSpaces) {
		if (graph.get(curId).isEmpty()) {
			prettyPrint(numberOfSpaces, curId, idToCharacter);
			return;
		}
		prettyPrint(numberOfSpaces, curId, idToCharacter);
		
		List<Integer> children = graph.get(curId);
		for (Integer child : children) {
			dfs(idToCharacter, graph, child, numberOfSpaces + 1);
		}
	}
	
	private void prettyPrint(int numberOfSpaces, int curId, Map<Integer, Character> idToCharacter) {
		for (int i = 0; i < numberOfSpaces; i++) {
			System.out.print(' ');
		}
		System.out.print(idToCharacter.get(curId));
		System.out.println();
	}
	
	private void buildGraph(Elem[] inputArr, Map<Integer, Character> idToCharacter, Map<Integer, List<Integer>> graph, Set<Integer> roots) {
		for (Elem elem : inputArr) {
			int id = elem.id;
			int parentId = elem.parentId;
			char value = elem.value;
			
			if (parentId == -1) {
				roots.add(id);
			}
			
			if (!idToCharacter.containsKey(id)) {
				idToCharacter.put(id, value);
			}
		
			if (!graph.containsKey(parentId)) {
				graph.put(parentId, new ArrayList<>());
			}
			graph.get(parentId).add(id);
			
			// still need to create a key-value for nodes with no children
			if (!graph.containsKey(id)) {
				graph.put(id, new ArrayList<>());
			}
		}
	}
	
	public static void main(String[] args) {
		Num01 test = new Num01();
		Elem[] inputArr = {
			new Elem(-1, 2, 'c'), 
			new Elem(1, 4, 'f'),
            new Elem(3, 5, 'I'),
            new Elem(-1, 1, 'A'),
            new Elem(4, 6, 'X'),
            new Elem(4, 7, 'E'),
            new Elem(5, 8, 'N'),
            new Elem(-1, 3, 'B'),
            new Elem(1, 10, 'G'),
            new Elem(8, 9, 'O')
		};

		test.pretty_print(inputArr);		
	}
}








