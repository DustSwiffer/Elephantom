package dustswiffer.nl.elephantom;

import dustswiffer.nl.elephantom.Commands.GACCommand;
import dustswiffer.nl.elephantom.Servicces.TimeService;
import dustswiffer.nl.elephantom.Listeners.ChunkListener;
import dustswiffer.nl.elephantom.Listeners.PhantomListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public final class Elephantom extends JavaPlugin {

    public List<Integer> xes = new ArrayList<Integer>();
    public List<Integer> zes = new ArrayList<Integer>();

    public String OverWorldName;
    public int MinTime, MaxTime;
    public boolean PhantomSpawned;

    @Override
    public void onEnable() {
        PluginManager pluginManager = this.getServer().getPluginManager();

        PhantomListener phantomListener = new PhantomListener(this);
        ChunkListener chunkListener = new ChunkListener(this);

        pluginManager.registerEvents(phantomListener, this);
        pluginManager.registerEvents(chunkListener, this);
        TimeService timeService = new TimeService(this);
        getCommand("gac").setExecutor(new GACCommand(this));

        getOverWorldName();

        MinTime = ThreadLocalRandom.current().nextInt(13188, 21000);
        MaxTime = ThreadLocalRandom.current().nextInt(MinTime, 21000);
        PhantomSpawned = false;

        getLogger().info(MinTime + " & " + MaxTime);
        this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, () -> {
            if(timeService.IsDay(0, MinTime)) {
                PhantomSpawned = false;
            }

            if(!PhantomSpawned) {
                if(timeService.IsNight(MinTime, MaxTime)) {

                    MinTime = ThreadLocalRandom.current().nextInt(13188, 21000);
                    MinTime = ThreadLocalRandom.current().nextInt(MinTime, 21000);


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

    public List<Integer> getXes() {
        return xes;
    }

    public void setXes(List<Integer> xes) {
        this.xes = xes;
    }

    public List<Integer> getZes() {
        return zes;
    }

    public void setZes(List<Integer> zes) {
        this.zes = zes;
    }
}
