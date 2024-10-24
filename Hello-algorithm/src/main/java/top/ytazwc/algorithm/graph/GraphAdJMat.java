package top.ytazwc.algorithm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title GraphAdJMat
 * @date 2024-10-15 09:45
 * @package top.ytazwc.algorithm.graph
 * @description 基于邻接矩阵实现的无向图类
 */
public class GraphAdJMat {
    /* 定点列表 元素代表-定点值，索引代表-定点索引 */
    List<Integer> vertices;
    /* 邻接矩阵，行列索引对应-顶点索引 */
    List<List<Integer>> adjMat;

    /* 构造方法 */
    public GraphAdJMat(int[] vertices, int[][] edges) {
        this.vertices = new ArrayList<>();
        this.adjMat = new ArrayList<>();
        // 添加顶点
        for (int val : vertices) {
            addVertex(val);
        }
        // 添加边
        // edges 元素代表顶点索引，对应vertices元素索引
        for (int[] e : edges) {
            // e[0]和e[1]为 边的两个端点对应的索引
            addEdge(e[0], e[1]);
        }
    }

    /* 获取顶点数量 */
    public int size() {
        return vertices.size();
    }

    /* 添加顶点 */
    public void addVertex(int val) {
        // 获取当前顶点数量
        int size = this.size();
        // 顶点列表中添加新顶点
        vertices.add(val);
        // 邻接矩阵中需要添加一行
        List<Integer> newRow = new ArrayList<>();
        for (int j = 0; j < size; ++ j) {
            newRow.add(0);
        }
        adjMat.add(newRow);
        // 再添加一列
        for (List<Integer> row : adjMat) {
            row.add(0);
        }
    }

    /* 删除顶点 */
    public void removeVertex(int index) {
        if (index >= size()) {
            // 越界抛出异常
            throw new IndexOutOfBoundsException();
        }
        // 顶点列表移除索引index的顶点
        vertices.remove(index);
        // 邻接矩阵删除索引index对应的行
        adjMat.remove(index);
        // 邻接矩阵删除索引index对应的列
        for (List<Integer> row : adjMat) {
            row.remove(index);
        }
    }

    /* 添加边 */
    public void addEdge(int i, int j) {
        // 索引检验
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j) {
            throw new IndexOutOfBoundsException();
        }
        // 在无向图中，邻接矩阵主对角线对称 即(i,j)==(j,,i)
        adjMat.get(i).set(j, 1);
        adjMat.get(j).set(i, 1);
    }

    /* 删除边 */
    public void removeEdge(int i, int j) {
        // 索引检验是否合法
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j) {
            throw new IndexOutOfBoundsException();
        }
        adjMat.get(i).set(j, 0);
        adjMat.get(j).set(i, 0);
    }

    /* 输出邻接矩阵 */
    public void print() {
        System.out.print("顶点列表 = ");
        System.out.println(vertices);
        System.out.println("邻接矩阵 = ");
        printMatrix(adjMat);
    }
    private void printMatrix(List<List<Integer>> adjMat) {
        for (List<Integer> row : adjMat) {
            for (Integer edge : row) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }

}
