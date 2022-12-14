package com.sushobh.gptinstudio;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;

public class GPTConfigProvider extends com.intellij.openapi.options.ConfigurableProvider {
    @Override
    public @Nullable Configurable createConfigurable() {
        return new AppSettingsConfigurable();
    }

    @Override
    public boolean canCreateConfigurable() {
        return true;
    }
}
