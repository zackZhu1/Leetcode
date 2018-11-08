package Graph;
import java.util.*;

// factor Combinations
public class Graph_1024_05 {
	
	public static List<List<Integer>> getFactors(int n) {
		List<List<Integer>> allFactors = new ArrayList<>();
		List<Integer> factors = new ArrayList<>();
		dfs(n, 2, factors, allFactors);
		return allFactors;
	}
	
	private static void dfs(int target, int lastChosen, List<Integer> factors, List<List<Integer>> allFactors) {
		if (target == 1) {
			if (factors.size() > 1) {
				allFactors.add(new ArrayList<>(factors));
			}
		}
		for (int i = lastChosen; i <= target; i++) {
			if (target % i == 0) {
				factors.add(i);
				dfs(target / i, i, factors, allFactors);
				factors.remove(factors.size() - 1);
			}
		}
	}
	
	public static void main(String[] args) {
		List<List<Integer>> allFactors = getFactors(12);
	}

}
