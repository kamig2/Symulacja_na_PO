package projekt;
import java.util.ArrayList;
import java.util.Random;

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
            Field field = new Field("TADPOLE");
            ArrayList<Field> array_row;
            array_row = Pond.pond_array.get(position_y);
            array_row.set(position_x,field);
            Pond.pond_array.set(position_y,array_row);
        }else if(growth_stage==Growth_stage.TADPOLE) {
            growth_stage = Growth_stage.FROG;
            win=true;
            frogs_number++;
            Field field = new Field("FROG");//Zmiana typu pola na FROG tylko nie wiem jak to chcemy zrobić żę te żaby będą dalej widoczne w stawie czy nie
            ArrayList<Field> array_row;
            array_row = Pond.pond_array.get(position_y);
            array_row.set(position_x,field);
            Pond.pond_array.set(position_y,array_row);
        }
    }

    void respawn_plankton(){ // funkcja odradzania się planktonu po zjedzeniu
        int x,y;
        do {
            x = random.nextInt(Simulation.getSize_x());
            y = random.nextInt(Simulation.getSize_y());
        } while (!(Pond.pond_array.get(y).get(x).get_is_empty())); //Sprawdzanie czy pole jest puste jeśli nie losuje inne miejsce
        Field field = new Field("PLANKTON");
        ArrayList<Field> array_row;
        array_row = Pond.pond_array.get(y);
        array_row.set(x,field);   //zamiena typu pola na PLANKTON
        Pond.pond_array.set(y,array_row);
    }
    private void eat(Field eaten_field){ // rodzaj jedzonego pola jako parametr zeby wiedziec o ile ma sie zmniejszyc glod
        if (growth_stage != Growth_stage.TADPOLE) return; // żaba je tylko wtedy gdy jest kijanką
        if (eaten_field.get_has_tadpole()) hunger=0; // głód się zeruje po zjedzeniu kijanki
        else if (eaten_field.get_has_plankton()) {
            hunger -= 50; // głód spada o połowe po zjedzeniu planktonu
            respawn_plankton();
        }
        if (hunger<0) hunger =0; // żeby głód nie mógł być na minusie
    }

    private void move(){
        if (growth_stage!=Growth_stage.TADPOLE) return;
        int x,y,sign,x1,y1;
        x1=position_x;
        y1=position_y;
        do {
            do {
                sign=random.nextInt(2);//dobra to wygląda troche dziwnie ale nie wiedziałam jak to inaczej zrobić żeby działało
                if (sign==0) {            //ale ta zmienna sign jest po to aby wylosować czy mamodjąć od aktualnej pozycji 1 lub 0 czy dodać 1 lub 0
                    x = position_x + random.nextInt(100);//bo chodzi o to żeby kijanka mogła sie przemieścić tylko na sąsiadujące pole
                }else {
                    x = position_x - random.nextInt(100);
                }
                sign=random.nextInt(2);
                if (sign==0){

                    y = position_y+random.nextInt(100);
                }else {
                    y = position_y - random.nextInt(100);
                }
            }while (!contains(x,y));//a ten warunek dałam żeby był wczesniej sprawdzony żeby przypadkiem nie podać do tablicy ujemnego indexu
        } while (Pond.pond_array.get(position_y).get(position_x).get_has_fish()  || (x==position_x && y==position_y) || Pond.pond_array.get(position_y).get(position_x).get_has_frogspawn());//dodałam warunek żeby kijanka nie mogła wejść na to samo pole co skrzek
        position_x=x;
        position_y=y;
        if (Pond.pond_array.get(position_y).get(position_x).get_has_plankton() || Pond.pond_array.get(position_y).get(position_x).getType().equals("TADPOLE")){ //edytowane
            eat(Pond.pond_array.get(position_y).get(position_x));
        }
        Field field = new Field("TADPOLE");
        ArrayList<Field> array_row;
        array_row = Pond.pond_array.get(position_y);
        array_row.set(position_x,field);
        Pond.pond_array.set(position_y,array_row);
        Field field1 = new Field("EMPTY"); //zamiana pola na którym była kijanka wcześniej na empty
        ArrayList<Field> array_row1;
        array_row1 = Pond.pond_array.get(y1);
        array_row1.set(x1,field1);
        Pond.pond_array.set(y1,array_row1);
    }

    void update() {
        age += 20;
        hunger += 10;
        if (hunger == 100) die();
        if (age >= 30+ random.nextInt(10)) {//dodałam randomową liczbe żeby nie wszystko sie zamieniało w tym samym czasie
            grow();
            age = 0;
        }
        move();
    }
}
