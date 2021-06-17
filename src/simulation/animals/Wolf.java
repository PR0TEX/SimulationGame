package simulation.animals;

import simulation.*;

import java.awt.*;

public class Wolf extends Animal {
    public Wolf() {}
    public Wolf(World _world, Coords _position) {
        super(_world, _position);
        power = 9;
        initiative = 5;
    }
    public Wolf(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    @Override
    public char getName() { return 'W'; }
    @Override
    public Color getColor() { return Color.red; }


}
