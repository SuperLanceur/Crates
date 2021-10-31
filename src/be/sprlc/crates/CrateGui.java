package be.sprlc.crates;

import be.sprlc.crates.util.ItemBuilder;
import be.sprlc.crates.util.virtual.VirtualItem;
import be.sprlc.crates.util.virtual.VirtualMenu;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CrateGui extends VirtualMenu {

    private Player player;
    private Random random;
    private int i = 0;

    public CrateGui(Player player){
        super(player, "§a§lCrate Roll", 3);
        this.player = player;
        this.random = new Random();
        fillItems();
        this.open();
    }

    private void fillItems(){
        this.addItem(new VirtualItem("Salut", new String[]{}, new ItemBuilder(Material.IRON_AXE).build()) {
            @Override
            public void onUse(Player p0) {

            }
        }, 0);
        for (int j = 0; j <= 26; j++) {
            if(j == 9) j = 18;
            this.addItem(generateRandomColorPane(), j);
        }
    }

    @Override
    public void update() {
        i++;
        this.player.sendMessage(i+"");
        for (int j = 0; j <= 26; j++) {
            if(j == 9) j = 18;
            this.addItem(generateRandomColorPane(), j);
        }
    }

    private VirtualItem generateRandomColorPane(){
        ItemStack pane = new ItemBuilder(Material.STAINED_GLASS_PANE).build();
        pane.setDurability((short)random.nextInt(15));
        return new VirtualItem(" ", new String[]{}, pane) {
            @Override
            public void onUse(Player p0) {

            }
        };
    }

    private VirtualItem generateWhiteColorPane(){
        ItemStack pane = new ItemBuilder(Material.STAINED_GLASS_PANE).build();
        //pane.setDurability((short)random.nextInt(1));
        return new VirtualItem(" ", new String[]{}, pane) {
            @Override
            public void onUse(Player p0) {

            }
        };
    }

    private VirtualItem generateBlackColorPane(){
        ItemStack pane = new ItemBuilder(Material.STAINED_GLASS_PANE).build();
        pane.setDurability((short)15);
        return new VirtualItem(" ", new String[]{}, pane) {
            @Override
            public void onUse(Player p0) {

            }
        };
    }
}
