package simulation.window;

import simulation.Human;
import simulation.animals.*;
import simulation.plants.*;
import simulation.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage extends JFrame implements ActionListener {
    private JLabel nameOfGameL, inputL;
    private JTextField widthT, heightT;
    private JPanel bottomPanel,topPanel,topBorder,centerPanel,inputPanel;
    private JButton confirmB,loadB;
    private World world;
    private final int sizeX=500;
    private final int sizeY=500;
    public StartPage(){
        setSize(sizeX,sizeY);
        Container pane =  this.getContentPane();
        pane.setLayout(new BorderLayout());
        setResizable(false);

        addLabels();
        addTextFields();
        addButtons();
        addPanels();

        pane.add(topPanel,BorderLayout.PAGE_START);
        pane.add(centerPanel,BorderLayout.CENTER);
        pane.add(bottomPanel,BorderLayout.PAGE_END);

        topBorder.setPreferredSize(new Dimension(pane.getWidth(),20));
        repaint();
    }
    private void addLabels(){
        nameOfGameL = new JLabel("Simulation Game", SwingConstants.CENTER);
        inputL = new JLabel("Insert size of the board",SwingConstants.CENTER);

        nameOfGameL.setForeground(Color.green);
        inputL.setForeground(Color.green);

        nameOfGameL.setBackground(Color.black);

        inputL.setFont(new Font("Apple Symbols",Font.BOLD,30));
        nameOfGameL.setFont(new Font("Apple Casual",Font.ITALIC,50));

    }
    private void addPanels(){
        topPanel = new JPanel();
        topBorder = new JPanel();
        centerPanel = new JPanel();
        inputPanel = new JPanel();
        bottomPanel= new JPanel();

        topPanel.setBackground(Color.black);
        centerPanel.setBackground(Color.black);
        inputPanel.setBackground(Color.black);
        bottomPanel.setBackground(Color.black);

        centerPanel.setLayout(new BorderLayout());
        inputPanel.setLayout(new FlowLayout(6));

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,10,250,10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,30));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20,120,0,0));

        inputPanel.add(widthT);
        inputPanel.add(heightT);
        inputPanel.add(confirmB);
        inputPanel.add(loadB);
        centerPanel.add(inputPanel);
        bottomPanel.add(topBorder,BorderLayout.PAGE_END);
        topPanel.add(nameOfGameL);
        centerPanel.add(inputL,BorderLayout.PAGE_START);
    }
    private void addTextFields(){
        widthT = new JTextField();
        heightT = new JTextField();

        widthT.setPreferredSize(new Dimension(60,20));
        heightT.setPreferredSize(new Dimension(60,20));

    }
    private void addButtons(){
        confirmB = new JButton("Confirm");
        loadB = new JButton("Load");

        confirmB.setPreferredSize(new Dimension(80,20));
        loadB.setPreferredSize(new Dimension(80,20));

        confirmB.addActionListener(this);
        loadB.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == confirmB) {
            dispose();
            int width = Integer.parseInt(widthT.getText());
            int height = Integer.parseInt(heightT.getText());
            world = new World(width, height);
            JFrame frame = new myFrame("Simualtion", world);
            frame.setVisible(true);
        }else if( source ==loadB){
            dispose();
            world = new World();
            world.load();
            JFrame frame = new myFrame("Simualtion", world);
            frame.setVisible(true);
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        printLegendNames(g);
        printColors(g);
    }
    protected void printLegendNames(Graphics g){
        int animalY=330;
        int animalX=60;
        int plantX=sizeX-160;
        int plantY=animalY;
        g.setColor(Color.green);
        g.setFont(new Font("Apple Symbols",Font.BOLD,30));
        g.drawString("GAME LEGEND",180,250);
        g.setFont(new Font("Apple Symbols",Font.BOLD,25));
        g.drawString("Human",200,300);
        g.setColor(Color.green);
        //animals
        g.drawString("Animals",animalX,animalY);
        g.setFont(new Font("Apple Symbols",Font.ITALIC,15));
        g.drawString("Wolf",animalX-10,animalY+20);
        g.drawString("Sheep",animalX-10,animalY+40);
        g.drawString("Fox",animalX-10,animalY+60);
        g.drawString("Turtle",animalX-10,animalY+80);
        g.drawString("Antelope",animalX-10,animalY+100);
        g.drawString("Cybersheep",animalX-10,animalY+120);
        //plants
        g.setFont(new Font("Apple Symbols",Font.BOLD,25));
        g.drawString("Plants",plantX+10,plantY);
        g.setFont(new Font("Apple Symbols",Font.ITALIC,15));
        g.drawString("Grass",plantX-10,plantY+20);
        g.drawString("Dandelion",plantX-10,plantY+40);
        g.drawString("Guarana",plantX-10,plantY+60);
        g.drawString("Wolfberry",plantX-10,plantY+80);
        g.drawString("Barszcz Sosnowskiego",plantX-10,plantY+100);
        g.setFont(new Font("Apple Symbols",Font.BOLD,20));
        g.drawString("Pawel Domaradzki 187586",sizeX/2+20,sizeY-20);
    }

    protected void printColors(Graphics g){
        int animalX=140;
        int animalY=340;
        int plantX=sizeX-30;
        int plantY=animalY;
        int rW=10;
        int rH=10;
        g.setColor(new Human().getColor());
        g.fillRect(280,287,15,15);
        //animals colors
        g.setColor(new Wolf().getColor());
        g.fillRect(animalX,animalY,rH,rH);
        g.setColor(new Sheep().getColor());
        g.fillRect(animalX,animalY+20,rW,rH);
        g.setColor(new Fox().getColor());
        g.fillRect(animalX,animalY+40,rW,rH);
        g.setColor(new Turtle().getColor());
        g.fillRect(animalX,animalY+60,rW,rH);
        g.setColor(new Antelope().getColor());
        g.fillRect(animalX,animalY+80,rW,rH);
        g.setColor(new Cybersheep().getColor());
        g.fillRect(animalX,animalY+100,rW,rH);
        //plants colors
        g.setColor(new Grass().getColor());
        g.fillRect(plantX,plantY,rW,rH);
        g.setColor(new Dandelion().getColor());
        g.fillRect(plantX,plantY+20,rW,rH);
        g.setColor(new Guarana().getColor());
        g.fillRect(plantX,plantY+40,rW,rH);
        g.setColor(new Wolfberries().getColor());
        g.fillRect(plantX,plantY+60,rW,rH);
        g.setColor(new BarszczSosnowskiego().getColor());
        g.fillRect(plantX,plantY+80,rW,rH);
    }
}
