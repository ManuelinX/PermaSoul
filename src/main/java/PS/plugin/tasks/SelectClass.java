package PS.plugin.tasks;

import PS.plugin.PermaSoul;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
//import net.md_5.bungee.api.chat.ClickEvent;
//import net.md_5.bungee.api.chat.ComponentBuilder;
//import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SelectClass {
    private PermaSoul plugin;
    public SelectClass(PermaSoul plugin){
        this.plugin = plugin;
    }


    public BukkitTask rememberSelectClass(int delay, int period){
        BukkitRunnable clockClass = new BukkitRunnable() {
            @Override
            public void run() {
                Integer start =  plugin.getMainConfigManager().getStart_select_day();
                Integer end = plugin.getMainConfigManager().getEnd_select_day();
                if(plugin.getCurrent_day() >= start && plugin.getCurrent_day() <= end){
                    for(Player player: Bukkit.getOnlinePlayers()) {
                        enviarMenu(player);
                    }
                }
                else if(plugin.getCurrent_day() > end){
                    this.cancel();
                }
            }
        };

        return clockClass.runTaskTimer(plugin,delay,period);
    }



    public void enviarMenu(Player player) {
        player.sendMessage("§6Asesino Habilidades: ");
        player.sendMessage("    -Con 9 corazones o más tienes Fuerza II");
        player.sendMessage("    -Con 6.5 a 8.5 corazones tienes Fuerza I");

        player.sendMessage("§6Tanque Habilidades: ");
        player.sendMessage("    -Con 2.5 corazones o menos tienes Resistencia II");
        player.sendMessage("    -Con 3 a 5.5 corazones tienes Resistencia I");

        player.sendMessage("§6Vampiro Habilidades: ");
        player.sendMessage("    -Si tienes 8.5 muslos de comida o más tienes Velocidad I");
        player.sendMessage("    -Cada sexto ataque a cualquier mob o jugador regeneras 1.5 corazones de salud");

        player.sendMessage("§6Elige tu clase:");

        // Opción Mago
        player.sendMessage("§b[⚡ Asesino] /selectClass asesino");

        // Opción Guerrero
        player.sendMessage("§c[⚔ Tanque] /selectClass tanque");

        // Opción Vampiro
        player.sendMessage("§c[🩸 Vampiro] /selectClass vampiro");
    }




}
