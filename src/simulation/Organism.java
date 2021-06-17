package simulation;

import java.awt.*;
import java.util.Random;

public abstract class Organism {
    protected int age;
    protected int power;
    protected int initiative;
    private Coords position;
    public World world;
    public Organism(){}
    public Organism(World _world, Coords _posiition){
        this.world=_world;
        this.position=_posiition;
        this.age=0;
    }
    public Organism(World _world, Coords _position, int _age, int _initiative, int _power){
        this.world=_world;
        this.position=_position;
        this.age=_age;
        this.initiative=_initiative;
        this.power=_power;
    }
    public void action(){}
    public void collision(Organism opponent){}

    protected Direction shift(Organism org){
        boolean entry=false;
        for (int i=0;i<4;i++){
            if(neight(Direction.values()[randNumber(4)],1) instanceof Empty
            || neight(Direction.values()[randNumber(4)],1) instanceof Plant){
                entry=true;
                break;
            }
        }
        if(!entry) return Direction.EMPTY;
        Direction turn;
        do {
            turn = Direction.values()[randNumber(4)];
        }while(!ableToMove(org,turn));
        return turn;
    }
    protected Direction findEmpty(){
        boolean entry=false;
        for (int i=0;i<4;i++){
            if(neight(Direction.values()[randNumber(4)],1) instanceof Empty){
                entry=true;
                break;
            }
        }
        if(!entry) return Direction.EMPTY;
        Direction turn = shift(this);
        while(!(neight(turn,1) instanceof Empty)){
            turn = Direction.values()[randNumber(4)];
        }
        return turn;
    }
    protected boolean ableToMove(Organism org, Direction turn){
        int x = org.getPosition().x;
        int y = org.getPosition().y;
        switch (turn){
            case UP:
                if(y > 0) return true;
                break;
            case DOWN:
                if(y < world.getHeight()-1) return true;
                break;
            case LEFT:
                if(x > 0) return true;
                break;
            case RIGHT:
                if(x < world.getWidth()-1) return true;
        }
        return false;
    }
    protected void move(Organism org,Direction turn){
        switch (turn){
            case UP:
                org.position.y--;
                break;
            case DOWN:
                org.position.y++;
                break;
            case LEFT:
                org.position.x--;
                break;
            case RIGHT:
                org.position.x++;
                break;
        }
    }
    protected Organism neight(Direction turn, int moveSize){
        switch(turn){
            case UP:
                if(ableToMove(this,Direction.UP)) {
                    return world.world[position.y - moveSize][position.x];
                }
                break;
            case DOWN:
                if(ableToMove(this,Direction.DOWN)) {
                    return world.world[position.y + moveSize][position.x];
                }
                break;
            case LEFT:
                if(ableToMove(this,Direction.LEFT)) {
                    return world.world[position.y][position.x - moveSize];
                }
                break;
            case RIGHT:
                if(ableToMove(this,Direction.RIGHT)) {
                    return world.world[position.y][position.x + moveSize];
                }
                break;
        }
        return this;
    }

    protected void fillPosition(Organism org){
        world.world[org.getPosition().y][org.getPosition().x]= org;
    }
    protected void clearPosition(Organism org){
        world.world[org.getPosition().y][org.getPosition().x]=new Empty();
    }
    protected void kill(Organism org){
        clearPosition(org);
        world.delOrg(org);
    }
    protected int randNumber(int limit){
        Random gen = new Random();
        int nb = gen.nextInt(limit);
        return nb;
    }

    abstract public Color getColor();
    abstract public char getName();
    public int getInitiative() { return initiative; }
    public int getPower() { return power; }
    public Coords getPosition() { return position; }
    public void setPosition(Coords _position) { this.position = _position; }
    public void setPower(int value){
        this.power = value;
    }
    public int getAge() { return age; }
    public void setAge(int _age) { this.age=_age; }

}
