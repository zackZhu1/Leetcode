package Graph;

// DFS1
public class Graph_1031_01 {
	public int numIslands(char[][] grid) {
		// sanity check
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
		int n = grid[0].length;
		boolean[][] visited = new boolean[m][n];
		int result = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && grid[i][j] == '1') {
					result++;
					dfs(grid, visited, i, j);
				}
			}
		}
		return result;
    }
    
    private void dfs(char[][] matrix, boolean[][] visited, int i, int j) {
		int m = matrix.length;
		int n = matrix[0].length;
		
		// check boundary
		if (i < 0 || i >= m || j < 0 || j >= n) {
			return;
		}
		// if visited
		if (visited[i][j] == true) {
			return;
		}
		// if not island
		if (matrix[i][j] == '0') {
			return;
		}
		
		// mark visited
		visited[i][j] = true;
		dfs(matrix, visited, i + 1, j);
		dfs(matrix, visited, i - 1, j);
		dfs(matrix, visited, i, j + 1);
		dfs(matrix, visited, i, j - 1);
	}
	
	public static void main(String[] args) {
		
	}
}
