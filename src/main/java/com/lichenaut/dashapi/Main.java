package com.lichenaut.dashapi;

import com.lichenaut.dashapi.util.UpdateChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static final DashAPI api = new DashAPI();
    private static final Logger log = LogManager.getLogger("DashAPI");

    @Override
    public void onEnable() {
        new Metrics(this, 17969);
        new UpdateChecker(this, this).getVersion(version -> {
            if (!this.getDescription().getVersion().equals(version)) {
                getLog().info("Update available.");
            }
        });
    }

    @SuppressWarnings("unused")
    public static DashAPI getInstance() {
        return api;
    }

    public Logger getLog() {
        return log;
    }
}
