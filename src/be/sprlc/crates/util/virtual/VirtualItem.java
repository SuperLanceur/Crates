package be.sprlc.crates.util.virtual;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class VirtualItem {

    public String itemName;
    public String[] itemDescription;
    public ItemStack itemStack;
    public boolean enchant;

    public VirtualItem(final String itemName, final String[] itemDescription, final ItemStack itemStack) {
        this.enchant = false;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemStack = itemStack;
    }

    public VirtualItem(final int i, final String[] strings, final ItemStack itemStack2) {
        this.enchant = false;
    }

    public abstract void onUse(final Player p0);

}
