package myscripts.com.ikov.cAgility;

import myscripts.com.ikov.cAgility.data.Variables;
import myscripts.com.ikov.cAgility.strategies.*;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.Loader;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.api.wrappers.Tile;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Capslock
 * On 19/06/15
 * With IntelliJ
 */
@ScriptManifest(author = "Capslock",
        category = Category.AGILITY,
        description = "Does net and swings from every agility course. (Except Ape Atoll)",
        name = "cAgility",
        servers = { "Ikov" },
        version = 1.0)
public class Core extends Script implements MessageListener {
    private final ArrayList<Strategy> strategies = new ArrayList<>();

    @Override
    public boolean onExecute() {
        Gui gui = new Gui();
        while (gui.isVisible()) {
            sleep(20);
        }
        strategies.add(new HandleLogin());
        if (Variables.isNetGnome()) {
            strategies.add(new NetGnome());
        } else if (Variables.isSwingBarb()) {
            strategies.add(new SwingBarb());
        } else if (Variables.isSwingWild()) {
            strategies.add(new SwingWild());
        }
        strategies.add(new Walk());
        provide(strategies);
        return true;
    }

    /**
     * @author Thank you very much El Maestro for this code <3
     */
    public static boolean areTilesTheSame(Tile first, Tile second) {
        return first.getX() == second.getX() && first.getPlane() == second.getPlane() && first.getY() == second.getY();
    }

    /**
     * @author Thank you very much Agrodon for this code <3
     */
    private void dropClient() {
        try {
            Method dropClient = Loader.getClient().getClass().getDeclaredMethod("W");
            dropClient.setAccessible(true);
            dropClient.invoke(Loader.getClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void messageReceived(MessageEvent msg) {
        String m = msg.getMessage();
        if (msg.getType() == 0) {
            if ((m.contains("object does not exist")) || (m.contains("too far away")) || (m.contains("unable to receive")) || (m.contains("is already on your")) || (m.contains("command")) || (m.contains("invalid")) || (m.contains("party"))) {
                dropClient();
                Time.sleep(1000);
            }
        }
    }
}