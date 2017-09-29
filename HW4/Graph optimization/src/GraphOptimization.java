import java.util.*;

/**
 * Created by Anton Skudarnov on 19.04.2017.
 */
public class GraphOptimization {



        public static Graph<String,String> MST(Graph<String,String> graph){

            BinaryMinHeap<Double, Graph<String,String>.Edge<String>> Q = new BinaryMinHeap<>();

            Graph<String,String> result = new Graph<>();

            String currentVertex = graph.getVertex(0);
            result.insertVertex(currentVertex);
            while (result.size < graph.size){



                for(Graph<String,String>.Edge<String> e:graph.edgesOfVertex(currentVertex)){
                    if(!result.containsVertex(graph.getOppositeVertex(currentVertex,e))){
                      int index = e.getWeight().indexOf(':');
                      double weight = Double.parseDouble(e.getWeight().substring(0,index));
                      Q.insert(weight,e);
                    }
                }
                while(!Q.isEmpty() && result.containsVertex(Q.min().getValue().getSecond())
                        && result.containsVertex(Q.min().getValue().getFirst()))
                {
                    Q.removeMin();
                }
                Graph<String,String>.Edge<String> edge = Q.removeMin().getValue();
                if (result.containsVertex(edge.getFirst())) currentVertex = edge.getSecond();
                else currentVertex = edge.getFirst();
                result.insertVertex(currentVertex);
                result.insertUndirectedEdge(edge);
            }

            return result;
        }


        public static String delivery(Graph<String,String> graph, String u, String v, double weight){

            Stack<String> path  = graph.dfs(u,v);
            double time = 0;
            double cost = 0;
            String first = path.pop();

            while(!path.isEmpty()){
                String second = path.pop();

                Graph<String,String>.Edge<String> e = graph.getEdge(first,second);
                int index = e.getWeight().indexOf(':');
                int index1 = e.getWeight().lastIndexOf(':');
                cost = cost + Double.parseDouble(e.getWeight().substring(index1+1));
                time = time + Double.parseDouble(e.getWeight().substring(index+1,index1));
                first = second;

            }



            cost = cost * weight;
            Locale.setDefault(Locale.US);
            String result = u + " " + v + " " + String.format("%.1f",weight) + " " + String.format("%.1f",time) + " " +String.format("%.1f",cost);
            return (result);
        }
}
