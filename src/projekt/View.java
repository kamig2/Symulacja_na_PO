package projekt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class View extends JFrame {
    JFrame frame;
    JPanel panel;
    JPanel grid_panel;
    JTextArea textArea;
    private JLabel[][] grid_labels;
    private ImageIcon[] fieldIcons;
    private Simulation simulation;
    public View(Simulation simulation){
        this.simulation = simulation;
        setTitle("Pond Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Inicjalizacja gridLabels
        grid_labels = new JLabel[Simulation.size_x][Simulation.size_y];

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(Simulation.size_x, Simulation.size_y));

        for (int row = 0; row < Simulation.size_x; row++) {
            for (int col = 0; col < Simulation.size_y; col++) {
                JLabel label = new JLabel();
                grid_labels[row][col] = label;  // Przypisanie labelki do tablicy gridLabels
                gridPanel.add(label);
            }
        }

        add(gridPanel);
        pack();
        setVisible(true);
    }


    public void create_and_show_gui(){
        frame = new JFrame("Frog Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel);

        grid_panel = new JPanel(new GridLayout(Simulation.size_x, Simulation.size_y));
        panel.add(grid_panel, BorderLayout.CENTER);

        grid_labels = new JLabel[Simulation.size_x][Simulation.size_y];
        create_grid_labels();

        textArea = new JTextArea(20,40);
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        JButton start_button = new JButton("Start Simulation");
        start_button.addActionListener(e -> start_simulation());
        panel.add(start_button, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void create_grid_labels(){
        for (int i=0; i<Simulation.size_x;i++){
            for(int j=0; j<Simulation.size_y; j++){
                grid_labels[i][j] = new JLabel();
                grid_labels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                grid_labels[i][j].setBorder(BorderFactory.createLineBorder(Color.CYAN));
                grid_panel.add(grid_labels[i][j]);
            }
        }
    }

    public void update_grid_labels() {
        for (int row = 0; row < Simulation.getSize_y(); row++) {
            for (int col = 0; col < Simulation.getSize_x(); col++) {
                Field field = Pond.pond_array.get(row).get(col);
                String type = field.getType();
                JLabel label = grid_labels[row][col];

                switch (type) {
                    case "FISH":
                        label.setIcon(get_image_icon("FISH.png"));
                        break;
                    case "FROGSPAWN":
                        label.setIcon(get_image_icon("FROGSPAWN.jpg"));
                        break;
                    case "PLANKTON":
                        label.setIcon(get_image_icon("PLANKTON.png"));
                        break;
                    case "TADPOLE":
                        label.setIcon(get_image_icon("TADPOLE.jpg"));
                        break;
                    case "FROG":
                        label.setIcon(get_image_icon("ZABA.png"));
                        break;
                }
            }
        }
    }

    private ImageIcon get_image_icon(String image_name){
        if(image_name!=null){
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(image_name)));
            Image image = icon.getImage();
            Image scaled_image = image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
            return new ImageIcon(scaled_image);
        }else return null;
    }

    private void start_simulation(){
        Thread simulation_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int step = 0; step < Simulation.TIME_STEP; step+=10) {
                    simulation.update_pond();
                    update_grid_labels();
                    try {
                        Thread.sleep(1000); // Pause for 1 second between steps
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        simulation_thread.start();
    }

    public void update_text_area(){
        textArea.setText(simulation.getPond().toString());
    }
}

