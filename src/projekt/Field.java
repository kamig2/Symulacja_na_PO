package projekt;
public class Field {
    private boolean is_empty=false;
    private boolean has_plankton=false;
    private boolean has_fish=false;
    private boolean has_frogspawn=false;
    private boolean has_tadpole=false;
    private boolean has_frog = false;
    private Field_type field_type;
    Field(Field_type type){
        this.field_type=type;
        if (type==Field_type.EMPTY) is_empty=true;
        if (type==Field_type.FISH) has_fish=true;
        if (type==Field_type.FROGSPAWN) has_frogspawn=true;
        if (type==Field_type.PLANKTON) has_plankton = true;
        if (type==Field_type.TADPOLE) has_tadpole=true;
        if (type==Field_type.FROG) has_frog=true;
    }
    public Field_type getType(){
        return field_type;
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
    public void set_type(Field_type type){//zamiana typu pola
        this.field_type=type;
        is_empty=false;
        has_fish=false;
        has_frogspawn=false;
        has_plankton=false;
        has_tadpole=false;
        has_frog=false;
        if (type==Field_type.EMPTY) is_empty=true;
        if (type==Field_type.FISH) has_fish=true;
        if (type==Field_type.FROGSPAWN) has_frogspawn=true;
        if (type==Field_type.PLANKTON) has_plankton = true;
        if (type==Field_type.TADPOLE) has_tadpole=true;
        if (type==Field_type.FROG) has_frog=true;
    }
}
