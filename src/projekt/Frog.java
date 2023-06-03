package projekt;

public class Frog extends Agent  {

    private int age=0;
    enum Growth_stage{FROGSPAWN, TADPOLE, FROG}
    Growth_stage growth_stage = Growth_stage.FROGSPAWN;
    private void grow(){
        if(growth_stage==Growth_stage.FROGSPAWN) {
            growth_stage = Growth_stage.TADPOLE;
            age=0;
            Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.TADPOLE);
        }else if(growth_stage==Growth_stage.TADPOLE) {
            growth_stage = Growth_stage.FROG;
            Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.FROG);
        }
    }
    @Override
    protected void eat(Field eaten_field, int x, int y){
        if (growth_stage != Growth_stage.TADPOLE) return;
        if (eaten_field.get_has_tadpole()){
            hunger=0;
            for (Agent agent: Pond.get_agents()){//usuwanie zjedzonej kijanki
                if (agent instanceof Frog && agent.position_x==x && agent.position_y==y){
                    Pond.set_agents(Pond.get_agents().indexOf(agent));
                    break;
                }
            }
        }
        else if (eaten_field.get_has_plankton()) {
            hunger -= 50;
            //respawn_plankton();
        }
        if (hunger<0) hunger =0;

    }

    /*private void move(){
        if (growth_stage!=Growth_stage.TADPOLE) return;
        int x,y,sign,x1,y1;
        x1=position_x;
        y1=position_y;
        do {
            do {
                sign=random.nextInt(2);
                if (sign==0) {
                    x = position_x ++;
                }else {
                    x = position_x --;
                }
                sign=random.nextInt(2);
                if (sign==0){

                    y = position_y++;
                }else {
                    y = position_y --;
                }
            }while (!contains(x,y));//a ten warunek dałam żeby był wczesniej sprawdzony żeby przypadkiem nie podać do tablicy ujemnego indexu
        } while (Pond.pond_array.get(position_y).get(position_x).get_has_fish()  || (x==position_x && y==position_y) || Pond.pond_array.get(position_y).get(position_x).get_has_frogspawn());//dodałam warunek żeby kijanka nie mogła wejść na to samo pole co skrzek
        if (Pond.pond_array.get(y).get(x).get_has_plankton() || Pond.pond_array.get(y).get(x).get_has_tadpole()){ //edytowane
            eat(Pond.pond_array.get(y).get(x));
        }
        position_y=y;
        position_x=x;
        Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.TADPOLE);
        Pond.pond_array.get(y1).get(x1).set_type(Field_type.EMPTY);

*/
    @Override
    void move() {
        if (growth_stage != Growth_stage.TADPOLE) return;
        int x, y, sign, x1, y1;
        x1 = position_x;
        y1 = position_y;
        do {
            do {
                sign = random.nextInt(2);
                if (sign == 0) {
                    x = position_x + random.nextInt(2);
                } else {
                    x = position_x - random.nextInt(2);
                }
                sign = random.nextInt(2);
                if (sign == 0) {

                    y = position_y + random.nextInt(2);
                } else {
                    y = position_y - random.nextInt(2);
                }
            } while (!contains(x, y));//a ten warunek dałam żeby był wczesniej sprawdzony żeby przypadkiem nie podać do tablicy ujemnego indexu
        } while (Pond.pond_array.get(y).get(x).get_has_fish() || (x == position_x && y == position_y) || Pond.pond_array.get(y).get(x).get_has_frogspawn());//dodałam warunek żeby kijanka nie mogła wejść na to samo pole co skrzek
        if (Pond.pond_array.get(y).get(x).get_has_plankton() || Pond.pond_array.get(y).get(x).get_has_tadpole()) { //edytowane
            eat(Pond.pond_array.get(y).get(x), x, y);
        }
        position_y = y;
        position_x = x;
        Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.TADPOLE);
        Pond.pond_array.get(y1).get(x1).set_type(Field_type.EMPTY);
    }
    @Override
    void update() {
        //View view = new View(Simulation.getSize_x(),Simulation.getSize_y());
        age += 20;
        if (growth_stage == Growth_stage.TADPOLE) hunger += 40;
        if (hunger == 100 && growth_stage!=Growth_stage.FROG) {
            die();
        }

        if (age >= 50+ random.nextInt(40)) {//dodałam randomową liczbe żeby nie wszystko sie zamieniało w tym samym czasie
            grow();
            age = 0;
        }
        if (alive==true) move();

        //view.repaint();
    }
}
