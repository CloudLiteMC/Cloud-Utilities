package com.buchard36.core;

import com.buchard36.core.config.ConfigManager;
import com.buchard36.core.config.global.GlobalConfig;
import com.buchard36.core.config.warp.WarpConfig;
import com.buchard36.core.modules.ModuleManager;
import com.burchard36.Api;
import com.burchard36.ApiLib;
import com.burchard36.json.PluginDataManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin implements Api {

    private ApiLib apiLib;
    private ConfigManager configManager;
    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        this.apiLib = new ApiLib().initializeApi(this);
        this.configManager = new ConfigManager(this);
        this.moduleManager = new ModuleManager(this);


        this.configManager.load();
        this.moduleManager.load();
    }

    @Override
    public void onDisable() {
        this.moduleManager.unLoad();
        this.configManager.unLoad(); // unload data stuff last
    }

    public ApiLib getApiLib() {
        return this.apiLib;
    }

    @Override
    public PluginDataManager getPluginDataManager() {
        return this.getApiLib().getPluginDataManager();
    }

    @Override
    public boolean isDebug() {
        return true;
    }

    public GlobalConfig getGlobalConfig() {
        return this.configManager.getGlobalConfig();
    }

    public WarpConfig getWarpConfig() {
        return this.configManager.getWarpConfig();
    }
}
