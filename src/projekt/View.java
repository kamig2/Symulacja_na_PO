package projekt;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class View extends JFrame {
    //static JLabel label;
    public View(int size_x, int size_y){
        this.setSize(Simulation.getSize_x(),Simulation.getSize_y());
        this.setTitle("Simulation Frogs");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
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
        label.setBounds(position_x, position_y,width,height); // poźniej jako x i y da się pozycje tych agentów i najs
        this.getContentPane().add(label);
        this.revalidate();
        this.repaint();
        return label;
    }


    public void remove_agent(JLabel label) {//to metoda do usuwania obrazka tylko nie wiem za bardzo skąd wziąć ten label w metodziie simulate
            this.getContentPane().remove(label);//żeby usuwało niepotrzebne obrazki
            this.revalidate();
            this.repaint();

    }


}
