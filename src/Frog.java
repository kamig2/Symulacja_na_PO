public class Frog extends Agent {
    int position_x;
    int position_y;
    enum Growth_stage{FROGSPAWN, TADPOLE, FROG}; // zastanawiam sie jeszcze bo wsm mozna by bylo zrobic to bez klas kijanka i skrzek
    Growth_stage growth_stage;                   // i po prostu zeby byla sama klasa zaba i w niej zeby była metoda ktora okresla
    void grow(){                                 // na jakim etapie wzrostu jest zaba i np ze moze jesc tylko jesli stan== kijanka itd. chyba czaisz
        if(growth_stage==Growth_stage.FROGSPAWN) { // no i nw chyba tak by bylo moze latwiej
            growth_stage = Growth_stage.TADPOLE;
            return;
        }
        if(growth_stage==Growth_stage.TADPOLE) {
            growth_stage = Growth_stage.FROG;
            return;
        }
    }
    void update(){

    }

}
