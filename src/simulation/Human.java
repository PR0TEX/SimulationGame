package simulation;

import java.awt.*;

public class Human extends Animal{
    private int prevPower;
    private int ability;
    public Human(){}
    public Human(World _world, Coords _position){
        super(_world,_position);
        power=5;
        initiative=4;
    }
    public Human(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    public void action(Direction turn){
        abilityLeft();
        if(turn == Direction.EMPTY) return;
        if(attackReturn(this)) return;
        if(ableToMove(this,turn)){
            if(neight(turn,1) instanceof Animal){
                collision(neight(turn,1));
            }else if(neight(turn,1) instanceof Plant) {
                neight(turn,1).collision(this);
            }else{
                    clearPosition(this);
                    move(this,turn);
                    fillPosition(this);
                }
            }
    }
    public void ability(){
        if(abilityLeft()){
            magicPotion();
        }else{
            world.createLogs("Ability is not avaliable");
        }
    }
    private boolean abilityLeft(){
        if(ability == 0) return true;
        else if(ability > 0){
            if(ability == 5) {
                this.setPower(prevPower);
                prevPower = 0;
            }
            ability--;
            if(prevPower > 0){
                this.setPower(this.getPower()-1);
            }
        }
        return false;
    }
    private void magicPotion(){
        this.setPrevPower(this.getPower());
        this.setPower(10);
        this.setAbility(10);
        world.createLogs(getName()+" uses magic potion");
    }
    @Override
    public Color getColor() { return Color.pink; }
    @Override
    public char getName() { return 'H'; }
    private void setAbility(int _ability) { this.ability=_ability; }
    private void setPrevPower(int _prevPower) { this.prevPower=_prevPower; }
}
