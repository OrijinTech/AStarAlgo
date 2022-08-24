package src.aStar;
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args){
        visualizer();
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
