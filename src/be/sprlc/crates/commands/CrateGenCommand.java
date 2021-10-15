package be.sprlc.crates.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrateGenCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if(!player.isOp()) return false;
        if(args.length != 1) return false;
        switch (args[0]){
            case "chest":
                // generate chest and give it
                break;
            case "tripwire":
            case "tp":

                //generate tp
                break;
            default:
                player.sendMessage("§cUnknown command");
                break;
        }
        return false;
    }
}
