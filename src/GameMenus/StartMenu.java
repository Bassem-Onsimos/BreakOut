
package GameMenus;

import Game.Game;
import Game.Size;
import GameEngine.GameState.State;
import GameMenu.AbstractMenu;
import GameMenu.MenuItem;
import GameMenu.SubMenuInitializer;

public class StartMenu extends AbstractMenu {
    
    private Game game;
    
    public StartMenu(Game game) {
        super(game);
        this.game = game;
    }

    @Override
    public void initiate() {
        
        addItem(new MenuItem("New Game") {
            @Override
            public void function() {
                game.setState(State.inGame);
                game.resetStats();
                game.reset();
            }
        });
        
        addItem(new SubMenuInitializer("Size") {
            @Override
            public void initiate() {
                addSubMenuItem(new MenuItem("Small") {
                    @Override
                    public void function() {
                        game.setGameSize(Size.small);
                    }
                });
                addSubMenuItem(new MenuItem("Medium") {
                    @Override
                    public void function() {
                        game.setGameSize(Size.medium);
                    }
                });
                addSubMenuItem(new MenuItem("Large") {
                    @Override
                    public void function() {
                        game.setGameSize(Size.large);
                    }
                });
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
