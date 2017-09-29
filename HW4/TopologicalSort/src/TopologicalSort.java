import java.util.LinkedList;

/**
 * Created by Anton Skudarnov on 22.04.2017.
 */
public class TopologicalSort {

    public static LinkedList<String> sortGraph(Graph<String, String> graph) {
        LinkedList<String> result = new LinkedList<>();

        while(graph.size > 0) {
            boolean flag = false;
            for (int i = 0; i < graph.size; i++) {
                if (!hasSuccessors(graph, i)) {
                    result.addFirst(graph.getVertex(i));
                    graph.removeVertex(i);
                    flag = true;
                }
            }
            if (flag == false)
                return null;
        }

        return result;
    }

    private static boolean hasSuccessors(Graph<String, String> graph, int index) {
        Graph<String, String>.Edge<String>[][] matrix = graph.getMatrix();
        for (int i = 0; i < graph.size; i++) {
            if (matrix[index][i] != null) return true;
        }
        return false;
    }


}
