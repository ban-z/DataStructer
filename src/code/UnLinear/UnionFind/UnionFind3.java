package code.UnLinear.UnionFind;


/*
* 优化2：对于size优化，小树指向大树
* */
public class UnionFind3 implements UF {

    private int[] parent;
    private int[] singleTreeSize;

    public UnionFind3(int size){
        parent = new int[size];
        singleTreeSize = new int[size];

        for (int i = 0; i < size; i++){
            parent[i] = i;
            singleTreeSize[i] = 1;
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

        if (singleTreeSize[pRoot] < singleTreeSize[qRoot]) {
            parent[pRoot] = qRoot;
            singleTreeSize[qRoot] += singleTreeSize[pRoot];
        }else {
            parent[qRoot] = pRoot;
            singleTreeSize[pRoot] += singleTreeSize[qRoot];
        }
    }
}
