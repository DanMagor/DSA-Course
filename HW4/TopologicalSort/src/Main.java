import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {
    private static int RADIX = 10;

    public static Graph<String,String> readGraph() throws IOException {
        Read read = new Read("input.txt");
        Graph<String, String> graph = new Graph<>();
        int amountOfLines = read.amountOfLines();
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < amountOfLines; i++) {
            for (String s : read.readline()) {
                String left = s.substring(0, 1);
                String right = s.substring(2);
                graph.insertVertex(left);
                String symbol = "";
                if (right.contains("*")) symbol = "*";
                if (right.contains("+")) symbol = "+";


                if (!symbol.equals("")) {
                    int indexOfSymbol = right.indexOf(symbol);
                    String a = right.substring(0, indexOfSymbol);
                    String b = right.substring(indexOfSymbol + 1);
                    if (!Read.isInteger(a, RADIX) && !Read.isInteger(b, RADIX)) {
                        graph.insertVertex(a);
                        graph.insertVertex(b);
                        graph.insertEdge(b, left, symbol);
                        graph.insertEdge(a, left, symbol);
                    } else {
                        if (Read.isInteger(a, RADIX)) {
                            graph.insertVertex(b);
                            graph.insertEdge(b, left, a + symbol);
                        } else {
                            graph.insertVertex(a);
                            graph.insertEdge(a, left, b + symbol);
                        }
                    }
                } else {
                    hashMap.put(left, right);
                }


            }
        }
        return graph;
    }

    public static void main(String[] args) throws IOException {
        Read read = new Read("input.txt");
        Write write = new Write("output.txt");
        Graph<String, String> graph = new Graph<>();
        int amountOfLines = read.amountOfLines();
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < amountOfLines; i++) {
            for (String s : read.readline()) {
                String left = s.substring(0, 1);
                String right = s.substring(2);
                graph.insertVertex(left);
                String symbol = "";
                if (right.contains("*")) symbol = "*";
                if (right.contains("+")) symbol = "+";


                if (!symbol.equals("")) {
                    int indexOfSymbol = right.indexOf(symbol);
                    String a = right.substring(0, indexOfSymbol);
                    String b = right.substring(indexOfSymbol + 1);
                    if (!Read.isInteger(a, RADIX) && !Read.isInteger(b, RADIX)) {
                        graph.insertVertex(a);
                        graph.insertVertex(b);
                        graph.insertEdge(b, left, symbol);
                        graph.insertEdge(a, left, symbol);
                    } else {
                        if (Read.isInteger(a, RADIX)) {
                            graph.insertVertex(b);
                            graph.insertEdge(b, left, a + symbol);
                        } else {
                            graph.insertVertex(a);
                            graph.insertEdge(a, left, b + symbol);
                        }
                    }
                } else {
                    hashMap.put(left, right);
                }


            }
        }

        // DEBUG
        graph.printMatrix();
        System.out.println();


        LinkedList<String> result = TopologicalSort.sortGraph(graph);
        graph = readGraph();
        String output;
        if (result != null) {
            for (String s : result) {
                System.out.print(s + " ");
            }
            System.out.println();
        } else {
            System.out.println("ERROR");
        }
        /////////
        ArrayList<String> vertecies = new ArrayList<>();
        for(String s: hashMap.keySet()){
            if (graph.isIsolated(s) && !s.equals("R"))
                vertecies.add(s);
        }
        for(String s: vertecies){
            hashMap.remove(s);
        }
        if (result != null) {
            int index = hashMap.size();
            String currentVertex = "";
            if(index<result.size())
                currentVertex = result.get(index);
            while (!hashMap.containsKey("R")) {

                String operator;
                int a, b;
                int temp;
                ArrayList<Graph<String, String>.Edge<String>> edges = graph.inputeEdgesOfVertex(currentVertex);
                if (edges.size() == 0) break;
                if (edges.size() == 2) {
                    a = Integer.parseInt(hashMap.get(edges.get(0).getFirst()));
                    b = Integer.parseInt(hashMap.get(edges.get(1).getFirst()));
                    operator = edges.get(0).getWeight();
                    switch (operator) {
                        case "*":
                            temp = a * b;
                            break;
                        case "+":
                            temp = a + b;
                            break;
                        default:
                            temp = a * b;
                            break;
                    }
                } else {
                    a = Integer.parseInt(hashMap.get(edges.get(0).getFirst()));
                    b = Integer.parseInt(edges.get(0).getWeight().substring(0, 1));
                    operator = edges.get(0).getWeight().substring(1);
                    switch (operator) {
                        case "*":
                            temp = a * b;
                            break;
                        case "+":
                            temp = a + b;
                            break;
                        default:
                            temp = a * b;
                            break;
                    }
                }
                hashMap.put(currentVertex, Integer.toString(temp));
                index++;
                if(index<result.size())
                currentVertex = result.get(index);
            }

            write.writeInt(Integer.parseInt(hashMap.get("R")));
        }
        else write.writeString("ERROR");



    }
}
