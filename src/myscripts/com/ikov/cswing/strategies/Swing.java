package myscripts.com.ikov.cswing.strategies;

import myscripts.com.ikov.cswing.Core;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Area;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by Capslock
 * On 16/06/15
 * With IntelliJ
 */
public class Swing implements Strategy {

    @Override
    public boolean activate() {
        final Area SWINGLOCATION = new Area(new Tile(3003, 3949), new Tile(3003, 3954), new Tile(3009, 3954));
        return SWINGLOCATION.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        SceneObject[] swingId = SceneObjects.getNearest(2283);
        if (swingId != null && Players.getMyPlayer().getAnimation() == -1) {
            swingId[0].interact(SceneObjects.Option.CHOP_DOWN);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    final Tile b = new Tile(3005, 3958);
                    return Core.areTilesTheSame(Players.getMyPlayer().getLocation(), b);
                }
            }, 1000);
        }
    }
}
