package projekt;
public class Field {
    boolean has_plankton;
    boolean has_fish;
    boolean has_frogspawn;
    boolean has_tadpole;
    private String type;

    Field(String type){
        this.type=type;
        has_fish=false;
        has_frogspawn=false;
        has_plankton=false;
        has_tadpole=false;
    }
    public String getType(){
        return type;
    }
    void disappear(){
        type="EMPTY";
    }


}
