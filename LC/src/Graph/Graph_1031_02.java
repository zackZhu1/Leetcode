package Graph;
import java.util.*;

public class Graph_1031_02 {
	// solution1: indirect
	public static int numOfLakes(int[][] matrix) {
		// sanity check
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		boolean[][] visited = new boolean[m][n];
		
		kbfs(matrix, visited);
		return bfs(matrix, visited);
	}
	
	static int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	private static void kbfs(int[][] matrix, boolean[][] visited) {
		int m = matrix.length; 
		int n = matrix[0].length;
		Queue<int[]> queue = new LinkedList<>();
		// add all the 0 in the boundary into queue
		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0) {
				queue.offer(new int[] {i, 0});
			}
			if (matrix[i][n - 1] == 0) {
				queue.offer(new int[] {i, n - 1});
			}
		}
		for (int i = 0; i < n; i++) {
			if (matrix[0][i] == 0) {
				queue.offer(new int[] {0, i});
			}
			if (matrix[m - 1][i] == 0) {
				queue.offer(new int[] {m -1, i});
			}
		}
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int[] DIR : DIRS) {
				int neiX = cur[0] + DIR[0];
				int neiY = cur[1] + DIR[1];
				if (neiX < 0 || neiX >= m || neiY < 0 || neiY >= n) {
					continue;
				}
				if (visited[neiX][neiY] == true) {
					continue;
				}
				if (matrix[cur[0]][cur[1]] == 1) {
					continue;
				}
				
				visited[neiX][neiY] = true;
				queue.offer(new int[] {neiX, neiY});
			}
		}
	}
	
	private static int bfs(int[][] matrix, boolean[][] visited) {
		int result = 0;
		// 去除边界
		for (int i = 1; i < matrix.length - 1; i++) {
			for (int j = 1; j < matrix[0].length - 1; j++) {
				if (matrix[i][j] == 0 && visited[i][j] == false) {
					result++;
					helper(matrix, visited, i, j);
				}
			}
		}
		return result;
	}
	
	private static void helper(int[][] matrix, boolean[][] visited, int i, int j) {
		int m = matrix.length;
		int n = matrix[0].length;
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {i, j});
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int[] DIR : DIRS) {
				int neiX = cur[0] + DIR[0];
				int neiY = cur[1] + DIR[1];
				if (neiX < 0 || neiX >= m || neiY < 0 || neiY >= n) {
					continue;
				}
				if (visited[neiX][neiY] == true) {
					continue;
				}
				if (matrix[cur[0]][cur[1]] == 1) {
					continue;
				}
				
				visited[neiX][neiY] = true;
				queue.offer(new int[] {neiX, neiY});
			}
		}
	}
	
	// solution2: direct
	public static int numOfLakes2(int[][] matrix) {
		// sanity check
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		boolean[][] visited = new boolean[m][n];
		
		return dfs(matrix, visited);
	}
	
	private static int dfs(int[][] matrix, boolean[][] visited) {
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0 && visited[i][j] == false) {
					if (!canReachBoundary(matrix, visited, i, j)) {
						count++;
					}
				}
			}
		}
		return count;
	}
	
	private static boolean canReachBoundary(int[][] matrix, boolean[][] visited, int i, int j) {
		int m = matrix.length;
		int n = matrix[0].length;
		if (i < 0 || i >= m || j < 0 || j >= n) {
			return true;
		}
		if (matrix[i][j] != 0) {
			return false;
		}
		if (visited[i][j]) {
			return false;
		}
		
		visited[i][j] = true;
		boolean toBoundary = false;
		toBoundary |= canReachBoundary(matrix, visited, i + 1, j);
		toBoundary |= canReachBoundary(matrix, visited, i - 1, j);
		toBoundary |= canReachBoundary(matrix, visited, i, j + 1);
		toBoundary |= canReachBoundary(matrix, visited, i, j - 1);
		return toBoundary;
	}
	

	public static void main (String[] args) {
		int[][] matrix = {
				{1, 1, 1, 1, 0},
				{1, 0, 0, 1, 0},
				{1, 1, 1, 1, 1},
				{0, 0, 0, 0, 0}
		};
//		int[][] matrix = {
//				{1, 1, 1, 1, 0},
//				{1, 1, 0, 1, 0},
//				{1, 1, 0, 0, 0},
//				{0, 0, 0, 0, 0}
//		};
		System.out.println(numOfLakes2(matrix));
	}
}
