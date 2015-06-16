package myscripts.com.ikov.cswing.strategies;

import myscripts.com.ikov.cswing.Core;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Walking;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by Capslock
 * On 16/06/15
 * With IntelliJ
 */
public class Walk implements Strategy {

    @Override
    public boolean activate() {
        final Tile b = new Tile(3005, 3958);
        return Core.areTilesTheSame(Players.getMyPlayer().getLocation(), b);
    }

    @Override
    public void execute() {
        final Tile a = new Tile(3005, 3953);
        Walking.walkTo(a);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Core.areTilesTheSame(Players.getMyPlayer().getLocation(), a);
            }
        }, 2500);
    }
}