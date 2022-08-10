import java.util.ArrayList;
import java.util.List;
//https://stackabuse.com/graphs-in-java-a-star-algorithm/
public class Node implements Comparable<Node>{
    private static int idCounter = 0;
    private final int nodeID; //this will not be changed
    public Node parent;
    public List<Edge> neighbors;
    //heuristic h(x)
    public double hx;
    //Evaluation functions
    public double fx = Double.MAX_VALUE; //cost function
    public double gx = Double.MAX_VALUE; //move function

    //Constructor
    public Node(double hx){
        this.nodeID = idCounter++;
        this.hx = hx;
        this.neighbors = new ArrayList<Edge>();
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.fx, n.fx);
    }

    //Getters
    public int getNodeID() {
        return nodeID;
    }

    public void addBranch(int weight, Node node){
        Edge newEdge = new Edge(weight, node);
        this.neighbors.add(newEdge);
    }

    public double calcHeuristic(Node target){ //you need the target node to calculate the heuristic
        return this.hx;
    }


}
