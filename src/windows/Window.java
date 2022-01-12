package windows;

import game.FlappyBird;

import javax.swing.*;

public class Window extends JFrame {

    public Window(){
        this.add(new FlappyBird());
        this.setTitle("Flappy Bird");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

}
