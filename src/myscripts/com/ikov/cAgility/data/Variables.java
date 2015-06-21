package myscripts.com.ikov.cAgility.data;

/**
 * Created by Capslock
 * On 19/06/15
 * With IntelliJ
 */
public class Variables {

    private static boolean netGnome;
    private static boolean swingBarb;
    private static boolean swingWild;

    public static boolean isNetGnome() {
        return netGnome;
    }

    public static boolean isSwingBarb() {
        return swingBarb;
    }

    public static boolean isSwingWild() {
        return swingWild;
    }

    public static void setNetGnome(boolean netGnome) {
        Variables.netGnome = netGnome;
    }

    public static void setSwingBarb(boolean swingBarb) {
        Variables.swingBarb = swingBarb;
    }

    public static void setSwingWild(boolean swingWild) {
        Variables.swingWild = swingWild;
    }
}
