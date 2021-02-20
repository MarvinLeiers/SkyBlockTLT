package de.marvinleiers.skyblocktlt.commands.subcommands;

import de.marvinleiers.mplugin.commands.Subcommand;
import de.marvinleiers.mplugin.player.MPlayer;
import de.marvinleiers.skyblocktlt.SkyBlockTLT;
import de.marvinleiers.skyblocktlt.skyblock.Island;
import de.marvinleiers.skyblocktlt.skyblock.IslandManager;
import org.bukkit.entity.Player;

public class TeleportHome extends Subcommand
{
    @Override
    public String getName()
    {
        return "home";
    }

    @Override
    public String getDescription()
    {
        return "Teleport to your island";
    }

    @Override
    public String getSyntax()
    {
        return "/island home";
    }

    @Override
    public String getPermission()
    {
        return "island.home";
    }

    @Override
    public void execute(Player player, String[] strings)
    {
        MPlayer mPlayer = SkyBlockTLT.getPlayerHandler().getPlayer(player);
        IslandManager islandManager = SkyBlockTLT.getIslandManager();

        if (!islandManager.hasIsland(mPlayer))
        {
            mPlayer.sendMessage(SkyBlockTLT.getInstance().getConfig().getString("message-dont-has-island"));
            return;
        }

        Island island = islandManager.loadIsland(mPlayer);
        player.teleport(island.getSpawn());
    }
}
