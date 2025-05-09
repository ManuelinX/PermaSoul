package PS.plugin.Listeners;

import PS.plugin.ListenersUtils.CustomCreatureSpawn;
import PS.plugin.PermaSoul;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.lang.model.element.Name;
import java.util.*;

public class CreatureListeners implements Listener {
    private PermaSoul plugin;

    public CreatureListeners(PermaSoul plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void spawnCreature(CreatureSpawnEvent event){
        LivingEntity entity = event.getEntity();
        CreatureSpawnEvent.SpawnReason reasonSpawn = event.getSpawnReason();
        Location location = event.getLocation();


        if(entity instanceof Monster monster){
            String name = monster.getCustomName();
            if(name != null){
                return;
            }
        }



        if(entity  instanceof Zombie){
            Zombie zombie = (Zombie) entity;
            CustomCreatureSpawn.customZombie(zombie,reasonSpawn,plugin);
        }
        else if(entity instanceof Enderman){
            Enderman enderman = (Enderman) entity;
            CustomCreatureSpawn.customEnderman(enderman,reasonSpawn,location,plugin);
        }
        else if(entity instanceof Phantom){
            Phantom phantom = (Phantom) entity;
            CustomCreatureSpawn.customPhantom(phantom,reasonSpawn,plugin);
        }
        else if(entity instanceof Creeper creeper){
            CustomCreatureSpawn.customCreeper(creeper,reasonSpawn,plugin);
        }
        else if(entity instanceof  Skeleton skeleton){
            CustomCreatureSpawn.customSkeleton(skeleton,reasonSpawn,plugin);
        }
        else if(entity instanceof WitherSkeleton witherSkeleton){
            CustomCreatureSpawn.customWitherSkeleton(witherSkeleton,reasonSpawn,plugin);
        }

        //Zombie zombie = (Zombie) entity;

        //CreatureSpawnEvent.SpawnReason reasonSpawn = event.getSpawnReason();
        /*
        if (event.getEntity() instanceof Zombie && event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) {
            Zombie spawnedZombie = (Zombie) event.getEntity();
            // Si el zombie sobre el que se hizo clic tiene un nombre personalizado, asignárselo al nuevo zombie
            if (event.getEntity().getCustomName() != null) {
                spawnedZombie.setCustomName(event.getEntity().getCustomName());
                spawnedZombie.setCustomNameVisible(true);
            }
        }*/
        //****************************************************************************************************************************************************
        //error, cuando se da click derecho sobre un zombie con un spawner egg en la mano, el zombie obtiene el nombre
        // del zombie especial y luego de cambia su nombre a "Zombie"...
         //****************************************************************************************************************************************************

    }

    @EventHandler
    public void creatureHit(EntityDamageByEntityEvent event){
        Entity entity = event.getDamager();
        Entity defensora = event.getEntity();
        if(entity instanceof Zombie){
            Zombie zombie = (Zombie) entity;
            String name = zombie.getCustomName();
            if(name != null && name.equalsIgnoreCase(ChatColor.YELLOW + "Electro Zombie")){
                Location location = defensora.getLocation();
                World world = location.getWorld();

                zombie.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,10,7));

                world.strikeLightning(location);
            }

        }
        else if(entity instanceof WitherSkeleton witherSkeleton){
            String name = witherSkeleton.getCustomName();
            Location eyeLocation = witherSkeleton.getEyeLocation();
            org.bukkit.util.Vector direction = eyeLocation.getDirection();

            if(name != null && name.equalsIgnoreCase(ChatColor.DARK_GRAY +"Esqueleto Wither del Vortice")){
                org.bukkit.util.Vector oppositeDirection = direction.multiply(-2).normalize();
                double speed = 1; // Puedes ajustar la velocidad

                // Mover al jugador en la dirección opuesta
                org.bukkit.util.Vector newVelocity = oppositeDirection.multiply(speed);
                defensora.setVelocity(newVelocity);
            }
        }
    }

    @EventHandler
    public void creatureDie(EntityDeathEvent event){
        LivingEntity entity = event.getEntity();
        Random random = new Random();
        int randomInt = random.nextInt(100)+1;
        if(entity instanceof Enderman){
            Enderman enderman = (Enderman) entity;
            String name = enderman.getCustomName();
            if(name != null &&  name.equalsIgnoreCase(ChatColor.AQUA+"EnderShulker") && randomInt <= 55){
                ItemStack item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE,1);
                event.getDrops().add(item);
            }


        }
    }

    @EventHandler
    public void damageByCreature(EntityDamageByEntityEvent event){
        Entity entity = event.getDamager();
        Entity defensora = event.getEntity();

        if(defensora instanceof LivingEntity){
            if(entity instanceof Projectile projectile){
                if(projectile.getShooter() instanceof Skeleton skeleton){
                    String name = skeleton.getCustomName();
                    if(name != null && name.equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Esqueleto Explosivo")){
                        Location location = defensora.getLocation();
                        World world = location.getWorld();
                        Creeper creeper = (Creeper) world.spawn(location,Creeper.class);
                        creeper.setExplosionRadius(1);
                        creeper.setCustomName("proyectil");
                        //creeper.setFuseTicks(1);
                        creeper.explode();
                    }
                }
            }
        }
        //zombie capitan
        else if(defensora instanceof Player player){
            if(entity instanceof Zombie zombie){
                String name = zombie.getCustomName();
                if(name != null && name.equalsIgnoreCase(ChatColor.BOLD +""+ ChatColor.GOLD+"Capitan de la Oleada")){
                    List<Entity> nearbyEntities = zombie.getNearbyEntities(5, 5, 5);

                    for (Entity nearbyEntity : nearbyEntities) {
                        if ((nearbyEntity instanceof Monster monster)){
                            monster.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,30,1));
                            monster.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,30,1));
                            monster.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,30,1));
                            monster.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,30,1));
                        }
                    }
                }
            }
        }

    }

    @EventHandler
    public void explodeCreeper(EntityExplodeEvent event){
        Entity entity = event.getEntity();

        if(entity instanceof Creeper creeper){
            String name = creeper.getCustomName();

            if(name != null && name.equalsIgnoreCase(ChatColor.RED+"Creeper Piromano Super Cargado")){
                Location location = creeper.getLocation();
                World world = location.getWorld();
                assert world != null;
                world.createExplosion(location, 16,true);
                /*
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage("¡Este mensaje es porque exploto el ignifugo!");
                }*/
                //Fireball fireball = (Fireball) world.spawn(location, Fireball.class);
                //fireball.setYield(4);

            }
            else if(name != null && name.equalsIgnoreCase(ChatColor.GREEN +"Creeper Envenenado")){

                Location location = creeper.getLocation();
                World world = location.getWorld();
                Vector<PotionEffectType> efectosPociones = new Vector<>();
                efectosPociones.add(PotionEffectType.WEAKNESS);
                efectosPociones.add(PotionEffectType.POISON);
                efectosPociones.add(PotionEffectType.HUNGER);
                efectosPociones.add(PotionEffectType.SLOWNESS);
                efectosPociones.add(PotionEffectType.BLINDNESS);
                efectosPociones.add(PotionEffectType.WITHER);
                efectosPociones.add(PotionEffectType.MINING_FATIGUE);

                Random random = new Random();

                int cantidadEfectos = random.nextInt(100)+1;
                AreaEffectCloud cloud = world.spawn(location,AreaEffectCloud.class);
                HashMap<String,Integer> diffChanges = plugin.getMainConfigManager().getDifficulty_changes();
                if((1 <= cantidadEfectos && cantidadEfectos <= 50) || diffChanges.get("creeperEnvenenado_twoEffects_day") > plugin.getCurrent_day()){
                    //aplicaremos un efecto de pocion

                    cloud.addCustomEffect(new PotionEffect((PotionEffectType) efectosPociones.get(random.nextInt(efectosPociones.size())),100,1,true,true),true);


                }
                else if((51 <= cantidadEfectos && cantidadEfectos <= 83) || diffChanges.get("creeperEnvenenado_threeEffects_day") > plugin.getCurrent_day()){
                    //aplicaremos 2 efectos
                    Set<Integer> numerosAleatorios = new HashSet<>();
                    while(numerosAleatorios.size() < 2){
                        numerosAleatorios.add(random.nextInt(efectosPociones.size()));
                    }

                    for(Integer numero:numerosAleatorios){
                        cloud.addCustomEffect(new PotionEffect(efectosPociones.get(numero),100,1,true,true),true);

                    }
                }
                else{
                    //aplciaremos 3 efectos
                    Set<Integer> numerosAleatorios = new HashSet<>();
                    while(numerosAleatorios.size() < 3){
                        numerosAleatorios.add(random.nextInt(efectosPociones.size()));
                    }

                    for(Integer numero:numerosAleatorios){
                        cloud.addCustomEffect(new PotionEffect(efectosPociones.get(numero),100,1,true,true),true);

                    }



                }

                cloud.setDuration(100);
                cloud.setRadius(5);


            }

        }


    }

    @EventHandler
    public void zombieCapitanOleada(EntityTargetLivingEntityEvent event){
        Entity entity = event.getEntity();
        if( entity instanceof  Zombie zombie){
            String name = zombie.getCustomName();

            if(name != null && name.equalsIgnoreCase(ChatColor.GOLD+"Capitan de la Oleada")){
                List<Entity> nearbyEntities = zombie.getNearbyEntities(5, 5, 5);

                for (Entity nearbyEntity : nearbyEntities) {
                    if ((nearbyEntity instanceof Monster monster)){

                        monster.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,230,1));
                        monster.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,230,1));
                        monster.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,230,1));
                        monster.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,230,1));
                    }

                }
            }

        }

    }

    @EventHandler
    public void playerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        List<Entity> nearbyEntitiesPlayer = player.getNearbyEntities(9, 9, 9);
        for(Entity nearbyEntity: nearbyEntitiesPlayer){
            if(nearbyEntity instanceof Zombie zombie){
                String name = zombie.getCustomName();
                if(name != null && name.equalsIgnoreCase(ChatColor.GOLD+"Capitan de la Oleada")){
                    List<Entity> nearbyEntitiesZombie = zombie.getNearbyEntities(5, 5, 5);
                    for(Entity nEZ: nearbyEntitiesZombie){
                        if(nEZ instanceof  Monster monster){
                            monster.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,60,1));
                            monster.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,60,1));
                            monster.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,60,1));
                            monster.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,60,1));
                        }
                    }
                }
            }
        }

    }



}
