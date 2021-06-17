package simulation.plants;

import simulation.*;
import java.awt.*;

public class Grass extends Plant {
    public Grass(){}
    public Grass(World _world, Coords _position){
        super(_world,_position,0);
    }
    public Grass(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    @Override
    public Color getColor() { return Color.green; }
    @Override
    public char getName() { return ','; }

}
