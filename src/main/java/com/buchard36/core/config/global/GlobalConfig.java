package com.buchard36.core.config.global;

import com.buchard36.core.config.global.subs.ModuleConfig;
import com.burchard36.json.JsonDataFile;
import com.burchard36.json.enums.FileFormat;
import com.burchard36.json.errors.InvalidClassAdapterException;
import com.squareup.moshi.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class GlobalConfig extends JsonDataFile {

    @Json(name = "modules")
    public ModuleConfig moduleConfig;

    public GlobalConfig(JavaPlugin plugin, String pathToFile, FileFormat format) {
        super(plugin, pathToFile, format);
    }

    @FromJson
    @Override
    public void fromJson(JsonReader reader, JsonAdapter<? extends JsonDataFile> classFileAdapter)
            throws IOException, InvalidClassAdapterException {
        final GlobalConfig config = (GlobalConfig) classFileAdapter.fromJson(reader);
        if (config == null) throw new InvalidClassAdapterException("Invalid adapter for class: GlobalConfig");

        this.moduleConfig = config.moduleConfig;
    }

    @ToJson
    @Override
    public void toJson(JsonWriter writer) throws IOException {
        writer.beginObject();
        this.writeModuleSection(writer);
        writer.endObject();
    }

    private void writeModuleSection(final JsonWriter writer) throws IOException{
        writer.name("modules");
        writer.beginObject();
        writer.name("spawn_module_enabled").value(this.moduleConfig.spawnModuleEnabled);
        writer.name("tpa_module_enabled").value(this.moduleConfig.tpaModuleEnabled);
        writer.name("warp_module_enabled").value(this.moduleConfig.warpModuleEnabled);
        writer.endObject();
    }
}
