package game;


import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if(FlappyBird.mode == FlappyBird.Mode.MENU){
            if(mouseX >= (FlappyBird.width / 2) - 70 && mouseX <= (FlappyBird.width / 2) + 50){
                if(mouseY >= 300 && mouseY <= 360){
                    FlappyBird.mode = FlappyBird.Mode.GAME;
                }
            }
        }

        if(FlappyBird.mode == FlappyBird.Mode.DEAD){
            if(mouseX >= 200 && mouseX <= 300){
                if(mouseY >= 200 && mouseY <= 250){
                    FlappyBird.topObstacleX3 = 880;
                    FlappyBird.topObstacleX2 = 690;
                    FlappyBird.topObstacleX = 500;
                    FlappyBird.mode = FlappyBird.Mode.GAME;
                    FlappyBird.birdX = 220;
                    FlappyBird.birdY = 230;
                    FlappyBird.acceleration = 0.5;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
