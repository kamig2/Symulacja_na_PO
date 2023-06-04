package projekt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Pond {
    static int size_x;
    static int size_y;
    Random random = new Random();
    static ArrayList<ArrayList<Field>> pond_array;//dwuwymiarowa lista przechowujca pole
    private static final ArrayList<Agent> agents = new ArrayList<>();//lista agentów
    public static ArrayList<Agent> get_agents(){return agents;}
    public static void set_agents(int index) {agents.get(index).alive=false;}
    void create_pond_array2D(int x, int y) {//metoda tworząca listę dwuwymiarową reprezentującą staw
        pond_array = new ArrayList<>(x);
        for (int row = 0; row < size_y; row++) {
            ArrayList<Field> array_row = new ArrayList<>(y);
            for (int col = 0; col < size_x; col++) {
                Field field = new Field(Field_type.EMPTY);//dodawanie pustych pól
                array_row.add(field);
            }
            pond_array.add(array_row);
        }
    }
    void create_agents_array(int amount_fish,int amount_frogs) {//metoda tworząca liste Agentów
        for (int i=0;i<amount_fish;i++) {
            Fish fish = new Fish();//dodawanie ryb do listy
            agents.add(fish);
        }
        for (int i=0;i<amount_frogs;i++) {
            Frog frog = new Frog();//dodawanie żab do listy
            agents.add(frog);
        }
    }
    public void place_agent(){//metoda rozmieszczająca agentów na randomowe pozycje na planszy
        int x,y;
        for (Agent agent:agents){
            do {
                x = random.nextInt(size_x);//losowanie pozycji x
                y = random.nextInt(size_y);//losowanie pozycji y
            }while (!(Pond.pond_array.get(y).get(x).get_is_empty()));//sprawdzanie pole o wylosowanych wartościach x i y jest puste
            agent.position_x=x;//przypisanie agentowi wylosowanej pozycji
            agent.position_y=y;
            if (agent instanceof Frog){
                Pond.pond_array.get(agent.position_y).get(agent.position_x).set_type(Field_type.FROGSPAWN);//zmiana typu pola na skrzek
            }else if (agent instanceof Fish){
                Pond.pond_array.get(agent.position_y).get(agent.position_x).set_type(Field_type.FISH);//zmiana typu pola na ryba
            }
        }
    }
    static void delete_agent(){//usuwanie nieżywych agentów z listy
        Iterator<Agent> iterator = agents.iterator();
        while (iterator.hasNext()){
            Agent agent=iterator.next();
            if (!agent.alive){//sprawdznie czy agent żyje
                if (agent instanceof Fish){//sprawdzanie czy agent jest rybą
                    Simulation.set_amount_fish();//zmniejszanie liczby ryb o jeden
                    System.out.println("zmniejszanie liczby ryb");
                }/*else {
                    Simulation.set_amount_frogs();//zmiejszaznie liczby żab o jeden
                    System.out.println("zmniejsznie liczby żab");
                }*/
                iterator.remove();
//                Pond.pond_array.get(agent.position_y).get(agent.position_x).set_type(Field_type.EMPTY);
            }
        }
    }
    void place_plankton(int amount_plankton) {
        int x, y;
        for (int i = 0; i < amount_plankton; i++) {
            do {
                x = random.nextInt(size_x);
                y = random.nextInt(size_y);
            } while (!(pond_array.get(y).get(x).get_is_empty())); //Sprawdzanie czy pole jest puste jeśli nie losuje inne miejsce
            pond_array.get(y).get(x).set_type(Field_type.PLANKTON);
        }
    }
    Pond(int x, int y, int amount_fish, int amount_frogspawn, int amount_plankton){ // konstruktor
        size_x = x;
        size_y = y;
        create_pond_array2D(size_x, size_y);
        create_agents_array(amount_fish,amount_frogspawn);
        place_agent();
        place_plankton(amount_plankton);
    }
    /* void respawn_plankton(){ // funkcja odradzania się planktonu po zjedzeniu
         int x,y;
         do {
             x = random.nextInt(Simulation.getSize_x());
             y = random.nextInt(Simulation.getSize_y());
         } while (!(Pond.pond_array.get(y).get(x).get_is_empty())); //Sprawdzanie czy pole jest puste jeśli nie losuje inne miejsc
         Field field = new Field("PLANKTON");
         ArrayList<Field> array_row;
         array_row = Pond.pond_array.get(y);
         array_row.set(x,field);   //zamiena typu pola na PLANKTON
         Pond.pond_array.set(y,array_row);
     }*/
}


