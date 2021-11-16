package com.buchard36.core;

import com.buchard36.core.config.ConfigManager;
import com.buchard36.core.config.global.GlobalConfig;
import com.buchard36.core.config.warp.WarpConfig;
import com.buchard36.core.modules.ModuleManager;
import com.buchard36.core.player.PlayerManager;
import com.burchard36.Api;
import com.burchard36.ApiLib;
import com.burchard36.json.PluginDataManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin implements Api {

    public static Core INSTANCE;
    private ApiLib apiLib;
    private ConfigManager configManager;
    private ModuleManager moduleManager;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.apiLib = new ApiLib().initializeApi(this);
        this.configManager = new ConfigManager(this);
        this.moduleManager = new ModuleManager(this);
        this.playerManager = new PlayerManager(this);


        this.configManager.load();
        this.moduleManager.load();
        this.playerManager.load();
    }

    @Override
    public void onDisable() {
        this.playerManager.unLoad();
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

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }
}
