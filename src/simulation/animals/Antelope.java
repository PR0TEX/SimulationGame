package simulation.animals;

import simulation.*;

import java.awt.*;

public class Antelope extends Animal {
    public Antelope(){}
    public Antelope(World _world, Coords _position){
        super(_world,_position);
        power = 4;
        initiative = 4;
    }
    public Antelope(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    @Override
    public void action(){
        if(shift(this) == Direction.EMPTY) return;
        Direction turn = shift(this);
        if(neight(turn,1) == this) return;
        if(attackReturn(neight(turn,1))) return;
        if(neight(turn,1) instanceof Empty || neight(turn,1) instanceof Plant){
           if(neight(turn,1) instanceof Plant){
                this.collision(neight(turn,1));
                return;
           }
           clearPosition(this);
           move(this,turn);
           if(ableToMove(this,turn)) {
               if (neight(turn, 1) instanceof Empty){
                   move(this, turn);
                   fillPosition(this);
               }else if(neight(turn,1) instanceof Plant){
                   this.collision(neight(turn,1));
               }else {
                   neight(turn, 1).collision(this);
               }
           }
       }else{
           neight(turn,1).collision(this);
       }
    }
    @Override
    public void collision(Organism opponent){
        int possibility = randNumber(100);
        if(possibility > 50) {
            if(this.findEmpty() == Direction.EMPTY) return;
            Direction turn = findEmpty();
            clearPosition(this);
            move(this,turn);
            fillPosition(this);
        }else super.collision(opponent);
    }
    @Override
    public char getName() { return 'A'; }
    @Override
    public Color getColor() { return Color.lightGray; }

}

