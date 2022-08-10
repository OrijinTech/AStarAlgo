import java.lang.reflect.Array;

public class Board {
    public int rows;
    public int cols;
    public Node[][] grid;

    public Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.grid = new Node[rows][cols];
    }

    public void printBoard(){
        for(int i = 0; i<this.grid.length; i++){
            for(int j = 0; j<this.grid[1].length; j++){
                System.out.print("[ ]");
            }
            System.out.println("");
        }
    }

    public void addObstacle(int r, int c, Node node){
        this.grid[r][c] = node;
    }

}
