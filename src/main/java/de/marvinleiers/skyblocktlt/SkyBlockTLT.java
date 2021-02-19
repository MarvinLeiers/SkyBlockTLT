package de.marvinleiers.skyblocktlt;

import de.marvinleiers.mplugin.MPlugin;
import de.marvinleiers.skyblocktlt.commands.SkyBlockCommand;
import de.marvinleiers.skyblocktlt.listeners.PlayerJoinListener;
import de.marvinleiers.skyblocktlt.worldgeneration.SkyBlockWorldGenerator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public final class SkyBlockTLT extends MPlugin
{
    private static World skyblockWorld;
    private String worldName;

    @Override
    protected void onStart()
    {
        this.worldName = getConfig().getString("world-name");

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        createDefaultSkyWorld();

        new SkyBlockCommand("skyblock");
    }

    @Override
    protected void onShutdown()
    {

    }

    private void createDefaultSkyWorld()
    {
        if (Bukkit.getWorld(worldName) == null)
        {
            skyblockWorld = getServer().createWorld(new WorldCreator(worldName).generateStructures(false)
                    .generator(new SkyBlockWorldGenerator()));

            log("World created!");
        }
        else
        {
            skyblockWorld = Bukkit.getWorld(worldName);
            log("World loaded");
        }
    }

    public static SkyBlockTLT getInstance()
    {
        return getPlugin(SkyBlockTLT.class);
    }
    
    public static World getSkyblockWorld()
    {
        return skyblockWorld;
    }
}
