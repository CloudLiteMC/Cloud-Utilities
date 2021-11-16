package com.buchard36.core.modules.warp.commands;

import com.buchard36.core.modules.warp.WarpModule;
import com.buchard36.core.modules.warp.gui.CreateWarpGui;
import com.burchard36.command.ApiCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static com.burchard36.ApiLib.convert;

public class CreateWarpCommand {

    public final WarpModule module;

    private final ApiCommand command;

    public CreateWarpCommand(final WarpModule module) {
        this.module = module;

        this.command = new ApiCommand("createwarp",
                "Creates a warp where you stand",
                "/createwarp [<name>]",
                new ArrayList<>())
                .onPlayerSender((playerSent) -> {
                    final String permission = "cloud.core.createwarp";
                    final Player player = playerSent.getSendingPlayer();

                    if (!player.hasPermission(permission)) {
                        player.sendMessage(Component.text(convert("&cYou do not have permission to execute this command!")));
                        return;
                    }

                    if (playerSent.args().size() != 1) {
                        player.sendMessage(Component.text(convert("&c You need to specify a warp ID like so: &b/warp &7<&bwarp_name&7>")));
                        return;
                    }

                    final CreateWarpGui gui = new CreateWarpGui(this.module,
                            playerSent.args().get(0));
                    gui.showTo(player);
        });
    }
    public final ApiCommand getCommand() {
        return this.command;
    }

}
