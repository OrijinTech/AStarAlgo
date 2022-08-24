package src.aStar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    Board aStarGrid;

    JButton button1 = new JButton("A* Search");
    JButton button2 = new JButton("Manual A*");
    JButton button3 = new JButton("Reset");
    JButton button4 = new JButton("Button 1");

    public Menu(String title, Board board){
        super(title);

        aStarGrid = board;

        this.setSize(700,300);
        this.setLocation(100,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.green));

        JPanel topPanel = new JPanel();
        topPanel.setBorder(new LineBorder(Color.BLACK,3));
        topPanel.setBackground(Color.orange);
        topPanel.setLayout(new FlowLayout(5));

        topPanel.add(button1);
        topPanel.add(button2);
        topPanel.add(button3);
        topPanel.add(button4);
        mainContainer.add(topPanel, BorderLayout.NORTH);


        // Add the A* grid to the application
        this.add(aStarGrid);
        this.pack();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1) aStarGrid.aStarAuto();
        if(e.getSource() == button2) aStarGrid.aStarManual();
        if(e.getSource() == button3) aStarGrid.resetBoard();
    }

}
