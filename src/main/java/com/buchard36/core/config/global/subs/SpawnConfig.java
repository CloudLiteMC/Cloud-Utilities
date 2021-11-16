package com.buchard36.core.config.global.subs;

import com.squareup.moshi.Json;

public class SpawnConfig {

    @Json(name = "location_x")
    public int locX;

    @Json(name = "location_y")
    public int locY;

    @Json(name = "location_z")
    public int locZ;

    @Json(name = "teleport_sound")
    public String teleportSound;

    @Json(name = "delay_enabled")
    public boolean delayEnabled;

    @Json(name = "delay_timer")
    public int delayTimer;

}
