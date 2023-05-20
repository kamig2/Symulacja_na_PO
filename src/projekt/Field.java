package projekt;
public class Field {
    private boolean is_empty=false;
    private boolean has_plankton=false;
    private boolean has_fish=false;
    private boolean has_frogspawn=false;
    private boolean has_tadpole=false;
     String type;
    Field(String type){
        this.type=type;
        if (type.equals("EMPTY")) is_empty=true;
        if (type.equals("FISH")) has_fish=true;
        if (type.equals("FROGSPAWN")) has_frogspawn=true;
        if (type.equals("PLANKTON")) has_plankton = true;
        if (type.equals("TADPOLE")) has_tadpole=true;
    }
    public String getType(){
        return type;
    }
    public boolean get_is_empty(){
        return is_empty;
    }
    public boolean get_has_plankton(){
        return has_plankton;
    }
    public boolean get_has_fish(){
        return has_fish;
    }
    public boolean get_has_frogspawn(){
        return has_frogspawn;
    }
    public boolean get_has_tadpole(){
        return has_tadpole;
    }
    void disappear(){
        type="EMPTY";
    }
}
