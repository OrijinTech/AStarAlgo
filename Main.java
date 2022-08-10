import java.util.*;

public class Main {
    public static void main(String[] args){







    }


    public static Node aStar(Node start, Node target){
        PriorityQueue<Node> closedNodes = new PriorityQueue<>();
        PriorityQueue<Node> openNodes = new PriorityQueue<>();

        start.fx = start.gx + start.calcHeuristic(target);
        openNodes.add(start);

        while(!openNodes.isEmpty()){
            //pick the first node in the priority queue, since it is the best value node
            Node n = openNodes.peek();
            if(n == target) return n;

            for(Edge edge : n.neighbors){
                Node m = edge.node;
                double totalWeight = n.gx + edge.weight;
                if(!openNodes.contains(m) && (!closedNodes.contains(m))){
                    openNodes.add(m);
                    m.parent = n;
                    m.gx = totalWeight;
                    m.fx = m.gx + m.calcHeuristic(target);
                }
                else{
                    if(totalWeight < m.gx){
                        m.parent = n;
                        m.gx = totalWeight;
                        m.fx = m.gx + m.calcHeuristic(target);
                        if(closedNodes.contains(m)){
                            openNodes.add(m);
                        }
                    }

                }
            }
            closedNodes.add(n);
        }
        return null;
    }

    public static void printPath(Node target){
        Node t = target;
        if(t == null){
            System.out.println("The target is NULL!");
        }

        List<Integer> stops = new ArrayList<>();
        while(t.parent != null){
            stops.add(t.getNodeID());
            t = t.parent;
        }
        stops.add(t.getNodeID());
        Collections.reverse((stops));
        for(int id : stops){
            System.out.print(id + " --> ");
            System.out.println("");
        }
    }



}
