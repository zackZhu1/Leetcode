package Graph;

// Number of Islands 200
public class Num07 {
	public static int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int m = grid.length;
		int n = grid[0].length;
		UnionFind uf = new UnionFind(m * n);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '0') continue;
				uf.count++;
				
				// convert two dimensional coordinates to one dimensional
				int id = i * n + j;
				if (j > 0 && grid[i][j - 1] == '1')
					uf.union(id, id - 1);
				if (i > 0 && grid[i - 1][j] == '1')
					uf.union(id, id - n);
			}
		}
		return uf.count;
	}
	
	static class UnionFind {
		private int[] ids;
		private int[] sizes;
		public int count;
		
		public UnionFind(int n) {
			ids = new int[n];
			sizes = new int[n];
			for (int i = 0; i < n; i++) {
				ids[i] = i;
				sizes[i] = 1;
			}
			count = 0;
		}
		
		public int root(int a) {
			int root = a;
			while (ids[root] != root) {
				root = ids[root];
			}
			
			// optimization
			while (a != root) {
				int t = ids[a];
				ids[a] = root;
				a = t;
			}
			return root;
		}
		
		public void union(int a, int b) {
			int rootA = root(a);
			int rootB = root(b);
			if (rootA != rootB) count--;
			else return;
			if (sizes[rootA] >= sizes[rootB]) {
				// merge rootB into rootA
				ids[rootB] = rootA;
				sizes[rootA] += sizes[rootB];
			} else {
				// merge rootA into rootB
				ids[rootA] = rootB;
				sizes[rootB] += sizes[rootA];
			}	
		}
		
		public boolean find(int a, int b) {
			return root(a) == root(b);
		}
	}
}
