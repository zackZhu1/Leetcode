package Graph;

import java.util.ArrayList;
import java.util.List;

public class Graph_1024_04 {
	public static List<List<Integer>> subsets(int[] nums, int k) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> subset = new ArrayList<>();
		dfs(nums, k, 0, subset, result);
		return result;
	}
	
	private static void dfs(int[] nums, int k, int index, List<Integer> subset, List<List<Integer>> result) {
		result.add(new ArrayList<>(subset));
		// implicit base case
		// recursion rule
		for (int i = index; i < nums.length; i++) {
			subset.add(nums[i]);
			dfs(nums, k, i + 2, subset, result);
			subset.remove(subset.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4};
		int k = 2;
		List<List<Integer>> result = subsets(nums, k);
		
		for (List<Integer> subset : result) {
			for (Integer ele : subset) {
				System.out.print(ele + " ");
			}
			System.out.println();
		}
	}
}
