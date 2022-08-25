package src.aStar;
import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args){
        Menu application = new Menu("A Star Algorithm", new Board());
        application.setVisible(true);
    }
}
