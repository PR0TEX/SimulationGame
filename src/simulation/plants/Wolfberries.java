package simulation.plants;

import simulation.*;

import java.awt.*;

public class Wolfberries extends Plant{
    public Wolfberries(){}
    public Wolfberries(World _world, Coords _position){
        super(_world,_position,99);
    }
    public Wolfberries(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    @Override
    public void collision(Organism opponent){
        kill(opponent);
        kill(this);
    }
    @Override
    public Color getColor() { return Color.BLUE; }
    @Override
    public char getName() { return '@'; }
}
