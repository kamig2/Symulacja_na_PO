package projekt;
public class Field {
    private boolean is_empty=false;
    private boolean has_plankton=false;
    private boolean has_fish=false;
    private boolean has_frogspawn=false;
    private boolean has_tadpole=false;

    private boolean has_frog = false;
    private String type;
    Field(String type){
        this.type=type;
        if (type.equals("EMPTY")) is_empty=true;
        if (type.equals("FISH")) has_fish=true;
        if (type.equals("FROGSPAWN")) has_frogspawn=true;
        if (type.equals("PLANKTON")) has_plankton = true;
        if (type.equals("TADPOLE")) has_tadpole=true;
        if (type.equals("FROG")) has_frog=true;
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
    public boolean get_has_frog(){return has_frog;}

    public void set_type(String type){//dodałam metode która zmienia typ pola
        this.type=type;
        is_empty=false;
        has_fish=false;
        has_frogspawn=false;
        has_plankton=false;
        has_tadpole=false;
        has_frog=false;
        if (type.equals("EMPTY")) is_empty=true;
        if (type.equals("FISH")) has_fish=true;
        if (type.equals("FROGSPAWN")) has_frogspawn=true;
        if (type.equals("PLANKTON")) has_plankton = true;
        if (type.equals("TADPOLE")) has_tadpole=true;
        if (type.equals("FROG")) has_frog=true;

    }
}
