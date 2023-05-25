package projekt;

import java.util.ArrayList;

public class Fish extends Agent{

    void eat(Field eaten_field, int x,int y){
        if (eaten_field.get_has_tadpole()) hunger-=30;
        if (hunger<0) hunger =0;
        for (Agent agent :Pond.get_agents()){//usuwanie zjedzonej kijanki
            if (agent instanceof Frog && agent.position_x==x && agent.position_y==y){
                Pond.set_agents(Pond.get_agents().indexOf(agent));
            }
        }
    }

    void move(){
        int x,y,x1,y1,sign;
        int[] position = new int[2];
        y1=position_y;
        x1=position_x;
        do {
            do {
                sign=random.nextInt(2);
                if (sign==0) x = position_x + random.nextInt(20);
                else x = position_x - random.nextInt(20);
                sign=random.nextInt(2);
                if (sign==0) y = position_y+random.nextInt(20);
                else y = position_y - random.nextInt(20);
            }while (!contains(x,y));
        }while ((x==position_x && y==position_y )|| Pond.pond_array.get(y).get(x).get_has_fish()|| Pond.pond_array.get(y).get(x).get_has_plankton()||Pond.pond_array.get(y).get(x).get_has_frogspawn());
        position_x=x;
        position_y=y;
        if (Pond.pond_array.get(position_y).get(position_x).get_has_tadpole()) eat(Pond.pond_array.get(position_y).get(position_x),position_x,position_y);
        Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.FISH);
        Pond.pond_array.get(y1).get(x1).set_type(Field_type.EMPTY);
    }
    void update(){
        hunger+=20;
        if(hunger==100) die();
        move();
    }
}
