package com.buchard36.core.config.warp;

import com.buchard36.core.config.warp.subs.JsonWarp;
import com.burchard36.json.JsonDataFile;
import com.burchard36.json.enums.FileFormat;
import com.squareup.moshi.Json;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WarpConfig extends JsonDataFile {

    @Json(name = "warps")
    public List<JsonWarp> warpList;

    public transient HashMap<String, JsonWarp> warps;

    public WarpConfig(final JavaPlugin plugin,
                      final String pathToFile,
                      final FileFormat format) {
        super(plugin, pathToFile, format);
    }


    public void addNewWarp(final Material guiMaterial,
                        final String nameKey,
                        final String guiName,
                        final @Nullable List<String> guiLore,
                        final Location location) {
        final JsonWarp warp = new JsonWarp(location, nameKey, guiMaterial, guiName, guiLore);


    }

}
