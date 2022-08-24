package src.oldFiles;
import javax.swing.JPanel;

public class Board extends JPanel {
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
