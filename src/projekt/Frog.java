package projekt;
import java.util.ArrayList;
import java.util.Random;

public class Frog extends Agent  {
    enum Growth_stage{FROGSPAWN, TADPOLE, FROG};
    Growth_stage growth_stage;
    void grow(){
        if(growth_stage==Growth_stage.FROGSPAWN) {
            growth_stage = Growth_stage.TADPOLE;
        }
        if(growth_stage==Growth_stage.TADPOLE) {
            growth_stage = Growth_stage.FROG;
        }
    }
    void respawn_plankton(){ // funkcja odradzania się planktonu po zjedzeniu
        int x,y;
        do {
            x = random.nextInt(Simulation.getSize_x());
            y = random.nextInt(Simulation.getSize_y());
        } while (!(Pond.pond_array.get(y).get(x).getType().equals("EMPTY"))); //Sprawdzanie czy pole jest puste jeśli nie losuje inne miejsce
        Field field = new Field("PLANKTON");
        ArrayList<Field> array_row;
        array_row = Pond.pond_array.get(y);
        array_row.set(x,field);   //zamiena typu pola na PLANKTON
        Pond.pond_array.set(y,array_row);
    }
    void eat(Field eaten_field){ // rodzaj jedzonego pola jako parametr zeby wiedziec o ile ma sie zmniejszyc glod
        if (growth_stage != Growth_stage.TADPOLE) return; // żaba je tylko wtedy gdy jest kijanką
        if (eaten_field.has_tadpole) hunger=0; // głód się zeruje po zjedzeniu kijanki
        else if (eaten_field.has_plankton) {
            hunger -= 50; // głód spada o połowe po zjedzeniu planktonu
            respawn_plankton();
        }
        if (hunger<0) hunger =0; // żeby głód nie mógł być na minusie
    }

    void move(){
        if (growth_stage!=Growth_stage.TADPOLE) return;//może się poruszać tylko w stadium kijanki
        int x,y;
        do {
            x = random.nextInt(position_x + 1 - (position_x - 1) + 1);//losuje randomową pozycje z pozycji oddalonych od aktualnej o jedo miejsce w tablicy
            y = random.nextInt(position_y + 1 - (position_x - 1) + 1);
        } while (Pond.pond_array.get(position_y).get(position_x).getType().equals("FISH") || (x==position_x && y==position_y) || !contains(x, y));
        position_x=x;
        position_y=y;
        if (Pond.pond_array.get(position_y).get(position_x).getType().equals("PLANKTON") || Pond.pond_array.get(position_y).get(position_x).getType().equals("TADPOLE")){ //edytowane
            eat(Pond.pond_array.get(position_y).get(position_x));
        }
        Field field = new Field("TADPOLE");
        ArrayList<Field> array_row;
        array_row = Pond.pond_array.get(position_y);
        array_row.set(position_x,field); //zamiana typu pola na kijanke
        Pond.pond_array.set(position_y,array_row);
    }
    boolean contains(int x, int y){
        if(x<0 && x>= Simulation.getSize_x() && y<0 && y>= Simulation.getSize_y()) return false;
        else return true;
    }

    void update_frog(){ //trzeba porobic te updaty ale juz mi sie dzisiaj nie chce wiec ogarne to jutro albo jak bys cos pisala to mozesz pokminic
        if(hunger==100) die(); // tylko nw dokonca jak to zrobic czy update do kazdej klasy osobny czy w agencie czy jak
    }

}
