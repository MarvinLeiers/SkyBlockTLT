package de.marvinleiers.skyblocktlt.skyblock;

import de.marvinleiers.mplugin.player.MPlayer;
import de.marvinleiers.mplugin.utils.CustomConfig;
import de.marvinleiers.skyblocktlt.SkyBlockTLT;
import org.bukkit.Location;

import java.util.UUID;

public class Island
{
    private final CustomConfig customConfig;
    private final Location spawn;
    private final UUID uuid;
    private MPlayer owner;

    public Island(Location spawn)
    {
        this(spawn, UUID.randomUUID());
    }

    public Island(Location spawn, UUID uuid)
    {
        this.spawn = spawn;
        this.uuid = uuid;

        this.customConfig = new CustomConfig(SkyBlockTLT.getInstance().getDataFolder().getPath()
                + "/islands/" + uuid.toString() + ".yml");
    }

    public void setOwner(MPlayer owner)
    {
        this.owner = owner;

        customConfig.set("owner", owner.getPlayer().getUniqueId().toString());
        customConfig.setLocation("spawn", getSpawn());
        owner.getCustomConfig().set("island", getUuid().toString());
    }

    public MPlayer getOwner()
    {
        return owner;
    }

    public Location getSpawn()
    {
        return spawn;
    }

    public UUID getUuid()
    {
        return uuid;
    }
}
