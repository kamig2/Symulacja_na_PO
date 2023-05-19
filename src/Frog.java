import java.util.Random;

public class Frog extends Agent {

    Random random = new Random();
    int position_x;
    int position_y;
    enum Growth_stage{FROGSPAWN, TADPOLE, FROG}; // zastanawiam sie jeszcze bo wsm mozna by bylo zrobic to bez klas kijanka i skrzek
    Growth_stage growth_stage;                   // i po prostu zeby byla sama klasa zaba i w niej zeby była metoda ktora okresla
    void grow(){                                 // na jakim etapie wzrostu jest zaba i np ze moze jesc tylko jesli stan== kijanka itd. chyba czaisz
        if(growth_stage==Growth_stage.FROGSPAWN) { // no i nw chyba tak by bylo moze latwiej
            growth_stage = Growth_stage.TADPOLE;
        }
        if(growth_stage==Growth_stage.TADPOLE) {
            growth_stage = Growth_stage.FROG;
        }
    }
    void eat(){
        if (growth_stage == Growth_stage.TADPOLE){
        }

    }

    void move(){//nie wiem czy to będzie działać xd
        if (growth_stage==Growth_stage.TADPOLE){//może się poruszać tylko w stadium kijanki
            int x,y;
            x=random.nextInt(position_x+1-(position_x-1)+1);//losuje randomową pozycje z pozycji oddalonych od aktualnej o jedo miejsce w tablicy
            y=random.nextInt(position_y+1-(position_x-1)+1);
            while (Pond.pond_array.get(position_y).get(position_x).getType().equals("FISH") || (x==position_x && y==position_y )){//nie wiem czy to potrzebne ale sprawdza czy na pozycji
                x=random.nextInt(position_x+1-(position_x-1)+1);                                                            //na którą chce sie poruszyć kijanka nie ma przypadkiem ryby
                y=random.nextInt(position_y+1-(position_x-1)+1);                                                            //i sprawdza też czy nie jest to ta sama pozycja co przed chwilą
            }
            position_x=x;
            position_y=y;
        }
    }
    void update(){


    }

}
