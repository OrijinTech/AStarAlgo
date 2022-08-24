package src.aStar;
import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args){
        Menu application = new Menu("A Star Algorithm", new Board());
        application.setVisible(true);
    }

    //this is the UI for visualizing A*
    public static void visualizer(){
        JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setResizable(false);
        application.add(new Board());
        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);




    }

}
