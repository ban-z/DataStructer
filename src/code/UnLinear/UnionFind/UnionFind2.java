package code.UnLinear.UnionFind;


/*
* 优化1：利用数组树进行实现
* */
public class UnionFind2 implements UF {

    private int[] parent;

    public UnionFind2(int size){
        parent = new int[size];

        for (int i = 0; i < size; i++){
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int p){
        //p的合法性判断
        while (p != parent[p]){
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

        parent[pRoot] = qRoot;
    }
}
