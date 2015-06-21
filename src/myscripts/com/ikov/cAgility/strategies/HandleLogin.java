package myscripts.com.ikov.cAgility.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Menu;

import java.awt.event.KeyEvent;

/**
 * Created by Capslock
 * On 31/05/15
 * With IntelliJ
 */
public class HandleLogin implements Strategy {

    public boolean activate()
    {
        return !Game.isLoggedIn();
    }

    public void execute() {
        if (!Game.isLoggedIn()) {
            Time.sleep(5000);
            Keyboard.getInstance().clickKey(KeyEvent.VK_ENTER);
            Time.sleep(new SleepCondition() {
                public boolean isValid() {
                    return Game.isLoggedIn();
                }
            }, 5000);
        }
        if (Game.isLoggedIn()) {
            Time.sleep(4000);
            Menu.sendAction(679, 17825792, 113, 4907, 1088, 1);
            Time.sleep(500);
        }
    }
}
