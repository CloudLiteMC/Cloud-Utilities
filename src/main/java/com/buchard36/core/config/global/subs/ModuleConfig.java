package com.buchard36.core.config.global.subs;

import com.squareup.moshi.Json;

public class ModuleConfig {

    @Json(name = "warp_module_enabled")
    public boolean warpModuleEnabled;

    @Json(name = "spawn_module_enabled")
    public boolean spawnModuleEnabled;

    @Json(name = "tpa_module_enabled")
    public boolean tpaModuleEnabled;

    public ModuleConfig() {
        this.warpModuleEnabled = true;
        this.spawnModuleEnabled = true;
        this.tpaModuleEnabled = true;
    }

}
