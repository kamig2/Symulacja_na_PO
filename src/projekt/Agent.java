package projekt;
import java.util.Random;

abstract public class Agent {
    Random random= new Random();
    protected boolean win=false;
    protected boolean alive = true;
    protected int position_x;
    protected int position_y;
    protected int hunger=0;
    protected void die(){
        alive = false;
        Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.EMPTY);
    }
    protected boolean contains(int x, int y){ //sprawdzenie czy pozycja należy do planszy
        if(x<0 || x>= Simulation.get_size_x() || y<0 || y>= Simulation.get_size_y()) return false;
        else return true;
    }
    abstract void update();
    abstract void move();
    abstract void eat(Field eaten_field, int x, int y);
}
