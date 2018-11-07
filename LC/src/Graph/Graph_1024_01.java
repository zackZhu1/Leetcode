package Graph;
import java.util.*;

public class Graph_1024_01 {
	static class TreeNode {
		TreeNode(int value) {
			this.value = value;
		}
		int value;
		TreeNode left, right;
	}
	
	// solution1
	public static void findAllPaths(TreeNode root, List<TreeNode> path, List<List<TreeNode>> allPath) {
		// base case
		if (root == null) {
			return;
		}		
		if (root.left == null && root.right == null) {
			path.add(root);
			allPath.add(new ArrayList<>(path));
			path.remove(root); // Don't forget to backtrack !!!
			return;
		}
		// recursion rule
		path.add(root);
		findAllPaths(root.left, path, allPath);
		findAllPaths(root.right, path, allPath);
		path.remove(root); // backtracking
	}
	
	// solution2:
	public static void findAllPaths2(TreeNode root, List<TreeNode> path, List<List<TreeNode>> allPath) {
		// base case 
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			allPath.add(new ArrayList<>(path));
			return;
		}
		path.add(root.left);
		findAllPaths2(root.left, path, allPath);
		path.remove(root.left);
		path.add(root.right);
		findAllPaths2(root.right, path, allPath);
		path.remove(root.right);
	}
	
	/*         1
	 *      2       3
	 *    4  5
	 *   6
	 */
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		root.left = node2; root.right = node3;
		node2.left = node4; node2.right = node5;
		node4.left = node6;
		
		List<TreeNode> curPath = new ArrayList<>();
		List<List<TreeNode>> allPath = new ArrayList<>();
		//findAllPaths(root, curPath, allPath);
		
		curPath.add(root);
		findAllPaths2(root, curPath, allPath);
		
		
		for (List<TreeNode> path : allPath) {
			for (TreeNode node : path) {
				System.out.print(node.value + " ");
			}
			System.out.println();
		}
	}
}
