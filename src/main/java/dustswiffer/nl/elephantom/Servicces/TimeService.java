package dustswiffer.nl.elephantom.Servicces;

import dustswiffer.nl.elephantom.Elephantom;
import org.bukkit.World;

public class TimeService {

    private final Elephantom plugin;

    public TimeService(Elephantom plugin) {
        this.plugin = plugin;
    }

    public long getTime() {
        World world = plugin.getServer().getWorld(plugin.OverWorldName);
        if (world != null) {
            return world.getTime();
        }

        return 0;
    }

    public boolean IsNight(int min, int max) {
        World world = plugin.getServer().getWorld(plugin.OverWorldName);
        if (world != null) {
            long time = world.getTime();

            return time > min && time < max;
        }
        return false;
    }

    public boolean IsDay(int min, int max) {
        World world = plugin.getServer().getWorld(plugin.OverWorldName);
        if (world != null) {
            long time = world.getTime();

            return time > min && time < max;
        }

        return false;
    }
}
