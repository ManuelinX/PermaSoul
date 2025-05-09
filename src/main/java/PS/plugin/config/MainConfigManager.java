package PS.plugin.config;

import PS.plugin.PermaSoul;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Vector;

public class MainConfigManager {
    private CustomConfig configFile;
    private PermaSoul plugin;

    private HashMap<String,Integer> difficulty_changes = new HashMap<>();
    private Integer start_select_day;
    private Integer end_select_day;
    private String clase_default;

    private long time = 0;

    public MainConfigManager(PermaSoul plugin){
        this.plugin = plugin;
        configFile =  new CustomConfig("config.yml",null,plugin,false);
        configFile.registerConfig();
        loadConfig();

    }

    public void loadConfig(){
        FileConfiguration file = configFile.getConfig();
        difficulty_changes.put("infectado_day",file.getInt("config.difficulty_changes.infectado_day"));
        difficulty_changes.put("electroZombie_day",file.getInt("config.difficulty_changes.electroZombie_day"));
        difficulty_changes.put("guerreroZombie_day",file.getInt("config.difficulty_changes.guerreroZombie_day"));
        difficulty_changes.put("capitandelaOleada_day",file.getInt("config.difficulty_changes.capitandelaOleada_day"));
        difficulty_changes.put("enderShulker_day",file.getInt("config.difficulty_changes.enderShulker_day"));
        difficulty_changes.put("phantomGiant_day",file.getInt("config.difficulty_changes.phantomGiant_day"));
        difficulty_changes.put("creeperEnvenenado_oneEffect_day",file.getInt("config.difficulty_changes.creeperEnvenenado_oneEffect_day"));
        difficulty_changes.put("creeperEnvenenado_twoEffects_day",file.getInt("config.difficulty_changes.creeperEnvenenado_twoEffects_day"));
        difficulty_changes.put("creeperEnvenenado_threeEffects_day",file.getInt("config.difficulty_changes.creeperEnvenenado_threeEffects_day"));
        difficulty_changes.put("creeperSuperCargado_day",file.getInt("config.difficulty_changes.creeperSuperCargado_day"));
        difficulty_changes.put("creeperPiromanoSuperCargado_day",file.getInt("config.difficulty_changes.creeperPiromanoSuperCargado_day"));
        difficulty_changes.put("esqueletoInfernal_day",file.getInt("config.difficulty_changes.esqueletoInfernal_day"));
        difficulty_changes.put("esqueletoExplosivo_day",file.getInt("config.difficulty_changes.esqueletoExplosivo_day"));
        difficulty_changes.put("esqueletoWitherdelVortice_day",file.getInt("config.difficulty_changes.esqueletoWitherdelVortice_day"));
        difficulty_changes.put("maldicionTotem",file.getInt("config.difficulty_changes.maldicionTotem"));

        start_select_day = file.getInt("config.clases.start_select_day");
        end_select_day = file.getInt("config.clases.end_select_day");
        clase_default = file.getString("config.clases.default");
        time = file.getLong("time");
    }

    public void reloadConfig(){
        configFile.reloadConfig();
        loadConfig();
    }

    public void saveConfig(){
        FileConfiguration config = configFile.getConfig();
        config.set("time",time);
        configFile.saveConfig();

        //configFile.saveConfig();
    }
    public HashMap<String, Integer> getDifficulty_changes() {
        return difficulty_changes;
    }

    public Integer getStart_select_day() {
        return start_select_day;
    }

    public Integer getEnd_select_day() {
        return end_select_day;
    }

    public String getClase_default() {
        return clase_default;
    }

    public long getTime(){
        return time;
    }

    public void setTime(long time){
        this.time = time;
    }
}
