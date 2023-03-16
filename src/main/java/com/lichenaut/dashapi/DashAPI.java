package com.lichenaut.dashapi;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import util.DDirectoryMaker;
import util.DUpdateChecker;

import java.util.logging.Logger;

public final class DashAPI extends JavaPlugin {

    private final DashAPI plugin = this;
    private static final DashFunctions functions = new DashFunctions();
    private Logger log;

    @Override
    public void onEnable() {
        log = getLogger();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Configuration config = getConfig();

        //int pluginId = ;
        //noinspection ALL
        //Metrics metrics = new Metrics(plugin, pluginId);

        if (config.getBoolean("disable-plugin")) {
            log.info("Plugin disabled in config.yml.");
        } else {
            //new DUpdateChecker(this, plugin).getVersion(version -> {if (!this.getDescription().getVersion().equals(version)) {getLog().info("Update available.");}});

            String dataFolderPath = getDataFolder().getPath();
            new DDirectoryMaker(plugin).makeDir(dataFolderPath);
        }
    }

    public static DashFunctions getInstance() {return functions;}
    public Logger getLog() {return log;}

}
