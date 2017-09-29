import java.io.IOException;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {

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


        read = new Read("input.txt");
        String result = "";
        int amountOfLines = read.amountOfLines();
        for (i = 0; i < amountOfLines - 1; i++) {
            LinkedList<String> s = read.readline();
            Graph pathways = DijkstraAlgorithm.findPathways(cities,s.getFirst());
            result = result + DijkstraAlgorithm.fastestPath(pathways, s.getFirst(), s.get(1), Double.parseDouble(s.getLast()));
            result = result + "\n";
        }

        LinkedList<String> s = read.readline();
        Graph pathways = DijkstraAlgorithm.findPathways(cities,s.getFirst());
        result = result + DijkstraAlgorithm.fastestPath(pathways, s.getFirst(), s.get(1), Double.parseDouble(s.getLast()));

        Write write = new Write("output.txt");
        write.writeString(result);











    }
}
