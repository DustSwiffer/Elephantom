package dustswiffer.nl.elephantom.Servicces;

import dustswiffer.nl.elephantom.Elephantom;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.concurrent.ThreadLocalRandom;

public class LocationService {

    private final Elephantom plugin;

    public LocationService(Elephantom plugin) {

        this.plugin = plugin;
    }

    public Location getRandomLocation() {

        int max = plugin.xes.size();

        int randomNumber = ThreadLocalRandom.current().nextInt(1, max);

        int x = plugin.xes.get(randomNumber);
        int z = plugin.zes.get(randomNumber);
        int y = ThreadLocalRandom.current().nextInt(1, 13);

        World world = plugin.getServer().getWorld(plugin.OverWorldName);
        if(world != null){
            Location preLocation = new Location(world, x, y, z);
            Block highestBlock =  world.getHighestBlockAt(preLocation);

            int randomY = ThreadLocalRandom.current().nextInt(highestBlock.getY() + 10,highestBlock.getY() + 1 );

            return new Location(world, x, randomY, z);
        }

        return null;
    }
}
