package PS.plugin.ListenersUtils;

import PS.plugin.PermaSoul;
import jdk.dynalink.linker.support.DefaultInternalObjectFilter;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.management.relation.RelationNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class CustomCreatureSpawn {
    public static void customZombie(Zombie zombie, CreatureSpawnEvent.SpawnReason reasonSpawn, PermaSoul plugin){

            //toma una semilla basada en el tiempo actual
        Random random = new Random();
        HashMap<String,Integer> diffChanges =  plugin.getMainConfigManager().getDifficulty_changes();
        int randomInt = random.nextInt(100)+1;// de 1 a 100
        if( 1 <= randomInt && randomInt<= 15 && diffChanges.get("infectado_day") <= plugin.getCurrent_day()){
            zombie.setCustomName(ChatColor.BOLD+"Infectado");
            // Mostrar el nombre del zombi en pantalla
            zombie.setCustomNameVisible(true);
            zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(25.0);
            zombie.setHealth(25);

            //Vector newVelocity = new Vector(1, 0, 1);
            //zombie.setVelocity(newVelocity);
            zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
        }
        else if( 16 <= randomInt && randomInt<= 20 && diffChanges.get("electroZombie_day") <= plugin.getCurrent_day()){
            zombie.setCustomName(ChatColor.YELLOW + "Electro Zombie");
            // Mostrar el nombre del zombi en pantalla
            zombie.setCustomNameVisible(true);
            zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
            zombie.setHealth(30);

            //Vector newVelocity = new Vector(0.5, 0, 0.5);
            //zombie.setVelocity(newVelocity);
        }
        else if(21 <= randomInt && randomInt <= 26 && diffChanges.get("guerreroZombie_day") <= plugin.getCurrent_day()){
            zombie.setCustomName(ChatColor.DARK_GRAY+"Guerrero Zombie");
            zombie.setCustomNameVisible(true);
            ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET);
            ItemStack chest = new ItemStack(Material.NETHERITE_CHESTPLATE);
            ItemStack leggins = new ItemStack(Material.NETHERITE_LEGGINGS);
            ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
            ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
            ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);

            Map<Enchantment, Integer> enchantmentMap = new HashMap<>();
            enchantmentMap.put(Enchantment.PROTECTION,4);
            enchantmentMap.put(Enchantment.THORNS,3);

            helmet.addEnchantments(enchantmentMap);
            chest.addEnchantments(enchantmentMap);
            leggins.addEnchantments(enchantmentMap);
            boots.addEnchantments(enchantmentMap);

            sword.addEnchantment(Enchantment.SHARPNESS,5);
            sword.addEnchantment(Enchantment.FIRE_ASPECT,2);
            sword.addEnchantment(Enchantment.KNOCKBACK,2);

            EntityEquipment equipment = zombie.getEquipment();
            equipment.setHelmet(helmet);
            equipment.setChestplate(chest);
            equipment.setLeggings(leggins);
            equipment.setBoots(boots);
            equipment.setItemInMainHand(sword);
            equipment.setItemInOffHand(totem);
            zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.38);
        }
        else if(27 <= randomInt && randomInt <= 35 && diffChanges.get("capitandelaOleada_day") <= plugin.getCurrent_day()){
            zombie.setCustomName(ChatColor.GOLD+"Capitan de la Oleada");
            zombie.setCustomNameVisible(true);

            zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
            zombie.setHealth(60);
            zombie.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(1.3);

        }



    }

    public static void customEnderman(Enderman enderman, CreatureSpawnEvent.SpawnReason spawnReason, Location location, PermaSoul plugin){
        Random random = new Random();
        int randomInt = random.nextInt(100)+1;
        World world = location.getWorld();
        enderman.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
        enderman.setHealth(60);
        enderman.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(1.9);
        HashMap<String,Integer> diffChanges = plugin.getMainConfigManager().getDifficulty_changes();
        if(location.distance(new Location(world, 0, 0, 0)) >= 3000 && 1 <= randomInt && randomInt<= 22 && diffChanges.get("enderShulker_day") <= plugin.getCurrent_day()){
            enderman.setCustomName(ChatColor.AQUA+"EnderShulker");
            enderman.setCustomNameVisible(true);
            Shulker shulker = (Shulker) world.spawn(location,Shulker.class);
            enderman.addPassenger(shulker);

        }


    }

    public static void customPhantom(Phantom phantom, CreatureSpawnEvent.SpawnReason spawnReason, PermaSoul plugin){
        Random random = new Random();
        int randomInt = random.nextInt(100)+1;
        HashMap<String,Integer> diffChanges = plugin.getMainConfigManager().getDifficulty_changes();
        if(1 <= randomInt && randomInt <= 20 && diffChanges.get("phantomGiant_day") <= plugin.getCurrent_day()){
            phantom.setSize(20);
            phantom.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
            phantom.setHealth(40);
            phantom.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        }
    }

    public static void customCreeper(Creeper creeper, CreatureSpawnEvent.SpawnReason spawnReason, PermaSoul plugin){


        Random random = new Random();
        int randomInt = random.nextInt(100)+1;
        HashMap<String,Integer> diffChanges = plugin.getMainConfigManager().getDifficulty_changes();
        if(1<= randomInt && randomInt <= 50 && diffChanges.get("creeperEnvenenado_oneEffect_day") <= plugin.getCurrent_day()){
            creeper.setCustomName(ChatColor.GREEN +"Creeper Envenenado");
            creeper.setCustomNameVisible(true);
        }
        else if(51<= randomInt && randomInt <= 75 && diffChanges.get("creeperSuperCargado_day") <= plugin.getCurrent_day()){
            creeper.setCustomName(ChatColor.BLUE+"Creeper Super Cargado");
            creeper.setCustomNameVisible(true);


            creeper.setPowered(true);
            creeper.setMaxFuseTicks(30*5);
            creeper.setExplosionRadius(3*5);
        }
        else if(76 <= randomInt && randomInt <= 90 && diffChanges.get("creeperPiromanoSuperCargado_day") <= plugin.getCurrent_day()){
            creeper.setCustomName(ChatColor.RED+"Creeper Piromano Super Cargado");
            creeper.setCustomNameVisible(true);


            creeper.setPowered(true);
            creeper.setMaxFuseTicks(30*5);
            creeper.setExplosionRadius(1);
        }

    }

    public static void customSkeleton(Skeleton skeleton,CreatureSpawnEvent.SpawnReason spawnReason, PermaSoul plugin){
        Random random = new Random();
        int randomInt = random.nextInt(100)+1;
        HashMap<String,Integer> diffChanges = plugin.getMainConfigManager().getDifficulty_changes();
        if(1 <= randomInt && randomInt <= 25 && diffChanges.get("esqueletoInfernal_day") <= plugin.getCurrent_day()){
            skeleton.setCustomName(ChatColor.RED +"Esqueleto Infernal");
            skeleton.setCustomNameVisible(true);

            ItemStack helmet = new ItemStack(Material.LEATHER_HELMET,1);
            ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE,1);
            ItemStack leggins = new ItemStack(Material.LEATHER_LEGGINGS,1);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS,1);

            LeatherArmorMeta metaData = (LeatherArmorMeta) helmet.getItemMeta();
            metaData.setColor(Color.fromRGB(255,0,0));
            helmet.setItemMeta(metaData);

            metaData = (LeatherArmorMeta) chest.getItemMeta();
            metaData.setColor(Color.fromRGB(255,0,0));
            chest.setItemMeta(metaData);

            metaData = (LeatherArmorMeta) leggins.getItemMeta();
            metaData.setColor(Color.fromRGB(255,0,0));
            leggins.setItemMeta(metaData);

            metaData = (LeatherArmorMeta) boots.getItemMeta();
            metaData.setColor(Color.fromRGB(255,0,0));
            boots.setItemMeta(metaData);

            helmet.addEnchantment(Enchantment.PROTECTION,4);
            chest.addEnchantment(Enchantment.PROTECTION,4);
            leggins.addEnchantment(Enchantment.PROTECTION,4);
            boots.addEnchantment(Enchantment.PROTECTION,4);

            ItemStack bow = new ItemStack(Material.BOW,1);
            bow.addEnchantment(Enchantment.FLAME,1);
            bow.addEnchantment(Enchantment.PUNCH,2);

            EntityEquipment equipment = skeleton.getEquipment();
            equipment.setHelmet(helmet);
            equipment.setChestplate(chest);
            equipment.setLeggings(leggins);
            equipment.setBoots(boots);
            equipment.setItemInMainHand(bow);


        }
        else if(26 <= randomInt && randomInt <=35 && diffChanges.get("esqueletoExplosivo_day") <= plugin.getCurrent_day()){
            skeleton.setCustomName(ChatColor.LIGHT_PURPLE + "Esqueleto Explosivo");
            skeleton.setCustomNameVisible(true);


        }

    }

    public static void customWitherSkeleton(WitherSkeleton witherSkeleton, CreatureSpawnEvent.SpawnReason reasonSpawn, PermaSoul plugin) {
        Random random = new Random();
        int randomInt = random.nextInt(100)+1;
        HashMap<String,Integer> diffChanges = plugin.getMainConfigManager().getDifficulty_changes();
        if(1 <= randomInt && randomInt <= 25 && diffChanges.get("esqueletoWitherdelVortice_day")<= plugin.getCurrent_day()){
            witherSkeleton.setCustomName(ChatColor.DARK_GRAY +"Esqueleto Wither del Vortice");
            witherSkeleton.setCustomNameVisible(true);
            witherSkeleton.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);

            ItemStack helmet = new ItemStack(Material.LEATHER_HELMET,1);
            ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE,1);
            ItemStack leggins = new ItemStack(Material.LEATHER_LEGGINGS,1);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS,1);

            helmet.addEnchantment(Enchantment.PROTECTION,4);
            chest.addEnchantment(Enchantment.PROTECTION,4);
            leggins.addEnchantment(Enchantment.PROTECTION,4);
            boots.addEnchantment(Enchantment.PROTECTION,4);

            helmet.addEnchantment(Enchantment.UNBREAKING,3);
            chest.addEnchantment(Enchantment.UNBREAKING,3);
            leggins.addEnchantment(Enchantment.UNBREAKING,3);
            boots.addEnchantment(Enchantment.UNBREAKING,3);

            helmet.addEnchantment(Enchantment.THORNS,3);
            chest.addEnchantment(Enchantment.THORNS,3);
            leggins.addEnchantment(Enchantment.THORNS,3);
            boots.addEnchantment(Enchantment.THORNS,3);

            LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
            meta.setColor(Color.fromRGB(0,0,0));
            helmet.setItemMeta(meta);

            meta = (LeatherArmorMeta) chest.getItemMeta();
            meta.setColor(Color.fromRGB(0,0,0));
            chest.setItemMeta(meta);

            meta = (LeatherArmorMeta) leggins.getItemMeta();
            meta.setColor(Color.fromRGB(0,0,0));
            leggins.setItemMeta(meta);

            meta = (LeatherArmorMeta) boots.getItemMeta();
            meta.setColor(Color.fromRGB(0,0,0));
            boots.setItemMeta(meta);

            EntityEquipment equipment = witherSkeleton.getEquipment();
            equipment.setHelmet(helmet);
            equipment.setChestplate(chest);
            equipment.setLeggings(leggins);
            equipment.setBoots(boots);

            witherSkeleton.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,PotionEffect.INFINITE_DURATION,0,true,true));

        }
    }
}
