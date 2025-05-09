package PS.plugin.commands;

import PS.plugin.PermaSoul;
import PS.plugin.managers.PlayerDataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MuertesCommand implements CommandExecutor {
    PermaSoul plugin;
    public MuertesCommand(PermaSoul plugin){
        this.plugin =plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Error!, no puedes ejecutar este comando aqui");
            return true;
        }
        PlayerDataManager playerDataManager = plugin.getPlayerDataManager();
        Player player = (Player) sender;
        int muertes = playerDataManager.getMuertesByPlayer(player);
        player.sendMessage("Tus muertes son: "+muertes);

        return false;
    }
}
