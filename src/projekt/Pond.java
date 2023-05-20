package projekt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Pond {
    int size_x;
    int size_y;
    Random random = new Random();
    static ArrayList<ArrayList<Field>> pond_array;
    static ArrayList<Fish> fish_array;
    static ArrayList<Frog> frogs_array;

    void create_fish_array(int amount_fish){
        fish_array = new ArrayList<>();
        for(int i = 0; i<amount_fish; i++){
            Fish fish = new Fish();
            fish_array.add(fish);
        }
    }
    void create_frogs_array(int amount_frogspawn){
        frogs_array = new ArrayList<>();
        for(int i = 0;i<amount_frogspawn;i++){
            Frog frog = new Frog();
            frogs_array.add(frog);
        }
    }
    void create_pond_array2D(int x, int y) {
        pond_array = new ArrayList<>(x);
        for (int row = 0; row < size_y; row++) {
            ArrayList<Field> array_row = new ArrayList<>(y);
            for (int col = 0; col < size_x; col++) {
                Field field = new Field("EMPTY");
                array_row.add(field);
            }
            pond_array.add(array_row);
        }
    }

    void place_fish(){ //Rozmieszczanie ryb na randomowej pozycji na planszy
        int x,y;
        for (Fish fish:fish_array){
            do {
                x = random.nextInt(size_x);
                y = random.nextInt(size_y);
            }while (!(pond_array.get(y).get(x).get_is_empty()));//Sprawdzanie czy pole jest puste jeśli nie losuje inne miejsce
            fish.position_x = x;
            fish.position_y= y;
            Field field = new Field("FISH");
            ArrayList<Field> array_row;
            array_row = pond_array.get(y);
            array_row.set(x,field); //zamiana typu pola na rybe
            pond_array.set(y,array_row);
        }
    }
    void place_frogspawn(){//Rozmieszczanie skrzeku na randomowe miejsca na planszy
        int x,y;
        for (Frog frogspawn : frogs_array){
            do {
                x = random.nextInt(size_x);
                y = random.nextInt(size_y);
            } while (!(pond_array.get(y).get(x).get_is_empty()));
            frogspawn.position_x = x;
            frogspawn.position_y= y;
            Field field = new Field("FROGSPAWN");
            ArrayList<Field> array_row;
            array_row = pond_array.get(y);
            array_row.set(x,field);   //zamiena typu pola na skrzek
            pond_array.set(y,array_row);
        }
    }
    void place_plankton(int amount_plankton){
        int x,y;
        for (int i=0;i<amount_plankton;i++){
            do {
                x = random.nextInt(size_x);
                y = random.nextInt(size_y);
            } while (!(pond_array.get(y).get(x).get_is_empty())); //Sprawdzanie czy pole jest puste jeśli nie losuje inne miejsce
            Field field = new Field("PLANKTON");
            ArrayList<Field> array_row;
            array_row = pond_array.get(y);
            array_row.set(x,field);   //zamiena typu pola na PLANKTON
            pond_array.set(y,array_row);
        }
    }
    static void delete_fish() {
        Iterator<Fish> iterator = fish_array.iterator(); //chat gpt zalecil zaimporowac jakas klase Iterator  i dziala juz więc najs
        while (iterator.hasNext()) {
            Fish fish = iterator.next();
            if (!fish.alive) {
                iterator.remove();
                Field field = new Field("EMPTY");
                ArrayList<Field> array_row = pond_array.get(fish.position_y);
                array_row.set(fish.position_x, field);
                pond_array.set(fish.position_y, array_row);
            }
        }
    }
    static void delete_frog() {
        Iterator<Frog> iterator = frogs_array.iterator(); //chat gpt zalecil zaimporowac jakas klase Iterator  i dziala juz więc najs
        while (iterator.hasNext()) {
            Frog frog = iterator.next();
            if (!frog.alive) {
                iterator.remove();
                Field field = new Field("EMPTY");
                ArrayList<Field> array_row = pond_array.get(frog.position_y);
                array_row.set(frog.position_x, field);
                pond_array.set(frog.position_y, array_row);
            }
        }
    }

    //metode update_pond dałam do klasy Simulaton bo tak było na diagramie klas

    Pond(int x, int y, int amount_fish, int amount_frogspawn, int amount_plankton){ // konstruktor
        size_x = x;
        size_y = y;
        create_pond_array2D(size_x, size_y);
        create_fish_array(amount_fish);
        create_frogs_array(amount_frogspawn);
        place_fish();
        place_frogspawn();
        place_plankton(amount_plankton);
    }
}


