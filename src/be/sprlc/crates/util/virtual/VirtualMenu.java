package be.sprlc.crates.util.virtual;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.sprlc.crates.Crates;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class VirtualMenu implements Listener
{
    private Player player;
    private String menuName;
    private int menuRow;
    private Inventory menuInventory;
    private BukkitRunnable runnable;
    private Map<String, Object> properties;
    protected HashMap<Integer, VirtualItem> eventClick;

    public VirtualMenu(final Player player, final String menuName, final int menuRow) {
        this.eventClick = new HashMap<Integer, VirtualItem>();
        this.player = player;
        this.menuName = menuName;
        this.menuRow = menuRow;
        this.menuInventory = Bukkit.createInventory(null, this.menuRow * 9, this.menuName);
        this.runnable = new BukkitRunnable() {
            @Override
            public void run() {
                update();
            }
        };
        this.properties = new HashMap<>();
        this.eventClick.clear();
    }

    public void open() {
        this.player.openInventory(this.menuInventory);
        this.runnable.runTaskTimer(Crates.getInstance(), 1, 1);
        if (!HandlerList.getHandlerLists().contains(this))
            Bukkit.getPluginManager().registerEvents(this, Crates.getInstance());
    }

    public void destroy() {
        HandlerList.unregisterAll(this);
        this.runnable.cancel();
        if (this.player.getOpenInventory() != null && this.player.getOpenInventory().getTitle().equals(this.menuName)) {
            this.player.closeInventory();
        }
        this.eventClick.clear();
        this.menuInventory.clear();
    }

    public void addItem(final VirtualItem item, final int slot) {
        ItemStack itemStack = item.itemStack;
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(item.itemName);
        final List<String> list = new ArrayList<>();
        for (final String desc : item.itemDescription) {
            list.add(desc);
        }
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        if (item.enchant) {
            itemStack = this.addGlow(itemStack);
        }
        this.eventClick.put(slot, item);
        this.menuInventory.setItem(slot, itemStack);
    }

    private ItemStack addGlow(final ItemStack item) {
        final net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = null;
        if (!nmsStack.hasTag()) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }
        if (tag == null) {
            tag = nmsStack.getTag();
        }
        final NBTTagList ench = new NBTTagList();
        tag.set("ench", ench);
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(final InventoryClickEvent e) {
        final Player player = (Player)e.getWhoClicked();
        final Inventory inv = e.getInventory();
        if (this.menuName.equals(inv.getName()) && this.player.equals(player)) {
            e.setCancelled(true);
            if (!e.getSlotType().equals(InventoryType.SlotType.CONTAINER)) {
                return;
            }
            if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta() || e.getCurrentItem().getItemMeta() == null) {
                return;
            }
            if (!this.eventClick.containsKey(e.getSlot())) {
                return;
            }
            final VirtualItem virtualItem = this.eventClick.get(e.getSlot());
            virtualItem.onUse(player);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryPickup(final InventoryPickupItemEvent e) {
        final Inventory inv = e.getInventory();
        if (this.menuName.equals(inv.getName()) && this.player.equals(this.player)) {
            e.setCancelled(true);
            this.destroy();
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        Inventory inv = e.getInventory();

        if ((this.menuName.equals(inv.getName())) && (this.player.equals(player)))
            destroy();
    }

    public void setProperty(String name, Object value){
        properties.put(name, value);
    }

    public <T> T getProperty(String name){
        return (T) properties.get(name);
    }

    public abstract void update(); // not sure if it should be abstract
    /*@EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClose(final InventoryCloseEvent e) {
        final Player player = (Player)e.getPlayer();
        final Inventory inv = e.getInventory();
        if (this.menuName.equals(inv.getName()) && this.player.equals(player)) {
            //this.destroy();
            Bukkit.getScheduler().runTaskLater(BedWars.getInstance(), new Runnable() {
                @Override
                public void run() {
                    player.openInventory(inv);
                }
            }, 1L);
        }
    }*/


}
