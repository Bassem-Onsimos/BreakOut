
package GameMenus;

import Game.Game;
import GameEngine.GameState.State;
import GameMenu.AbstractMenu;
import GameMenu.MenuItem;

public class PauseMenu extends AbstractMenu {
    
    Game game;
    
    public PauseMenu(Game game) {
        super(game);
        this.game = game;
    }

    @Override
    public void initiate() {
        setBackgroundOpacity(transparent);
        
        addItem(new MenuItem("Continue") {
            @Override
            public void function() {
                game.setState(State.inGame);
            }
        });
        
        addItem(new MenuItem("New Game") {
            @Override
            public void function() {
                game.setState(State.inGame);
                game.resetStats();
                game.reset();
            }
        });
        
        addItem(new MenuItem("Main Menu") {
            @Override
            public void function() {
                game.setState(State.startMenu);
            }
        });
        
        addItem(new MenuItem("Exit") {
            @Override
            public void function() {
                System.exit(0);
            }
        });
    }
}
