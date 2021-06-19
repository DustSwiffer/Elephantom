package dustswiffer.nl.elephantom.Servicces;

import dustswiffer.nl.elephantom.Elephantom;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

import java.util.concurrent.ThreadLocalRandom;

public class PhantomService {

    private final Elephantom plugin;
    private final LocationService locationService;

    public PhantomService(Elephantom plugin) {
        this.plugin = plugin;
        this.locationService = new LocationService(plugin);
    }

    public void spawn() {
        int count = plugin.getServer().getOnlinePlayers().size();
        if (count > 0) {
            int multiplier = ThreadLocalRandom.current().nextInt(1, 3);
            int spawnAmount = count * multiplier;

            World world = plugin.getServer().getWorld(plugin.OverWorldName);

            if (world != null) {
                for (int i = 0; i < spawnAmount + 1; i++) {
                    Location spawnLocation = locationService.getRandomLocation();
                    world.spawnEntity(spawnLocation, EntityType.PHANTOM);
                }
            }
        }

    }
}
