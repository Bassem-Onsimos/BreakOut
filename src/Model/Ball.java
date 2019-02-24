
package Model;

import Game.Game;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

public class Ball {
    
    private double x, y;
    private double initialY;
    private double dx, dy;
    //
    private final double size = 20;
    //
    private final double speed;
    //
    private boolean gameStarted;
    private boolean ballReleased;
    private boolean touchingBar;
    private boolean touchingWall;
    //
    private double angleOfProjection;
    //
    private Game game;
    //
    
    
    public Ball(Game game, double bottomMargin) {
        this.game = game;
        
        ballReleased = false;
        gameStarted = false;
        
        y = game.getHeight() - (bottomMargin + size);
        initialY = y;
        x = game.getWidth()/2.0 - size/2.0;
        speed = game.getHeight() * 0.65;
        angleOfProjection = Math.PI/2.0;
    }
    
    public void update() {
        
        if(!game.getController().hasTargets()) {
            ballReleased = false;
                
            y = initialY;
            angleOfProjection = Math.PI/2.0;
            game.getController().getBar().resetPosition();
            
            game.gameWon();
         }
        
        if(ballReleased) {
        
            if(getBounds().intersects(game.getController().getBar().getBounds().getBounds2D())) {

                if( !touchingBar && 
                    ( game.getController().getBar().intersectsTop(x+size/2.0, y+size) || 
                        game.getController().getBar().intersectsBottom(x+size/2.0, y) ) ){
                    dy *= -1;
                    touchingBar = true;
                }
                if( !touchingBar &&
                    ( game.getController().getBar().intersectsLeft(x+size, y+size/2.0) ||
                        game.getController().getBar().intersectsRight(x, y+size/2.0) ) ) { 
                    dx *= -1;
                    touchingBar = true;
                }
            }
            else
                touchingBar = false;
                   
            for(int i = 0; i<game.getController().getBlocks().size(); i++) {

                Block block = game.getController().getBlocks().get(i);

                if(getBounds().intersects(block.getBounds())) {

                    if(block.intersectsRight(x, y+size/2.0) || block.intersectsLeft(x+size, y+size/2.0)) {
                        
                        if(block instanceof Wall && !touchingWall) {
                            dx *= -1;
                            touchingWall = true;
                        }
                        else if(block instanceof Target) {
                            dx *= -1;
                            game.getController().removeBlock(block);
                            game.getScore().setValue(game.getScore().getValue() + 2);
                        }
                    }  
                    if(block.intersectsBottom(x+size/2.0, y) || block.intersectsTop(x+size/2.0, y+size)) {
                        
                        if(block instanceof Wall && !touchingWall) {
                            dy *= -1;
                            touchingWall = true;
                        }
                        else if(block instanceof Target) {
                            dy *= -1;
                            game.getController().removeBlock(block);
                            game.getScore().setValue(game.getScore().getValue() + 2);
                        }
                    }
                }
                else
                    touchingWall = false;
            }

            x += dx * speed * game.getElapsedTime();
            y += dy * speed * game.getElapsedTime();
            
            if(y > game.getHeight() || x > game.getWidth() || y < 0 || x < 0) {
                ballReleased = false;
                game.getLives().setValue(game.getLives().getValue() - 1);
                
                y = initialY;
                angleOfProjection = Math.PI/2.0;
                game.getController().getBar().resetPosition();
                x = game.getController().getBar().getX() + (game.getController().getBar().getWidth() / 2.0 - size/2.0);

                game.getStopWatch().pause();
                
                if(game.getLives().getValue() == 0) game.gameLost();
                
            }
           
        }
        else {
            x = game.getController().getBar().getX() + (game.getController().getBar().getWidth() / 2.0 - size/2.0);
            
            if(game.getInput().isKey(KeyEvent.VK_A) && angleOfProjection < Math.PI-0.1) {
                angleOfProjection += 3 * game.getElapsedTime();
            }
            
            if(game.getInput().isKey(KeyEvent.VK_D) && angleOfProjection > 0.1) {
                angleOfProjection -= 3 * game.getElapsedTime();
            }
            
            
            if(angleOfProjection < 0.1) angleOfProjection = 0.1;
            if(angleOfProjection > Math.PI-0.1) angleOfProjection = Math.PI-0.1;
            
            if(game.getInput().isKeyUp(KeyEvent.VK_SPACE)) {
                dx = Math.cos(-angleOfProjection);
                dy = Math.sin(-angleOfProjection);
                ballReleased = true;
                
                if(!gameStarted) {
                    game.getStopWatch().start();
                    gameStarted = true;
                }
                
                game.getStopWatch().resume();
                
            }
        }
    }
    
    public void render(Graphics2D g) {
        
        if(!ballReleased){
            g.setColor(Color.yellow);
            g.setStroke(new BasicStroke(7, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.drawLine((int)(x + size/2.0), (int)y, (int)(x + 100*Math.cos(angleOfProjection) + size/2.0), (int)(y - 100*Math.sin(angleOfProjection)));
        }
        
        g.setColor(Color.lightGray);
        g.fillOval((int)x, (int)y, (int)size, (int)size);
        
    }
    
    public Shape getBounds() {
        return new Ellipse2D.Double(x, y, size, size);
    }
    
    
}
