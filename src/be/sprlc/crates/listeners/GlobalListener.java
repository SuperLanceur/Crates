package be.sprlc.crates.listeners;

import be.sprlc.crates.CrateGenerator;
import be.sprlc.crates.CrateGui;
import be.sprlc.crates.Crates;
import be.sprlc.crates.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class GlobalListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        // todo check for crate - cancel
        if(event.getClickedBlock() == null) return; //cancel if player clicked in air
        if(event.getClickedBlock().getType() != Material.CHEST) return; //cancel if clicked block is not a chest
        Chest chest = (Chest) event.getClickedBlock().getState();
        if(!chest.getInventory().getName().equals("§aCrate")) return; //cancel if clicked chest is not a crate

        if(player.getItemInHand() == null //cancel if nothing in hand
                || player.getItemInHand().getType() == Material.AIR
                || player.getItemInHand().getItemMeta().getDisplayName() == null //
                || player.getItemInHand().getType() != Material.TRIPWIRE_HOOK) {
            player.sendMessage("§cYou cannot open a crate without a key!");
            event.setCancelled(true);
            return;
        }

        if(!player.getItemInHand().getItemMeta().getDisplayName().equals("§aCrate Key")){
            player.sendMessage("§cNice try! But it's not a valid key!");
            event.setCancelled(true);
            return;
        }
        player.sendMessage(String.valueOf(player.getItemInHand().equals(CrateGenerator.KEY_ITEM)));
        event.setCancelled(true);
        CrateGenerator.removeKeyFromPlayer(player);
        new CrateGui(player);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if(player.getItemInHand().getItemMeta().getDisplayName() == null) return;
        if(player.getItemInHand().equals(CrateGenerator.KEY_ITEM)){
            player.sendMessage("§cYou cannot place a crate key!");
            event.setCancelled(true);
            return;
        }
        if(!(player.getItemInHand().getItemMeta().getDisplayName().equals("§aCrate"))) return;
        player.sendMessage("§aYou just placed a crate !");
        //event.getBlock().setMetadata("IsCrate", new FixedMetadataValue(Crates.getInstance(), "true"));
    }

    // todo créer une fonction pour vérifier qu'une clé est bonne, et l'utiliser dans le onBlockPlace pour l'empecher de les placer
    // todo empêcher de fermer le crategui
}
