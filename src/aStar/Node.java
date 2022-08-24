package src.aStar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton {
    Node parent;
    int col;
    int row;
    int gCost;
    int hCost;
    int fCost;
    boolean isStart;
    boolean isTarget;
    boolean isObstacle;
    boolean isOpen;
    boolean isChecked;

    public Node(int col, int row){
        this.col = col;
        this.row = row;
        setBackground(Color.getHSBColor(0.85f,0.2f,1.0f));
        setForeground(Color.black);
    }

    public void setNode(String mode){
        if(mode.equalsIgnoreCase("start")){
            setBackground(Color.getHSBColor(0.58f,1f,1f));
            setForeground(Color.white);
            setText("Start");
            isStart = true;
        }
        if(mode.equals("target")){
            setBackground(Color.getHSBColor(0.05f,1.0f,1.0f));
            setForeground(Color.black);
            setText("Target");
            isTarget = true;
        }
        if(mode.equals("obstacle")){
            setBackground(Color.black);
            setForeground(Color.black);
            isObstacle = true;
        }
    }

    public void setAsOpen(){
        isOpen = true;
    }

    public void setAsChecked(){
        if(!isStart && !isTarget){
            setBackground(Color.getHSBColor(0.25f,0.35f,1f));
            setForeground(Color.black);
        }
        isChecked = true;
    }

    public void setAsPath(){
        setBackground(Color.getHSBColor(0f,1f,1f));
        setForeground(Color.black);
    }

}
