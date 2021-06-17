package simulation.animals;

import simulation.*;

import java.awt.*;

public class Sheep extends Animal {
    public Sheep(){}
    public Sheep(World _world,Coords _position){
        super(_world,_position);
        power = 4;
        initiative = 4;
    }
    public Sheep(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    @Override
    public char getName() { return 'S'; }
    @Override
    public Color getColor() { return Color.CYAN; }

}
