package src;
import java.util.*;
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args){
        visualizer();
//        Board b = new Board(20,20);
//        Coordinate cord1 = new Coordinate(4,4);
//        Coordinate cord2 = new Coordinate(4, 10);
//        //b.addObstacle(cord1, cord2);
//        //adding starting node
//        Node start = new Node(1,5);
//        Node target = new Node(8,5);
//        b.addNodeToBoard(start);
//        b.addNodeToBoard(target);
//        b.printBoard();
//        aStar(start,target,b);
//        printPath(target,b);
    }

    //this is the UI for visualizing A*
    public static void visualizer(){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(new Board());

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public static Node aStar(Node start, Node target, Board board){
        PriorityQueue<Node> closedNodes = new PriorityQueue<>();
        PriorityQueue<Node> openNodes = new PriorityQueue<>();

        start.fx = start.gx + start.calcHeuristic(target);
        openNodes.add(start);

        while(!openNodes.isEmpty()){
            //pick the first node in the priority queue, since it is the best value node
            Node n = openNodes.peek();
            if(n == target) return n;

            for(Node node : n.neighborsOf8(n,board)){
                double totalWeight = n.gx + n.hx;
                if(node != null){
                    if(!openNodes.contains(node) && (!closedNodes.contains(node))){
                        System.out.println(node.coordinate.xCord + " " + node.coordinate.yCord);
                        openNodes.add(node);
                        node.parent = n;
                        node.gx = totalWeight;
                        node.fx = node.gx + node.calcHeuristic(target);
                    }
                    else{
                        if(totalWeight < node.gx){
                            node.parent = n;
                            node.gx = totalWeight;
                            node.fx = node.gx + node.calcHeuristic(target);
                            if(closedNodes.contains(node)){
                                openNodes.add(node);
                            }
                        }
                    }
                }
            }
            openNodes.remove(n);
            closedNodes.add(n);
        }
        return null;
    }

    public static void printPath(Node target, Board board){
        Node t = target;
        if(t == null){
            System.out.println("The target is NULL!");
        }

        List<Node> stops = new ArrayList<>();
        while(t.parent != null){
            stops.add(t);
            t = t.parent;
        }
        stops.add(t);
        Collections.reverse((stops));
        for(int idIdx = 0; idIdx<stops.size(); idIdx++){
            if(idIdx != stops.size()-1) {
                System.out.print(stops.get(idIdx).coordinate.xCord + " " + stops.get(idIdx).coordinate.yCord + " --> ");
                stops.get(idIdx).sign = "[o]";
                board.addNodeToBoard(stops.get(idIdx));
            }
            else{
                System.out.print(stops.get(idIdx));
                stops.get(idIdx).sign = "[o]";
                board.addNodeToBoard(stops.get(idIdx));
            }
        }

        System.out.println("");
        board.printBoard();
    }

}
