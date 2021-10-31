package be.sprlc.crates;

import be.sprlc.crates.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Drop {

    private int chance, amount;
    private String name;
    private List<String> lore;

    //todo add material

    public Drop(int chance, int amount, String name, List<String> lore) {
        this.chance = chance;
        this.amount = amount;
        this.name = name;
        this.lore = lore;
    }

    public int getChance() {
        return chance;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public String toString(){
        return "Name: " + name;
    }

    public ItemStack getItem(){
        return new ItemBuilder(Material.STONE).setAmount(amount).setDisplayName(name).build(); //todo add lore
    }
}
