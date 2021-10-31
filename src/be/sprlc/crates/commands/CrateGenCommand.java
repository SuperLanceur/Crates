package be.sprlc.crates.commands;

import be.sprlc.crates.CrateGenerator;
import be.sprlc.crates.CrateGui;
import be.sprlc.crates.Crates;
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
            case "crate":
                CrateGenerator.giveCrateToPlayer(player);
                break;
            case "tripwire":
            case "tp":
            case "key":
                CrateGenerator.giveKeyToPlayer(player);
                //generate tp
                break;
            case "debug":
                Crates.getInstance().getDrops().forEach(drop -> {
                    player.sendMessage(drop.toString());
                    drop.getLore().forEach(lore -> {
                        player.sendMessage(drop+ " " + lore);
                    });
                });
            default:
                player.sendMessage("§cUnknown command");
                break;
        }
        return false;
    }
}
