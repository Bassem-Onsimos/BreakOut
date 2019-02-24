
package Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Wall extends Block {
    
    public Wall(double x, double y, double size) {
        super(x, y, size);
    }
    
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.darkGray);
        g.fillRect((int)x, (int)y, (int)size, (int)size);
        /*
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(3));
        g.drawRect((int)x, (int)y, (int)size, (int)size);
        */
    }
    
}
