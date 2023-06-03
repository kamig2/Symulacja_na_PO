package projekt;
public class Fish extends Agent{
    @Override
    void eat(Field eaten_field, int x,int y){
        if (eaten_field.get_has_tadpole())hunger-=50;
        for (Agent agent: Pond.get_agents()){//usuwanie zjedzonej kijanki
            if (agent instanceof Frog && agent.position_x==x && agent.position_y==y){
                Pond.set_agents(Pond.get_agents().indexOf(agent));
                break;
            }
        }
        if (hunger<0) hunger =0;
    }
    @Override
    void move(){
        int x,y,x1,y1,sign;
        y1=position_y;
        x1=position_x;
        do {
            do {
                sign=random.nextInt(2);
                if (sign==0) x = position_x + random.nextInt(2);
                else x = position_x - random.nextInt(2);
                sign=random.nextInt(2);
                if (sign==0) y = position_y+random.nextInt(2);
                else y = position_y - random.nextInt(2);
            }while (!contains(x,y));
        }while ((x==position_x && y==position_y )|| Pond.pond_array.get(y).get(x).get_has_fish()|| Pond.pond_array.get(y).get(x).get_has_plankton()||Pond.pond_array.get(y).get(x).get_has_frogspawn());
        if (Pond.pond_array.get(y).get(x).get_has_tadpole()) eat(Pond.pond_array.get(y).get(x),x,y);
        position_x=x;
        position_y=y;
        Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.FISH);
        Pond.pond_array.get(y1).get(x1).set_type(Field_type.EMPTY);
    }
    @Override
    void update(){
        //View view = new View(Simulation.getSize_x(),Simulation.getSize_y());
        hunger+=10;
        if(hunger==100) {
            die();
        }
        if (alive==true)move();
        //view.repaint();
    }
}
