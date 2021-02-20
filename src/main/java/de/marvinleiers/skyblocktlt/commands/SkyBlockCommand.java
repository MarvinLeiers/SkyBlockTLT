package de.marvinleiers.skyblocktlt.commands;

import de.marvinleiers.mplugin.commands.RootCommand;
import de.marvinleiers.skyblocktlt.commands.subcommands.CreateIsland;
import de.marvinleiers.skyblocktlt.commands.subcommands.TeleportHome;
import org.bukkit.command.ConsoleCommandSender;

public class SkyBlockCommand extends RootCommand
{
    public SkyBlockCommand(String name)
    {
        super(name);

        addSubcommand(new CreateIsland());
        addSubcommand(new TeleportHome());
    }

    @Override
    protected void onConsoleExecuted(ConsoleCommandSender sender, String[] args)
    {
        sender.sendMessage("§cOnly players perform this comamand!");
    }
}
