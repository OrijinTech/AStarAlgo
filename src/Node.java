package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//https://stackabuse.com/graphs-in-java-a-star-algorithm/
public class Node implements Comparable<Node>{
    private static int idCounter = 0;
    public int nodeID; //0 means obstacle
    public Node parent;
    public List<Edge> neighbors;
    //heuristic h(x)
    public double hx;
    //Evaluation functions
    public double fx = Double.MAX_VALUE; //cost function
    public double gx = Double.MAX_VALUE; //move function
    public boolean walkable; // true --> available block    false --> obstacle
    //Board fields
    public String sign;
    public Coordinate coordinate;

    //Constructor for a star nodes
    public Node(double hx, int xcord, int ycord){
        this.nodeID = idCounter++;
        this.hx = hx;
        this.neighbors = new ArrayList<Edge>();
        this.walkable = true;
        this.coordinate = new Coordinate(xcord, ycord);
        this.sign = "[ ]";
    }

    //Constructor for board nodes
    public Node(boolean walkable, int xcord, int ycord){
        this.nodeID = 0;
        this.walkable = walkable;
        this.coordinate = new Coordinate(xcord, ycord);
        if(walkable){
            this.sign = "[ ]";
        }
        else{
            this.sign = "[x]";
        }
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.fx, n.fx);
    }

//    //Getters
//    public int getNodeID() {
//        return nodeID;
//    }

    public void addBranch(int weight, Node node){
        Edge newEdge = new Edge(weight, node);
        this.neighbors.add(newEdge);
    }

    public double calcHeuristic(Node target){ //you need the target node to calculate the heuristic
        return this.hx;
    }

    public boolean nodeWalkable(){
        return this.walkable;
    }

    public double manhattanDist(Node target){
        double dist;
        dist = Math.abs(this.coordinate.xCord - target.coordinate.xCord) + Math.abs(this.coordinate.yCord - target.coordinate.yCord);
        return dist;
    }



}
