package de.marvinleiers.skyblocktlt.listeners;

import de.marvinleiers.skyblocktlt.SkyBlockTLT;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        SkyBlockTLT.getIslandManager().loadIsland(SkyBlockTLT.getPlayerHandler().getPlayer(player));
    }
}
