package PS.plugin.model;

import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {
    private UUID uuid;
    private String name;
    private String clase;
    private int muertes;
    private int mobs_kills;
    private int players_kills;

    public PlayerData(UUID uuid, String name, String clase, int muertes, int mobs_kills, int players_kills){
        this.uuid = uuid;
        this.name = name;
        this.clase = clase;
        this.muertes = muertes;
        this.mobs_kills = mobs_kills;
        this.players_kills = players_kills;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public int getMuertes() {
        return muertes;
    }

    public void setMuertes(int muertes) {
        this.muertes = muertes;
    }

    public int getMobs_kills() {
        return mobs_kills;
    }

    public void setMobs_kills(int mobs_kills) {
        this.mobs_kills = mobs_kills;
    }

    public int getPlayers_kills() {
        return players_kills;
    }

    public void setPlayers_kills(int players_kills) {
        this.players_kills = players_kills;
    }
}
