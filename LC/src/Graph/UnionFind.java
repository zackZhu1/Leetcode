package Graph;

public class UnionFind {
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
	
	public void printIds() {
		for (int id : ids) {
			System.out.print(id + " ");
		}
		System.out.println();
	}
	
	public void printSizes() {
		for (int size : sizes) {
			System.out.print(size + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		UnionFind uf = new UnionFind(10);
		System.out.println("union 3, 4"); uf.union(3, 4); uf.printIds(); uf.printSizes();
		System.out.println("union 4, 9"); uf.union(4, 9); uf.printIds(); uf.printSizes();
		System.out.println("union 8, 0"); uf.union(8, 0); uf.printIds(); uf.printSizes();
		System.out.println("union 2, 3"); uf.union(2, 3); uf.printIds(); uf.printSizes();
		System.out.println("union 5, 6"); uf.union(5, 6); uf.printIds(); uf.printSizes();
		System.out.println("union 5, 9"); uf.union(5, 9); uf.printIds(); uf.printSizes();
		System.out.println("union 7, 3"); uf.union(7, 3); uf.printIds(); uf.printSizes();
		System.out.println("union 4, 8"); uf.union(4, 8); uf.printIds(); uf.printSizes();
		System.out.println("union 6, 1"); uf.union(6, 1); uf.printIds(); uf.printSizes();
	}
}
