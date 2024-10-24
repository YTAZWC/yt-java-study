package top.ytazwc.algorithm.graph;

import java.util.*;

/**
 * @author 花木凋零成兰
 * @title GraphAdjList
 * @date 2024-10-15 10:19
 * @package top.ytazwc.algorithm.graph
 * @description 基于邻接表实现的无向图类
 */
public class GraphAdjList {
    // 邻接表，key为顶点，value为该顶点的所有邻接顶点
    Map<Vertex, List<Vertex>> adjList;

    public GraphAdjList(Vertex[][] edges) {
        this.adjList = new HashMap<>();
        // 添加顶点和边
        for (Vertex[] edge : edges) {
            addVertex(edge[0]);
            addVertex(edge[1]);
            addEdge(edge[0], edge[1]);
        }
    }

    /* 获取顶点数量 */
    public int size() {
        return adjList.size();
    }

    /* 添加边 */
    public void addEdge(Vertex vet1, Vertex vet2) {
        if (!adjList.containsKey(vet1) || !adjList.containsKey(vet2) || vet1 == vet2) {
            throw new IllegalArgumentException();
        }
        // 添加边
        adjList.get(vet1).add(vet2);
        adjList.get(vet2).add(vet1);
    }

    /* 删除边 */
    public void removeEdge(Vertex vet1, Vertex vet2) {
        if (!adjList.containsKey(vet1) || !adjList.containsKey(vet2) || vet1 == vet2) {
            throw new IllegalArgumentException();
        }
        // 删除边
        adjList.get(vet1).remove(vet2);
        adjList.get(vet2).remove(vet1);
    }

    /* 添加顶点 */
    public void addVertex(Vertex vet) {
        if (adjList.containsKey(vet)) {
            return ;
        }
        // 邻接表中增加一个新链表
        adjList.put(vet, new ArrayList<>());
    }

    /* 删除顶点 */
    public void removeVertex(Vertex vet) {
        if (!adjList.containsKey(vet)) {
            throw new IllegalArgumentException();
        }
        // 邻接表中删除顶点 vet
        adjList.remove(vet);
        // 遍历其他链表 删除包含vet的边
        for (List<Vertex> value : adjList.values()) {
            value.remove(vet);
        }
    }

    /* 打印邻接表 */
    public void print() {
        System.out.println("邻接表 = ");
        for (Map.Entry<Vertex, List<Vertex>> pair : adjList.entrySet()) {
            List<Integer> tmp = new ArrayList<>();
            for (Vertex vertex : pair.getValue()) {
                tmp.add(vertex.val);
            }
            System.out.println(pair.getKey().val + ": " + tmp + ",");
        }
    }

    /* 广度优先遍历-使用邻接表来表示图，以便获取指定顶点的所有邻接顶点 */
    public static List<Vertex> graphBfs(GraphAdjList graph, Vertex startVertex) {
        // 遍历序列
        List<Vertex> res = new ArrayList<>();
        // 哈希集合 记录已经被访问过的顶点
        Set<Vertex> visited = new HashSet<>();
        visited.add(startVertex);
        // 队列 实现BFS
        Queue<Vertex> que = new ArrayDeque<>();
        que.offer(startVertex);
        while (!que.isEmpty()) {
            // 队首顶点出队
            Vertex vet = que.poll();
            // 记录访问顶点
            res.add(vet);
            // 遍历顶点的所有邻接顶点
            for (Vertex vertex : graph.adjList.get(vet)) {
                // 跳过被访问过的顶点
                if (visited.contains(vertex)) {
                    continue;
                }
                // 入队未访问的顶点
                que.offer(vertex);
                // 标记该顶点已被访问
                visited.add(vertex);
            }
        }
        return res;
    }

    /* 深度优先遍历-使用邻接表来表示图 */
    List<Vertex> graphDfs(GraphAdjList graph, Vertex startVet) {
        // 顶点遍历序列
        List<Vertex> res = new ArrayList<>();
        // 哈希集合 记录已被访问过的顶点
        Set<Vertex> visited = new HashSet<>();
        dfs(graph, visited, res, startVet);
        return res;
    }

    void dfs(GraphAdjList graph, Set<Vertex> visited, List<Vertex> res, Vertex vet) {
        // 记录访问节点
        res.add(vet);
        // 标记节点已被访问
        visited.add(vet);
        // 遍历顶点的所有邻接顶点
        for (Vertex vertex : graph.adjList.get(vet)) {
            if (visited.contains(vertex)) {
                continue;
            }
            dfs(graph, visited, res, vertex);
        }
    }

    // 定义顶点类 如此顶点与顶点之间无其他关系，为各自独立的实例
    public static class Vertex {
        // 顶点值
        int val;

        /* 构造方法 */
        public Vertex() {
        }

        public Vertex(int val) {
            this.val = val;
        }

        /* 重写 hashcode */
        @Override
        public int hashCode() {
            return Objects.hash(val);
        }

        /* 重写 equals 方法 */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Vertex vertex = (Vertex) obj;
            return this.val == vertex.val;
        }
    }
}
