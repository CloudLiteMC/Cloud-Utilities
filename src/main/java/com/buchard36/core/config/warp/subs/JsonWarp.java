package com.buchard36.core.config.warp.subs;

import com.squareup.moshi.Json;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

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

    @Json(name = "name")
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

        final Location location = new Location()
    }

}
