package de.marvinleiers.skyblocktlt.commands.subcommands;

import de.marvinleiers.mplugin.MPlugin;
import de.marvinleiers.mplugin.commands.Subcommand;
import de.marvinleiers.mplugin.player.MPlayer;
import de.marvinleiers.skyblocktlt.SkyBlockTLT;
import de.marvinleiers.skyblocktlt.skyblock.Island;
import org.bukkit.entity.Player;

public class CreateIsland extends Subcommand
{
    @Override
    public String getName()
    {
        return "create";
    }

    @Override
    public String getDescription()
    {
        return "Create your island";
    }

    @Override
    public String getSyntax()
    {
        return "/island create";
    }

    @Override
    public String getPermission()
    {
        return "island.create";
    }

    @Override
    public void execute(Player player, String[] strings)
    {
        MPlayer mPlayer = MPlugin.getPlayerHandler().getPlayer(player);

        if (mPlayer.getCustomConfig().isSet("island"))
        {
            mPlayer.sendMessage(SkyBlockTLT.getInstance().getConfig().getString("message-already-has-island"));
            return;
        }

        Island island = SkyBlockTLT.getIslandManager().getNewIsland();
        island.setOwner(mPlayer);

        mPlayer.sendMessage(SkyBlockTLT.getInstance().getConfig().getString("message-new-island"));
    }
}
