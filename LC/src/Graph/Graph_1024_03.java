package Graph;

import java.util.*;

// Subset where size = k
public class Graph_1024_03 {
	public static List<List<Integer>> subsets1(int[] nums, int k) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> subset = new ArrayList<>();
		dfs1(nums, k, 0, subset, result);
		return result;
	}
	
	private static void dfs1(int[] nums, int k, int level, List<Integer> subset, List<List<Integer>> result) {
		// base case
		if (subset.size() == k) {
			result.add(new ArrayList<>(subset));
			return;
		}
		if (level == nums.length) {
			return;
		}
		// recursion rule
		subset.add(nums[level]);
		dfs1(nums, k, level + 1, subset, result);
		subset.remove(subset.size() - 1);
		
		dfs1(nums, k, level + 1, subset, result);
	}
	
	public static List<List<Integer>> subsets2(int[] nums, int k) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> subset = new ArrayList<>();
		dfs2(nums, k, 0, subset, result);
		return result;
	}
	
	private static void dfs2(int[] nums, int k, int index, List<Integer> subset, List<List<Integer>> result) {
		if (subset.size() == k) { // pruning optimization
			result.add(new ArrayList<>(subset));
			return;
		}
		
		// implicit base case
		// recursion rule
		for (int i = index; i < nums.length; i++) {
			subset.add(nums[i]);
			dfs2(nums, k, i + 1, subset, result);
			subset.remove(subset.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 2, 3};
		int k = 2;
		List<List<Integer>> result = subsets2(nums, k);
		
		for (List<Integer> subset : result) {
			for (Integer ele : subset) {
				System.out.print(ele + " ");
			}
			System.out.println();
		}
	}
}
