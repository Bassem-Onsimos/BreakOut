
package Model;

import Game.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

public class Bar {
    
    private double x, y;
    private double width, height = 20.0;
    //
    private Game game;
    //
    private double blockSize;
    private double bottomMargin;
    //
    private double speed;
    
    public Bar(Game game, double blockSize, double bottomMargin) {
        this.game = game;
        speed = game.getHeight() * 0.65 + 50;
        
        width = game.getWidth()/4.0;
        
        x = (game.getWidth() / 2.0) - (width / 2.0);
        y = game.getHeight() - bottomMargin;
        
        this.blockSize = blockSize;
        this.bottomMargin = bottomMargin;
    }
    
    public void update() {        
        if(game.getInput().isKey(KeyEvent.VK_RIGHT)) x += speed * game.getElapsedTime();
        if(game.getInput().isKey(KeyEvent.VK_LEFT)) x -= speed * game.getElapsedTime();

        if(x < blockSize) x = blockSize;
        if(x > game.getWidth() - (blockSize + width) ) x = game.getWidth() - (blockSize + width);
        
    }
    
    public void render(Graphics2D g) {
        g.setColor(Color.red);
        g.fillRoundRect((int)x, (int)y, (int)width, (int)height, (int)height, (int)height);
    }
    
    public boolean intersectsRight(double px, double py) {
        return (x + width >= px) && getBounds().contains(px, py);
    }
    
    public boolean intersectsLeft(double px, double py) {
        return (x <= px) && getBounds().contains(px, py);
    }
    
    public boolean intersectsTop(double px, double py) {
        return (y <= py) && getBounds().contains(px, py);
    }
    
    public boolean intersectsBottom(double px, double py) {
        return (y + height >= py) && getBounds().contains(px, py);
    }
    
    public void resetPosition() {
        x = (game.getWidth() / 2.0) - (width / 2.0);
        y = game.getHeight() - bottomMargin;
    }
    
    public RoundRectangle2D getBounds() {
        return new RoundRectangle2D.Double(x, y, width, height, height, height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
    
    
    
}
