
package Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public abstract class Block {
    
    protected double x, y;
    protected double size;
    
    public Block(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    
    public abstract void render(Graphics2D g);
    
    public boolean intersectsRight(double px, double py) {
        return (x + size >= px) && getBounds().contains(px, py);
    }
    
    public boolean intersectsLeft(double px, double py) {
        return (x <= px) && getBounds().contains(px, py);
    }
    
    public boolean intersectsTop(double px, double py) {
        return (y <= py) && getBounds().contains(px, py);
    }
    
    public boolean intersectsBottom(double px, double py) {
        return (y + size >= py) && getBounds().contains(px, py);
    }
    
    public Rectangle2D getBounds() {
        return new Rectangle((int)x, (int)y, (int)size, (int)size);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSize() {
        return size;
    }
    
    
    
}
