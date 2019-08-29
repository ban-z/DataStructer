package code.UnLinear.UnionFind;


/*
* 路径压缩(find)
* */
public class UnionFind5 implements UF {

    private int[] parent;
    private int[] rank;

    public UnionFind5(int size){
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int p){
        //p的合法性判断
        while (p != parent[p]){
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot){
            return;
        }

        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        }else if (rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        }else {// rank[qRoot] == rank[pRoot]
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}
