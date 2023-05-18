public class Frog extends Agent {
    int position_x;
    int position_y;
    enum Growth_stage{FROGSPAWN, TADPOLE, FROG};
    Growth_stage growth_stage;
    void grow(){
        if(growth_stage==Growth_stage.FROGSPAWN) {
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
