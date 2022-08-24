package src.aStar;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInp implements KeyListener {
    Board map;

    public UserInp(Board map){
        this.map = map;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_ENTER){
            map.aStarManual();
        }
        else if(code == KeyEvent.VK_A){
            map.aStarAuto();
        }
        else if(code == KeyEvent.VK_R){
            map.resetBoard();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
