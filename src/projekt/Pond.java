package projekt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Pond {
    private static Random random = new Random();
    private static double plankton_growth = 0.998;
    static ArrayList<ArrayList<Field>> pond_array;//dwuwymiarowa lista przechowujca pole
    private static ArrayList<Agent> agents = new ArrayList<>();//lista agentów
    public static ArrayList<Agent> get_agents(){return agents;}
    public static void set_agents(int index) {agents.get(index).alive=false;}
    private void create_pond_array2D(int x, int y) {//metoda tworząca listę dwuwymiarową reprezentującą staw
        pond_array = new ArrayList<>(x);
        for (int row = 0; row < Simulation.get_size_y(); row++) {
            ArrayList<Field> array_row = new ArrayList<>(y);
            for (int col = 0; col < Simulation.get_size_x(); col++) {
                Field field = new Field(Field_type.EMPTY);//dodawanie pustych pól
                array_row.add(field);
            }
            pond_array.add(array_row);
        }
    }
    private void create_agents_array(int amount_fish,int amount_frogs) {//metoda tworząca liste Agentów
        for (int i=0;i<amount_fish;i++) {
            Fish fish = new Fish();//dodawanie ryb do listy
            agents.add(fish);
        }
        for (int i=0;i<amount_frogs;i++) {
            Frog frog = new Frog();//dodawanie żab do listy
            agents.add(frog);
        }
    }
    private void place_agent(){//metoda rozmieszczająca agentów na randomowe pozycje na planszy
        int x,y;
        for (Agent agent:agents){
            do {
                x = random.nextInt(Simulation.get_size_x());//losowanie pozycji x
                y = random.nextInt(Simulation.get_size_y());//losowanie pozycji y
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
    public static void delete_agent(){//usuwanie nieżywych agentów z listy
        Iterator<Agent> iterator = agents.iterator();
        while (iterator.hasNext()){
            Agent agent=iterator.next();
            if (!agent.alive){//sprawdznie czy agent żyje
                if (agent instanceof Fish) {//sprawdzanie czy agent jest rybą
                    Simulation.set_amount_fish();//zmniejszanie liczby ryb o jeden
                }else {
                    Simulation.set_amount_frogs();//zmniejszanie liczby żab
                }
                iterator.remove();
            }
        }
    }
    private void place_plankton(int amount_plankton) {
        int x, y;
        for (int i = 0; i < amount_plankton; i++) {
            do {
                x = random.nextInt(Simulation.get_size_x());
                y = random.nextInt(Simulation.get_size_y());
            } while (!(pond_array.get(y).get(x).get_is_empty())); //Sprawdzanie czy pole jest puste jeśli nie losuje inne miejsce
            pond_array.get(y).get(x).set_type(Field_type.PLANKTON);
        }
    }

    //zrobiłam coś takiego możesz zobaczyć jak to działa ale według mnie tak dziwnie jak masz inny pomysł to spróbuj zrobić
    //w ogóle chba będzie trzeba dodać jakiś warunek w metodzie move ryby bo przez to że sie ten plankton spawnuje to może wystąpić
    //taki błąd że ryba nie będzie sie miała gdzie ruszyc bo będzie otoczona planktonem i przez to nie pójdzie symulacja dalej bo sie będzie losować
    //ciągle nowa pozycja dla ryby a warunek nie puści dalej
    public static void respawn_plankton(){
        double probability;
        int x,y;
        for (ArrayList<Field> fields : pond_array ){
            for (Field field : fields){
                if (field.get_is_empty()){//sprawdzanie czy pole jest puste
                    probability = random.nextDouble();//losowanie liczby rzeczywistej od 0 do 1
                    if (probability>plankton_growth) {//jeżeli wylosowana liczba jest większa od ustalonego współczynnika to pole zmienia typ na plankton
                        field.set_type(Field_type.PLANKTON);
                        Simulation.set_amount_plankton2();//zwiększanie liczby planktonu
                    }

                }
            }
        }
    }
    Pond(int x, int y, int amount_fish, int amount_frogspawn, int amount_plankton){ // konstruktor
        create_pond_array2D(x, y);
        create_agents_array(amount_fish,amount_frogspawn);
        place_agent();
        place_plankton(amount_plankton);
    }

}


