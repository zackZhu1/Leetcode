package Graph;
import java.util.*;

// subset
public class Graph_1024_02 {
	// solution1:
	public static List<List<Integer>> subsets1(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> subset = new ArrayList<>();
		// input, current state, global state
		dfs1(nums, 0, subset, result); 
		return result;
	}
	
	private static void dfs1(int[] nums, int level, List<Integer> subset, List<List<Integer>> result) {
		// base case
		if (level == nums.length) {
			result.add(new ArrayList<>(subset));
			return;
		}
		// recursion rule
		subset.add(nums[level]);
		dfs1(nums, level + 1, subset, result);
		subset.remove(subset.size() - 1);
		
		dfs1(nums, level + 1, subset, result);
	}
	
	// solution2
	public static List<List<Integer>> subsets2(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> subset = new ArrayList<>();
		// input, current state, global state
		dfs2(nums, 0, subset, result); 
		return result;
	}
	
	private static void dfs2(int[] nums, int index, List<Integer> subset, List<List<Integer>> result) {
		result.add(new ArrayList<>(subset));
		// recursion rule
		for (int i = index; i < nums.length; i++) {
			subset.add(nums[i]);
			dfs2(nums, i + 1, subset, result);
			subset.remove(subset.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		
	}
}
