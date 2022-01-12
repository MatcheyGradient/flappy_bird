package windows;

import game.FlappyBird;

import java.awt.*;

import static java.awt.Color.BLACK;

public class DeadMenu {
    public void render(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        Rectangle replay = new Rectangle(200, 200, 100, 50);


        g.setColor(Color.RED);
        g.setFont(new Font("arial", Font.BOLD, 40));
        FontMetrics fm = g.getFontMetrics();
        int x = (FlappyBird.width - fm.stringWidth("You Died!")) / 2;
        g.drawString("You Died!", x, 100);
        g.setColor(BLACK);
        g.setFont(new Font("arial", Font.BOLD, 40));
        FontMetrics fm2 = g.getFontMetrics();
        int x2 = (FlappyBird.width - fm2.stringWidth(FlappyBird.points + " point(s)")) / 2;
        g.drawString(FlappyBird.points + " point(s)", x2, 150);

        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Play again", replay.x + 3, replay.y + 30);

        g2d.draw(replay);

    }
}
