package projekt;

public class Frog extends Agent  {

    private int age=0;
    boolean win = false;
    static int frogs_number=0;
    enum Growth_stage{FROGSPAWN, TADPOLE, FROG};
    Growth_stage growth_stage = Growth_stage.FROGSPAWN;
    private void grow(){
        if(growth_stage==Growth_stage.FROGSPAWN) {
            growth_stage = Growth_stage.TADPOLE;
            age=0;
            Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.TADPOLE);
        }else if(growth_stage==Growth_stage.TADPOLE) {
            growth_stage = Growth_stage.FROG;
            win=true;
            frogs_number++;
            Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.FROG);
        }
    }

   /* void respawn_plankton(){ // funkcja odradzania się planktonu po zjedzeniu
        int x,y;
        do {
            x = random.nextInt(Simulation.getSize_x());
            y = random.nextInt(Simulation.getSize_y());
        } while (!(Pond.pond_array.get(y).get(x).get_is_empty())); //Sprawdzanie czy pole jest puste jeśli nie losuje inne miejsc
        Field field = new Field("PLANKTON");
        ArrayList<Field> array_row;
        array_row = Pond.pond_array.get(y);
        array_row.set(x,field);   //zamiena typu pola na PLANKTON
        Pond.pond_array.set(y,array_row);
    }*/

    protected void eat(Field eaten_field){
        if (growth_stage != Growth_stage.TADPOLE) return;
        if (eaten_field.get_has_tadpole()) hunger=0; //trzeba dodać usuwanie zjedzonej kijanki z listy agentów metoda jhak w fish
        else if (eaten_field.get_has_plankton()) {
            hunger -= 50;
            //respawn_plankton();
        }
        if (hunger<0) hunger =0;
    }

    private void move(){
        if (growth_stage!=Growth_stage.TADPOLE) return;
        int x,y,sign,x1,y1;
        x1=position_x;
        y1=position_y;
        do {
            do {
                sign=random.nextInt(2);
                if (sign==0) {
                    x = position_x + random.nextInt(10);
                }else {
                    x = position_x - random.nextInt(10);
                }
                sign=random.nextInt(2);
                if (sign==0){

                    y = position_y+random.nextInt(10);
                }else {
                    y = position_y - random.nextInt(10);
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

    }
    @Override
    void update() {
        age += 20;
        if (growth_stage != Growth_stage.FROGSPAWN) hunger += 20;
        if (hunger == 100 && growth_stage!=Growth_stage.FROG) die();//dodałam warunek że żąba nie może umrzeć
        if (age >= 50+ random.nextInt(40)) {//dodałam randomową liczbe żeby nie wszystko sie zamieniało w tym samym czasie
            grow();
            age = 0;
        }
        move();
    }
}
