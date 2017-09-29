import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {
        Graph<String, Double> graph = new Graph<>();
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertUndirectedEdge("A", "B", 2d);
        graph.insertUndirectedEdge("A", "C", 4d);
        graph.insertUndirectedEdge("A", "D", 1d);
        graph.insertUndirectedEdge("D", "C", 1d);
        graph.insertUndirectedEdge("C", "B", 3d);



        Graph<String, String> cities = new Graph<>();

        Read read = new Read("russia.txt");
        for (String s : read.readline()) {
            cities.insertVertex(s);
        }
        int i = 0;
        String u = "";
        String v = "";
        String weight = "";
        for (String s : read.readline()) {
            switch (i % 3) {
                case 0:
                    u = s;
                    break;
                case 1:
                    v = s;
                    break;
                case 2:
                    weight = s;
                    cities.insertUndirectedEdge(u, v, weight);
                    break;

            }
            i++;
        }

        Graph mst = GraphOptimization.MST(cities);
        read = new Read("input.txt");
        String result = "";
        int amountOfLines = read.amountOfLines();
        for (i = 0; i < amountOfLines - 1; i++) {
            LinkedList<String> s = read.readline();
            result = result + GraphOptimization.delivery(mst, s.getFirst(), s.get(1), Double.parseDouble(s.getLast()));
            result = result + "\n";
        }

        LinkedList<String> s = read.readline();



        result = result + GraphOptimization.delivery(mst, s.getFirst(), s.get(1), Double.parseDouble(s.getLast()));

        Write write = new Write("output.txt");
        write.writeString(result);










    }
}
