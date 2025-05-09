package PS.plugin;
import PS.plugin.Listeners.CreatureListeners;
import PS.plugin.Listeners.PlayerListeners;
import PS.plugin.commands.*;
import PS.plugin.config.CustomConfig;
import PS.plugin.config.MainConfigManager;
import PS.plugin.config.PlayersConfigManager;
import PS.plugin.managers.PlayerDataManager;
import PS.plugin.tasks.AutoSave;
import PS.plugin.tasks.Clock;
import PS.plugin.tasks.SelectClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class PermaSoul extends JavaPlugin {
    private MainConfigManager mainConfigManager;
    long current_day = 0;
    PlayerDataManager playerDataManager;
    private PlayersConfigManager playersConfigManager;
    private AutoSave autoSave;
    private Clock clock;
    private SelectClass rememberSelectClass;
    @Override
    public void onEnable() {
        super.onEnable();
        registerCommands();
        mainConfigManager = new MainConfigManager(this);
        current_day = mainConfigManager.getTime()/86400;


        playerDataManager = new PlayerDataManager(this);
        playersConfigManager = new PlayersConfigManager(this,"players");
        //autoguardado
        registerEvents();
        autoSave = new AutoSave(this);
        rememberSelectClass = new SelectClass(this);

        rememberSelectClass.rememberSelectClass(20*60*7,20*60*7);
        //cada 8 minutos se debe ejecutar
        autoSave.runTaskTimer(20*60*8,20*60*8);

        clock = new Clock(this);
        clock.runClockTimer(1 * 20, 1 * 20);

        Bukkit.getConsoleSender().sendMessage("**************************");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&'," &eSe ha prendido PermaSoul "));
        Bukkit.getConsoleSender().sendMessage("**************************");
    }

    @Override
    public void onDisable(){
        super.onDisable();
        playersConfigManager.saveConfigs();
        mainConfigManager.saveConfig();
        Bukkit.getConsoleSender().sendMessage("*************************");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&'," &fSe ha apagado PermaSoul "));

        Bukkit.getConsoleSender().sendMessage("*************************");
    }

    //falta llamar a los listener aqui xD
    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new PlayerListeners(this),this);
        getServer().getPluginManager().registerEvents(new CreatureListeners(this),this);
    }

    public void registerCommands(){
        this.getCommand("permasoul").setExecutor(new MainCommand(this));
        this.getCommand("muertes").setExecutor(new MuertesCommand(this));
        this.getCommand("mobs_kills").setExecutor(new MobsKillsCommand(this));
        this.getCommand("players_kills").setExecutor(new PlayersKillsCommand(this));
        this.getCommand("total_kills").setExecutor(new TotalKillsCommand(this));
        this.getCommand("current_day").setExecutor(new CurrentDayCommand(this));
        this.getCommand("select_class").setExecutor( new SelectClassCommand(this));
    }

    public MainConfigManager getMainConfigManager() {
        return mainConfigManager;
    }

    public long getCurrent_day() {
        return current_day;
    }

    public PlayersConfigManager getPlayersConfigManager() {
        return playersConfigManager;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public void setCurrent_day(long current_day){
        this.current_day = current_day;
    }

}
