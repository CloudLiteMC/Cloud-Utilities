package com.buchard36.core.modules.tpa;

import com.buchard36.core.Core;
import com.buchard36.core.modules.Module;
import com.buchard36.core.modules.ModuleManager;

public class TpaModule implements Module {

    private final ModuleManager manager;

    public TpaModule(final ModuleManager manager) {
        this.manager = manager;
    }

    @Override
    public void load() {

    }

    @Override
    public void unLoad() {

    }

    @Override
    public void reload() {

    }

    public final Core getPlugin() {
        return this.manager.getPlugin();
    }
}
