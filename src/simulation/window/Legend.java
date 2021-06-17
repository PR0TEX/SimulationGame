package simulation.window;

import simulation.Human;
import simulation.animals.*;
import simulation.plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Legend extends JFrame implements ActionListener{
    private final int sizeX = 500;
    private final int sizeY = 500;
    private JPanel panel;
    public Legend(){
        panel = new JPanel();
        setLayout(null);
        setSize(sizeX,sizeY);
        setTitle("Game legend");
        setResizable(false);
        panel.setSize(sizeX,sizeY);
        panel.setBackground(Color.black);
        setVisible(true);
        addButton();
        add(panel);
        repaint();
    }

    private void addButton(){
        JButton backB = new JButton("Back");
        backB.setForeground(Color.black);
        backB.addActionListener(this);
        backB.setBounds(30,40,40,40);
        panel.add(backB);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
        printLegendNames(g);
        printColors(g);
    }
    protected void printLegendNames(Graphics g){
        int animalY=200;
        int animalX=10;
        int plantX=sizeX-160;
        int plantY=animalY;
        g.setColor(Color.green);
        g.setFont(new Font("Apple Symbols",Font.BOLD,30));
        g.drawString("GAME LEGEND",180,80);
        g.setFont(new Font("Apple Symbols",Font.BOLD,25));
        g.drawString("Human",200,150);
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
        int animalX=90;
        int animalY=210;
        int plantX=sizeX-30;
        int plantY=animalY;
        int rW=10;
        int rH=10;
        g.setColor(new Human().getColor());
        g.fillRect(280,137,15,15);
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
