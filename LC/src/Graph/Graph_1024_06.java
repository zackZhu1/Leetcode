package Graph;
import java.util.*;

// sum combinations
public class Graph_1024_06 {
	public static List<List<Integer>> getCombinations(int num) {
		List<List<Integer>> allComb = new ArrayList<>();
		List<Integer> comb = new ArrayList<>();
		// dfs(num, 1, comb, allComb);
		dfs2(num, comb, allComb);
		return allComb;
	}
	
	// combination: de-duplication
	private static void dfs(int num, int lastChosen, List<Integer> comb, List<List<Integer>> allComb) {
		if (num == 0) {
			allComb.add(new ArrayList<>(comb));
			return;
		}
		for (int i = lastChosen; i <= num; i++) {
			comb.add(i);
			dfs(num - i, i, comb, allComb);
			comb.remove(comb.size() - 1);
		}
	}
	
	// permutation
	private static void dfs2(int num, List<Integer> comb, List<List<Integer>> allComb) {
		if (num == 0) {
			allComb.add(new ArrayList<>(comb));
			return;
		}
		for (int i = 1; i <= num; i++) {
			comb.add(i);
			dfs2(num - i, comb, allComb);
			comb.remove(comb.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		List<List<Integer>> result = getCombinations(4);
		for (List<Integer> comb : result) {
			for (Integer ele : comb) {
				System.out.print(ele + " ");
			}
			System.out.println();
		}
	}
}
