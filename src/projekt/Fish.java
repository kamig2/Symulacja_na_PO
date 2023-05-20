package projekt;

import java.util.ArrayList;

public class Fish extends Agent{

    void eat(Field eaten_field){ // rodzaj jedzonego pola jako parametr zeby wiedziec o ile ma sie zmniejszyc glod
        if (eaten_field.get_has_tadpole()) hunger-=30; // głód się zmniejsza o 30 po zjedzeniu kijanki
        if (hunger<0) hunger =0; // żeby głód nie mógł być na minusie
        //trzeba jakoś uśmiercić tą kijanek którą zjada ryba
    }

    void move(){
        int x,y,x1,y1,sign;
        y1=position_y;
        x1=position_x;
        do {
            do {
                sign=random.nextInt(2);//dobra to wygląda troche dziwnie ale nie wiedziałam jak to inaczej zrobić żeby działało
                if (sign==0) x = position_x + random.nextInt(2); //ale ta zmienna sign jest po to aby wylosować czy mamodjąć od aktualnej pozycji 1 lub 0 czy dodać 1 lub 0
                else x = position_x - random.nextInt(2);   //bo chodzi o to żeby ryba mogła sie przemieścić tylko na sąsiadujące pole
                sign=random.nextInt(2);
                if (sign==0) y = position_y+random.nextInt(2);
                else y = position_y - random.nextInt(2);
            }while (!contains(x,y)); //dodałam warunek żeby sprawdzało czy wartości są dobre i żeby sprawdzało czy na tym polu nie ma skrzeku planktonu lub ryby
        }while ((x==position_x && y==position_y )|| Pond.pond_array.get(y).get(x).get_has_fish()|| Pond.pond_array.get(y).get(x).get_has_plankton()||Pond.pond_array.get(y).get(x).get_has_frogspawn());
        position_x=x;
        position_y=y;
        if (Pond.pond_array.get(position_y).get(position_x).get_has_tadpole()) eat(Pond.pond_array.get(position_y).get(position_x));
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
        hunger+=10;
        if(hunger==100) die();
        move();
    }
}
