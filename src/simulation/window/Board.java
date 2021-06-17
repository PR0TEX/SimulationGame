package simulation.window;

import simulation.*;
import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private World world;
    public Board(World _world){
        this.world=_world;
        repaint();
    }
    @Override
    public void paint(Graphics g){
        paintComponent(g);
    }
    protected void paintComponent(Graphics g){
        for(int i=0; i<world.getHeight(); i++){
            for(int j=0; j<world.getWidth(); j++){
                g.setColor(world.world[i][j].getColor());
                g.fillRect((j+1)*10,(i+3)*10,15,15);
            }
        }
    }
}
