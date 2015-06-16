package myscripts.com.ikov.cswing;

import myscripts.com.ikov.cswing.strategies.HandleLogin;
import myscripts.com.ikov.cswing.strategies.Swing;
import myscripts.com.ikov.cswing.strategies.Walk;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.rev317.min.Loader;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.Tile;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Capslock
 * On 16/06/15
 * With IntelliJ
 */
@ScriptManifest(author = "Capslock",
        category = Category.AGILITY,
        description = "Does the rope swing in the wildy for xp",
        name = "cRopeSwing",
        servers = { "Ikov" },
        version = 1.0)
public class Core extends Script implements MessageListener, Paintable {
    private final Color color1 = new Color(51, 255, 51);
    private final Color color2 = new Color(255, 255, 255);
    private final Font font1 = new Font("Arial", 0, 14);
    private final Timer RUN_TIMER = new Timer();
    private final int START_EXP = Skill.AGILITY.getExperience();

    @Override
    public boolean onExecute() {
        provide(Arrays.asList(new HandleLogin(), new Swing(), new Walk()));
        return true;
    }

    @Override
    public void messageReceived(MessageEvent m) {
        if (m.getType() == 0) {
            if ((m.getMessage().contains("object does not exist")) || (m.getMessage().contains("too far away")) || (m.getMessage().contains("unable to receive")) || (m.getMessage().contains("is already on your")) || (m.getMessage().contains("command")) || (m.getMessage().contains("invalid")) || (m.getMessage().contains("party"))) {
                dropClient();
                Time.sleep(1000);
            }
        }
        if (m.getMessage().contains("Asd")) {
            System.out.println(Players.getMyPlayer().getLocation().toString());
        }
    }

    /**
     * @author Thank you very much El Maestro for this code <3
     */
    public static boolean areTilesTheSame(Tile first, Tile second) {
        return first.getX() == second.getX() && first.getPlane() == second.getPlane() && first.getY() == second.getY();
    }

    /**
     * @author Thank you very much Empathy for this code <3
     */
    private void dropClient() {
        try {
            Method dropClient = Loader.getClient().getClass().getDeclaredMethod("V");
            dropClient.setAccessible(true);
            dropClient.invoke(Loader.getClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String formatBigNumber(int num){
        return num > 100000000 ? num/1000000+"m" : (num > 100000 ? num/1000+"k" : ""+num);
    }

    @Override
    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        g.setColor(color1);
        g.fillRoundRect(554, 218, 180, 242, 16, 16);
        g.setFont(font1);
        g.setColor(color2);
        int expGained = Skill.AGILITY.getExperience() - START_EXP;
        int expHr = RUN_TIMER.getPerHour(expGained);
        int lvlStart = Skill.AGILITY.getLevel();
        g.drawString("Run Time: " + RUN_TIMER.toString(), 561, 249);
        g.drawString("Exp gained: " + formatBigNumber(expGained)+" ("+formatBigNumber(expHr)+")" , 561, 350);
        g.drawString("Agility Level: " + lvlStart, 561, 450);
    }
}