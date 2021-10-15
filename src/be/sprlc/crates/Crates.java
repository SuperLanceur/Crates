package be.sprlc.crates;

import org.bukkit.plugin.java.JavaPlugin;

public class Crates extends JavaPlugin {

    private static Crates instance;

    @Override
    public void onEnable(){
        Crates.instance = this;
    }

    public static Crates getInstance() {
        return instance;
    }
}
