package com.buchard36.core.modules;

import com.buchard36.core.Core;
import com.buchard36.core.Manager;
import com.buchard36.core.config.global.GlobalConfig;
import com.buchard36.core.modules.warp.WarpModule;

public class ModuleManager implements Manager {

    public final Core plugin;
    public WarpModule warpModule;
    public GlobalConfig globalConfig;

    public ModuleManager(final Core core) {
        this.plugin = core;
    }

    @Override
    public void load() {
        this.globalConfig = this.plugin.getGlobalConfig();

        if (this.globalConfig.isWarpModuleEnabled()) this.loadWarpModule();
    }

    @Override
    public void unLoad() {
        if (this.warpModule != null) this.warpModule.unLoad();
    }

    @Override
    public void reload() {
        this.warpModule.reload();
    }

    public final Core getPlugin() {
        return this.plugin;
    }

    private void loadWarpModule() {
        this.warpModule = new WarpModule(this);
        this.warpModule.load();
    }

    private void unloadWarpModule() {
        this.warpModule.unLoad();
        this.warpModule = null;
    }
}
