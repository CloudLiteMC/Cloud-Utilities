package com.buchard36.core.config.global.subs;

import com.squareup.moshi.Json;

public class TpaConfig {

    @Json(name = "tpa_permission")
    public String tpaPermission;

    @Json(name = "tpa_here_permission")
    public String tpaHerePermission;

    @Json(name = "tpa_expiration_time")
    public int tpaExpirationTime;

}
