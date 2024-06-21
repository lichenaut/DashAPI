package com.lichenaut.dashapi;

import org.apache.logging.log4j.LogManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import com.lichenaut.dashapi.util.DUpdateChecker;
import org.apache.logging.log4j.Logger;

public final class Main extends JavaPlugin {

    private static final DashAPI api = new DashAPI();
    private static final Logger log = LogManager.getLogger("DashAPI");

    @Override
    public void onEnable() {
        new Metrics(this, 17969);
        new DUpdateChecker(this, this).getVersion(version -> {if (!this.getDescription().getVersion().equals(version)) {getLog().info("Update available.");}});
    }

    @SuppressWarnings("unused")
    public static DashAPI getInstance() {
        return api;
    }

    public Logger getLog() {
        return log;
    }

}
