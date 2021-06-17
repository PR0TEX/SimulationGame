package simulation.plants;

import simulation.*;
import java.awt.*;

public class Dandelion extends Plant {
    public Dandelion(){}
    public Dandelion(World _world, Coords _position) {
        super(_world,_position,0);
    }
    public Dandelion(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    @Override
    protected boolean spread(int attempt){
        if(attempt > 3){
            attempt = 3;
        }
        super.spread(attempt);
        return false;
    }
    @Override
    public Color getColor() { return Color.yellow; }
    @Override
    public char getName() { return '*'; }
}
