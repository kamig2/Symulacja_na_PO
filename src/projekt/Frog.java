package projekt;

public class Frog extends Agent  {

    private int age=0;
    enum Growth_stage{FROGSPAWN, TADPOLE, FROG}
    Growth_stage growth_stage = Growth_stage.FROGSPAWN;
    private void grow(){//metoda wzrostu żaby w kolejne stadium wzrostu
        if(growth_stage==Growth_stage.FROGSPAWN) {//sprawdzanie czy stadium wzrostu to skrzek
            growth_stage = Growth_stage.TADPOLE;//zamiana stadium wzrostu na kijanka
            age=0;
            Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.TADPOLE);//zamiana typu pola na kijanka
        }else if(growth_stage==Growth_stage.TADPOLE) {//sprawdzanie czy stadium wzrostu to kijanka
            growth_stage = Growth_stage.FROG;//zamiana stadium wzrostu na żaba
            Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.FROG);//zamiana typu pola na żaba
        }
    }
    @Override
    protected void eat(Field eaten_field, int x, int y){//metoda jedzenia
        if (growth_stage != Growth_stage.TADPOLE) return;
        if (eaten_field.get_has_tadpole()){//sprawdzanie czy na jedzonym polu znajduje się kijanka
            hunger=0;
            Simulation.set_amount_frogs();
            for (Agent agent: Pond.get_agents()){//usuwanie zjedzonej kijanki
                if (agent instanceof Frog && agent.position_x==x && agent.position_y==y){
                    System.out.println("kijanja zjada kijanke");
                    Pond.set_agents(Pond.get_agents().indexOf(agent));
                    break;
                }
            }
        }
        else if (eaten_field.get_has_plankton()) {//sprawdzanie czy na jedzonym polu jest plankton
            hunger -= 50;
            System.out.println("kijanka je plankton");
            //respawn_plankton();
        }
        if (hunger<0) hunger =0;//ustawienie wartości głodu na 0 gdy jest liczbą ujemną

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
    void move() {//metoda poruszania po planszy
        if (growth_stage != Growth_stage.TADPOLE) return;//jeżeli stadium wzrostu żaby nie jest kijanka żaba nie może się poruszać
        int x, y, sign, x1, y1;
        x1 = position_x;
        y1 = position_y;
        do {
            do {
                sign = random.nextInt(2);//losowanie liczby 0 lub 1, jeśli 0 dodajemy do aktualnej pozycji 1, jeśli 1 to odejmujemy od aktualnej pozycji 1
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
            } while (!contains(x, y));//sprawdznie czy wylosowane pozycje x i y nie są liczbami ujemnymi
        } while (Pond.pond_array.get(y).get(x).get_has_fish() || (x == position_x && y == position_y) || Pond.pond_array.get(y).get(x).get_has_frogspawn());//sprawdznie czy na wylosowanej pozycji nie znajduje się ryba lub skrzek oraz sprawdznie czy nowa pozycja niejest równa starej
        if (Pond.pond_array.get(y).get(x).get_has_plankton() || Pond.pond_array.get(y).get(x).get_has_tadpole()) { //sprawdznie czy na wylosowanej pozycji jest plankton lub kijanka
            eat(Pond.pond_array.get(y).get(x), x, y);//zjadanie
        }
        position_y = y;//zmiana pozycji na nową
        position_x = x;
        Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.TADPOLE);//zmiana typu pola nowej pozycji na kijanka
        Pond.pond_array.get(y1).get(x1).set_type(Field_type.EMPTY);//zmiana typu pola starej pozycji na pusty
    }
    @Override
    void update() {//zaktualizowanie wieku,głodu,pozycji,stadium wzrostu
        //View view = new View(Simulation.getSize_x(),Simulation.getSize_y());
        age += 20;
        if (growth_stage == Growth_stage.TADPOLE) hunger += 40;
        if (hunger == 100 && growth_stage!=Growth_stage.FROG) {
            die();
            Simulation.set_amount_frogs();
        }

        if (age >= 50+ random.nextInt(40)) {//dodałam randomową liczbe żeby nie wszystko sie zamieniało w tym samym czasie
            grow();
            age = 0;
        }
        if (alive==true) move();

        //view.repaint();
    }
}
