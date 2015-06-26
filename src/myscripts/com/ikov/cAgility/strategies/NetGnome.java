package myscripts.com.ikov.cagility.strategies;

import myscripts.com.ikov.cagility.data.Variables;
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
public class NetGnome implements Strategy {

    @Override
    public boolean activate() {
        final Area GNOME_AREA = new Area(new Tile(2482, 3426), new Tile(2482, 3421), new Tile(2489, 3426));
        return Variables.isNetGnome() && GNOME_AREA.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        SceneObject[] netId = SceneObjects.getNearest(2286);
        if (netId != null && Players.getMyPlayer().getAnimation() == -1) {
            netId[0].interact(SceneObjects.Option.FIRST);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Players.getMyPlayer().getAnimation() != -1;
                }
            }, 2500);
        }
    }
}
