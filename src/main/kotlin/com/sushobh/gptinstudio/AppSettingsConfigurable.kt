package com.sushobh.gptinstudio

import com.intellij.openapi.options.Configurable
import com.sushobh.gptinstudio.repo.AppSettingsState
import com.sushobh.gptinstudio.repo.OpenAIRepo
import com.sushobh.gptinstudio.ui.AppSettingsComponent
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

/**
 * Provides controller functionality for application settings.
 */
class AppSettingsConfigurable  // A default constructor with no arguments is required because this implementation
// is registered as an applicationConfigurable EP
    : Configurable {
    private var mySettingsComponent: AppSettingsComponent? = null
    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        return "GPTInStudio Configration"
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return mySettingsComponent!!.preferredFocusedComponent
    }

    override fun createComponent(): JComponent {
        mySettingsComponent = AppSettingsComponent()
        return mySettingsComponent!!.panel
    }

    override fun isModified(): Boolean {
        val settings = AppSettingsState.instance;
        val modified = !mySettingsComponent?.tokenText.equals(settings.tokenText);
           return modified;
    }

    override fun apply() {
        val settings = AppSettingsState.instance;
        settings.tokenText = mySettingsComponent?.tokenText.orEmpty()
        OpenAIRepo.setService(mySettingsComponent?.tokenText.orEmpty())
    }

    override fun reset() {
        val settings = AppSettingsState.instance;
        mySettingsComponent?.tokenText = settings.tokenText;
    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }
}