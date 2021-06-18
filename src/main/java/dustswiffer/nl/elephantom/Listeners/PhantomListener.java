package dustswiffer.nl.elephantom.Listeners;

import dustswiffer.nl.elephantom.Elephantom;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class PhantomListener implements Listener {

    private Elephantom plugin;

    public  PhantomListener(Elephantom plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPhantomSpawn(CreatureSpawnEvent event) {
        LivingEntity entity = event.getEntity();
        if((entity instanceof Phantom) && (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL)) {
            event.setCancelled(true);
        }
    }
}