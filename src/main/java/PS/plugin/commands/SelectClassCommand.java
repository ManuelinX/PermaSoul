package PS.plugin.commands;

import PS.plugin.PermaSoul;
import PS.plugin.model.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SelectClassCommand implements CommandExecutor {
    PermaSoul plugin;

    public SelectClassCommand(PermaSoul plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(sender instanceof Player player){
            //plugin.getPlayerDataManager().getPlayers().
            if(plugin.getCurrent_day() >= plugin.getMainConfigManager().getStart_select_day() && plugin.getCurrent_day() <= plugin.getMainConfigManager().getEnd_select_day()) {
                if (args[0].equalsIgnoreCase("vampiro")) {
                    plugin.getPlayerDataManager().setClass(player, "vampiro");
                    String message = ChatColor.translateAlternateColorCodes('&',
                            "Has seleccionado la clase &cVampiro");
                    player.sendMessage(message);
                } else if (args[0].equalsIgnoreCase("tanque")) {
                    String message = ChatColor.translateAlternateColorCodes('&',
                            "Has seleccionado la clase &7Tanque");
                    player.sendMessage(message);
                    plugin.getPlayerDataManager().setClass(player, "tanque");
                } else if (args[0].equalsIgnoreCase("asesino")) {
                    String message = ChatColor.translateAlternateColorCodes('&',
                            "Has seleccionado la clase &eAsesino");
                    player.sendMessage(message);
                    plugin.getPlayerDataManager().setClass(player, "asesino");
                }
                else{
                    sender.sendMessage("No existe esa clase, intente con: vampiro, tanque o asesino");
                }
            }
            else{
                sender.sendMessage("Aun no es dia o ya paso el dia de la seleccion...");
            }
        }
        else{
            sender.sendMessage("No puedes ejecutar este comando aqui");
        }

        return false;
    }


}
