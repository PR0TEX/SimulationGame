package simulation;

import simulation.animals.*;
import simulation.plants.*;
import java.awt.*;

public class Plant extends Organism{
    public Plant(){}
    public Plant(World _world,Coords _position,int _power){
        super(_world,_position);
        this.initiative=0;
        this.power=_power;
    }
    public Plant(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    @Override
    public void action(){
        if(this.findEmpty() == Direction.EMPTY) return;
        int attempt=1;
        if(spread(attempt)){
            attempt = world.getWidth()/2;
            while(attempt > 0 && spread(attempt)){
                breed();
            }
        }
    }
    @Override
    public void collision(Organism opponent){
        if(opponent.getPower() > this.getPower()){
            world.createLogs(opponent.getName()+" eats "+this.getName());
            clearPosition(opponent);
            kill(this);
            opponent.setPosition(new Coords(this.getPosition().x,this.getPosition().y));
            fillPosition(opponent);
        }else {
            world.createLogs(this.getName()+" kills "+opponent.getName());
            kill(this);
        }
    }
    protected boolean spread(int attempt){
        attempt--;
        int possibility = randNumber(100);
        if(possibility > 50) return true;
        return false;
    }

    protected void breed(){
        if(this.findEmpty() == Direction.EMPTY) return;
        Plant plant = pickPlant(this);
        world.addOrg(plant);
    }
    protected Plant pickPlant(Organism opponent) {
        Direction turn = this.findEmpty();
        Plant plant = new Plant();
        if (opponent instanceof Grass) {
            plant = new Grass(world,new Coords(opponent.getPosition().x, opponent.getPosition().y));
        } else if (opponent instanceof Dandelion) {
            plant = new Dandelion(world,new Coords(opponent.getPosition().x, opponent.getPosition().y));
        } else if (opponent instanceof Guarana) {
            plant = new Guarana(world,new Coords(opponent.getPosition().x, opponent.getPosition().y));
        } else if (opponent instanceof Wolfberries) {
            plant = new Wolfberries(world,new Coords(opponent.getPosition().x, opponent.getPosition().y));
        } else if (opponent instanceof BarszczSosnowskiego
        ) {
            plant = new BarszczSosnowskiego(world,new Coords(opponent.getPosition().x, opponent.getPosition().y));
        }
        move(plant,turn);
        return plant;
    }
    @Override
    public Color getColor() { return Color.darkGray; }
    @Override
    public char getName() { return 'p'; }


}
