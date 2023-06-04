package projekt;
import javax.swing.*;

public class Simulation {
    static final int TIME_STEP = 1000;
    static int size_x = 20;
    static int size_y = 20;
    private static int amount_fish = 5;
    private static int amount_frogs = 5;
    private static int amount_plankton = 5;
    private final View view;

    public static int set_amount_fish(){
        return amount_fish--;
    }
    public static int set_amount_frogs(){
        return amount_frogs--;
    }
    public static int set_amount_plankton(){
        return amount_plankton--;
    }
    public static int getSize_x() {
        return size_x;
    }
    public static int getSize_y() {
        return size_y;
    }
    public Simulation(){
        Pond pond = new Pond(size_x, size_y, amount_fish, amount_frogs, amount_plankton);
        view = new View(size_x*41,size_y*40);
    }
    void update_pond() {//metoda akttualizuje stan planszy
        for (Agent agent: Pond.get_agents()){
            if (agent.alive) agent.update();
        }
        Pond.delete_agent();
    }
    private void updateView() { //działąjący update widoku
        view.getContentPane().removeAll();
        view.update_view();
        view.revalidate();
        view.repaint();
    }
    void simulate() {
        System.out.println("Liczba żab   | Liczba ryb   |Liczba planktonu"  );
        int x = 0;
        do {
            SwingUtilities.invokeLater(this::updateView); //dodany dobry update widoku
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
            System.out.println("     "+amount_frogs + "      |      "+amount_fish+"      |       "+amount_plankton);
        } while (x < 30);
        System.out.println("Liczba wygranych żab: " + amount_frogs);
    }
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.simulate();
    }
}
