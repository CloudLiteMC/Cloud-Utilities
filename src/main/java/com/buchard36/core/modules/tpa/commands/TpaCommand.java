package com.buchard36.core.modules.tpa.commands;

import com.buchard36.core.Core;
import com.buchard36.core.modules.tpa.TpaModule;
import com.buchard36.core.modules.tpa.TpaRequest;
import com.burchard36.command.ApiCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static com.burchard36.ApiLib.convert;

public class TpaCommand {

    private final TpaModule module;
    private final ApiCommand command;

    public TpaCommand(final TpaModule module) {
        this.module = module;

        this.command = new ApiCommand(
                "tpa",
                "requests a player to teleport torwards you",
                "&e/tpa &b<playerName>",
                new ArrayList<>()
        ).onPlayerSender((playerSent) -> {
            final Player player = playerSent.getSendingPlayer();
            final String permission = Core.INSTANCE.getGlobalConfig().tpaConfig.getTpaPermission();

            if (!player.hasPermission(permission)) {
                player.sendMessage(Component.text(convert("&cYou do not have permission to execute this command")));
                return;
            }

            if (playerSent.args().size() != 1) {
                player.sendMessage(Component.text(convert("&cInvalid syntax! &e/tpa &b<player>")));
                return;
            }

            final String arg0 = playerSent.args().get(0);
            final Player receivingPlayer = Bukkit.getPlayer(arg0);
            if (receivingPlayer == null) {
                player.sendMessage(Component.text("&cA player by that name does not exist!"));
                return;
            }


            final TpaRequest tpaRequest = new TpaRequest(this.module, player, receivingPlayer);
            tpaRequest.process();
        });
    }

    public final ApiCommand getCommand() {
        return this.command;
    }
}
