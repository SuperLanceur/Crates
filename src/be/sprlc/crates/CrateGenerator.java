package be.sprlc.crates;

import be.sprlc.crates.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CrateGenerator {

    public static ItemStack generateChest(){
        ItemBuilder chest = new ItemBuilder(Material.CHEST);

        return chest.build();
    }
}
