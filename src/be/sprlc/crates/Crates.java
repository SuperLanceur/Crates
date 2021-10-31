package be.sprlc.crates;

import be.sprlc.crates.commands.CommandManager;
import be.sprlc.crates.listeners.ListenerManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Crates extends JavaPlugin {

    private static Crates instance;
    private List<Drop> drops;

    @Override
    public void onEnable(){
        instance = this;
        drops = new ArrayList<>();
        this.saveDefaultConfig();
        new ListenerManager(this).registerListeners();
        new CommandManager().registerCommands();
        setupDrops();
    }

    private void setupDrops(){
        getConfig().getConfigurationSection("drops").getKeys(false).forEach(configDrop -> {
            ConfigurationSection config = getConfig().getConfigurationSection("drops").getConfigurationSection(configDrop);
            Drop drop = new Drop(config.getInt("chance"), config.getInt("amount"), config.getString("name"), config.getStringList("lore"));
            this.drops.add(drop);
        });

    }

    public List<Drop> getDrops() {
        return drops;
    }

    public static Crates getInstance() {
        return instance;
    }

}
