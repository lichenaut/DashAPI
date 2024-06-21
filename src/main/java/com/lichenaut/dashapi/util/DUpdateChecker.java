package com.lichenaut.dashapi.util;

import com.lichenaut.dashapi.Main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class DUpdateChecker {

    private final JavaPlugin plugin;
    private final Main main;

    public DUpdateChecker(JavaPlugin plugin, Main main) {
        this.plugin = plugin;
        this.main = main;
    }

    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + 108639).openStream(); Scanner scanner = new
                    Scanner(inputStream)) {if (scanner.hasNext()) {consumer.accept(scanner.next());}
            } catch (IOException e) {
                main.getLog().error("Unable to check for updates!\n{}", e.getMessage());
            }
        });
    }
}