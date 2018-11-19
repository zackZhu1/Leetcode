package Graph;

import java.util.*;

public class Graph_1028_01 {
	public static boolean isDead(int[][] matrix, int x, int y) {
		int m = matrix.length;
		int n = matrix[0].length;
		boolean[][] visited = new boolean[m][n];
		//return isDead(matrix, visited, x, y, m, n);
		return isDead2(matrix, x, y);
	}
	
	// solution1: DFS
	static int[][] DIRS = {{1, 0},{-1, 0},{0, 1},{0, -1}};
	private static boolean isDead(int[][] matrix, boolean[][] visited, int x, int y, int m, int n) {
		if (outOfBound(x, y, m, n)) return true;
		if (matrix[x][y] == 1) return true;
		if (matrix[x][y] == -1) return false; // expand 的时候check if valid
		if (visited[x][y]) return true;
		
		// expand
		visited[x][y] = true;
		// generate
		for (int[] DIR : DIRS) {
			int newX = DIR[0] + x;
			int newY = DIR[1] + y;
			if (!isDead(matrix, visited, newX, newY, m, n)) return false;
		}
		return true;
	}
	
	private static boolean outOfBound(int x, int y, int m, int n) {
		if (x < 0 || x >= m || y < 0 || y >= n) {
			return true;
		}
		return false;
	}
	
	
	// solution2: BFS
	static class Point {
		Point(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
		
		int x;
		int y;
		int value;
	}
	public static boolean isDead2(int[][] matrix, int x, int y) {
		int m = matrix.length;
		int n = matrix[0].length;
		
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[m][n];
		Point start = new Point(x, y, 0);
		queue.offer(start);
		visited[x][y] = true;
		
		while (!queue.isEmpty()) {
			// expand
			Point cur = queue.poll();
			int curX = cur.x;
			int curY = cur.y;
			
			// generate
			for (int[] DIR : DIRS) {
				int neiX = curX + DIR[0];
				int neiY = curY + DIR[1];
				if (outOfBound(neiX, neiY, matrix.length, matrix[0].length)) {
					continue;
				}
				if (visited[neiX][neiY]) {
					continue;
				}
				if (matrix[neiX][neiY] == -1) {
					return false;
				} else if (matrix[neiX][neiY] == 0) {
					// mark visited
					visited[neiX][neiY] = true;
					// offer into the queue
					queue.offer(new Point(neiX, neiY, matrix[neiX][neiY]));
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[][] matrix = {
				{1, 1, 1, 1},
				{1, -1, 0, 1},
				{1, 1, 1, 1}
		};
		int x = 1;
		int y = 2;
		System.out.println(isDead(matrix, x, y));
	}
}
