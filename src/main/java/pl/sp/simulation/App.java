package pl.sp.simulation;

import pl.sp.simulation.GUI.GUI;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App extends JFrame {

    private static final long serialVersionUID = 1L;
    private GUI gof;

    public App() {
        setTitle("Fire Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gof = new GUI(this);
        gof.initialize(this.getContentPane());

        this.setSize(1024, 768);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }

}
