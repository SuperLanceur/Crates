package be.sprlc.crates.commands;

import be.sprlc.crates.Crates;

public class CommandManager {

    public void registerCommands(){
        Crates.getInstance().getCommand("crategen").setExecutor(new CrateGenCommand());
    }

}
