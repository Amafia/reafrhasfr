package myscripts.com.ikov.cAgility.strategies;

import myscripts.com.ikov.cAgility.data.Variables;
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
 * On 19/06/15
 * With IntelliJ
 */
public class SwingBarb implements Strategy {

    @Override
    public boolean activate() {
        final Area BARB_AREA = new Area(new Tile(2548, 3555), new Tile(2553, 3555), new Tile(2553, 3552));
        return Variables.isSwingBarb() && BARB_AREA.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        SceneObject[] swingId = SceneObjects.getNearest(2282);
        if (swingId != null && Players.getMyPlayer().getAnimation() == -1) {
            swingId[0].interact(SceneObjects.Option.FIRST);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Players.getMyPlayer().getAnimation() != -1;
                }
            }, 2500);
        }
    }
}
