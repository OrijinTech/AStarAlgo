package src.oldFiles;

import src.oldFiles.Board;
import java.util.ArrayList;
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
    public Node(int xcord, int ycord){
        this.nodeID = idCounter++;
        this.neighbors = new ArrayList<Edge>();
        this.walkable = true;
        this.coordinate = new Coordinate(xcord, ycord);
        this.sign = "[o]";
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
        this.hx = manhattanDist(target);
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

    // Array is enough, we are not changing the size of the neighbor list (always 8 neighbors)
    public Node[] neighborsOf8(Node cell, Board b){
        Node[] nodes = new Node[8];
        int nodeIdx = 0;
        for(int y = -1; y<=1; y++){
            for(int x = -1; x<=1; x++){
                if(inBounds(cell.coordinate.xCord + x, cell.coordinate.yCord + y, b)){
                    Node node = b.grid[cell.coordinate.xCord + x][cell.coordinate.yCord + y];
                    if(node.walkable && node != cell){
                        nodes[nodeIdx] = node;
                        nodeIdx++;
                    }
                }
            }
        }
        return nodes;
    }

    public boolean inBounds(int xCord, int yCord, Board b){
        return (xCord < b.rows && yCord < b.cols) && (xCord >= 0 && yCord >= 0);
    }


}
