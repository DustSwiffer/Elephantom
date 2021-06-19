package dustswiffer.nl.elephantom.Commands;

import dustswiffer.nl.elephantom.Elephantom;
import dustswiffer.nl.elephantom.Servicces.TimeService;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DebugCommand implements CommandExecutor {

    private final Elephantom plugin;
    private final TimeService timeService;

    public DebugCommand(Elephantom plugin) {
        this.plugin = plugin;
        this.timeService = new TimeService(this.plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String labels, String[] args) {
        if (sender.hasPermission("elephantom.debug")) {
            World world = plugin.getServer().getWorld(plugin.OverWorldName);
            if (world != null) {
                Chunk[] chunks = world.getLoadedChunks();
                if (chunks.length > 0) {
                    sender.sendMessage(ChatColor.GREEN.toString() + "Amount of loaded chunks: " + ChatColor.WHITE.toString() + chunks.length);
                } else {
                    sender.sendMessage(ChatColor.RED.toString() + "THere are no active chunks");
                }
            }
            sender.sendMessage(ChatColor.GREEN.toString() + "---------------------------");
            sender.sendMessage(ChatColor.GOLD.toString() + "Next spawn wave between: " +
                    ChatColor.WHITE.toString() + plugin.MinTime +
                    ChatColor.GOLD.toString() + " Ticks and " +
                    ChatColor.WHITE.toString() + plugin.MaxTime +
                    ChatColor.GOLD.toString() + " Ticks");
            sender.sendMessage(ChatColor.GOLD.toString() + "In-game Time: " +
                    ChatColor.WHITE.toString() + timeService.getTime());
        }
        return true;
    }
}
