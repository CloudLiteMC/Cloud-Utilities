package com.buchard36.core.modules.warp;

import com.buchard36.core.config.warp.WarpConfig;
import com.buchard36.core.modules.Module;
import com.buchard36.core.modules.ModuleManager;

public class WarpModule implements Module {

    public final ModuleManager manager;
    public final WarpConfig config;

    public WarpModule(final ModuleManager manager) {
        this.manager = manager;
        this.config = this.manager.getPlugin().getWarpConfig();
    }

    @Override
    public void load() {

    }

    @Override
    public void unLoad() {
        this.config.save();
    }

    @Override
    public void reload() {
        this.unLoad();
        this.load();
    }
}
