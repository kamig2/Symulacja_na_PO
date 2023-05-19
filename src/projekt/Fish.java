package projekt;
public class Fish extends Agent{

    void eat(Field eaten_field){ // rodzaj jedzonego pola jako parametr zeby wiedziec o ile ma sie zmniejszyc glod
        if (eaten_field.has_tadpole) hunger-=30; // głód się zmniejsza o 30 po zjedzeniu kijanki
        if (hunger<0) hunger =0; // żeby głód nie mógł być na minusie
    }

    void move(){//wydaje mi sie że do tej metody i do metody move w klasie Frog trzeba dodać jescze jakieś warunki kiedy może sie ruszać na dane pole
        int x,y;//bo chyba powinno być tak że jedno pole może mieć tylko jeden typ na raz czyli że np skrzek i kijanka nie mogą być razem na jednym polu
        x=random.nextInt(position_x+1-(position_x-1)+1);
        y=random.nextInt(position_y+1-(position_x-1)+1);
        while ((x==position_x && y==position_y ) || position_x<0 || position_y<0){
            x=random.nextInt(position_x+1-(position_x-1)+1);
            y=random.nextInt(position_y+1-(position_x-1)+1);
        }
        position_x=x;
        position_y=y;
        if (Pond.pond_array.get(position_y).get(position_x).getType().equals("TADPOLE")){
            eat(Pond.pond_array.get(position_y).get(position_x));
        }
    }
    void update(){

    }
}
