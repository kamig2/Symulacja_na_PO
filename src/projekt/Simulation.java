package projekt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Simulation {
    static final int TIME_STEP = 1000;
    static int size_x = 15;
    static int size_y = 15;
    private int amount_fish = 15;
    private int amount_frogs = 15;
    private int amount_plankton = 15;
    private Pond pond;
    private View view;
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
    public Simulation(){
        pond = new Pond(size_x, size_y, amount_fish, amount_frogs, amount_plankton);
        view = new View(size_x*40,size_y*40);
    }
    void update_pond() {
        for (Agent agent: Pond.get_agents()){
            agent.update();
        }
        Pond.delete_agent();
    }

    void simulate() {
        int i = 1, j, x = 0;
        do {
            long start_time = System.currentTimeMillis();
            update_pond();  // Aktualizacja stanu stawu
            long elapsed_time = System.currentTimeMillis() - start_time;
            long sleep_time = TIME_STEP - elapsed_time;
            if (sleep_time < 0) sleep_time = 0;

            try {
                Thread.sleep(sleep_time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            x++;
            System.out.println("Liczba żab: " + amount_frogs);
            System.out.println("Liczba ryb: " + amount_fish);
            System.out.println("Liczba ryb: " + amount_plankton);
            view.getContentPane().removeAll();
            view.update_view(pond);
            view.revalidate();
            view.repaint();
        } while (x < 10);
        System.out.println("Liczba wygranych żab: " + Frog.frogs_number);
    }
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.simulate();
    }
}
