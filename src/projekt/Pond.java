package projekt;
import java.util.ArrayList;
import java.util.Random;

public class Pond {
    int size_x;
    int size_y;
    Random random = new Random();
    static ArrayList<ArrayList<Field>> pond_array;
    static ArrayList<Fish> fish_array;
    static ArrayList<Frog> frogs_array;//zmieniłam nazwe na frogs_aray bo wsumie odnosi sie to do każdego stadium żaby

    void create_fish_array(int amount_fish){  // tworzenie tablicy przechowującej ryby trzeba zrobic jeszcze taką dla zab, planktonu itp
        fish_array = new ArrayList<>();
        for(int i = 0; i<amount_fish; i++){
            Fish fish = new Fish();
            fish_array.add(fish);
        }
    }
    void create_frogs_array(int amount_frogspawn){ //tworzenie tablicy przechowującej skrzek tylko utworzyłam tu obiekt na klasie Frog
        frogs_array = new ArrayList<>();           //bo wsumie faktycznie chyba łatwiej będzie zrobić tylko klase Frog
        for(int i = 0;i<amount_frogspawn;i++){
            Frog frog = new Frog();
            frogs_array.add(frog);
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
            } while (!(pond_array.get(y).get(x).get_is_empty())); //zmieniłam że zamiast sprawdzać czy typ pola to EMPTY to sprawdzam to za pomocą tego boolean
            frogspawn.position_x = x;                             //lepiej to wyhygląda i jak będziemy jeszcze potrzebować sprawdzić typ pola to łatwiej będzie
            frogspawn.position_y= y;
            Field field = new Field("FROGSPAWN");           //jeszcze sie zastanawiam jak można zrobić łatwiej zamiane typu pola ale na razie nie mam pomysłu
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

    static void delete_fish(){
        for (Fish fish:fish_array){
            if (!fish.alive){//nie wiem o co chodzi ale coś jest nie tak z tą metodą bo wyrzuca błąd
                fish_array.remove(fish);
                Field field = new Field("EMPTY"); //zamiana typu pola na empty
                ArrayList<Field> array_row;
                array_row = pond_array.get(fish.position_y);
                array_row.set(fish.position_x,field);
                pond_array.set(fish.position_y,array_row);
            }
        }
    }
    static void delete_frog(){
        for (Frog frog: frogs_array){
            if (!frog.alive){
                frogs_array.remove(frog);
                Field field = new Field("EMPTY"); //zamiana typu pola na empty
                ArrayList<Field> array_row;
                array_row = pond_array.get(frog.position_y);
                array_row.set(frog.position_x,field);
                pond_array.set(frog.position_y,array_row);
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


