package projekt;
import javax.swing.*;
import java.awt.*;
public class View extends JFrame {
    public View(int size_x, int size_y){
        this.setSize(size_x,size_y); //ustawianie rozmiaru okienka
        this.setTitle("Simulation Frogs"); //tytuł okna
        this.setResizable(false); // niezmienianie rozmiaru okna
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //okno zamyka się po naciśnieciu krzyżyka
        Color color_pond = new Color(113,191,182);
        this.getContentPane().setBackground(color_pond); //ustawienie koloru tła
        ImageIcon icon = new ImageIcon("FROG.png"); // dodanie obrazka, który ma być ikonką okna
        this.setIconImage(icon.getImage()); // ustawienie jako ikonke
        this.setLayout(null); //żeby samodzielnie ustawiać położenia obiektów w oknie
        this.setVisible(true); // widoczność okna
    }
    public JLabel draw_agent(String image_path,int position_x, int position_y, int width, int height){
        ImageIcon original_agent = new ImageIcon(image_path); // dodanie orginalnego obrazka agenta
        Image original_agent_im = original_agent.getImage();
        Image resized_agent_im = original_agent_im.getScaledInstance(width,height,Image.SCALE_SMOOTH); //powiększenie do odpowiednich rozmiarów
        ImageIcon resized_agent_ic = new ImageIcon(resized_agent_im);
        JLabel label = new JLabel(); // dodanie Panelu
        label.setIcon(resized_agent_ic);
        label.setBounds(position_x*40, position_y*40,width,height); // ustawienie położenia obrazka
        this.getContentPane().add(label);
        this.revalidate(); //odświeża układ obiektów w oknie
        this.repaint(); // odświeża wygląd okna
        return label;
    }
    public void remove_agent(int position_x, int position_y) { //metoda usuwająca obrazka agenta
        Component[] components = this.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                int x = label.getX();
                int y = label.getY();
                int width = label.getWidth();
                int height = label.getHeight();
                if (x == position_x && y == position_y && width == 40 && height == 40) {
                    this.getContentPane().remove(label);
                    this.revalidate();
                    this.repaint();
                    break;
                }
            }
        }
    }
    public void update_view() { //odświeżanie widoku
        getContentPane().removeAll();
        for (int i = 0; i < Simulation.get_size_y(); i++) {
            for (int j = 0; j < Simulation.get_size_x(); j++) {
                Field field = Pond.pond_array.get(i).get(j);
                if (field.get_has_fish()) {
                    remove_agent(j, i);
                    draw_agent("FISH.png", j, i, 40, 40);
                } else if (field.get_has_tadpole()) {
                    remove_agent(j, i);
                    draw_agent("TADPOLE.png", j, i, 40, 40);
                } else if (field.get_has_frogspawn()) {
                    remove_agent(j, i);
                    draw_agent("FROGSPAWN.png", j, i, 40, 40);
                } else if (field.get_has_plankton()) {
                    remove_agent(j, i);
                    draw_agent("PLANKTON.png", j, i, 40, 40);
                } else if (field.get_has_frog()) {
                    remove_agent(j, i);
                    draw_agent("FROG.png", j, i, 40, 40);
                } else {
                    remove_agent(j, i);
                }
            }
        }
        revalidate();
        repaint();
    }
}
