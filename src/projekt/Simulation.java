package projekt;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    static final int TIME_STEP = 1000;
    //static final int NUM_STEPS = 10;
    static int size_x=500;
    static int size_y=500;
    private int amount_frogs;
    private int amount_fish=0;
    private int amount_frogspawn=40;
    private int amount_tadpole;
    private int amount_plankton=0;
    private Pond pond;
    private View view;
    private List<Frog> frogs;
    private List<Fish> fish;

    public static int getSize_x(){
        return size_x;
    }
    public static int getSize_y(){
        return size_y;
    }
    public Pond getPond() {return pond;}
    public Simulation(){
        pond = new Pond(size_x, size_y, amount_fish,amount_frogspawn,amount_plankton);
        frogs = new ArrayList<>();
        fish = new ArrayList<>();
    }
    void update_pond(){
        for(Frog frog:Pond.frogs_array) frog.update();
        for (Fish fish:Pond.fish_array) fish.update();
        Pond.delete_fish(); //przy każdym update będzie sprawdzane które ryby i żaby nie żyją i nie żywe będą usuwane
        Pond.delete_frog();
    }


    void simulate(){
        int x=0;
        boolean play= true;
        do{
            long start_time = System.currentTimeMillis();//dodałam czas w milisekundach tylko on nie liczy trwania całej symulacji
            for (ArrayList<Field> row : Pond.pond_array) {//nie wiem czy taki czas który będzie liczył ila trwa cała symulacja jest potrzebny?
                for (Field field : row) {
                    System.out.print("["+field.getType()+"]"); //wyświetla typ pola
                }
                System.out.println();
            }
            System.out.println();
            update_pond();
            long elapsed_time = System.currentTimeMillis() - start_time;
            long sleep_time = TIME_STEP - elapsed_time; //TIME_STEP ustawiłam na razie na jedną sekunde czyli chyba update jest co 1s
            try {
                Thread.sleep(sleep_time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            x++;
            //view.updateView(Pond.pond_array);
        }while (x<20);//na razie dałam taki warunek żeby sprawdzić czy działa w ogóle
        System.out.println("Liczba wygranych żab: " + Frog.frogs_number);
    }

    public static void main(String[] args) { //wyswietlanie planszy jak narazie wstępne żeby widziec czy dziala

        //Simulation simulation = new Simulation();
        View view = new View(getSize_x(),getSize_y());

        //simulation.simulate();


    }
}
