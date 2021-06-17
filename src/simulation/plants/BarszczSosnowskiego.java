package simulation.plants;

import simulation.*;
import simulation.animals.*;

import java.awt.*;

public class BarszczSosnowskiego extends Plant {
    public BarszczSosnowskiego() {}
    public BarszczSosnowskiego(World _world, Coords _position){
        super(_world,_position,10);
    }
    public BarszczSosnowskiego(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    @Override
    public void action(){
        Direction turn;
        for(int i=0;i<4;i++){
            turn=Direction.values()[i];
            if(neight(turn,1) instanceof Animal && !(neight(turn,1) instanceof Cybersheep)){
               kill((neight(turn,1)));
            }
        }
    }
    @Override
    public void collision(Organism opponent){
        if(opponent instanceof Animal &&  !(opponent instanceof Cybersheep)){
            kill(opponent);
        }else if(opponent instanceof Cybersheep){
            opponent.collision(this);
        }
        kill(this);
    }
    @Override
    public Color getColor() { return new Color(128,0,128); }
    @Override
    public char getName() { return '!'; }
}
