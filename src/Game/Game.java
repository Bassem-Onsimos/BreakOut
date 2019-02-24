
package Game;

import GameClock.StopWatch;
import GameEngine.AbstractGame;
import GameEngine.GameState.State;
import GameMenus.PauseMenu;
import GameMenus.PostGameMenu;
import GameMenus.StartMenu;
import GamePanel.GameData;
import GamePanel.IntegerPanelItem;
import java.awt.Graphics2D;

public class Game extends AbstractGame {
    
    private Controller controller;
    //
    private StopWatch stopWatch;
    private IntegerPanelItem lives, score;    
    //
    private PostGameMenu postGameMenu;
    //
    private Size size = Size.small;
    
            
    public Game(int width, int height, float scale) {
        super(width, height, scale);
    }

    @Override
    public void initiate() {
        setFBSlimited(true);
        setDebugInfoDisplayed(false);
        setPausable(true);
        
        controller = new Controller(this);
        
        stopWatch = new StopWatch(this);
        
        lives = new IntegerPanelItem("Lives", 0);
        score = new IntegerPanelItem("Score", 0);
        
        addGamePanel(new GameData() {
            @Override
            public void initiate() {
                addItem(stopWatch);
                addItem(lives);
                addItem(score);
            }
        });
        
        setStartMenu(new StartMenu(this));
        setPauseMenu(new PauseMenu(this));
        
        postGameMenu = new PostGameMenu(this);
        setPostGameMenu(postGameMenu);
        
    }
    
    @Override
    public void reset() {
        controller.newGame();
        stopWatch.reset();
    }

    @Override
    public void update() {
        stopWatch.update();
        controller.update();
    }

    @Override
    public void render(Graphics2D g) {
        controller.render(g);
    }

    public void setGameSize(Size size) {
        this.size = size;
        
        switch(size) {
            case small:{
                setSize(500, 600);
                break;
            }
            case medium:{
                setSize(600, 600);
                break;
            }
            case large:{
                setSize(700, 700);
                break;
            }
        }      
    }
    
    public void resetStats() {
        lives.setValue(5);
        score.setValue(0);
    }
    
    public void gameWon() {
        lives.setValue(lives.getValue() + 5);
        stopWatch.stop();
        postGameMenu.setTitle("You Win!");
        postGameMenu.setSubTitle("Score = " + score.getValue());
        setState(State.postGame);
    }
    
    public void gameLost() {
        stopWatch.stop();
        postGameMenu.setTitle("Game Over");
        postGameMenu.setSubTitle("Score = " + score.getValue());
        resetStats();
        setState(State.postGame);       
    }

    public IntegerPanelItem getLives() {
        return lives;
    }
    
    public IntegerPanelItem getScore() {
        return score;
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }
    
    public Controller getController() {
        return controller;
    }

}
