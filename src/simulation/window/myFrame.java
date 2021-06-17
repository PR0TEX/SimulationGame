package simulation.window;

import javax.swing.*;
import simulation.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class myFrame extends JFrame implements ActionListener, KeyListener {
    private World world;
    private Board board;
    private JButton moveB, infoB, saveB, endB, legendB;
    private JLabel signL;
    private JPanel buttonPanel;
    private int sizeX,sizeY;
    private final int minSizeX=500,minSizeY=500;
    public myFrame(String title,World _world){
        this.world=_world;
        sizeX=world.getWidth()*11;
        sizeY=world.getHeight()*14;
        setSize(sizeX,sizeY);
        setMinimumSize(new Dimension(minSizeX,minSizeY));
        setTitle(title);
        setBackground(Color.black);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(1));
        buttonPanel.setBackground(Color.black);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,0,10,2*world.getWidth()));
        board = new Board(world);

        board.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
        board.setVisible(true);
        addButtons();
        addSign();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        this.add(board);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }
    public void go(){
        repaint();
    }
    public void paint(Graphics g){ board.paint(g); }
    private void addButtons(){
        moveB = new JButton("Move");
        infoB = new JButton("Info");
        saveB = new JButton("Save");
        endB = new JButton("Close");
        legendB = new JButton("Legend");

        moveB.setBounds(10,22*world.getHeight()/2,50,50);
        infoB.setBounds(60,22*world.getHeight()/2,50,50);
        legendB.setBounds(110,22*world.getHeight()/2,80,50);
        saveB.setBounds(190,22*world.getHeight()/2,50,50);
        endB.setBounds(240,22*world.getHeight()/2,50,50);

        moveB.setFocusable(false);
        infoB.setFocusable(false);
        legendB.setFocusable(false);
        saveB.setFocusable(false);

        moveB.addActionListener(this);
        endB.addActionListener(this);
        legendB.addActionListener(this);
        infoB.addActionListener(this);
        saveB.addActionListener(this);

        buttonPanel.add(moveB);
        buttonPanel.add(infoB);
        buttonPanel.add(saveB);
        buttonPanel.add(endB);
        buttonPanel.add(legendB);
    }
    protected void addSign(){
        signL = new JLabel("Paweł Domaradzki 187586");
        signL.setBounds(sizeX-200,sizeY-60,200,30);
        add(signL);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if(source == moveB) {
          world.play(Direction.EMPTY);
          go();
        }else if(source == endB){
            dispose();
        }else if(source == legendB){
            Legend window = new Legend();
        }else if(source == infoB){
            Logs info = new Logs(world);
        }else if(source == saveB){
            world.save();
        }
    }
    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                world.play(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                world.play(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                world.play(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                world.play(Direction.RIGHT);
                break;
            case KeyEvent.VK_P:
                world.humanAbility();
                break;
        }
        go();
    }
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyReleased(KeyEvent e){}
}
