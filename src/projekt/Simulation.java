package projekt;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    static int size_x=10;
    static int size_y=10;
    private int amount_frogs;
    private int amount_fish=6;
    private int amount_frogspawn=7;
    private int amount_tadpole;
    private int amount_plankton=5;
    private Pond pond;
    private List<Frog> frogs;
    private List<Fish> fish;

    public static int getSize_x(){
        return size_x;
    }
    public static int getSize_y(){
        return size_y;
    }
    public Simulation(){
        pond = new Pond(size_x, size_y, amount_fish,amount_frogspawn,amount_plankton);
        frogs = new ArrayList<>();
        fish = new ArrayList<>();

    }
    void update_pond(){
        for(Frog frog:Pond.frogspawn_array) frog.update();
        for (Fish fish:Pond.fish_array) fish.update();
        Pond.delete_fish(); //przy każdym update będzie sprawdzane które ryby i żaby nie żyją i nie żywe będą usuwane
        Pond.delete_frog();
    }

    void simulate(){//to na razie tylko taka pomocnicza metoda żeby widzieć jak działaja funkcja przemieszczania ryby
        int x=0;
        while (x<5){
            update_pond();
            for (ArrayList<Field> row : Pond.pond_array) {
                for (Field field : row) {
                    System.out.print("["+field.getType()+"]"); //wyświetla typ pola
                }
                System.out.println();
            }
            System.out.println();
            x++;
        }
    }

    public static void main(String[] args) { //wyswietlanie planszy jak narazie wstępne żeby widziec czy dziala
        Simulation simulation = new Simulation();
        for (ArrayList<Field> row : Pond.pond_array) {
            for (Field field : row) {
                System.out.print("["+field.getType()+"]"); //wyświetla typ pola
            }
            System.out.println();
        }
        System.out.println();
        simulation.simulate();
    }
}
