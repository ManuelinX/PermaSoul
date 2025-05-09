package PS.plugin.Listeners;


import PS.plugin.ListenersUtils.CustomCreatureSpawn;
import PS.plugin.PermaSoul;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.swing.text.StyledEditorKit;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class PlayerListeners implements Listener {
    public static Map<UUID, Boolean> playerMaldicionTotem = new HashMap<>();
    public static Map<UUID, Boolean> playerTanqueEnable = new HashMap<>();
    public static Map<UUID,Boolean> playerAsesinoEnable = new HashMap<>();
    public static Map<UUID,Integer> playerVampiroHits = new HashMap<>();
    public static Map<UUID,Boolean> playerVampiroEnable = new HashMap<>();

    PermaSoul plugin;
    boolean maldicionActivada;
    boolean clasesActivadas;
    public PlayerListeners(PermaSoul plugin){
        clasesActivadas = (plugin.getCurrent_day() >= plugin.getMainConfigManager().getStart_select_day());
        //maldicionActivada = (plugin.getCurrent_day() >= plugin.getMainConfigManager().getStart_select_day() && plugin.getCurrent_day() <= plugin.getMainConfigManager().getEnd_select_day());
        maldicionActivada = plugin.getCurrent_day() >= plugin.getMainConfigManager().getDifficulty_changes().get("maldicionTotem");
        this.plugin = plugin;
    }



    public void putMaldicionTotem(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,Integer.MAX_VALUE,100));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,Integer.MAX_VALUE,100));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,Integer.MAX_VALUE,100));
    }

    public void removeMaldicionTotem(Player player){
        player.removePotionEffect(PotionEffectType.BLINDNESS);
        player.removePotionEffect(PotionEffectType.WEAKNESS);
        player.removePotionEffect(PotionEffectType.SLOWNESS);

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String name = player.getName();
        plugin.getPlayerDataManager().updateName(player);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&eBienvenido al servidor: "+name));
        UUID uuid = player.getUniqueId();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        ItemStack offHandItem = player.getInventory().getItemInOffHand();
        if(maldicionActivada) {
            if ((mainHandItem.getType() == Material.TOTEM_OF_UNDYING || offHandItem.getType() == Material.TOTEM_OF_UNDYING)) {
                playerMaldicionTotem.put(uuid, Boolean.TRUE);
                putMaldicionTotem(player);
                //player.sendMessage("Maldicion del Totem  ");
            } else {
                playerMaldicionTotem.put(uuid, Boolean.FALSE);
                removeMaldicionTotem(player);
                //player.sendMessage("QUITAR maldicion del Totem  ");
            }
        }
    }

    @EventHandler
    public void onDisconected(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        playerMaldicionTotem.remove(uuid);
    }

    @EventHandler
    public void itemHandSelect(PlayerItemHeldEvent event){
        Player player = event.getPlayer();

        //ItemStack item = player.getInventory().getItemInMainHand();
        int newSlot = event.getNewSlot();
        Inventory inventory = event.getPlayer().getInventory();
        ItemStack item = inventory.getItem(newSlot);
        //ItemStack item = event.getNewSlot()
        ItemStack offItem = player.getInventory().getItemInOffHand();
        if(maldicionActivada) {
            if (item != null && item.getType() == Material.TOTEM_OF_UNDYING) {
                UUID uuid = player.getUniqueId();
                playerMaldicionTotem.put(uuid, Boolean.TRUE);
                putMaldicionTotem(player);
                //player.sendMessage("Maldicion del Totem  ");


            } else if (offItem.getType() != Material.TOTEM_OF_UNDYING && playerMaldicionTotem.get(player.getUniqueId()) == Boolean.TRUE) {
                UUID uuid = player.getUniqueId();
                playerMaldicionTotem.put(uuid, Boolean.FALSE);
                removeMaldicionTotem(player);
                //player.sendMessage("QUITAR Maldicion del Totem  ");
            }
        }
    }

    //creo que este hay que cambiarlo
    @EventHandler
    public void clickInventory(InventoryClickEvent event){
        if(maldicionActivada) {
            if (event.getWhoClicked() instanceof /*org.bukkit.entity.Player*/ Player player) {
                ItemStack itemCursor = event.getCursor();
                int clickSlot = event.getSlot();
                int offhandSlot = 40;
                int mainhandSlot = player.getInventory().getHeldItemSlot();
                if ((clickSlot == offhandSlot || clickSlot == mainhandSlot) && itemCursor != null && itemCursor.getType() == Material.TOTEM_OF_UNDYING) {
                    UUID uuid = player.getUniqueId();
                    playerMaldicionTotem.put(uuid, Boolean.TRUE);
                    putMaldicionTotem(player);
                    //player.sendMessage("Maldicion del totem");

                }
                UUID uuid = player.getUniqueId();
                if (playerMaldicionTotem.get(uuid) == Boolean.TRUE && (itemCursor == null || itemCursor.getType() != Material.TOTEM_OF_UNDYING)) {
                    ItemStack itemOff = player.getInventory().getItemInOffHand();
                    ItemStack itemMain = player.getInventory().getItemInMainHand();
                    if (clickSlot == offhandSlot && itemMain.getType() != Material.TOTEM_OF_UNDYING) {
                        removeMaldicionTotem(player);
                        //player.sendMessage("QUITAR Maldicion del Totem  ");
                    } else if (clickSlot == mainhandSlot && itemOff.getType() != Material.TOTEM_OF_UNDYING) {
                        removeMaldicionTotem(player);
                        //player.sendMessage("QUITAR Maldicion del Totem  ");
                    }
                }

            }
        }

    }

    @EventHandler
    public void entityRessurect(EntityResurrectEvent event){
        LivingEntity entity = event.getEntity();
        if( entity instanceof Player player){
            if(maldicionActivada) {
                ItemStack mainItem = player.getInventory().getItemInMainHand();
                ItemStack offItem = player.getInventory().getItemInOffHand();
                if (mainItem.getType() == Material.TOTEM_OF_UNDYING && offItem.getType() == Material.TOTEM_OF_UNDYING) {
                    putMaldicionTotem(player);
                    //player.sendMessage("Maldicion del totem");
                } else if (playerMaldicionTotem.get(player.getUniqueId()) == Boolean.TRUE) {
                    playerMaldicionTotem.put(player.getUniqueId(), Boolean.FALSE);
                    removeMaldicionTotem(player);
                    //player.sendMessage("QUITAR Maldicion del Totem  ");
                }
            }

        }
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        plugin.getPlayerDataManager().addMuerte(player);
    }

    @EventHandler
    public void playerKillLivingEntity(EntityDeathEvent event){
        LivingEntity entity = event.getEntity();

        Player player = entity.getKiller();
        if(player != null && !(entity instanceof Player)){
            plugin.getPlayerDataManager().addMobsKills(player);
        }

        if(entity instanceof Player && player != null){
            plugin.getPlayerDataManager().addPlayerKills(player);
        }

    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        double damage = (double ) event.getDamage();
        if(entity instanceof Player player){
            double vida =  (double )player.getHealth();
            if(clasesActivadas){
                if (Math.ceil(vida - damage) >= 6 && Math.ceil(vida - damage)<= 11 && plugin.getPlayerDataManager().getPlayers().get(player.getUniqueId()).getClase().equalsIgnoreCase("tanque")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, Integer.MAX_VALUE, 0));
                    playerTanqueEnable.put(player.getUniqueId(), Boolean.TRUE);
                } else if (Math.ceil(vida - damage) <= 5 && plugin.getPlayerDataManager().getPlayers().get(player.getUniqueId()).getClase().equalsIgnoreCase("tanque")) {
                    if(playerTanqueEnable.get(player.getUniqueId()) == Boolean.TRUE) {
                        player.removePotionEffect(PotionEffectType.RESISTANCE);
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, Integer.MAX_VALUE, 1));
                    playerTanqueEnable.put(player.getUniqueId(), Boolean.TRUE);
                }
                if(Math.ceil(vida - damage) <= 13  && plugin.getPlayerDataManager().getPlayers().get(player.getUniqueId()).getClase().equalsIgnoreCase("asesino")){
                    player.removePotionEffect(PotionEffectType.STRENGTH);
                }
                else if(Math.ceil(vida - damage) <= 18 && plugin.getPlayerDataManager().getPlayers().get(player.getUniqueId()).getClase().equalsIgnoreCase("asesino")){
                    if(playerAsesinoEnable.get(player.getUniqueId()) == Boolean.TRUE) {
                        player.removePotionEffect(PotionEffectType.STRENGTH);
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,Integer.MAX_VALUE,0));
                }

            }
        }
    }

    @EventHandler
    public void onPlayerRegainHealth(EntityRegainHealthEvent event) {
        Entity entity = event.getEntity();

        if( entity instanceof Player player){
            //player.sendMessage("tu clase es: -"+plugin.getPlayerDataManager().getPlayers().get(player.getUniqueId()).getClase()+"-");
            if(clasesActivadas) {
                //player.sendMessage("    -clases activadas");
                double vida = (double) player.getHealth();
                double gain = (double) event.getAmount();
                if (Math.ceil(vida + gain) >= 11 && playerTanqueEnable.get(player.getUniqueId()) == Boolean.TRUE) {
                    playerTanqueEnable.put(player.getUniqueId(), Boolean.FALSE);
                    player.removePotionEffect(PotionEffectType.RESISTANCE);
                }
                else if(Math.ceil(vida + gain) >= 5  && playerTanqueEnable.get(player.getUniqueId()) == Boolean.TRUE){
                    player.removePotionEffect(PotionEffectType.RESISTANCE);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, Integer.MAX_VALUE, 0));
                }
                //player.sendMessage("    - paso1");
                //player.sendMessage("    -vida: "+vida+gain);
                if(Math.ceil(vida + gain) >= 18 && plugin.getPlayerDataManager().getPlayers().get(player.getUniqueId()).getClase().equalsIgnoreCase("asesino")){
                    if(playerAsesinoEnable.get(player.getUniqueId()) == Boolean.TRUE){
                        player.removePotionEffect(PotionEffectType.STRENGTH);
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,Integer.MAX_VALUE,1));
                    playerAsesinoEnable.put(player.getUniqueId(),Boolean.TRUE);
                }
                else if(Math.ceil(vida + gain) >= 13 && plugin.getPlayerDataManager().getPlayers().get(player.getUniqueId()).getClase().equalsIgnoreCase("asesino")){

                    //player.sendMessage("    - paso2");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,Integer.MAX_VALUE,0));
                    playerAsesinoEnable.put(player.getUniqueId(),Boolean.TRUE);
                }

                //player.sendMessage("    - paso3");

            }
        }
    }

    @EventHandler
    public void onPlayerHitEntity(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player player){
            if(event.getEntity() instanceof LivingEntity){
                if(plugin.getPlayerDataManager().getPlayers().get(player.getUniqueId()).getClase().equalsIgnoreCase("vampiro")){
                    if(playerVampiroHits.get(player.getUniqueId()) == null){
                        playerVampiroHits.put(player.getUniqueId(),1);
                    }
                    else{
                        playerVampiroHits.put(player.getUniqueId(),playerVampiroHits.get(player.getUniqueId())+1);
                    }
                    if((int ) playerVampiroHits.get(player.getUniqueId())%6 == 0){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,20,3));
                    }
                }


            }
        }

    }
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        // Verifica si el evento afecta a un jugador
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            int oldFoodLevel = player.getFoodLevel(); // Nivel actual antes del cambio
            int newFoodLevel = event.getFoodLevel();  // Nivel de comida después del cambio

            if (newFoodLevel >= 17 && plugin.getPlayerDataManager().getPlayers().get(player.getUniqueId()).getClase().equalsIgnoreCase("vampiro")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,Integer.MAX_VALUE,0));
                playerVampiroEnable.put(player.getUniqueId(),Boolean.TRUE);
                //player.sendMessage("Perdiste " + (oldFoodLevel - newFoodLevel) + " muslos de comida 🍗");
            } else if (newFoodLevel < 17 && plugin.getPlayerDataManager().getPlayers().get(player.getUniqueId()).getClase().equalsIgnoreCase("vampiro")) {
                if(playerVampiroEnable.get(player.getUniqueId()) == Boolean.TRUE) {
                    player.removePotionEffect(PotionEffectType.SPEED);
                }
                playerVampiroEnable.put(player.getUniqueId(),Boolean.FALSE);

            }
        }
    }
}
