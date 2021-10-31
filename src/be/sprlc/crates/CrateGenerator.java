package be.sprlc.crates;

import be.sprlc.crates.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CrateGenerator {

    public static final ItemStack KEY_ITEM = new ItemBuilder(Material.TRIPWIRE_HOOK)
            .setDisplayName("§aCrate Key")
            .setGlow(true)
            .setLore("§aUse this key on a crate !").build();

    public static void giveCrateToPlayer(Player player){
        ItemBuilder crate = new ItemBuilder(Material.CHEST)
                .setDisplayName("§aCrate")
                .setLore("§aPlace this crate to allow player to use it !");
        player.getInventory().addItem(crate.build());
    }

    public static void giveKeyToPlayer(Player player){
        /*ItemBuilder key = new ItemBuilder(Material.TRIPWIRE_HOOK)
                .setDisplayName("§aCrate Key")
                .setGlow(true)
                .setLore("§aUse this key on a crate !");*/
        player.getInventory().addItem(KEY_ITEM);
    }

    public static void removeKeyFromPlayer(Player player){
        /*ItemBuilder key = new ItemBuilder(Material.TRIPWIRE_HOOK)
                .setDisplayName("§aCrate Key")
                .setGlow(true)
                .setLore("§aUse this key on a crate !");*/
        player.getInventory().removeItem(KEY_ITEM);
    }

}
