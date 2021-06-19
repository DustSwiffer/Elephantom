package dustswiffer.nl.elephantom;

import dustswiffer.nl.elephantom.Commands.DebugCommand;
import dustswiffer.nl.elephantom.Servicces.PhantomService;
import dustswiffer.nl.elephantom.Servicces.TimeService;
import dustswiffer.nl.elephantom.Listeners.PhantomListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public final class Elephantom extends JavaPlugin {

    public String OverWorldName;
    public int MinTime, MaxTime;
    public boolean PhantomSpawned;

    @Override
    public void onEnable() {
        PluginManager pluginManager = this.getServer().getPluginManager();

        PhantomListener phantomListener = new PhantomListener(this);

        pluginManager.registerEvents(phantomListener, this);
        TimeService timeService = new TimeService(this);
        PhantomService phantomService = new PhantomService(this);

        getCommand("e-debug").setExecutor(new DebugCommand(this));

        getOverWorldName();

        MinTime = ThreadLocalRandom.current().nextInt(13188, 21000);
        MaxTime = ThreadLocalRandom.current().nextInt(MinTime, 21000);
        PhantomSpawned = false;

        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if(timeService.IsDay(0, MinTime)) {
                PhantomSpawned = false;
            }

            if(!PhantomSpawned) {
                if(timeService.IsNight(MinTime, MaxTime)) {

                    MinTime = ThreadLocalRandom.current().nextInt(13188, 21000);
                    MinTime = ThreadLocalRandom.current().nextInt(MinTime, 21000);

                    phantomService.spawn();
                    PhantomSpawned = true;
                }
            }
        }, 20l, 10l);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void getOverWorldName(){
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("server.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.OverWorldName = properties.getProperty("level-name");
    }
}
