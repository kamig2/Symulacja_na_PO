package projekt;
import java.util.Random;

abstract public class Agent {
    Random random= new Random();
    boolean alive = true;
    int position_x;
    int position_y;
    int hunger;

    void die(){
        alive = false;
    }

    boolean contains(int x, int y){//zmieniłam && na || bo tak chyba powinno być
        if(x<0 || x>= Simulation.getSize_x() || y<0 || y>= Simulation.getSize_y()) return false;
        else return true;
    }
    abstract void update();
}
