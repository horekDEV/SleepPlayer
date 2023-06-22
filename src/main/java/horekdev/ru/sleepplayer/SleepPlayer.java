package horekdev.ru.sleepplayer;

import horekdev.ru.sleepplayer.event.PlayerBedEvent;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class SleepPlayer extends JavaPlugin {
    public static SleepPlayer instance;

    public static SleepPlayer getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        System.out.println("Plugin " + getDescription().getName() + " was start");
        System.out.println("Made by " + getDescription().getAuthors());

        getServer().getPluginManager().registerEvents(new PlayerBedEvent(), this);
    }

    @Override
    public void onDisable() {
        instance = null;

        System.out.println("Plugin " + getDescription().getName() + " was stop");
        System.out.println("Made by " + getDescription().getAuthors());

        HandlerList.unregisterAll(new PlayerBedEvent());
    }
}
