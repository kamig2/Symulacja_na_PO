package projekt;
import java.util.Random;

abstract public class Agent {
    Random random= new Random();
    boolean alive;
    int position_x;
    int position_y;
    int hunger;

    void die(){
        alive = false;
    }
    void update(){
        if(hunger==100) die();
    }
}
