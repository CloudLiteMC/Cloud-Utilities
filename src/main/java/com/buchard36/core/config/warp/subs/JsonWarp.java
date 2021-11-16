package com.buchard36.core.config.warp.subs;

import com.burchard36.inventory.ItemWrapper;
import com.squareup.moshi.Json;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import static com.burchard36.Logger.error;

public class JsonWarp {

    @Json(name = "world_uuid")
    public String warpWorldUuid;

    public int x;

    public int y;

    public int z;

    @Json(name = "look_x")
    public int lookX;

    @Json(name = "look_y")
    public int lookY;

    @Json(name = "look_z")
    public int lookZ;

    @Json(name = "warp_name")
    public String warpName;

    @Json(name = "gui_material")
    public String guiMaterial;

    @Json(name = "gui_name")
    public String guiName;

    @Json(name = "gui_lore")
    public List<String> guiLore;

    public JsonWarp(
            final Location warpLocation,
            final String warpName,
            final Material guiMaterial,
            final String guiName,
            final @Nullable List<String> guiLore
            ) {
        this.warpWorldUuid = warpLocation.getWorld().getUID().toString();
        this.x = warpLocation.getBlockX();
        this.y = warpLocation.getBlockY();
        this.z = warpLocation.getBlockZ();
        final Vector look = warpLocation.getDirection();
        this.lookX = look.getBlockX();
        this.lookY = look.getBlockY();
        this.lookZ = look.getBlockZ();
        this.warpName = warpName;
        this.guiMaterial = guiMaterial.name();
        this.guiName = guiName;
        this.guiLore = guiLore;
    }

    public final Location getWarpLocation() {

        final World world = Bukkit.getWorld(UUID.fromString(this.warpWorldUuid));
        if (world == null) {
            error("&4World with UUID: &b" + this.warpWorldUuid + " &4does not exist!");
            return null;
        }

        final Location location = new Location(world, this.x, this.y, this.z);
        location.setDirection(new Vector(this.lookX, this.lookY, this.lookZ));

        return location;
    }

    public ItemStack getWarpGuiItem() {
        final ItemWrapper wrapper = new ItemWrapper(new ItemStack(Material.valueOf(this.guiMaterial), 1));
        wrapper.setDisplayName(this.guiName);
        if (this.guiLore != null) wrapper.setItemLore(this.guiLore);

        return wrapper.getItemStack();
    }

}
