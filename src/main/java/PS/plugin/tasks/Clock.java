package PS.plugin.tasks;

import PS.plugin.PermaSoul;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Clock {
    private PermaSoul plugin;

    public Clock(PermaSoul plugin){
        this.plugin = plugin;

    }
    public BukkitTask runClockTimer(int delay, int period){
        BukkitRunnable clock = new BukkitRunnable() {
            @Override
            public void run() {
                plugin.getMainConfigManager().setTime(plugin.getMainConfigManager().getTime()+1);
                //cambiar el 120 por 86400 si es que esta el 120
                if(plugin.getMainConfigManager().getTime()%86400 == 0) {
                    plugin.setCurrent_day((long ) ((int) (plugin.getMainConfigManager().getTime() / 86400)));

                }
            }
        };

        return clock.runTaskTimer(plugin,delay,period);

    }
}
