package com.lichenaut.dashapi.util;

import com.lichenaut.dashapi.Main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {

    private final JavaPlugin plugin;
    private final Main main;

    public UpdateChecker(JavaPlugin plugin, Main main) {
        this.plugin = plugin;
        this.main = main;
    }

    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try (InputStream inputStream = new URI("https://api.spigotmc.org/legacy/update.php?resource=" + 108639).toURL()
                    .openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException e) {
                main.getLog().error("IOException: Unable to check for updates!\n{}", e.getMessage());
            } catch (URISyntaxException e) {
                main.getLog().error("URISyntaxException: Unable to check for updates!\n{}", e.getMessage());
            }
        });
    }
}