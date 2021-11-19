package com.buchard36.core.modules.tpa;

import com.buchard36.core.player.CorePlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import static com.burchard36.ApiLib.convert;

public class TpaRequest {

    private final TpaModule module;
    private final Player sendingPlayer;
    private final Player receivingPlayer;
    private boolean isResolved;

    public TpaRequest(
            final TpaModule module,
            final Player sender,
            final Player receiver
            ) {
        this.module = module;
        this.sendingPlayer = sender;
        this.receivingPlayer = receiver;
    }

    public void process() {
        final CorePlayer senderPlayer = this.module.getPlugin().getPlayerManager().getPlayer(this.sendingPlayer);

        if (this.sendingPlayer.getUniqueId() == this.receivingPlayer.getUniqueId()) {
            this.sendingPlayer.sendMessage(Component.text(convert("&cCannot send requests to self!")));
            return;
        }

        if (senderPlayer.isOnTpaCoolDown()) {
            this.sendingPlayer.sendMessage(Component.text(convert("&cYou cannot send teleport requests yet!")));
            return;
        }

        final CorePlayer receiverPlayer = this.module.getPlugin().getPlayerManager().getPlayer(this.receivingPlayer);

        if (receiverPlayer.isOnTpaCoolDown()) {
            this.sendingPlayer.sendMessage(Component.text(convert("&cThe player you are trying to send to is also on a cool-down!")));
            return;
        }

        sendingPlayer.sendMessage(Component.text(convert("&aTeleport request sent!")));


    }

    public boolean isResolved() {
        return this.isResolved;
    }

    public final void setResolved(final boolean resolved) {
        this.isResolved = resolved;
    }

}
