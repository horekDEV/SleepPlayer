package horekdev.ru.sleepplayer.event;

import horekdev.ru.sleepplayer.SleepPlayer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class PlayerBedEvent implements Listener {
    @EventHandler
    public void onBed(PlayerBedEnterEvent event) {
        new BukkitRunnable() {

            @Override
            public void run() {
                Collection<? extends Player> players = Bukkit.getOnlinePlayers();
                int everyone = players.size();
                int needing = (int) Math.ceil(everyone / 2);
                int sleeping = (int) players.stream().filter(Player::isSleeping).count();
                int count = needing - sleeping;

                if (sleeping >= needing) {
                    Bukkit.getWorlds().stream().filter(World::isNatural).forEach(world -> world.setTime(100));
                    broadCast(players, "чтобы скипнуть ночь необходимо еще: " + count + " игроков");

                } else {
                    skipCast(players);
                }
            }
        }.runTaskLater(SleepPlayer.getInstance(), 1L);
    }

    private void skipCast(Collection<? extends Player> collection) {
        for (Player p: collection) {
            p.sendMessage(SleepPlayer.getInstance().getConfig().getString(".skip_message"));
        }
    }

    private void broadCast(Collection<? extends  Player> collection, String text) {
        for (Player player: collection) {
            player.sendMessage(text);
        }
    }
}
