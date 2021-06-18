package dustswiffer.nl.elephantom.Listeners;

import dustswiffer.nl.elephantom.Elephantom;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import static jdk.dynalink.linker.support.Guards.isNotNull;

public class ChunkListener implements Listener {

    private Elephantom plugin;

    public ChunkListener(Elephantom plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChunkLoad(ChunkLoadEvent event) {

        final Chunk currentChunk = event.getChunk();

        plugin.getXes().add(currentChunk.getX());
        plugin.getZes().add(currentChunk.getZ());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChunkUnload(ChunkUnloadEvent event) {

        final Chunk currentChunk = event.getChunk();
        Integer place = null;

        if(plugin.getXes().contains(currentChunk.getX())) {
            
            for(int i = 0; i < plugin.getXes().toArray().length ; i++){
                if((plugin.getXes().get(i).equals(currentChunk.getX()) && plugin.getZes().get(i).equals(currentChunk.getZ()))) {
                    place = i;
                    break;
                }
            }

            if(place != null) {
                if (plugin.getXes().get(place).equals(currentChunk.getX()) &&
                        plugin.getZes().get(place).equals(currentChunk.getZ())) {
                    plugin.getXes().remove(place);
                    plugin.getZes().remove(place);
                }
            }
        }
    }
}
