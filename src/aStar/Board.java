package src.aStar;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
    final int maxRow = 40;
    final int maxCol = 40;
    final int nodeSize = 20;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;
    boolean targetReached = false;
    int step = 0; //current step of the A star

    //Node
    Node[][] nodes = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    public Board(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.orange);
        this.setLayout(new GridLayout(maxRow, maxCol));
        this.setFocusable(true);
        this.initializeBoard();
    }

    //create new nodes on the grid (default nodes)
    public void initializeBoard(){
        int col = 0;
        int row = 0;
        while(col < maxCol && row < maxRow){
            nodes[col][row] = new Node(col, row);
            this.add(nodes[col][row]);
            col++;
            if(col == maxCol){
                col = 0;
                row++;
            }
        }
        addStartandTarget();
    }

    public void addStartandTarget(){
        //set start and end node
        setStartNode(3,6);
        setTargetNode(21,26);
        //set the cost texts on all the nodes
        setCostText();
    }


    public void setStartNode(int col, int row){
        if((col < maxCol && row < maxRow) && (col >= 0 && row>=0)){
            nodes[col][row].setNode("start");
            startNode = nodes[col][row];
            currentNode = startNode;
            System.out.println("Start ID: " + currentNode.id);
        }
    }

    public void setTargetNode(int col, int row){
        if((col < maxCol && row < maxRow) && (col >= 0 && row>=0)){
            nodes[col][row].setNode("target");
            goalNode = nodes[col][row];
            System.out.println("Start ID: " + goalNode.id);
        }
    }

    public void setObstacleNode(int col, int row){
        if((col < maxCol && row < maxRow) && (col >= 0 && row>=0)){
            nodes[col][row].setNode("obstacle");
        }
    }

    //calculating the f(x) and sets it to the node
    public void getCost(Node node){
        //calculating g(x)
        double xDist = Math.abs(node.col - startNode.col);
        double yDist = Math.abs(node.row - startNode.row);
        node.gCost = Math.sqrt(xDist*xDist + yDist*yDist);
        //calculating h(x)
        xDist = Math.abs(node.col - goalNode.col);
        yDist = Math.abs(node.row - goalNode.row);
        node.gCost = Math.sqrt(xDist*xDist + yDist*yDist);
        //calculating f(x) and set it for the node
        node.fCost = node.gCost + node.hCost;
        //if we are not at the start or target node, add the cost info
        if(node != startNode && node != goalNode){
            //node.setText("<html>Col:" + node.col + "<br>Row:" + node.row + "</html>");
            //node.setText("<html>F:" + node.fCost + "<br>G:" + node.gCost + "</html>");
        }
    }

    //Sets the cost of each node wrt. the start node.
    private void setCostText(){
        int row = 0;
        int col = 0;
        while(col < maxCol && row < maxRow){
            getCost(nodes[col][row]);
            col++;
            if(col == maxCol){
                col = 0;
                row ++;
            }
        }
    }

    //We set the node as "opened" and add it to the open list
    private void openNode(Node node){
        if(!node.isOpen && !node.isChecked && !node.isObstacle){
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void getPath(){
        Node current = goalNode;

        while(current != startNode){
            current = current.parent;
            if(current != startNode){
                current.setAsPath();
            }
        }
    }

    //manual mode of the A* search, 1 step of A*
    public void aStarManual(){
        if(!targetReached && step < 300) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);
            //neighbors of 4
            if(row - 1 >= 0) openNode(nodes[col][row-1]);
            if(row + 1 < maxRow) openNode(nodes[col][row+1]);
            if(col - 1 >= 0) openNode(nodes[col-1][row]);
            if(col + 1 < maxCol) openNode(nodes[col+1][row]);
            int bestIndex = 0;
            double bestFCost = 999;

            for(int i = 0; i<openList.size();i++){
                //check if the this node has a better f cost than the default node's f cost
                if(openList.get(i).fCost < bestFCost){
                    bestIndex = i;
                    bestFCost = openList.get(i).fCost;
                }
                //if f cost is equal, compare g cost
                else if(openList.get(i).fCost == bestFCost){
                    if(openList.get(i).gCost < openList.get(bestIndex).gCost){
                        bestIndex = i;
                    }
                }
            }
            // our next best step
            currentNode = openList.get(bestIndex);
            //if the current node is a target, then we reached it!
            if(currentNode == goalNode) {
                System.out.println("TARGET REACHED! "+ currentNode.id);
                targetReached = true;
                //this will backtrack to the starting node + set the grid to the path color
                getPath();
            }
        }
        step++;
    }

    //one-click A* search
    public void aStarAuto(){
        while(!targetReached && step < 300) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            //neighbors of 4
            if(row - 1 >= 0) openNode(nodes[col][row-1]); //Up
            if(row + 1 < maxRow) openNode(nodes[col][row+1]); //Down
            if(col - 1 >= 0) openNode(nodes[col-1][row]); //Left
            if(col + 1 < maxCol) openNode(nodes[col+1][row]); //Right
            int bestIndex = 0;
            double bestFCost = 999; //default the f(x) to a very high value

            for(int curIdx = 0; curIdx < openList.size(); curIdx++){
                //check if the this node has a better f cost than the default node's f cost
                if(openList.get(curIdx).fCost < bestFCost){ //it is better
                    //update the index and f(x) of the current best node to go
                    bestIndex = curIdx;
                    bestFCost = openList.get(curIdx).fCost;
                }
                //if f cost is equal, compare g cost
                else if(openList.get(curIdx).fCost == bestFCost){
                    if(openList.get(curIdx).gCost < openList.get(bestIndex).gCost){
                        //only update the index
                        bestIndex = curIdx;
                    }
                }
            }
            // update our next best step
            currentNode = openList.get(bestIndex);
            //if the current node is a target, then we reached it!
            if(currentNode == goalNode){
                targetReached = true;
                System.out.println("TARGET REACHED! "+ currentNode.id);
                // draw the path
                getPath();
            }
        }
        //increase A* steps
        step++;
    }

    public void clearObstacle(){
        openList.clear();
        checkedList.clear();
        targetReached = false;
        step = 0;
        goalNode.isOpen = false;
        //clear the board
        int col = 0;
        int row = 0;
        while(col < maxCol && row < maxRow){
            if(!nodes[col][row].isStart && !nodes[col][row].isTarget){
                nodes[col][row].resetNode();
            }
            col++;
            if(col == maxCol){
                col = 0;
                row++;
            }
        }
        addStartandTarget();
    }

    public void resetBoard(){
        openList.clear();
        checkedList.clear();
        targetReached = false;
        step = 0;
        goalNode.isOpen = false;
        //clear the board
        int col = 0;
        int row = 0;
        while(col < maxCol && row < maxRow){
            if(!nodes[col][row].isStart && !nodes[col][row].isTarget && !nodes[col][row].isObstacle){
                nodes[col][row].resetNode();
            }
            col++;
            if(col == maxCol){
                col = 0;
                row++;
            }
        }
        addStartandTarget();
    }



}
