package Graph;

public class Num06 {
	static class WeightedUnionFind {
		private int[] ids;
		private int[] sizes;
		
		public WeightedUnionFind(int n) {
			ids = new int[n];
			sizes = new int[n];
			for (int i = 0; i < n; i++) {
				ids[i] = i;
				sizes[i] = 1;
			}
		}
		
		// O(1)
		public void union(int a, int b) {
			int rootA = root(a);
			int rootB = root(b);
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
		
		// O(1)
		public boolean find(int a, int b) {
			return root(a) == root(b);
		}
		
		private int root(int a) {
			int root = a;
			while (ids[root] != root) {
				root = ids[root];
			}
			
			// path compression
			while (a != root) {
				int t = ids[a];
				ids[a] = root;
				a = t;
			}
			return root;
		}
	}
}
