package util;

import com.lichenaut.dashapi.DashAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class DUpdateChecker {

    private final JavaPlugin plugin;
    private final DashAPI dPlugin;

    public DUpdateChecker(JavaPlugin plugin, DashAPI dPlugin) {this.plugin = plugin;this.dPlugin = dPlugin;}

    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + 01134).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {consumer.accept(scanner.next());}
            } catch (IOException e) {
                dPlugin.getLog().warning("Unable to check for updates!");
                e.printStackTrace();
            }
        });
    }
}