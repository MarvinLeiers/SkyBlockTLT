package de.marvinleiers.skyblocktlt.skyblock;

import de.marvinleiers.mplugin.player.MPlayer;
import de.marvinleiers.mplugin.utils.CustomConfig;
import de.marvinleiers.skyblocktlt.SkyBlockTLT;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.UUID;

public class IslandManager
{
    private static final HashMap<UUID, Island> islands = new HashMap<>();
    private static final SkyBlockTLT plugin = SkyBlockTLT.getInstance();
    private static IslandManager instance;

    private final CustomConfig customConfig;
    private final Location nextIsland;
    private final int islandDistance;

    public IslandManager()
    {
        this.customConfig = new CustomConfig(SkyBlockTLT.getInstance().getDataFolder().getPath() + "/islands.yml");

        if (!customConfig.isSet("next-island"))
            customConfig.setLocation("next-island", new Location(SkyBlockTLT.getSkyblockWorld(), 0, 75, 0));

        this.nextIsland = customConfig.getLocation("next-island");
        this.islandDistance = plugin.getConfig().getInt("island-distance");
    }

    public Island loadIsland(MPlayer mPlayer)
    {
        if (!hasIsland(mPlayer))
            return null;

        if (islands.containsKey(mPlayer.getPlayer().getUniqueId()))
            return islands.get(mPlayer.getPlayer().getUniqueId());

        String islandUUID = mPlayer.getCustomConfig().getString("island");
        CustomConfig islandConfig = new CustomConfig(SkyBlockTLT.getInstance().getDataFolder().getPath()
                + "/islands/" + islandUUID + ".yml");

        Location spawn = islandConfig.getLocation("spawn");
        Island island = new Island(spawn, UUID.fromString(islandUUID));

        islands.put(mPlayer.getPlayer().getUniqueId(), island);

        SkyBlockTLT.getInstance().log("Loaded island " + islandUUID + " by " + mPlayer.getPlayer().getName());

        return island;
    }

    public boolean hasIsland(MPlayer mPlayer)
    {
        return mPlayer.getCustomConfig().isSet("island");
    }

    public Island getNewIsland()
    {
        Island island = new Island(nextIsland.clone());
        customConfig.setLocation("next-island", nextIsland.add(islandDistance, 0, islandDistance));

        plugin.log("Next island will be at x=" + nextIsland.getBlockX() + ", z=" + nextIsland.getBlockZ());

        for (int i = -5; i < 5; i++)
        {
            for (int j = -5; j < 5; j++)
            {
                Block block = island.getSpawn().getWorld().getBlockAt(island.getSpawn().clone().add(i, -1, j));

                SkyBlockTLT.getInstance().log(block.getLocation().toString());

                if (block.getType() == Material.AIR) block.setType(Material.GRASS_BLOCK);
            }
        }

        return island;
    }

    public CustomConfig getCustomConfig()
    {
        return customConfig;
    }

    public static IslandManager getInstance()
    {
        if (instance == null)
            instance = new IslandManager();

        return instance;
    }
}
