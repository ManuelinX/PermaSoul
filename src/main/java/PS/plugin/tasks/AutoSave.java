package PS.plugin.tasks;

import PS.plugin.PermaSoul;
import PS.plugin.model.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;
import java.util.Vector;

public class AutoSave {
    PermaSoul plugin;
    public AutoSave(PermaSoul plugin){
        this.plugin = plugin;
    }


    public BukkitTask runTaskTimer( long delay, long period){

        return new BukkitRunnable() {
            @Override
            public void run() {
                plugin.getPlayersConfigManager().saveConfigs();
                plugin.getMainConfigManager().saveConfig();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage("Datos guardados...");
                }
                Bukkit.getConsoleSender().sendMessage("[PermaSoul] : Datos guardados...");
            }
            //si quiero pasarle ticks se pasan don L, eejemplo
            //.runTaskTimer(plugin, 20L, 100L);
        }.runTaskTimer(plugin, delay, period);


    }

}
