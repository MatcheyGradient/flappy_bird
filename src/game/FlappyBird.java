package game;

import windows.DeadMenu;
import windows.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import static java.awt.Color.*;


public class FlappyBird extends JPanel implements ActionListener {

    Rectangle birdHitbox = new Rectangle();
    Rectangle groundBox = new Rectangle(0, 450, 500, 50);
    Rectangle topBox = new Rectangle(0, 0, width, 1);

    // start of first obstacle
    Rectangle topObstacle1 = new Rectangle();
    Rectangle bottomObstacle1 = new Rectangle();
    int topObstacleHeight; // 420
    static int topObstacleX = 500;
    // end of first obstacle

    // start of third obstacle
    Rectangle topObstacle2 = new Rectangle();
    Rectangle bottomObstacle2 = new Rectangle();
    int topObstacleHeight2; // 420
    static int topObstacleX2 = 690;
    // end of second obstacle

    // start of third obstacle
    Rectangle topObstacle3 = new Rectangle();
    Rectangle bottomObstacle3 = new Rectangle();
    int topObstacleHeight3; // 420
    static int topObstacleX3 = 880;
    // end of third obstacle

    public final static int width = 500;
    public final static int height = 500;
    static int birdX = 220;
    static int birdY = 230;
    static double acceleration = 0.5;
    boolean running = false;
    int delay = 50;
    Timer timer;
    Random random = new Random();
    Menu menu;
    DeadMenu dMenu;
    public static Mode mode = Mode.MENU;

    public static int points;
    int pointOb1;
    int pointOb2;
    int pointOb3;


    public FlappyBird(){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(0, 255, 255, 255));
        this.setFocusable(true);
        this.addKeyListener(new game.FlappyBird.keyListener());
        this.addMouseListener(new game.MouseListener());
        timer = new Timer(delay, this);
        menu = new Menu();
        dMenu = new DeadMenu();
        startGame();
    }

    public int getRandHeight(){
        return random.nextInt(220) + 200;
    }

    public void startGame() {
        timer.start();
        running = true;

        topObstacleHeight = getRandHeight();
        topObstacleHeight2 = getRandHeight();
        topObstacleHeight3 = getRandHeight();
    }

    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }

    public void draw(Graphics g){
        birdHitbox = new Rectangle(birdX, birdY, 20, 20);

        Graphics2D g2d = (Graphics2D) g;


        if(mode == Mode.MENU){
            menu.render(g);
        }
        g.setColor(red);
        //g2d.draw(birdHitbox); // draws hitbox of bird

        g.fillOval(birdX, birdY, 20, 20); // draws bird

        g.setColor(green);
        g.fillRect(topObstacleX, -topObstacleHeight, 50, 500);
        g.fillRect(topObstacleX,-topObstacleHeight + 580, 50, 500);

        g.fillRect(topObstacleX2, -topObstacleHeight2, 50, 500);
        g.fillRect(topObstacleX2,-topObstacleHeight2 + 580, 50, 500);

        g.fillRect(topObstacleX3, -topObstacleHeight3, 50, 500);
        g.fillRect(topObstacleX3,-topObstacleHeight3 + 580, 50, 500);

        topObstacle1 = new Rectangle(topObstacleX, -topObstacleHeight, 50, 500);
        bottomObstacle1 = new Rectangle(topObstacleX,-topObstacleHeight + 580, 50, 500);

        topObstacle2 = new Rectangle(topObstacleX2, -topObstacleHeight2, 50, 500);
        bottomObstacle2 = new Rectangle(topObstacleX2,-topObstacleHeight2 + 580, 50, 500);

        topObstacle3 = new Rectangle(topObstacleX3, -topObstacleHeight3, 50, 500);
        bottomObstacle3 = new Rectangle(topObstacleX3,-topObstacleHeight3 + 580, 50, 500);

        g.setColor(black);
        g2d.draw(topObstacle1);
        g2d.draw(bottomObstacle1);

        g2d.draw(topObstacle2);
        g2d.draw(bottomObstacle2);

        g2d.draw(topObstacle3);
        g2d.draw(bottomObstacle3);

        g.setColor(new Color(232, 222, 188, 255));
        g.fillRect(0, 450, 500, 50); // draws ground

        g.setColor(black);
        g2d.draw(groundBox);

        if(mode == Mode.GAME){
            g.setFont(new Font("arial", Font.BOLD, 40));
            FontMetrics fm = g.getFontMetrics();
            int x = (FlappyBird.width - fm.stringWidth(String.valueOf(points))) / 2;
            g.drawString(String.valueOf(points), x, 35);
        }

        if(mode == Mode.DEAD){
            dMenu.render(g);
        }

    }

    public void moveBird(){ // !!!!DUMBASS "GRAVITY" SYSTEM!!!!
        birdY += acceleration;
        if(acceleration < 10){
            acceleration += 3;
        }
    }

    public void moveTiles(){ // move tiles
        topObstacleX -= 5;
        topObstacleX2 -= 5;
        topObstacleX3 -= 5;
    }

    public void hitBoxCheck(){ // ok hb check
        if(birdHitbox.intersects(groundBox) || birdHitbox.intersects(topBox)){
            points = 0;
            mode = Mode.DEAD;
        }
        if(birdHitbox.intersects(topObstacle1) || birdHitbox.intersects(bottomObstacle1)){
            points = 0;
            mode = Mode.DEAD;
        }

        if(birdHitbox.intersects(topObstacle2) || birdHitbox.intersects(bottomObstacle2)){
            mode = Mode.DEAD;
        }

        if(birdHitbox.intersects(topObstacle3) || birdHitbox.intersects(bottomObstacle3)){
            mode = Mode.DEAD;
        }
    }

    public void pointCheck(){ // budget af point check
        if(pointOb1 == 0 && birdX > topObstacleX + 10 && birdX < topObstacleX + 20){
            points++;
            pointOb1++;
            pointOb2 = 0;
            pointOb3 = 0;
        }
        if(pointOb2 == 0 && birdX > topObstacleX2 + 10 && birdX < topObstacleX2 + 20){
            points++;
            pointOb2++;
            pointOb1 = 0;
            pointOb3 = 0;
        }
        if(pointOb3 == 0 && birdX > topObstacleX3 + 10 && birdX < topObstacleX3 + 20){
            points++;
            pointOb3++;
            pointOb1 = 0;
            pointOb2 = 0;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            if(mode == Mode.GAME){
                moveBird();
                moveTiles();
                if(topObstacleX < -50){
                    topObstacleHeight = getRandHeight();
                    topObstacleX = width;
                }
                if(topObstacleX2 < -50){
                    topObstacleHeight2 = getRandHeight();
                    topObstacleX2 = width;
                }
                if(topObstacleX3 < -50){
                    topObstacleHeight3 = getRandHeight();
                    topObstacleX3 = width;
                }
                hitBoxCheck();
                pointCheck();
            }
        }
        repaint();
    }

    public static class keyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE ){
                acceleration = -10;
            }
        }
    }

    public enum Mode{
        GAME,
        MENU,
        DEAD
    }

}
