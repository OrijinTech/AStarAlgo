package src;
import javax.swing.JPanel;
import java.awt.*;

public class Board extends JPanel {
    final int maxRow = 10;
    final int maxCol = 15;
    final int nodeSize = 80;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;

    //Node
    NodeUI[][] nodes = new NodeUI[maxCol][maxRow];
    NodeUI startNode, goalNode, currentNode;

    public Board(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.orange);
        this.setLayout(new GridLayout(maxRow, maxCol));
        this.initializeBoard();
    }

    //create new nodes on the grid (default nodes)
    public void initializeBoard(){
        int col = 0;
        int row = 0;
        while(col < maxCol && row < maxRow){
            nodes[col][row] = new NodeUI(col, row);
            this.add(nodes[col][row]);
            col++;
            if(col == maxCol){
                col = 0;
                row++;
            }
        }
        setStartNode(3,6);
        setTargetNode(11,3);
    }

    public void setStartNode(int col, int row){
        nodes[col][row].setNode("start");
        startNode = nodes[col][row];
        currentNode = startNode;
    }

    public void setTargetNode(int col, int row){
        nodes[col][row].setNode("start");
        goalNode = nodes[col][row];
    }


    public int rows;
    public int cols;
    public Node[][] grid;

    public Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.grid = new Node[rows][cols];
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                Node newNode = new Node(true, i, j);
                this.grid[i][j] = newNode;
            }
        }
    }

    public void printBoard(){
        for(int i = 0; i<this.grid.length; i++){
            for(int j = 0; j<this.grid[1].length; j++){
                System.out.print(this.grid[i][j].sign);
            }
            System.out.println("");
        }
    }

    public void addNodeToBoard(Node node){
        this.grid[node.coordinate.xCord][node.coordinate.yCord] = node;
    }

    public void addObstacle(Coordinate cord1, Coordinate cord2){
        for(int i = 0; i<Math.abs(cord2.yCord-cord1.yCord); i++){
            //create an obstacle node
            Node obstacle = new Node(false, cord1.xCord,cord1.yCord+i);
            //put the obstacle on the grid
            this.grid[cord1.xCord][cord1.yCord+i] = obstacle;
        }
    }

    public Node getNode(int xcord, int ycord){
        return this.grid[xcord][ycord];
    }



}
