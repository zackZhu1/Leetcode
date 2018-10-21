package Graph;
import java.util.*;

//Number of Islands 305
public class Num08 {
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		List<Integer> res = new ArrayList<>();
		int[][] map = new int[m][n];
		UnionFind uf = new UnionFind(m * n);
		uf.count = 0;
		
		int k = positions.length;
		for (int i = 0; i < k; i++) {
			int[] position = positions[i];
			int x = position[0];
			int y = position[1];
			
			if (map[x][y] == 1) continue; // de-duplicate the input
			uf.count++;
			map[x][y] = 1;
			int id = x * n + y;
			if (x - 1 >= 0 && map[x - 1][y] == 1) 
				uf.union(id, id - n);
			if (x + 1 < m && map[x + 1][y] == 1)
				uf.union(id, id + n);
			if (y - 1 >= 0 && map[x][y - 1] == 1)
				uf.union(id, id - 1);
			if (y + 1 < n && map[x][y + 1] == 1)
				uf.union(id, id + 1);
			
			res.add(uf.count);
		}
		return res;
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
