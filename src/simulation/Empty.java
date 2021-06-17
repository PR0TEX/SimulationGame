package simulation;

import java.awt.*;

public class Empty extends Organism{
    public Empty(){
        initiative=0;
        power=0;
    }
    public Empty(World _world, Coords _position){
        super(_world,_position);
        initiative=0;
        power=0;
    }
    @Override
    public Color getColor() { return Color.black; }
    @Override
    public char getName() { return '.'; }
}
