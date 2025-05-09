package PS.plugin.config;

import PS.plugin.PermaSoul;
import PS.plugin.model.PlayerData;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayersConfigManager extends DataFolderConfigManager{

    public PlayersConfigManager(PermaSoul plugin, String folderName) {
        super(plugin, folderName);
    }

    @Override
    public void loadConfigs() {
        Map<UUID, PlayerData> players = new HashMap<>();
        for(CustomConfig customConfig: configFiles){
            FileConfiguration config = customConfig.getConfig();
            UUID uuid = UUID.fromString(customConfig.getPath().replace(".yml",""));
            String name = config.getString("name");
            String clase = config.getString("clase");
            int muertes = config.getInt("muertes");
            int mobs_kills = config.getInt("mobs_kills");
            int players_kills = config.getInt("players_kills");
            PlayerData playerData = new PlayerData(uuid,name,clase,muertes,mobs_kills,players_kills);
            players.put(uuid,playerData);
        }
        plugin.getPlayerDataManager().setPlayers(players);
    }

    @Override
    public void saveConfigs() {
        Map<UUID,PlayerData> players = plugin.getPlayerDataManager().getPlayers();
        for(Map.Entry<UUID,PlayerData> entry: players.entrySet()){
            PlayerData playerData = entry.getValue();
            String pathName = playerData.getUuid().toString() + ".yml";
            CustomConfig customConfig = getConfigFile(pathName);
            if(customConfig == null){
                //hay que crearlo
                customConfig =  registerConfigFile(pathName);
            }
            FileConfiguration config = customConfig.getConfig();
            config.set("name",playerData.getName());
            config.set("clase",playerData.getClase());
            config.set("muertes",playerData.getMuertes());
            config.set("mobs_kills",playerData.getMobs_kills());
            config.set("players_kills",playerData.getPlayers_kills());
        }
        saveConfigFiles();
    }
}
