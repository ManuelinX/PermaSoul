package PS.plugin.commands;

import PS.plugin.PermaSoul;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {
    private PermaSoul plugin;
    public MainCommand(PermaSoul plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length >= 1){
            if(args[0].equalsIgnoreCase("day")) {
                sender.sendMessage("Este dia es el dia: " + plugin.getCurrent_day()+"");
            }
            else if(args[0].equalsIgnoreCase("difficulty_changes")){
                if(args.length == 1){
                    sender.sendMessage("Necesitas especificar un mob o cambio de dificultad: ");
                    if( plugin.getMainConfigManager().getDifficulty_changes().get("infectado_day") <= plugin.getCurrent_day()) {
                        sender.sendMessage("infectado");
                    }
                    if( plugin.getMainConfigManager().getDifficulty_changes().get("electroZombie_day") <= plugin.getCurrent_day()) {
                        sender.sendMessage("electro_zombie");
                    }
                    if( plugin.getMainConfigManager().getDifficulty_changes().get("guerreroZombie_day") <= plugin.getCurrent_day()) {
                        sender.sendMessage("guerrero_zombie");
                    }
                    if( plugin.getMainConfigManager().getDifficulty_changes().get("capitandelaOleada_day") <= plugin.getCurrent_day()) {
                        sender.sendMessage("capitan_oleada");
                    }
                    if( plugin.getMainConfigManager().getDifficulty_changes().get("enderShulker_day") <= plugin.getCurrent_day()) {
                        sender.sendMessage("enderShulker");
                    }
                    if( plugin.getMainConfigManager().getDifficulty_changes().get("phantomGiant_day") <= plugin.getCurrent_day()) {
                        sender.sendMessage("phantom_giant");
                    }


                    if( plugin.getMainConfigManager().getDifficulty_changes().get("creeperEnvenenado_oneEffect_day") <= plugin.getCurrent_day()) {
                        sender.sendMessage("creeper_envenenado");
                    }

                    if( plugin.getMainConfigManager().getDifficulty_changes().get("creeperSuperCargado_day") <= plugin.getCurrent_day()) {
                        sender.sendMessage("creeper_super_cargado");
                    }
                    if( plugin.getMainConfigManager().getDifficulty_changes().get("creeperPiromanoSuperCargado_day") <= plugin.getCurrent_day()) {
                        sender.sendMessage("creeper_piromano_super_cargado");
                    }
                    if( plugin.getMainConfigManager().getDifficulty_changes().get("esqueletoInfernal_day") <= plugin.getCurrent_day()) {
                        sender.sendMessage("esqueleto_infernal");
                    }
                    if( plugin.getMainConfigManager().getDifficulty_changes().get("esqueletoExplosivo_day") <= plugin.getCurrent_day()) {
                        sender.sendMessage("esqueleto_explosivo");
                    }
                    if( plugin.getMainConfigManager().getDifficulty_changes().get("esqueletoWitherdelVortice_day") <= plugin.getCurrent_day()) {
                        sender.sendMessage("esqueleto_wither_vortice");
                    }
                    if( plugin.getMainConfigManager().getDifficulty_changes().get("maldicionTotem") <= plugin.getCurrent_day()) {
                        sender.sendMessage("maldicion_totem");
                    }

                }
                else{
                    subCommandDiffChanges(sender,args);
                }
            }
        }


        return false;
    }

    public void subCommandDiffChanges(CommandSender sender,String[] args){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',ChatColor.DARK_PURPLE+args[1]));
        if(args[1].equalsIgnoreCase("infectado") && plugin.getMainConfigManager().getDifficulty_changes().get("infectado_day") <= plugin.getCurrent_day()){
            sender.sendMessage(" - Zombie con un pequeño aumento de vida");
            sender.sendMessage(" - Es mas rapido que un zombie normal");
        }
        else if(args[1].equalsIgnoreCase("electro_zombie") && plugin.getMainConfigManager().getDifficulty_changes().get("electroZombie_day") <= plugin.getCurrent_day()){
            sender.sendMessage(" - Zombie con un pequeño aumento de vida");
            sender.sendMessage(" - Al atacar lanza un rayo a la criatura que ataco");
        }
        else if(args[1].equalsIgnoreCase("guerrero_zombie") && plugin.getMainConfigManager().getDifficulty_changes().get("guerreroZombie_day") <= plugin.getCurrent_day()){
            sender.sendMessage(" - Zombie con armadura full netherite");
            sender.sendMessage(" - Armadura full proteccion 4");
            sender.sendMessage(" - Armadura full espinas 3");
            sender.sendMessage(" - Espada de netherite");
            sender.sendMessage(" - Espada con Filo 4, aspecto de fuego 2 y empuje 2");
            sender.sendMessage(" - Totem de inmortalidad en la mano secundaria");
            sender.sendMessage(" - Velocidad aumentada");
        }
        else if(args[1].equalsIgnoreCase("capitan_oleada") && plugin.getMainConfigManager().getDifficulty_changes().get("capitandelaOleada_day") <= plugin.getCurrent_day()){
            sender.sendMessage(" - Zombie con vida aumentada");
            sender.sendMessage(" - Empuje aumentado al atacar");
            sender.sendMessage(" - Al estar cerca de un jugador inspira a los mounstruos cercanos");
            sender.sendMessage(" - Los mounstruos cecanos inspirados tendran:");
            sender.sendMessage(" * Resistencia II");
            sender.sendMessage(" * Fuerza II");
            sender.sendMessage(" * Velocidad II");
            sender.sendMessage(" - Las criaturas inspiradas estaran brillando");

        }
        else if(args[1].equalsIgnoreCase("enderShulker") && plugin.getMainConfigManager().getDifficulty_changes().get("enderShulker_day") <= plugin.getCurrent_day()){
            sender.sendMessage(" - Enderman con vida aumentada");
            sender.sendMessage(" - Tendra un shulker encima de el");
            sender.sendMessage(" - Al morir tiene 55% de probabilidad de dropear una manzana dorada encantada");
            sender.sendMessage(" - Tiene un mayor empuje al atacar");
            sender.sendMessage(" - Aparece unicamente a una distancia de 3000 bloques del punto (0,0)");
        }
        else if(args[1].equalsIgnoreCase("phantom_giant") && plugin.getMainConfigManager().getDifficulty_changes().get("phantomGiant_day") <= plugin.getCurrent_day()){
            sender.sendMessage(" - Phantom de mucho mayor tamaño");
            sender.sendMessage(" - Tiene un aumento de vida");
            sender.sendMessage(" - Tiene mucho aumento de daño");
        }
        else if(args[1].equalsIgnoreCase("creeper_envenenado")){
            if(plugin.getMainConfigManager().getDifficulty_changes().get("creeperEnvenenado_threeEffects_day") <= plugin.getCurrent_day()){
                sender.sendMessage(" - Creeper con probabilidad de dejar de 1 a 3 efectos de pocion al morir");
                sender.sendMessage(" - Todos los efectos seran negativos");
            }
            else if(plugin.getMainConfigManager().getDifficulty_changes().get("creeperEnvenenado_twoEffects_day") <= plugin.getCurrent_day()){
                sender.sendMessage(" - Creeper con probabilidad de dejar de 1 a 2 efectos de pocion al morir");
                sender.sendMessage(" - Todos los efectos seran negativos");
            }
            else if(plugin.getMainConfigManager().getDifficulty_changes().get("creeperEnvenenado_oneEffect_day") <= plugin.getCurrent_day()){
                sender.sendMessage(" - Creeper con probabilidad de dejar 1 efecto de pocion al morir");
                sender.sendMessage(" - El efecto sera negativo");
            }
        }
        else if(args[1].equalsIgnoreCase("creeper_super_cargado") && plugin.getMainConfigManager().getDifficulty_changes().get("creeperSuperCargado_day") <= plugin.getCurrent_day()){
            sender.sendMessage(" - Creeper cargado con daño masivo");
            sender.sendMessage(" - Radio de explosion de aproximandamente 15 bloques");

        }
        else if(args[1].equalsIgnoreCase("creeper_piromano_super_cargado") && plugin.getMainConfigManager().getDifficulty_changes().get("creeperPiromanoSuperCargado_day") <= plugin.getCurrent_day()){
            sender.sendMessage(" - Creeper cargado con daño masivo");
            sender.sendMessage(" - Radio de explosion de aproximandamente 15 bloques");
            sender.sendMessage(" - Puede incendiar bloques al explotar");
        }
        else if(args[1].equalsIgnoreCase("esqueleto_infernal") && plugin.getMainConfigManager().getDifficulty_changes().get("esqueletoInfernal_day") <= plugin.getCurrent_day()){
            sender.sendMessage(" - Esqueleto con armadura de cuero color rojo");
            sender.sendMessage(" - Armadura full proteccion 4");
            sender.sendMessage(" - Arco encantado con flama 1 y golpeo 2");

        }
        else if(args[1].equalsIgnoreCase("esqueleto_explosivo") &&  plugin.getMainConfigManager().getDifficulty_changes().get("esqueletoExplosivo_day") <= plugin.getCurrent_day()){
            sender.sendMessage(" - Esqueleto con vida y daño base");
            sender.sendMessage(" - Al impactar su flecha con un objetivo esta misma causa una pequeña explosion");
        }
        else if(args[1].equalsIgnoreCase("esqueleto_wither_vortice") &&  plugin.getMainConfigManager().getDifficulty_changes().get("esqueletoWitherdelVortice_day") <= plugin.getCurrent_day()){
            sender.sendMessage(" - Esqueleto whiter con armadura de cuero negro");
            sender.sendMessage(" - La armadura estara encantada con: ");
            sender.sendMessage(" * Proteccion IV");
            sender.sendMessage(" * Espinas III");
            sender.sendMessage(" * Irrompible III");
            sender.sendMessage(" - Tendra regeneracion I con un tiempo infinito");
            sender.sendMessage(" - Al golpearte te jalara hacia el");
        }
        else if(args[1].equalsIgnoreCase("maldicion_totem") &&  plugin.getMainConfigManager().getDifficulty_changes().get("maldicionTotem") <= plugin.getCurrent_day()){
            sender.sendMessage(" - Al sostener un totem en la mano secundaria o primaria obtienes: ");
            sender.sendMessage(" * Ceguera ");
            sender.sendMessage(" * Debilidad");
            sender.sendMessage(" * Lentitud");

        }
        else{
            sender.sendMessage("Mob no reconocido o informacion no disponible...");
        }
    }
}
