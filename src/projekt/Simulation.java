package projekt;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    static final int TIME_STEP = 1000;
    //static final int NUM_STEPS = 10;
    static int size_x = 800;
    static int size_y = 800;
    private int amount_fish = 100;
    private int amount_frogs = 60;
    private int amount_plankton = 100;
    private Pond pond;
    private ArrayList<Agent> agents;

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
        //frogs = new ArrayList<>();
        //fish = new ArrayList<>();
    }

    void update_pond() {
        for (Frog frog : Pond.frogs_array) frog.update();
        for (Fish fish : Pond.fish_array) fish.update();
        Pond.delete_fish();
        Pond.delete_frog();
    }


    void simulate(){
        int i=1,j,x=0;
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
                        view.draw_agent("FROGSPAWN.png",j,i,40,40);
                    }else if (field.get_has_plankton()){
                        view.draw_agent("PLANKTON.png",j,i,40,40);
                    }else if (field.get_has_frog()) {
                        view.remove_agent(j,i);
                        view.draw_agent("FROG.png", j, i, 40, 40);
                    }else {
                        view.remove_agent(j,i);
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
        }while (Pond.fish_array.size()>0 || Pond.fish_array.size()>0);
        System.out.println("Liczba wygranych żab: " + Frog.frogs_number);
    }




    public static void main(String[] args) {

        Simulation simulation = new Simulation();
        simulation.simulate();



    }
}
