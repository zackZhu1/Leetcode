package Graph;
import java.util.*;

public class Graph_1028_02 {
	
	// solution1: DFS
	static int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            dfs(matrix, pacific, i, 0, Integer.MIN_VALUE); // left 
            dfs(matrix, atlantic, i, n - 1, Integer.MIN_VALUE); // right
        }
        
        for (int i = 0; i < n; i++) {
            dfs(matrix, pacific, 0, i, Integer.MIN_VALUE); // top
            dfs(matrix, atlantic, m - 1, i, Integer.MIN_VALUE); // bottom
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(new int[]{i, j});
                }
            }
        }
        return res;
    }
    
    // height: 上一个点的height
    private void dfs(int[][] matrix, boolean[][] visited, int x, int y, int height) {
        int m = matrix.length; 
        int n = matrix[0].length;
        
        // out of bound
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return;
        }
        // already visited
        if (visited[x][y]) {
            return;
        }
        // lower than the previous height
        if (matrix[x][y] < height) {
            return;
        }
        
        // mark visited
        visited[x][y] = true;
        for (int[] DIR : DIRS) {
            int newX = DIR[0] + x;
            int newY = DIR[1] + y;
            dfs(matrix, visited, newX, newY, matrix[x][y]);
        }      
    }
    
    
    // BFS
    public List<int[]> pacificAtlantic2(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        
        // KBFS
        for (int i = 0; i < m; i++) {
            pQueue.offer(new int[]{i, 0});
            aQueue.offer(new int[]{i, n - 1});
            pacific[i][0] = true;
            atlantic[i][n - 1] = true;
        }
        
        for (int i = 0; i < n; i++) {
            pQueue.offer(new int[]{0, i});
            aQueue.offer(new int[]{m - 1, i});
            pacific[0][i] = true;
            atlantic[m - 1][i] = true;
        }
        
        bfs(matrix, pQueue, pacific);
        bfs(matrix, aQueue, atlantic);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(new int[]{i, j});
                }
            }
        }
        return res;
    }
    
    private void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
        int m = matrix.length;
        int n = matrix[0].length;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] DIR : DIRS) {
                int neiX = cur[0] + DIR[0];
                int neiY = cur[1] + DIR[1];
                if (neiX < 0 || neiX >= m || neiY < 0 || neiY >= n || visited[neiX][neiY] || matrix[neiX][neiY] < matrix[cur[0]][cur[1]]) {
                    continue;
                }
                visited[neiX][neiY] = true;
                queue.offer(new int[] {neiX, neiY});
            }
        }
    }
    
    
	public static void main(String[] args) {
		boolean[] a = new boolean[2];
		System.out.println(a[0]);
	}
}
