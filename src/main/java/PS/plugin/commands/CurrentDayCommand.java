package PS.plugin.commands;

import PS.plugin.PermaSoul;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CurrentDayCommand implements CommandExecutor {
    private PermaSoul plugin;
    public  CurrentDayCommand(PermaSoul plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender,Command command,String alias,String[] args) {
        long day = plugin.getCurrent_day();
        //day = 10293;
        String d = day+"";
        sender.sendMessage("El dia actual es: "+d);
        return false;
    }
}
