package projekt;
public class Fish extends Agent{
    @Override
    protected void eat(Field eaten_field, int x, int y){//metoda jedzenia
        if (eaten_field.get_has_tadpole()){//sprawdzanie czy na jedzonym polu znajduje się kijanka
            System.out.println("ryba je kijanke");
            hunger-=50;
            for (Agent agent: Pond.get_agents()){//usuwanie zjedzonej kijanki
                if (agent instanceof Frog && agent.position_x==x && agent.position_y==y){
                    Pond.set_agents(Pond.get_agents().indexOf(agent));
                    break;
                }
            }
        }
        else if (eaten_field.get_has_frogspawn()) {//sprawdzanie czy na jedzonym polu jest plankton
            System.out.println("ryba je skrzek");
            hunger -= 30;
            for (Agent agent : Pond.get_agents()) {//usuwanie zjedzonego skrzeku
                if (agent instanceof Frog && agent.position_x == x && agent.position_y == y) {
                    Pond.set_agents(Pond.get_agents().indexOf(agent));
                    break;
                }
            }
            if (hunger < 0) hunger = 0;//ustawienie wartości głodu na 0 gdy jest liczbą ujemną
        }
    }
    @Override
    protected void move(){//metoda pruszania po planszy
        int x,y,x1,y1,sign;
        y1=position_y;//zmienne przechowujące poprzednią pozycje ryby
        x1=position_x;
        do {
            do {//sing to zmienna w której losujemy czy odjąć czy dodac wylosowaną liczbe 0 lub 1 do aktualnej pozycji
                sign=random.nextInt(2);
                if (sign==0) x = position_x + random.nextInt(2);//losowanie liczby 0 lub 1 i dodawanie do pozycji x
                else x = position_x - random.nextInt(2);//losowanie liczby 0 lub 1 i odejmowanie  od pozycji x
                sign=random.nextInt(2);
                if (sign==0) y = position_y + random.nextInt(2);//losowanie liczby 0 lub 1 i dodawanie do pozycji y
                else y = position_y-random.nextInt(2);//losowanie liczby 0 lub 1 i odejmowanie  od pozycji y
            }while (!contains(x,y));//sprawdzani czy wylosowane pozycje x i y są liczbami ujemnymi
        }while (!(Pond.pond_array.get(y).get(x).get_is_empty() || Pond.pond_array.get(y).get(x).get_has_tadpole()|| Pond.pond_array.get(y).get(x).get_has_frogspawn()) );//sprawdanie czy nowa pozycja jest równa starej,i czy na polu nie stoją plankton,inna ryba, żaba lub skrzek
        if (Pond.pond_array.get(y).get(x).get_has_tadpole() || Pond.pond_array.get(y).get(x).get_has_frogspawn()) { //sprawdzanie czy pole, na które ma się przemieścić ryba, nie znajduje się kijanka
            eat(Pond.pond_array.get(y).get(x),x,y);//zjadanie kijanki
        }
        position_x=x;//zmiana pozycji ryby na nową
        position_y=y;
        Pond.pond_array.get(position_y).get(position_x).set_type(Field_type.FISH);//zmiana typu nowego pola na fish
        Pond.pond_array.get(y1).get(x1).set_type(Field_type.EMPTY);//zmmiana typu starego pola na empty
    }

    @Override
    protected void update(){//zaktualizowanie stanu głodu, pozycji, życia
        hunger+=5;//zwiększenie głodu
        if(hunger==100) {
            System.out.println("ryba umiera");
            die();//ryba umiera
        }
        if (alive)move();//poruszanie ryby jeśli żyje
    }
}
