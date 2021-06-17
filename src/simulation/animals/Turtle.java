package simulation.animals;

import simulation.*;
import java.awt.*;

public class Turtle extends Animal {
    public Turtle(){}
    public Turtle(World _world, Coords _position){
        super(_world, _position);
        power = 2;
        initiative = 1;
    }
    public Turtle(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    @Override
    public void action(){
        int possibility = randNumber(100);
        if(possibility > 75) super.action();
    }
    @Override
    public void collision(Organism opponent){
            if(opponent.getPower() < 5) super.collision(opponent);
    }
    @Override
    public char getName() { return 'T'; }
    @Override
    public Color getColor() { return new Color(0,100,0); }
}
