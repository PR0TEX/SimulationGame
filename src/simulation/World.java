package simulation;

import simulation.animals.*;
import simulation.plants.*;
import simulation.window.StartPage;
import simulation.window.myFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class World {
    private int goal;
    private int height;
    private int width;
    private String raport;
    private List<Organism> addList = new LinkedList<>();
    private List<Organism> delList = new LinkedList<>();
    public List<Organism> organisms = new LinkedList<>();
    public Organism[][] world;
    JFrame frame;
    public World(){}
    public World(int _width, int _height){
        goal=6;
        this.width=_width;
        this.height=_height;
        world = new Organism[height][width];
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                world[i][j] = new Empty();
            }
        }
        generateOrganisms(_height,_width);
    }
    public void play(Direction turn){
        for(Organism org : organisms){
            if(org.age > 0){
                if(world[org.getPosition().y][org.getPosition().x].getName() == org.getName()){
                    if(org instanceof Human){
                        ((Human) org).action(turn);
                    }else if(!(org instanceof Empty)){
                        org.action();
                    }
                }
            }
        }
        for(Organism org : addList){
            if(!(org instanceof Empty)){
                addOrganism(org);
            }
        }
        addList.clear();
        for(Organism org : organisms){
            if(org instanceof Empty){
                delList.add(org);
            }
        }
        for(Organism org : delList){
            organisms.remove(org);
        }
        delList.clear();
        for(Organism org : organisms) {
            org.setAge(org.getAge()+1);
        }
    }

    public void printWorld(){
        frame = new StartPage();
        frame.setVisible(true);

    }
    private void generateOrganisms(int nbOfAnimals,int nbOfPlants){
        int x,y;
        Random gen = new Random();
        addOrganism(new Human(this,new Coords(0,0)));
        for(int i=0;i<nbOfAnimals;i++){
            do {
                x = gen.nextInt(this.getWidth());
                y = gen.nextInt(this.getHeight());
            }while(!(world[y][x] instanceof Empty));

            int type = gen.nextInt(6);
            genAnimal(type,new Coords(x,y));
        }
        for(int i=0;i<nbOfPlants;i++){
            do {
                x = gen.nextInt(this.getWidth());
                y = gen.nextInt(this.getHeight());
            }while(!(world[y][x] instanceof Empty));

            int type = gen.nextInt(5);
            genPlant(type,new Coords(x,y));
        }
    }
    private void genAnimal(int type, Coords position){
        switch(type){
            case 0:
                addOrganism(new Wolf(this,new Coords(position.x,position.y)));
                break;
            case 1:
                addOrganism(new Sheep(this,new Coords(position.x,position.y)));
                break;
            case 2:
                addOrganism(new Fox(this,new Coords(position.x,position.y)));
                break;
            case 3:
                addOrganism(new Turtle(this,new Coords(position.x,position.y)));
                break;
            case 4:
                addOrganism(new Antelope(this,new Coords(position.x,position.y)));
                break;
            case 5:
                addOrganism(new Cybersheep(this,new Coords(position.x,position.y)));
                break;
        }

    }
    private void genPlant(int type, Coords position){
        switch(type){
            case 0:
                addOrganism(new Grass(this,new Coords(position.x,position.y)));
                break;
            case 1:
                addOrganism(new Dandelion(this,new Coords(position.x,position.y)));
                break;
            case 2:
                addOrganism(new Guarana(this,new Coords(position.x,position.y)));
                break;
            case 3:
                addOrganism(new Wolfberries(this,new Coords(position.x,position.y)));
                break;
            case 4:
                addOrganism(new BarszczSosnowskiego(this,new Coords(position.x,position.y)));
                break;
        }
    }
    public void humanAbility(){
        for(Organism org : organisms){
            if(org instanceof Human){
                ((Human) org).ability();
            }
        }
    }
    protected void addOrganism(Organism org){
        world[org.getPosition().y][org.getPosition().x] = org;
        if(organisms.isEmpty()){ organisms.add(org); }
        else{
            boolean mod = false;
            for( Organism o : organisms ){
                if(org.getInitiative() > o.getInitiative()){
                    organisms.add(organisms.indexOf(o),org);
                    mod = true;
                    break;
                }
            }
            if(!mod){ organisms.add(org); }
        }
    }
    protected void delOrg(Organism org){
        delList.add(org);
    }
    protected void addOrg(Organism org){
        addList.add(org);
    }
    public void createLogs(String log){
            if(raport == null){
                raport=log;
            }else raport+=log;
            raport+='\n';
    }
    public String getRaport() { return raport; }
    public int getHeight() { return height; }
    public int getWidth() { return width; }

    public void save(){
        String save = "";
        save += (width+" "+height+" "+organisms.size()+" "+"\n");
        for(Organism org : organisms){
            save+=(org.getName()+" ");
            save+=(org.getPosition().x+" ");
            save+=(org.getPosition().y+" ");
            save+=(org.getAge()+" ");
            save+=(org.getPower()+" ");
            save+=(org.getInitiative()+" ");
            save+="\n";
        }
        try{
            makeFile(save);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    private void makeFile(String data) throws IOException {
        try {
            String[] parts = data.split("\n");
            File file = new File("Save");
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter("Save"));
            for (String l : parts) {
                writer.write(l);
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void load(){
        try {
            loadFromFile();
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }
    private void loadFromFile() throws IOException {
        File file = new File("./Save");
        if(file.exists()){
            raport="";
            world=null;
            organisms.clear();
            BufferedReader reader = new BufferedReader(new FileReader("Save"));
            String row = reader.readLine();
            String[] parts = row.split(" ");

            width = Integer.parseInt(parts[0]);
            height = Integer.parseInt(parts[1]);
            int nbOfOrganism = Integer.parseInt(parts[2]);

            world = new Organism[height][width];
            for(int i=0;i<height;i++){
                for(int j=0;j<width;j++){
                    world[i][j] = new Empty();
                }
            }
            for(int i=0;i<nbOfOrganism;i++){
                row = reader.readLine();
                parts = row.split(" ");
                int val[] = translate(parts);
                readOrganisms(val,parts[0].charAt(0));
            }
        }
    }
    private int[] translate(String parts[]){
        int tab[] = new int[6];
        char temp;
        for(int i=0;i<tab.length;i++){
            try{
                tab[i] = Integer.parseInt(parts[i]);
            }catch (NumberFormatException e){
                temp = parts[i].charAt(0);
                tab[i] = Character.getNumericValue(temp);
            }
        }
        return tab;
    }
    private void readOrganisms(int[] data,char type){
        switch (type){
            case 'W':
                this.addOrganism(new Wolf(this,new Coords(data[1],data[2]),data[3],data[4],data[5]));
                break;
            case 'S':
                this.addOrganism(new Sheep(this,new Coords(data[1],data[2]),data[3],data[4],data[5]));
                break;
            case 'F':
                this.addOrganism(new Fox(this,new Coords(data[1],data[2]),data[3],data[4],data[5]));
                break;
            case 'T':
                this.addOrganism(new Turtle(this,new Coords(data[1],data[2]),data[3],data[4],data[5]));
                break;
            case 'A':
                this.addOrganism(new Antelope(this,new Coords(data[1],data[2]),data[3],data[4],data[5]));
                break;
            case 'C':
                this.addOrganism(new Cybersheep(this,new Coords(data[1],data[2]),data[3],data[4],data[5]));
                break;
            case ',':
                this.addOrganism(new Grass(this,new Coords(data[1],data[2]),data[3],data[4],data[5]));
                break;
            case '*':
                this.addOrganism(new Dandelion(this,new Coords(data[1],data[2]),data[3],data[4],data[5]));
                break;
            case '^':
                this.addOrganism(new Guarana(this,new Coords(data[1],data[2]),data[3],data[4],data[5]));
                break;
            case '@':
                this.addOrganism(new Wolfberries(this,new Coords(data[1],data[2]),data[3],data[4],data[5]));
                break;
            case '!':
                this.addOrganism(new BarszczSosnowskiego(this,new Coords(data[1],data[2]),data[3],data[4],data[5]));
                break;
            case 'H':
                this.addOrganism(new Human(this,new Coords(data[1],data[2]),data[3],data[4],data[5]));
                break;
        }
    }


}
