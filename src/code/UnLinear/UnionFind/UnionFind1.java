package code.UnLinear.UnionFind;


/*
* 利用数组进行实现
* */
public class UnionFind1 implements UF {

    private int[] id;

    public UnionFind1(int size){
        id = new int[size];

        for (int i = 0; i < id.length; i++){
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    private int find(int p){
        //p的合法性检查
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID){
            return;
        }

        for (int i = 0; i < id.length; i++){
            if (id[i] == pID){
                id[i] = qID;
            }
        }
    }
}
