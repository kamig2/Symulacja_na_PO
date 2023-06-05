package projekt;
import java.util.Random;

abstract public class Agent {
    Random random= new Random();
    boolean win=false;
    boolean alive = true;
    int position_x;
    int position_y;
    int hunger=0;
    void die(){
        alive = false;
        Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.EMPTY);
    }
    boolean contains(int x, int y){
        if(x<0 || x>= Simulation.getSize_x() || y<0 || y>= Simulation.getSize_y()) return false;
        else return true;
    }
    abstract void update();
    abstract void move();
    abstract void eat(Field eaten_field, int x, int y);
}
