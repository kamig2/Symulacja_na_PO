import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Pond {
    private int size_x;
    private int size_y;

    static ArrayList<ArrayList<Field>> pond_array;
    ArrayList<Fish> fish_array;
    ArrayList<Frog> frog_array;
    void create_fish_array(int amount_fish){  // tworzenie tablicy przechowującej ryby trzeba zrobic jeszcze taką dla zab, planktonu itp
        fish_array = new ArrayList<>();
        for(int i = 0; i<amount_fish; i++){
            Fish fish = new Fish();
            fish_array.add(fish);
        }
    }
    void create_pond_array2D(int x, int y) { //tworzenie tablicy dwuwymiarowej jak narazie pustej czyli staw
        pond_array = new ArrayList<>(x);
        for (int row = 0; row < size_y; row++) {
            ArrayList<Field> array_row = new ArrayList<>(y);
            for (int col = 0; col < size_x; col++) {
                Field field = new Field();
                array_row.add(field);
            }
            pond_array.add(array_row);
        }
    }

    Pond(int x, int y, int amount_fish){ // konstruktor
        size_x = x;
        size_y = y;
        create_pond_array2D(size_x, size_y);
        create_fish_array(amount_fish);
    }
}


