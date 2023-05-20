package projekt;

import java.util.ArrayList;

public class Fish extends Agent{

    void eat(Field eaten_field){ // rodzaj jedzonego pola jako parametr zeby wiedziec o ile ma sie zmniejszyc glod
        if (eaten_field.get_has_tadpole()) hunger-=30; // głód się zmniejsza o 30 po zjedzeniu kijanki
        if (hunger<0) hunger =0; // żeby głód nie mógł być na minusie
    }

    void move(){
        int x,y,x1,y1;
        y1=position_y;
        x1=position_x;
        do {
            do {
                x = random.nextInt(position_x+1-(position_x-1)+1);//coś jest źle z tym losowaniem ale nie wiem co poszukam jutro o co chodzi
                y = random.nextInt(position_y+1-(position_y-1)+1);
            }while (!contains(x,y)); //dodałam warunek żeby sprawdzało czy wartości są dobre i żeby sprawdzało czy na tym polu nie ma skrzeku planktonu lub ryby
        }while ((x==position_x && y==position_y )|| Pond.pond_array.get(y).get(x).get_has_fish()|| Pond.pond_array.get(y).get(x).get_has_plankton()||Pond.pond_array.get(y).get(x).get_has_frogspawn());
        position_x=x;
        position_y=y;
        if (Pond.pond_array.get(position_y).get(position_x).get_has_tadpole()){
            eat(Pond.pond_array.get(position_y).get(position_x));
        }
        Field field = new Field("FISH");
        ArrayList<Field> array_row;
        array_row = Pond.pond_array.get(position_y);
        array_row.set(position_x,field);
        Pond.pond_array.set(position_y,array_row);
        Field field1 = new Field("EMPTY"); //zamiana pola na którym była ryba wcześniej na empty
        ArrayList<Field> array_row1;
        array_row1 = Pond.pond_array.get(y1);
        array_row1.set(x1,field1);
        Pond.pond_array.set(y1,array_row1);
    }
    void update(){
        if(hunger==100) die();
        move();
    }
}
