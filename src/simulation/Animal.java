package simulation;

import java.awt.*;

import simulation.animals.*;

public class Animal extends Organism {
    public Animal(){}
    public Animal(World _world, Coords _position){
        super(_world,_position);
    }
    public Animal(World _world, Coords _position, int _age, int _power, int _initiative){
        super(_world,_position,_age,_power,_initiative);
    }
    @Override
    public void action(){
        if(shift(this) == Direction.EMPTY) return;
        Direction turn = shift(this);
        if(attackReturn(neight(turn,1))) return;
        if(neight(turn,1) instanceof Empty) {
            clearPosition(this);
            move(this, turn);
            fillPosition(this);
        }else if(neight(turn,1) instanceof Plant){
            this.collision(neight(turn,1));
        }
        else{ neight(turn,1).collision(this); }
    }
    @Override
    public void collision(Organism opponent){
        if(this.getName() == opponent.getName()){
            meet(opponent);
        }else if(opponent instanceof Plant){
            opponent.collision(this);
        }else if(this.getPower() > opponent.getPower()){
            world.createLogs(this.getName()+" killed "+opponent.getName());
            clearPosition(this);
            setPosition(opponent.getPosition());
            kill(opponent);
            fillPosition(this);
        }else{
            world.createLogs(opponent.getName()+" killed "+this.getName());
            clearPosition(opponent);
            opponent.setPosition(this.getPosition());
            kill(this);
            fillPosition(opponent);
        }
    }
    protected void meet(Organism opponent){
        if(pickAnimal(opponent) == opponent) return;
        Animal animal = pickAnimal(opponent);
        world.addOrg(animal);
    }
    protected Animal pickAnimal(Organism opponent){
        if(opponent.findEmpty() == Direction.EMPTY) return (Animal)opponent;
        Direction turn = opponent.findEmpty();
        Animal animal = new Animal();
        if(opponent instanceof Antelope){
            animal = new Antelope(world, new Coords(opponent.getPosition().x,opponent.getPosition().y));
        }else if(opponent instanceof Cybersheep){
            animal = new Cybersheep(world, new Coords(opponent.getPosition().x,opponent.getPosition().y));
        }else if(opponent instanceof Fox){
            animal = new Fox(world, new Coords(opponent.getPosition().x,opponent.getPosition().y));
        }else if(opponent instanceof Sheep){
            animal = new Sheep(world, new Coords(opponent.getPosition().x,opponent.getPosition().y));
        }else if(opponent instanceof Turtle){
            animal = new Turtle(world, new Coords(opponent.getPosition().x,opponent.getPosition().y));
        }else if(opponent instanceof Wolf){
            animal = new Wolf(world, new Coords(opponent.getPosition().x,opponent.getPosition().y));
        }
        animal.move(animal,turn);
        return animal;
    }
    protected boolean attackReturn(Organism org){
        if(org instanceof Turtle && this.getPower() < 5) return true;
        return false;
    }

    @Override
    public Color getColor() { return Color.darkGray; }
    @Override
    public char getName() { return 'a'; }
}
