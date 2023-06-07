package projekt;
import javax.swing.*;
import java.util.ArrayList;

public class Simulation {
    static int size_x = 20;
    static int size_y = 20;
    private static int amount_fish = 10;
    private static int amount_frogs = 10;
    private static int amount_plankton = 20/*(int)(size_x*size_y*Pond.plankton_growth)*/;
    static int TIME_STEP = (amount_fish+amount_frogs+amount_plankton)*65;//zrobiłam tak że to jest zależne od ilości wyswietlanych agentow na planszy bo im wiecej tym większy czas potrzebny
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
    public static void set_amount_plankton2(){
        amount_plankton++;
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
    void update_pond() {//metoda akttualizuje stan planszy
        for (Agent agent: Pond.get_agents()){
            if (agent.alive && !agent.win) agent.update();
        }
        Pond.delete_agent();
        Pond.respawn_plankton();
    }
    private void updateView() { //działąjący update widoku
        view.getContentPane().removeAll();
        view.update_view();
        view.revalidate();
        view.repaint();
    }
    void simulate() {
        System.out.println(amount_plankton);
        System.out.println("Liczba żab   | Liczba ryb   |Liczba planktonu"  );
        int x = 0;
        do {//pozmieniałam troche z tym czasem nie działa to jakoś mega szybko ale przynajmniej sie wyświetla każdy update
            long start_time = System.currentTimeMillis();
            update_pond();  // Aktualizacja stanu stawu
            SwingUtilities.invokeLater(this::updateView); //dodany dobry update widoku
            long elapsed_time = System.currentTimeMillis() - start_time;
            long sleep_time = TIME_STEP - elapsed_time;
//            System.out.println("time sleep: "+ sleep_time);
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
            /*System.out.println("ile ryb na planszy "+ryby);
            System.out.println("ryby policzone "+amount_fish);*/
            System.out.println("ile żab na planszy "+ile);
            System.out.println("zaby "+amount_frogs);
            x++;

//            System.out.println("     "+amount_frogs + "      |      "+amount_fish+"      |       "+amount_plankton);
        } while (amount_fish>0);//tzreba dodac że stadium wzrostu wszystkich żab musi być żaba
        int ile=0;
        for (ArrayList<Field> list :Pond.pond_array){//dodałam to chwilowo żeby sprawdzać czy liczb żab w pond_array zgadza się z liczbą żywych żab
            for (Field field : list){
                if (field.get_has_frog() || field.get_has_tadpole() || field.get_has_frogspawn()){
                    ile++;
                }
            }
        }
        System.out.println("ile:"+ile);
        System.out.println("Liczba wygranych żab: " + amount_frogs);
    }
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.simulate();
    }
}
