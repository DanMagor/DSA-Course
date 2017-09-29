import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Anton Skudarnov on 17.04.2017.
 */
public class Graph<V, W> {
    private int matrixSize = 128;
    public int size = 0;
    private Edge matrix[][];
    private ArrayList<V> vertices;

    public class Edge<W> {
        V first;
        V second;
        W weight;

        Edge(V u, V v) {
            first = u;
            second = v;
            weight = null;
        }

        Edge(V u, V v, W w) {
            first = u;
            second = v;
            weight = w;
        }

        public V getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }

        public W getWeight() {
            return weight;
        }
    }

    Graph() {
        matrix = new Edge[matrixSize][matrixSize];
        vertices = new ArrayList<>();
    }


    public Edge[][] getMatrix() {
        return matrix;
    }



    public void insertVertex(V x) {
        if (size >= matrixSize)
            resize();
        if (!vertices.contains(x)) {
            vertices.add(x);
            size++;

        }

    }

    public void removeVertex(V x) {
        int index = getVertexIndex(x);
        size--;
        Edge[][] temp = new Edge[size][size];
        for (int i = 0, k = 0; i <= size; i++, k++) {
            if (i == index) i++;
            for (int j = 0, l = 0; j <= size; j++, l++) {
                if (j == index) j++;
                temp[k][l] = matrix[i][j];
            }
        }
        vertices.remove(index);
    }

    public void removeVertex(int index) {

        size--;
        Edge[][] temp = new Edge[matrixSize][matrixSize];
        for (int i = 0, k = 0; i <= size; i++, k++) {
            if (i == index) i++;
            for (int j = 0, l = 0; j <= size; j++, l++) {
                if (j == index) j++;
                temp[k][l] = matrix[i][j];
            }
        }
        matrix = temp;
        vertices.remove(index);
    }

    public boolean containsVertex(V x) {
        if (vertices.contains(x))
            return true;
        return false;
    }

    private void resize() {
        Edge temp[][] = matrix;
        matrixSize = matrixSize * 2;
        matrix = new Edge[matrixSize][matrixSize];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
    }

    public void insertEdge(V u, V v) {
        if (vertices.contains(u) && vertices.contains(v)) {
            Edge temp = new Edge(u, v);
            int first = vertices.indexOf(u);
            int second = vertices.indexOf(v);
            matrix[first][second] = temp;
        }
    }

    public void insertEdge(V u, V v, W weight) {
        if (vertices.contains(u) && vertices.contains(v)) {
            Edge temp = new Edge(u, v, weight);
            int first = vertices.indexOf(u);
            int second = vertices.indexOf(v);
            matrix[first][second] = temp;

        }
    }

    public void insertUndirectedEdge(Edge<W> edge) {
        if (edge != null) {
            V u = edge.getFirst();
            V v = edge.getSecond();
            W weight = edge.getWeight();
            if (vertices.contains(u) && vertices.contains(v)) {
                int first = vertices.indexOf(u);
                int second = vertices.indexOf(v);
                matrix[first][second] = edge;
                matrix[second][first] = edge;

            }
        }
    }

    public void insertUndirectedEdge(V u, V v) {
        if (vertices.contains(u) && vertices.contains(v)) {
            Edge temp = new Edge(u, v);
            int first = vertices.indexOf(u);
            int second = vertices.indexOf(v);
            matrix[first][second] = temp;
            matrix[second][first] = temp;
        }
    }


    public void insertUndirectedEdge(V u, V v, W weight) {
        if (vertices.contains(u) && vertices.contains(v)) {
            Edge temp = new Edge(u, v, weight);
            int first = vertices.indexOf(u);
            int second = vertices.indexOf(v);
            matrix[first][second] = temp;
            matrix[second][first] = temp;
        }
    }


    public void removeEdge(V u, V v) {
        if (vertices.contains(u) && vertices.contains(v)) {
            int first = vertices.indexOf(u);
            int second = vertices.indexOf(v);
            matrix[first][second] = null;
        }
    }

    public void removeUndirectedEdge(V u, V v) {
        if (vertices.contains(u) && vertices.contains(v)) {
            int first = vertices.indexOf(u);
            int second = vertices.indexOf(v);
            matrix[first][second] = null;
            first = vertices.indexOf(v);
            second = vertices.indexOf(u);
            matrix[first][second] = null;
        }
    }

    public V getVertex(int index) {
        if (index < size)
            return vertices.get(index);
        else
            return null;
    }

    public ArrayList<V> getVertices() {
        return vertices;
    }

    public int getVertexIndex(V v) {
        if (vertices.contains(v)) {
            return vertices.indexOf(v);
        } else
            return -1;
    }

    public ArrayList<Edge<W>> edgesOfVertex(V v) {
        ArrayList<Edge<W>> edges = new ArrayList<Edge<W>>();
        if (vertices.contains(v)) {
            int index = getVertexIndex(v);
            for (int i = 0; i < size; i++) {
                if (matrix[index][i] != null)
                    edges.add(matrix[index][i]);
            }

        }
        return edges;
    }
    public ArrayList<Edge<W>> inputeEdgesOfVertex(V v) {
        ArrayList<Edge<W>> edges = new ArrayList<Edge<W>>();
        if (vertices.contains(v)) {
            int index = getVertexIndex(v);
            for (int i = 0; i < size; i++) {
                if (matrix[i][index] != null)
                    edges.add(matrix[i][index]);
            }

        }
        return edges;
    }

    public Edge<W> getEdge(V v, V u) {
        int first = vertices.indexOf(u);
        int second = vertices.indexOf(v);
        return matrix[first][second];
    }

    public V getOppositeVertex(V v, Edge<W> e) {
        if (edgesOfVertex(v).contains(e)) {
            if (e.getSecond().equals(v)) return e.getFirst();
            return e.getSecond();
        } else
            return null;
    }

    public Stack<V> dfs(V v, V u) {
        HashSet<V> visited = new HashSet<V>();
        Stack<V> path = new Stack<V>();
        path.add(v);
        visited.add(v);
        boolean flag;
        while (!path.peek().equals(u)) {
            flag = true;
            for (Edge<W> e : edgesOfVertex(path.peek())) {
                V opposite = e.getSecond();
                if (opposite.equals(path.peek())) opposite = e.getFirst();
                if (!visited.contains(opposite)) {
                    path.add(opposite);
                    visited.add(opposite);
                    flag = false;
                    break;
                }
            }
            if (flag) path.pop();
        }
        return path;
    }

    public boolean isIsolated(V v){
        if (edgesOfVertex(v).size() == 0 && inputeEdgesOfVertex(v).size() == 0)
            return true;
        return false;
    }
    public void printMatrix() {
        for (int i = 0; i < size; i++) {
            System.out.print(vertices.get(i) + " ");
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] != null) {
                    System.out.print(1 + " ");
                } else
                    System.out.print(0 + " ");
            }
            System.out.println();
        }
    }


}
