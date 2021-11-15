package com.buchard36.core.modules;

import com.buchard36.core.Core;
import com.buchard36.core.Manager;
import com.buchard36.core.modules.warp.WarpModule;

public class ModuleManager implements Manager {

    public final Core plugin;
    public WarpModule warpModule;

    public ModuleManager(final Core core) {
        this.plugin = core;
    }

    @Override
    public void load() {
        this.warpModule = new WarpModule(this);

        this.warpModule.load();
    }

    @Override
    public void unLoad() {
        this.warpModule.unLoad();
    }

    @Override
    public void reload() {
        this.warpModule.reload();
    }

    public final Core getPlugin() {
        return this.plugin;
    }
}
