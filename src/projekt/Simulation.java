package projekt;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    private static Random random = new Random();
    private static int size_x = 19; // rozmiar planszy
    private static int size_y = 19; // rozmiar planszy
    private static int amount_fish = 20; //początkowa liczba ryb
    private static int amount_frogs = 10; //początkowa liczba żab
    private static int amount_plankton = 10; //początkowa liczba planktonu
    private static int TIME_STEP = (amount_fish+amount_frogs+amount_plankton)*65;
    private final View view;
    public static int get_size_x(){
        return size_x;
    }
    public static int get_size_y(){
        return size_y;
    }

    public static int set_amount_fish(){
        return amount_fish--;
    }
    public static int set_amount_frogs(){
        return amount_frogs--;
    }
    public static int set_amount_plankton(){
        return amount_plankton--;
    }
    public static void set_amount_plankton2(){
        amount_plankton++;
    }
    public Simulation(){
        Pond pond = new Pond(size_x, size_y, amount_fish, amount_frogs, amount_plankton);
        view = new View(size_x*41,size_y*42);
        long start_time = System.currentTimeMillis();
        SwingUtilities.invokeLater(this::updateView);
        long elapsed_time = System.currentTimeMillis() - start_time;
        long sleep_time = TIME_STEP - elapsed_time;
        if (sleep_time < 0) sleep_time = 0;
        try {
            Thread.sleep(sleep_time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void update_pond() {//metoda aktualizująca stan planszy
        for (Agent agent: Pond.get_agents()){
            if (agent.alive && !agent.win) agent.update();
        }
        Pond.delete_agent();
        Pond.respawn_plankton();
    }
    private void updateView() { //update widoku
        view.getContentPane().removeAll();
        view.update_view();
        view.revalidate();
        view.repaint();
    }
    private void simulate() {
        System.out.println("Liczba żab   | Liczba ryb   |Liczba planktonu"  );
        int x = 0;
        int amount_adult_frogs=0;
        do {
            long start_time = System.currentTimeMillis();
            update_pond();  // Aktualizacja stanu stawu
            SwingUtilities.invokeLater(this::updateView); //dodany dobry update widoku
            long elapsed_time = System.currentTimeMillis() - start_time;
            long sleep_time = TIME_STEP - elapsed_time;
            if (sleep_time < 0) sleep_time = 0;
            try {
                Thread.sleep(sleep_time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int ile=0,ryby=0;
            for (ArrayList<Field> list :Pond.pond_array){//dodałam to chwilowo żeby sprawdzać czy liczb żab w pond_array zgadza się z liczbą żywych żab
                for (Field field : list){
                    if (field.get_has_frog() || field.get_has_tadpole() || field.get_has_frogspawn()){
                        ile++;
                    }
                    if (field.get_has_fish()){
                        ryby++;
                    }
                }
            }
            amount_adult_frogs=0;
            for (ArrayList<Field> list :Pond.pond_array){//zliczanie pól typu frog na planszy
                for (Field field : list){
                    if (field.get_has_frog()){
                        amount_adult_frogs++;//zwiększanie liczby dorosłych żab
                    }
                }
            }
            x++;
            System.out.println("     "+amount_frogs + "      |      "+amount_fish+"      |       "+amount_plankton);
        } while (amount_fish>0 || amount_frogs!=amount_adult_frogs);//warunek działania symulacji liczba ryb większa od 0 lub nie wszystkie żywe żaby wyewoluowały
        System.out.println("Liczba wygranych żab: " + amount_frogs);
    }
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.simulate();
    }
}
