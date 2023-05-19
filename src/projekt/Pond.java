package projekt;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Pond {
    int size_x;
    int size_y;
    Random random = new Random();
    static ArrayList<ArrayList<Field>> pond_array;
    ArrayList<Fish> fish_array;
    ArrayList<Frog> frogspawn_array;

    void create_fish_array(int amount_fish){  // tworzenie tablicy przechowującej ryby trzeba zrobic jeszcze taką dla zab, planktonu itp
        fish_array = new ArrayList<>();
        for(int i = 0; i<amount_fish; i++){
            Fish fish = new Fish();
            fish_array.add(fish);
        }
    }
    void create_frogspawn_array(int amount_frogspawn){ //tworzenie tablicy przechowującej skrzek tylko utworzyłam tu obiekt na klasie Frog
        frogspawn_array = new ArrayList<>();           //bo wsumie faktycznie chyba łatwiej będzie zrobić tylko klase Frog
        for(int i = 0;i<amount_frogspawn;i++){
            Frog frog = new Frog();
            frogspawn_array.add(frog);
        }
    }
    //nie utworzyłam metody do tworzenia listy dla planktonu bo nie mamy klasy plankton i nw jak by miała wyglądać ta lista
    void create_pond_array2D(int x, int y) { //tworzenie tablicy dwuwymiarowej jak narazie pustej czyli staw
        pond_array = new ArrayList<>(x);
        for (int row = 0; row < size_y; row++) {
            ArrayList<Field> array_row = new ArrayList<>(y);
            for (int col = 0; col < size_x; col++) {
                Field field = new Field("EMPTY"); //Dodałam typ pola
                array_row.add(field);
            }
            pond_array.add(array_row);
        }
    }

    void place_fish(){ //Rozmieszczanie ryb na randomowej pozycji na planszy
        int x,y;
        for (Fish fish:fish_array){
            x= random.nextInt(size_x);
            y= random.nextInt(size_y);
            while (!(pond_array.get(y).get(x).getType().equals("EMPTY"))){ //Sprawdzanie czy pole jest puste jeśli nie losuje inne miejsce
                x = random.nextInt(size_x);
                y = random.nextInt(size_y);
            }
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
        for (Frog frogspawn : frogspawn_array){
            do {
                x = random.nextInt(size_x);
                y = random.nextInt(size_y);
            } while (!(pond_array.get(y).get(x).getType().equals("EMPTY"))); //Sprawdzanie czy pole jest puste jeśli nie losuje inne miejsce
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
            } while (!(pond_array.get(y).get(x).getType().equals("EMPTY"))); //Sprawdzanie czy pole jest puste jeśli nie losuje inne miejsce
            Field field = new Field("PLANKTON");
            ArrayList<Field> array_row;
            array_row = pond_array.get(y);
            array_row.set(x,field);   //zamiena typu pola na PLANKTON
            pond_array.set(y,array_row);
        }
    }

    void delete_fish(){//usuwanie ryby z planszy jeśli umrze
        for (Fish fish:fish_array){
            if (!fish.alive){
                fish_array.remove(fish); // usuwanie ryby z listy
                Field field = new Field("EMPTY"); //zamiana typu pola na empty
                ArrayList<Field> array_row;
                array_row = pond_array.get(fish.position_x);
                array_row.set(fish.position_x,field);
                pond_array.set(fish.position_y,array_row);
            }
        }
    }
    void delete_frog(){ //usuwanie żaby z planszy jeśli umrze
        for (Frog frog:frogspawn_array){
            if (!frog.alive){
                frogspawn_array.remove(frog); // usuwanie ryby z listy
                Field field = new Field("EMPTY"); //zamiana typu pola na empty
                ArrayList<Field> array_row;
                array_row = pond_array.get(frog.position_x);
                array_row.set(frog.position_x,field);
                pond_array.set(frog.position_y,array_row);
            }
        }
    }

    void update_pond(){
        for(Frog frog:frogspawn_array) frog.update_frog();
    }

    Pond(int x, int y, int amount_fish, int amount_frogspawn, int amount_plankton){ // konstruktor
        size_x = x;
        size_y = y;
        create_pond_array2D(size_x, size_y);
        create_fish_array(amount_fish);
        create_frogspawn_array(amount_frogspawn);
        place_fish();
        place_frogspawn();
        place_plankton(amount_plankton);
    }
}


