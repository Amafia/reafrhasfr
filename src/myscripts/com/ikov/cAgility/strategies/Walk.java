package myscripts.com.ikov.cAgility.strategies;

import myscripts.com.ikov.cAgility.Core;
import myscripts.com.ikov.cAgility.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Walking;
import org.rev317.min.api.wrappers.Area;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by Capslock
 * On 19/06/15
 * With IntelliJ
 */
public class Walk implements Strategy {

    @Override
    public boolean activate() {
        final Area GNOME_AREA = new Area(new Tile(2482, 3426), new Tile(2482, 3430), new Tile(2489, 3426));
        final Area BARB_AREA = new Area(new Tile(2549, 3549), new Tile(2553, 3549), new Tile(2553, 3547));
        final Area WILD_AREA = new Area(new Tile(3009, 3956), new Tile(3003, 3956), new Tile(3003, 3960));
        return GNOME_AREA.contains(Players.getMyPlayer().getLocation()) || BARB_AREA.contains(Players.getMyPlayer().getLocation()) || WILD_AREA.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        if (Variables.isNetGnome()) {
            final Tile gnome = new Tile(2484, 3425);
            Walking.walkTo(gnome);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Core.areTilesTheSame(Players.getMyPlayer().getLocation(), gnome);
                }
            }, 2500);
        } else if (Variables.isSwingBarb()) {
            final Tile barb = new Tile(2551, 3554);
            Walking.walkTo(barb);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Core.areTilesTheSame(Players.getMyPlayer().getLocation(), barb);
                }
            }, 2500);
        } else if (Variables.isSwingWild()) {
            final Tile wild = new Tile(3005, 3953);
            Walking.walkTo(wild);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Core.areTilesTheSame(Players.getMyPlayer().getLocation(), wild);
                }
            }, 2500);
        }
    }
}