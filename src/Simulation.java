import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private int size_x=5;
    private int size_y=7;
    private int amount_frogs;
    private int amount_fish=6;
    private int amount_frogspawn=7;
    private int amount_tadpole;
    private int amount_plankton=5;
    private Pond pond;
    private List<Frog> frogs;
    private List<Fish> fish;

    public Simulation(){
        pond = new Pond(size_x, size_y, amount_fish,amount_frogspawn,amount_plankton);
        frogs = new ArrayList<>();
        fish = new ArrayList<>();

    }
    public static void main(String[] args) { //wyswietlanie planszy jak narazie wstępne żeby widziec czy dziala
        Simulation simulation = new Simulation();
        for (ArrayList<Field> row : Pond.pond_array) {
            for (Field field : row) {
                System.out.print("["+field.getType()+"]"); //wyświetla typ pola
            }
            System.out.println();
        }
    }
    }
