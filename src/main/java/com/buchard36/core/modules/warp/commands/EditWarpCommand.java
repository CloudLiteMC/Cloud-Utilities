package com.buchard36.core.modules.warp.commands;

import com.buchard36.core.modules.warp.WarpModule;
import com.burchard36.command.ApiCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.burchard36.ApiLib.convert;

public class EditWarpCommand implements TabCompleter {

    public final WarpModule module;
    public final ApiCommand command;

    public List<String> tabCompleter;

    public EditWarpCommand(final WarpModule module) {
        this.module = module;
        this.loadTabCompleter();

        this.command = new ApiCommand("editwarp",
                "Edits a warp",
                "&e/editwarp &c<warp_name>",
                new ArrayList<>())
                .onPlayerSender((playerSent) -> {
                    final Player player = playerSent.getSendingPlayer();
                    final String permission = "cloud.core.createwarp";

                    if (!player.hasPermission(permission)) {
                        player.sendMessage(Component.text(convert("&cYou do not have permissions to execute this command")));
                        return;
                    }

                    if (playerSent.args().size() != 1) {
                        player.sendMessage(Component.text(convert("&cInvalid syntax, command need to contains a Warp ID like so: &e" + "/editwarp &b<warp_name>")));
                        return;
                    }


                });
    }

    /**
     * Calls this if a warp gets added or removed
     */
    public final void loadTabCompleter() {
        this.tabCompleter = new ArrayList<>();
        this.module.config.warpList.forEach((jsonWarp) -> this.tabCompleter.add(jsonWarp.warpName));
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,
                                                @NotNull Command command,
                                                @NotNull String alias,
                                                @NotNull String[] args) {
        if (command == this.command) {
            return this.tabCompleter;
        } else return null;
    }
}
