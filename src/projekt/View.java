package projekt;
import javax.swing.*;
import java.awt.*;


public class View extends JFrame {
    public View(int size_x, int size_y){
        this.setSize(Simulation.getSize_x(),Simulation.getSize_y());
        this.setTitle("Simulation Frogs");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.green);
        this.setVisible(true);

    }



}