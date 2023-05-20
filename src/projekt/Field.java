package projekt;
public class Field {
    private boolean is_empty=false; //zrobiłam te pola private bo wydaje mi sie że tak powinno byc żęby ta hermetyzacja była xd
    private boolean has_plankton=false; //i dodałam getery na dole więc jak będziesz chciała użyć to uzywaj geterów
    private boolean has_fish=false;
    private boolean has_frogspawn=false;
    private boolean has_tadpole=false;
    private String type;

    Field(String type){
        this.type=type;
        if (type.equals("EMPTY")) is_empty=true; //dodałam żęby na podstawie podanego typa pola do konstruktora boolean dla odpowiedniego typu był prawdziwy
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
