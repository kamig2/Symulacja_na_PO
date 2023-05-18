import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private int size_x=5;
    private int size_y=7;
    private int amount_frogs;
    private int amount_fish;
    private int amount_frogspawn;
    private int amount_tadpole;
    private Pond pond;
    private List<Frog> frogs;
    private List<Fish> fish;

    public Simulation(){
        pond = new Pond(size_x, size_y, amount_fish);
        frogs = new ArrayList<>();
        fish = new ArrayList<>();

    }
    public static void main(String[] args) { //wyswietlanie planszy jak narazie wstępne żeby widziec czy dziala
        Simulation simulation = new Simulation();
        for (ArrayList<Field> row : Pond.pond_array) {
            for (Field field : row) {
                System.out.print("[]");
            }
            System.out.println();
        }
    }
    }
