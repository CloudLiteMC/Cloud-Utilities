package com.buchard36.core.player;

import com.buchard36.core.Core;
import com.buchard36.core.modules.tpa.TpaRequest;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static com.burchard36.ApiLib.convert;

public class CorePlayer {

    private final Player player;

    private boolean afk;
    private Location lastLocation;
    private BukkitTask afkTask;

    private boolean onTpaCoolDown;
    private BukkitTask tpaTask;
    private TpaRequest tpaRequest;

    public CorePlayer(final Player player) {
        this.player = player;

        this.lastLocation = player.getLocation();
        this.afkTask = new BukkitRunnable() {
            @Override
            public void run() {

                /* For now just a basic location check, later on make this do chat & command checks */

                final Location location = player.getLocation();
                if (location.getBlockX() == lastLocation.getBlockX() &&
                    location.getBlockY() == lastLocation.getBlockY() &&
                    location.getBlockZ() == lastLocation.getBlockZ()) {
                    afk = true;
                    player.sendMessage(Component.text(convert("&7&oYou have gone AFK.")));
                } else {
                    afk = false;
                    player.sendMessage(Component.text(convert("&7&oYou are now back from being AFK")));
                }
            }
        }.runTaskTimerAsynchronously(Core.INSTANCE, 20 * 60, 20 * 60);

        this.afk = false;
        this.onTpaCoolDown = false;
    }

    /**
     * Returns true if the player is AFK
     * @return true if player is AFK
     */
    public final boolean isAfk() {
        return this.afk;
    }

    /**
     * Sets wether or not a player is AFK
     * @param afk true if you want the player to be AFK
     */
    public final void setAfk(final boolean afk) {
        this.afk = afk;
    }

    /**
     * Gets whether this player is on TPA Cooldown
     * @return true if player is on cool-down
     */
    public final boolean isOnTpaCoolDown() {
        return this.onTpaCoolDown;
    }

    /**
     * Sets the length for how long this player will be on TPA Cooldown for
     * @param length
     */
    public final void setTpaCoolDown(final int length) {
        if (this.isOnTpaCoolDown()) return;
        this.onTpaCoolDown = true;
        this.tpaTask = new BukkitRunnable() {
            @Override
            public void run() {
                clearTpaCoolDown();
            }
        }.runTaskLaterAsynchronously(Core.INSTANCE, length);
    }

    public final void clearTpaCoolDown() {
        onTpaCoolDown = false;
        this.tpaTask.cancel();
        tpaTask = null;
    }

    public final Player getPlayer() {
        return this.player;
    }

    public final void cancelTasks() {
        this.afkTask.cancel();
        this.tpaTask.cancel();
    }

}
