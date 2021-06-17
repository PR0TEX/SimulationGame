package simulation.animals;

import simulation.*;

import java.awt.*;

public class Fox extends Animal {
    public Fox(){}
    public Fox(World _world, Coords _position){
        super(_world, _position);
        power = 3;
        initiative = 7;
    }
    public Fox(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    @Override
    public void action(){
        Direction turn = shift(this);
        if(attackReturn(neight(turn,1))) return;
        if(neight(turn,1) == this) return;
        if(neight(turn,1) instanceof Empty) {
            clearPosition(this);
            move(this, turn);
            fillPosition(this);
        }else if(neight(turn,1) instanceof Plant){
                this.collision(neight(turn,1));
        }else if(neight(turn,1).getPower() <= this.getPower()){
            neight(turn,1).collision(this);
        }
    }
    @Override
    public char getName() { return 'F'; }
    @Override
    public Color getColor() { return Color.orange; }

}
