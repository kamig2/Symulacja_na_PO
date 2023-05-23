package projekt;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    static final int TIME_STEP = 1000;
    //static final int NUM_STEPS = 10;
    static int size_x = 800;
    static int size_y = 800;
    private int amount_frogs;
    private int amount_fish = 20;
    private int amount_frogspawn = 40;
    private int amount_tadpole;
    private int amount_plankton = 5;
    private Pond pond;
    private View view;
    private List<Frog> frogs;
    private List<Fish> fish;

    public static int getSize_x() {
        return size_x;
    }

    public static int getSize_y() {
        return size_y;
    }

    public Pond getPond() {
        return pond;
    }

    public Simulation() {
        pond = new Pond(size_x, size_y, amount_fish, amount_frogspawn, amount_plankton);
        frogs = new ArrayList<>();
        fish = new ArrayList<>();
    }

    void update_pond() {
        for (Frog frog : Pond.frogs_array) frog.update();
        for (Fish fish : Pond.fish_array) fish.update();
        Pond.delete_fish(); //przy każdym update będzie sprawdzane które ryby i żaby nie żyją i nie żywe będą usuwane
        Pond.delete_frog();
    }


    void simulate(){//kurwa nie wiem jak to zrobiś żęby to dobrze działałao już prubowałam na różne sposoby ale nie wychodzi
        int i=1,j,x=0;//cały czas jest tak że pojawiają sie nowe obrazki ale stare nie znikają narazie zostawiam to tak
        View view = new View(getSize_x(),getSize_y());
        do{
            i=1;
            long start_time = System.currentTimeMillis();
            for (ArrayList<Field> row : Pond.pond_array) {
                j=1;
                for (Field field : row) {
                    if (field.get_has_fish()){
                        view.draw_agent("FISH.png",j,i,40,40);
                    }else if (field.get_has_tadpole()){
                        view.draw_agent("TADPOLE.png",j,i,40,40);
                    } else if (field.get_has_frogspawn()) {
                        view.draw_agent("FROGSPAWN.jpg",j,i,40,40);
                    }else if (field.get_has_plankton()){
                        view.draw_agent("PLANKTON.png",j,i,40,40);
                    }else if (field.get_has_frog()) {
                        view.draw_agent("FROG.png", j, i, 40, 40);
                    }
                    j++;
                }
                i++;
            }
            update_pond();
            long elapsed_time = System.currentTimeMillis() - start_time;
            long sleep_time = TIME_STEP - elapsed_time;
            if (sleep_time<0) sleep_time=0;
            try {
                Thread.sleep(sleep_time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            x++;
            //view.updateView(Pond.pond_array);
        }while (x<30);
        System.out.println("Liczba wygranych żab: " + Frog.frogs_number);
    }




    public static void main(String[] args) { //wyswietlanie planszy jak narazie wstępne żeby widziec czy dziala

        Simulation simulation = new Simulation();
        simulation.simulate();

        /*View view = new View(getSize_x(),getSize_y());
        view.draw_agent("FISH.png",100,100,40,30);
        view.draw_agent("FROG.png",100,200,40,40);
        view.draw_agent("TADPOLE.png",100,300,60,30);
        view.draw_agent("PLANKTON.png",1,400,50,50);
*/
        //String workingDirectory = System.getProperty("user.dir");
        //System.out.println("Working Directory: " + workingDirectory);

    }
}
