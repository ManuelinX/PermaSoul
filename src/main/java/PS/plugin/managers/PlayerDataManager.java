package PS.plugin.managers;

import PS.plugin.PermaSoul;
import PS.plugin.model.PlayerData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {
    private Map<UUID, PlayerData> players;
    private Map<String,UUID> playerNames;

    PermaSoul plugin;
    public PlayerDataManager(PermaSoul plugin){
        this.plugin = plugin;
        players = new HashMap<>();
        playerNames = new HashMap<>();

    }

    public Map<UUID, PlayerData> getPlayers() {
        return players;
    }

    public void addMuerte(Player player){
        PlayerData playerData = getPlayer(player,true);
        playerData.setMuertes(playerData.getMuertes()+1);
    }

    public void addMobsKills(Player player){
        PlayerData playerData = getPlayer(player,true);
        playerData.setMobs_kills(playerData.getMobs_kills()+1);
    }

    public void addPlayerKills(Player player){
        PlayerData playerData = getPlayer(player,true);
        playerData.setPlayers_kills(playerData.getPlayers_kills()+1);
    }
    public void addPlayer(PlayerData p){
        players.put(p.getUuid(),p);
        playerNames.put(p.getName(),p.getUuid());
    }

    public PlayerData getPlayerByName(String playerName){
        UUID uuid = playerNames.get(playerName);
        return players.get(uuid);
    }
    public PlayerData getPlayer(Player player, boolean create){
        PlayerData playerData =  players.get(player.getUniqueId());
        if(playerData == null && create){
            playerData = new PlayerData(player.getUniqueId(),player.getName(),plugin.getMainConfigManager().getClase_default(),0,0,0);
            addPlayer(playerData);
        }
        return playerData;
    }

    public int getMuertesByPlayer(Player player){
        PlayerData playerData = getPlayer(player,false);
        if(playerData == null){
            return 0;
        }

        return playerData.getMuertes();
    }

    public int getMobsKillsByPlayer(Player player){
        PlayerData playerData = getPlayer(player,false);
        if(playerData == null){
            return 0;
        }

        return playerData.getMobs_kills();
    }

    public int getPlayersKillsByPlayer(Player player){
        PlayerData playerData = getPlayer(player,false);
        if(playerData == null){
            return 0;
        }

        return playerData.getPlayers_kills();
    }
    public int getMuertesByName(String name){
        PlayerData playerData =  getPlayerByName(name);
        if(playerData == null){
            return 0;
        }

        return playerData.getMuertes();
    }
    public void setPlayers(Map<UUID, PlayerData> players) {
        this.players = players;
        for(Map.Entry<UUID,PlayerData> entry: players.entrySet()){
            playerNames.put(entry.getValue().getName(),entry.getKey());
        }
    }

    public void updateName(Player player){
        PlayerData playerData = getPlayer(player,false);
        if(playerData != null){
            String newName = player.getName();
            String oldName = playerData.getName();
            //*************************************************************************
            //player.sendMessage("-"+playerData.getClase()+"-");
            if(!newName.equals(oldName)){
                playerData.setName(newName); //quiza esta linea no va
                playerNames.remove(oldName);
                playerNames.put(newName,player.getUniqueId());
            }
        }


    }

    public void setClass(Player player,String clase){
        PlayerData playerData = getPlayer(player, true);
        playerData.setClase(clase);
    }
}
