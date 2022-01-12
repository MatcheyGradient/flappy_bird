package windows;

import game.FlappyBird;

import java.awt.*;

public class Menu {

    public Rectangle playGame = new Rectangle((FlappyBird.width / 2) - 70, 300, 120, 60);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.BLACK);
        g.setFont(new Font("arial", Font.BOLD, 60));
        FontMetrics fm = g.getFontMetrics();
        int x = (FlappyBird.width - fm.stringWidth("Flappy Bird")) / 2;
        g.drawString("Flappy Bird", x, 100);

        g.setFont(new Font("arial", Font.BOLD, 40));
        g.drawString("Play", playGame.x + 20, playGame.y + 43);

        g2d.draw(playGame);
    }
}
