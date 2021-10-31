package be.sprlc.crates.listeners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerManager {

    Plugin plugin;

    public ListenerManager(Plugin plugin){
        this.plugin = plugin;
    }

    public void registerListeners(){
        final PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new GlobalListener(), this.plugin);
    }

}
