package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NodeUI extends JButton implements ActionListener {
    NodeUI parent;
    int col;
    int row;
    int gCost;
    int hCost;
    int fCOst;
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;

    public NodeUI(int col, int row){
        this.col = col;
        this.row = row;
        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(Color.orange);

    }
}
