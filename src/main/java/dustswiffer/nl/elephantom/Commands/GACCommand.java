package dustswiffer.nl.elephantom.Commands;

import dustswiffer.nl.elephantom.Elephantom;
import dustswiffer.nl.elephantom.Servicces.TimeService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GACCommand implements CommandExecutor {

    private final Elephantom plugin;
    private final TimeService timeService;

    public GACCommand(Elephantom plugin){
        this.plugin = plugin;
        this.timeService = new TimeService(this.plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String labels, String[] args) {
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GREEN.toString() + "Active chunks here the phantoms might spawn in:");
        sender.sendMessage("X: " + plugin.xes + " Z: " + plugin.zes);
        sender.sendMessage(ChatColor.GREEN.toString() + "---------------------------");
        sender.sendMessage(ChatColor.GOLD.toString() + "Next spawn wave between: " +
                ChatColor.WHITE.toString() + plugin.MinTime +
                ChatColor.GOLD.toString() + " Ticks and " +
                ChatColor.WHITE.toString() + plugin.MaxTime +
                ChatColor.GOLD.toString() + " Ticks");
        sender.sendMessage(ChatColor.GOLD.toString() + "In-game Time: " +
                ChatColor.WHITE.toString() + timeService.getTime());

        return true;
    }
}
