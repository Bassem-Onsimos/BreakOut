
package Game;

import Model.Ball;
import Model.Bar;
import Model.Block;
import Model.Target;
import Model.Wall;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Controller {

    private Game game;
    //
    private Bar bar;
    private Ball ball;
    private ArrayList<Block> blocks;
    //
    private double blockSize = 25.0;
    private double bottomMargin = 60.0;
    //
    private Random rand;
    //
    private boolean gameSet;
    
    public Controller(Game game) {
        this.game = game;
        
        gameSet = false;
    }
    
    public void newGame() {
        
        bar = new Bar(game, blockSize, bottomMargin);
        ball = new Ball(game, bottomMargin);
        
        blocks = new ArrayList<>();
        
        for(int i = 0; i < game.getWidth() / blockSize; i++) {
            blocks.add(new Wall(i * blockSize, 0, blockSize));
        }
        
        for(int i = 1; i < game.getHeight() / blockSize; i++) {
            blocks.add(new Wall(0, i * blockSize, blockSize));
            blocks.add(new Wall(game.getWidth() - blockSize, i * blockSize, blockSize));
        }
        
        rand = new Random();
        ArrayList<Color> colors = new ArrayList<>();
        
        colors.add(Color.red);
        colors.add(Color.blue);
        colors.add(Color.yellow);
        colors.add(Color.pink);
        colors.add(Color.green);
        colors.add(Color.orange);
                
        for(int i = 2; i < (game.getWidth() / blockSize) - 2; i++ ) {
            for(int j = 2; j < (game.getHeight()/ blockSize) / 2; j++) {
             
                blocks.add(new Target(i * blockSize, j * blockSize, blockSize, colors.get(rand.nextInt(colors.size()))));
            }
        }
        
        gameSet = true;
        
    }
    
    public void update() {
        if(gameSet) { 
            bar.update();
            ball.update();
        }
    }
    
    public void render(Graphics2D g) {      
        if(gameSet) { 
            bar.render(g);
            for(Block block : blocks) block.render(g);
            ball.render(g);
        }
    }

    public Bar getBar() {
        return bar;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
    
    public void removeBlock(Block block) {
        if(blocks.contains(block)) blocks.remove(block);
    }
    
    public boolean hasTargets() {
        for(Block block : blocks) {
            if(block instanceof Target) return true;
        }
        return false;
    }
    
    
}
