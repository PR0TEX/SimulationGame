package simulation.window;

import javax.swing.*;
import simulation.*;

import java.awt.*;

public class Logs extends JFrame {
    private final int width=363;
    private final int height=500;
    private World world;
    private JPanel panel;
    private JTextArea areaT;
    private JScrollPane scrollS;
    public Logs(World _world){
        this.world=_world;
        panel=new JPanel();
        areaT = new JTextArea(world.getRaport(),1000,30);
        scrollS=new JScrollPane(areaT);
        scrollS.setVisible(true);
        areaT.setEditable(false);
        areaT.setBackground(Color.black);
        areaT.setForeground(Color.green);
        panel.setBackground(Color.black);
        //setResizable(false);
        getContentPane().add(scrollS,BorderLayout.EAST);
        add(panel);
        setSize(width,height);
        setVisible(true);
    }
}
