package simulation.animals;

import simulation.*;
import simulation.plants.BarszczSosnowskiego;

import java.awt.*;

public class Cybersheep extends Animal {
    public Cybersheep(){}
    public Cybersheep(World _world, Coords _position){
        super(_world, _position);
        power = 11;
        initiative = 4;
    }
    public Cybersheep(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    @Override
    public void action(){
        Direction turn = findBarszcz();
        if(attackReturn(neight(turn,1))) return;
        if(ableToMove(this,turn)){
            if(neight(turn,1) instanceof Empty){
                clearPosition(this);
                move(this,turn);
                fillPosition(this);
            }else{
                collision(neight(turn,1));
            }
        }
    }
    @Override
    public void collision(Organism opponent){
        if(opponent instanceof BarszczSosnowskiego){
            clearPosition(this);
            kill(opponent);
            setPosition(new Coords(opponent.getPosition().x,opponent.getPosition().y));
            fillPosition(this);
        }else super.collision(opponent);
    }
    private Direction findBarszcz(){
        int dist = world.getHeight()+world.getWidth();
        int tempDist;
        Coords targePos=new Coords();
        boolean exist=false;
        for(Organism org : world.organisms){
            if(org instanceof BarszczSosnowskiego){
                exist=true;
                break;
            }
        }
        if(exist){
            for (Organism org : world.organisms){
                if(org instanceof BarszczSosnowskiego){
                    tempDist = org.getPosition().y+org.getPosition().x;
                    if(tempDist < dist){
                        dist=tempDist;
                        targePos = org.getPosition();
                    }
                }
            }
            if(Math.abs(this.getPosition().y - targePos.y) < Math.abs(this.getPosition().x - targePos.x)){
                if(this.getPosition().x > targePos.x) return Direction.LEFT;
                else return Direction.RIGHT;
            }else{
                if(this.getPosition().y > targePos.y) return Direction.UP;
                else return Direction.DOWN;
            }

        }else{
            return shift(this);
        }
    }
    @Override
    public char getName() { return 'C'; }
    @Override
    public Color getColor() { return Color.white; }
}
