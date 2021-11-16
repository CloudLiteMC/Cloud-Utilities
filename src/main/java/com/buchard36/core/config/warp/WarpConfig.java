package com.buchard36.core.config.warp;

import com.buchard36.core.config.warp.subs.JsonWarp;
import com.burchard36.json.JsonDataFile;
import com.burchard36.json.enums.FileFormat;
import com.burchard36.json.errors.InvalidClassAdapterException;
import com.squareup.moshi.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.burchard36.Logger.log;
import static jdk.internal.org.jline.utils.Log.error;

public class WarpConfig extends JsonDataFile {

    @Json(name = "warps")
    public List<JsonWarp> warpList;

    public WarpConfig(final JavaPlugin plugin,
                      final String pathToFile,
                      final FileFormat format) {
        super(plugin, pathToFile, format);

        this.warpList = new ArrayList<>();
    }


    public void addNewWarp(final Material guiMaterial,
                        final String nameKey,
                        final String guiName,
                        final @Nullable List<String> guiLore,
                        final Location location) {
        final JsonWarp warp = new JsonWarp(location, nameKey, guiMaterial, guiName, guiLore);

        boolean canAdd = true;

        for (final JsonWarp jsonWarp : this.warpList) {
            if (Objects.equals(jsonWarp.warpName, warp.warpName)) {
                canAdd = false;
                error("&cDetected duplicate warp key: &b" + jsonWarp.warpName);
            }
        }

        if (canAdd) this.warpList.add(warp);
    }

    @FromJson
    @Override
    public void fromJson(JsonReader reader, JsonAdapter<? extends JsonDataFile> classFileAdapter)
            throws IOException, InvalidClassAdapterException {
        final WarpConfig warpConfig = (WarpConfig) classFileAdapter.fromJson(reader);
        if (warpConfig == null) throw new InvalidClassAdapterException("Cannot find class adapter for class WarpConfig");

        this.warpList = warpConfig.warpList;
    }

    @ToJson
    @Override
    public void toJson(JsonWriter writer) throws IOException {
        writer.beginObject();
        this.writeWarps(writer);
        writer.endObject();
    }

    private void writeWarps(final JsonWriter writer) throws IOException {
        writer.name("warps");
        writer.beginArray();

        this.warpList.forEach((jsonWarp) -> {
            try {
                writer.beginObject();

                Field[] fields = jsonWarp.getClass().getDeclaredFields();

                for (Field field : fields) {
                    log(field.getType().getSimpleName());
                    log(field.getName());
                }

                writer.name("world_uuid").value(jsonWarp.warpWorldUuid);
                writer.name("x").value(jsonWarp.x);
                writer.name("y").value(jsonWarp.y);
                writer.name("z").value(jsonWarp.z);
                writer.name("look_x").value(jsonWarp.lookX);
                writer.name("look_y").value(jsonWarp.lookY);
                writer.name("look_z").value(jsonWarp.lookZ);
                writer.name("warp_name").value(jsonWarp.warpName);
                writer.name("gui_material").value(jsonWarp.guiMaterial);
                writer.name("gui_name").value(jsonWarp.guiName);
                if (jsonWarp.guiLore == null) {
                    writer.name("gui_lore").jsonValue(new ArrayList<>());
                } else writer.name("gui_lore").jsonValue(jsonWarp.guiLore);

                writer.endObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writer.endArray();
    }
}
