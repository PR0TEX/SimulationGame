package simulation.plants;

import simulation.*;

import java.awt.*;

public class Guarana extends Plant {
    public Guarana(){}
    public Guarana(World _world, Coords _position){
        super(_world,_position,0);
    }
    public Guarana(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    @Override
    public void collision(Organism opponent){
        opponent.setPower(opponent.getPower()+3);
        super.collision(opponent);
    }
    @Override
    public Color getColor() { return Color.MAGENTA; }
    @Override
    public char getName() { return '^'; }
}
