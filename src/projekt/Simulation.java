package projekt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Simulation {
    static final int TIME_STEP = 1000;
    //static final int NUM_STEPS = 10;
    static int size_x = 800;
    static int size_y = 800;
    private int amount_fish = 20;
    private int amount_frogs = 10;
    private int amount_plankton = 10;
    private Pond pond;
//    private static ArrayList<Agent> agents = new ArrayList<>(); //i nie wiem co z tą listą agentów bo on chyba mówił zeby to było w klasie Simulation
                                                                 //ale jak i tak metody do tej listy są w Pondzie to chyba lepiej żeby tam było
    Random random = new Random();

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
        pond = new Pond(size_x, size_y, amount_fish, amount_frogs, amount_plankton);

    }

    void update_pond() {
        for (Agent agent: Pond.get_agents()){
            agent.update();
        }
        Pond.delete_agent();

    }
    void simulate(){//trzeba zmienić żeby update widoku wykonywał się po przejściu przez całą pętle a nie po każdym dodaniu obrazka
        int i=1,j,x=0; //tylko ja nie wiem jak to zrobić próbowałam ale mi sie nie udału może tobie się uda
        View view = new View(getSize_x(),getSize_y());
        do{
            i=1;
            long start_time = System.currentTimeMillis();
            for (ArrayList<Field> row : Pond.pond_array) {//jak skrzek sie zamienia w kijanke nie znika od razu
                j=1;
                for (Field field : row) {
                    if (field.get_has_fish()){
                        view.draw_agent("FISH.png",j,i,40,40);
                    }else if (field.get_has_tadpole()){
//                        view.remove_agent(j,i);
                        view.draw_agent("TADPOLE.png",j,i,40,40);
                    } else if (field.get_has_frogspawn()) {
                        view.draw_agent("FROGSPAWN.png",j,i,40,40);
                    }else if (field.get_has_plankton()){
                        view.draw_agent("PLANKTON.png",j,i,40,40);
                    }else if (field.get_has_frog()) {
                        view.remove_agent(j,i);
                        view.draw_agent("FROG.png", j, i, 40, 40);
                    }else {
                    }
                    if (field.get_is_empty()) view.remove_agent(j,i);
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
        }while (Pond.get_agents().size()>0);
        System.out.println("Liczba wygranych żab: " + Frog.frogs_number);
    }




    public static void main(String[] args) {

        Simulation simulation = new Simulation();
        simulation.simulate();



    }
}
