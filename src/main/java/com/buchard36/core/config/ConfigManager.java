package com.buchard36.core.config;

import com.buchard36.core.Core;
import com.buchard36.core.Manager;
import com.buchard36.core.config.global.GlobalConfig;
import com.buchard36.core.config.warp.WarpConfig;
import com.burchard36.json.PluginDataManager;
import com.burchard36.json.PluginDataMap;
import com.burchard36.json.enums.FileFormat;

public class ConfigManager implements Manager {

    private final Core plugin;
    private PluginDataMap dataMap;

    private GlobalConfig globalConfig;
    private WarpConfig warpConfig;

    public ConfigManager(final Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        final PluginDataManager manager = this.plugin.getPluginDataManager();

        manager.registerPluginMap(ConfigMaps.GLOBAL, new PluginDataMap());
        this.dataMap = manager.getDataMap(ConfigMaps.GLOBAL);
        this.globalConfig = new GlobalConfig(this.plugin, "/config/global_config", FileFormat.JSON);
        this.warpConfig = new WarpConfig(this.plugin, "/data/warps", FileFormat.JSON);

        this.dataMap.loadDataFile( "global_config", this.globalConfig);
        this.dataMap.loadDataFile("warp_data", this.warpConfig);
    }

    @Override
    public void unLoad() {
        this.dataMap.saveAll();
    }

    @Override
    public void reload() {

    }

    public final GlobalConfig getGlobalConfig() {
        return (GlobalConfig) this.dataMap.getDataFile("global_config");
    }

    public final WarpConfig getWarpConfig() {
        return (WarpConfig) this.dataMap.getDataFile("warp_data");
    }
}
