
package Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Target extends Block {
    
    Color color;
    
    public Target(double x, double y, double size, Color color) {
        super(x, y, size);
        this.color = color;
    }
    
    public void render(Graphics2D g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, (int)size, (int)size);
        
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(3));
        g.drawRect((int)x, (int)y, (int)size, (int)size);
        
    }
    
}
