package dustswiffer.nl.elephantom.Servicces;

import dustswiffer.nl.elephantom.Elephantom;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LocationService {

    private final Elephantom plugin;

    public LocationService(Elephantom plugin) {

        this.plugin = plugin;
    }

    public Location getRandomLocation() {
        World world = plugin.getServer().getWorld(plugin.OverWorldName);
        if (world != null) {
            Chunk[] chunks = world.getLoadedChunks();

            if (chunks.length > 0) {

                Random random = new Random();
                int number = random.nextInt(chunks.length);

                Chunk chunk = chunks[number];

                int x = chunk.getX();
                int z = chunk.getZ();

                Location preLocation = new Location(world, x, 1, z);
                Block highestBlock = world.getHighestBlockAt(preLocation);

                int randomY = ThreadLocalRandom.current().nextInt(highestBlock.getY() + 15, highestBlock.getY() + 36);

                return new Location(world, x, randomY, z);
            }

        }

        return null;
    }
}
