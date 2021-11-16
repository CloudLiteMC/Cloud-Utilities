package com.buchard36.core.modules.warp;

import com.buchard36.core.config.warp.WarpConfig;
import com.buchard36.core.modules.Module;
import com.buchard36.core.modules.ModuleManager;
import com.buchard36.core.modules.warp.commands.CreateWarpCommand;
import com.burchard36.Api;
import com.burchard36.ApiLib;

public class WarpModule implements Module {

    public final ModuleManager manager;
    public WarpConfig config;
    public CreateWarpCommand createWarpCommand;

    public WarpModule(final ModuleManager manager) {
        this.manager = manager;
    }

    @Override
    public void load() {
        this.config = this.manager.getPlugin().getWarpConfig();

        this.createWarpCommand = new CreateWarpCommand(this);

        this.manager.getPlugin().getApiLib().registerCommand(this.createWarpCommand.getCommand());
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
