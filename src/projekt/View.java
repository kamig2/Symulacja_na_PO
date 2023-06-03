package projekt;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class View extends JFrame {
    public View(int size_x, int size_y){
        this.setSize(Simulation.getSize_x()*40,Simulation.getSize_y()*40);
        this.setTitle("Simulation Frogs");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.CYAN);
        ImageIcon icon = new ImageIcon("FROG.png");
        this.setIconImage(icon.getImage());
        this.setLayout(null);
        this.setVisible(true);
    }
    public JLabel draw_agent(String image_path,int position_x, int position_y, int width, int height){
        ImageIcon original_fish = new ImageIcon(image_path);
        Image original_fish_im = original_fish.getImage();
        Image resized_fish_im = original_fish_im.getScaledInstance(width,height,Image.SCALE_SMOOTH);
        ImageIcon resized_fish_ic = new ImageIcon(resized_fish_im);
        JLabel label = new JLabel();
        label.setIcon(resized_fish_ic);
        label.setBounds(position_x*40, position_y*40,width,height); // poźniej jako x i y da się pozycje tych agentów i najs
        this.getContentPane().add(label);
        this.revalidate();
        this.repaint();
        return label;
    }
    public void remove_agent(int position_x, int position_y) { //poprawione
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
    public void update_view(Pond pond) {
        //getContentPane().removeAll();
        for (int i = 0; i < pond.size_y; i++) {
            for (int j = 0; j < pond.size_x; j++) {
                Field field = pond.pond_array.get(i).get(j);

                if (field.get_has_fish()) {
                    draw_agent("FISH.png", j, i, 40, 40);
                } else if (field.get_has_tadpole()) {
                    draw_agent("TADPOLE.png", j, i, 40, 40);
                } else if (field.get_has_frogspawn()) {
                    remove_agent(j, i);
                    draw_agent("FROGSPAWN.png", j, i, 40, 40);
                } else if (field.get_has_plankton()) {
                    draw_agent("PLANKTON.png", j, i, 40, 40);
                } else if (field.get_has_frog()) {
                    remove_agent(j, i);
                    draw_agent("FROG.png", j, i, 40, 40);
                } else {
                    remove_agent(j, i);
                }
            }
        }
        //revalidate();
        //repaint();
    }
}
