package com.buchard36.core.player;

import com.buchard36.core.Core;
import com.buchard36.core.Manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager implements Manager, Listener {

    private final Core plugin;

    public HashMap<UUID, CorePlayer> corePlayers;

    public PlayerManager(final Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        this.corePlayers = new HashMap<>();

        Bukkit.getOnlinePlayers().forEach(this::addNewPlayer);
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @Override
    public void unLoad() {
        this.corePlayers.values().forEach(CorePlayer::cancelTasks);
        this.corePlayers.clear();

        HandlerList.unregisterAll(this);
    }

    @Override
    public void reload() {
        this.unLoad();
        this.load();
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player joinedPlayer = event.getPlayer();
        this.addNewPlayer(joinedPlayer);
    }

    public void addNewPlayer(final Player player) {
        this.corePlayers.putIfAbsent(player.getUniqueId(), new CorePlayer(player));
    }
}
