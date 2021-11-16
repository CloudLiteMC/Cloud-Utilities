package com.buchard36.core.config.global;

import com.buchard36.core.config.global.subs.ModuleConfig;
import com.buchard36.core.config.global.subs.SpawnConfig;
import com.buchard36.core.config.global.subs.TpaConfig;
import com.burchard36.json.JsonDataFile;
import com.burchard36.json.enums.FileFormat;
import com.burchard36.json.errors.InvalidClassAdapterException;
import com.squareup.moshi.*;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class GlobalConfig extends JsonDataFile {

    @Json(name = "modules")
    public ModuleConfig moduleConfig;

    @Json(name = "tpa_section")
    public TpaConfig tpaConfig;

    @Json(name = "spawn_section")
    public SpawnConfig spawnConfig;

    public GlobalConfig(JavaPlugin plugin, String pathToFile, FileFormat format) {
        super(plugin, pathToFile, format);

        this.moduleConfig = new ModuleConfig();
    }

    @FromJson
    @Override
    public void fromJson(JsonReader reader, JsonAdapter<? extends JsonDataFile> classFileAdapter)
            throws IOException, InvalidClassAdapterException {
        final GlobalConfig config = (GlobalConfig) classFileAdapter.fromJson(reader);
        if (config == null) throw new InvalidClassAdapterException("Invalid adapter for class: GlobalConfig");

        this.moduleConfig = config.moduleConfig;
        this.tpaConfig = config.tpaConfig;
    }

    @ToJson
    @Override
    public void toJson(JsonWriter writer) throws IOException {
        writer.beginObject();
        this.writeModuleSection(writer);
        this.writeTpaSection(writer);
        this.writeSpawnSection(writer);
        writer.endObject();
    }

    private void writeModuleSection(final JsonWriter writer) throws IOException {
        writer.name("modules");
        writer.beginObject();
        writer.name("spawn_module_enabled").value(this.moduleConfig.spawnModuleEnabled);
        writer.name("tpa_module_enabled").value(this.moduleConfig.tpaModuleEnabled);
        writer.name("warp_module_enabled").value(this.moduleConfig.warpModuleEnabled);
        writer.endObject();
    }

    private void writeTpaSection(final JsonWriter writer) throws IOException {
        writer.name("tpa_section");
        writer.beginObject();
        writer.name("tpa_permission").value(this.tpaConfig.tpaPermission);
        writer.name("tpa_here_permission").value(this.tpaConfig.tpaHerePermission);
        writer.name("tpa_expiration_time").value(this.tpaConfig.tpaExpirationTime);
        writer.endObject();
    }

    private void writeSpawnSection(final JsonWriter writer) throws IOException {
        writer.name("spawn_section");
        writer.beginObject();
        writer.name("location_x").value(this.spawnConfig.locX);
        writer.name("location_y").value(this.spawnConfig.locY);
        writer.name("location_z").value(this.spawnConfig.locZ);
        writer.name("teleport_sound").value(this.spawnConfig.teleportSound);
        writer.name("delay_enabled").value(this.spawnConfig.delayEnabled);
        writer.name("delay_timer").value(this.spawnConfig.delayTimer);
        writer.endObject();
    }

    public final boolean isWarpModuleEnabled() {
        return this.moduleConfig.warpModuleEnabled;
    }

    public final boolean isTpaModuleEnabled() {
        return this.moduleConfig.warpModuleEnabled;
    }

    public final boolean isSpawnModuleEnabled() {
        return this.moduleConfig.spawnModuleEnabled;
    }
}
