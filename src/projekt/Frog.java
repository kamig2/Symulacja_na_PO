package projekt;
import java.util.ArrayList;
import java.util.Random;

public class Frog extends Agent  {

    private int age; //dodałam pole wiek będzie określać w jakim wieku jest żaba w sensie czy juz może rosnąć czy jeszcze nie
    boolean win = false;//dodqałam pole żeby zmienić je na tru jak kijanka stanie sie żabą wtedy ta żaba wygrywa
    static int frogs_number=0;//dodałam pole żaby zliczało wygrane żaby
    enum Growth_stage{FROGSPAWN, TADPOLE, FROG};
    Growth_stage growth_stage;
    private void grow(){
        if(growth_stage==Growth_stage.FROGSPAWN) {
            growth_stage = Growth_stage.TADPOLE;
            age=0;
            //trzeba zmienić nazwe pola na planczy TEDPOLE
        }
        if(growth_stage==Growth_stage.TADPOLE) {
            growth_stage = Growth_stage.FROG;
            win=true;
            frogs_number++;
        }
    }
    //nie wiem czy tą funkcje z planktonem nie lepiej dać do innej klasy albo do field albo do pond bo wsumie tu tak troche nie pasuje ale to można sie zastanowić
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
        int x,y;
        do {
            x = random.nextInt(position_x + 1 - (position_x - 1) + 1);
            y = random.nextInt(position_y + 1 - (position_x - 1) + 1);
        } while (Pond.pond_array.get(position_y).get(position_x).get_has_fish() || (x==position_x && y==position_y) || !contains(x, y));
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
    }
    //tą metode contains dałam do klasy agent bo będzie też potrzebna w klasie Fish

    void update(){ //trzeba porobic te updaty ale juz mi sie dzisiaj nie chce wiec ogarne to jutro albo jak bys cos pisala to mozesz pokminic
        if(hunger==100) die(); // tylko nw dokonca jak to zrobic czy update do kazdej klasy osobny czy w agencie czy jak
        if (age==100) grow(); //dodałam warunek wieku żeby skrzek mógł rosnąć w kijanke a kijanka w żabe
        move();
    }

}
