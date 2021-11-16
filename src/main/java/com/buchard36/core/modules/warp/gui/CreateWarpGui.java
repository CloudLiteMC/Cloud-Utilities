package com.buchard36.core.modules.warp.gui;

import com.buchard36.core.modules.warp.WarpModule;
import com.burchard36.inventory.ClickableItem;
import com.burchard36.inventory.ItemWrapper;
import com.burchard36.inventory.PluginInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CreateWarpGui {

    private PluginInventory inventory;
    private final WarpModule module;

    private ClickableItem createWarpItem;
    private ClickableItem cancelWarpItem;

    public CreateWarpGui(final WarpModule module,
                         final String warpName) {
        this.module = module;
        this.inventory = new PluginInventory(9, "&b&lCreate Warp?");
        this.createWarpItem = new ClickableItem(new ItemWrapper(new ItemStack(Material.GREEN_DYE, 1)));
        this.cancelWarpItem = new ClickableItem(new ItemWrapper(new ItemStack(Material.RED_DYE, 1)));

        this.inventory.onClick((clickAction) -> clickAction.setCancelled(true));

        this.createWarpItem.onClick((onClick) -> {
            final Player player = (Player) onClick.getWhoClicked();

            this.module.config.addNewWarp(Material.DIRT,
                    warpName,
                    warpName,
                    null,
                    player.getLocation());
            player.getInventory().close();
        });

        this.cancelWarpItem.onClick((onClick) -> {
            onClick.getInventory().close();
        });

        this.inventory.addClickableItemAtSlot(3, this.createWarpItem);
        this.inventory.addClickableItemAtSlot(5, this.cancelWarpItem);
    }

    public final void showTo(final Player player) {
        this.inventory.register(this.module.manager.getPlugin());
        this.inventory.open(player);
    }

}
