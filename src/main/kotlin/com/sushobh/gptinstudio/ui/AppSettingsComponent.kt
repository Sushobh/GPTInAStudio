package com.sushobh.gptinstudio.ui

import javax.swing.JPanel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import com.intellij.ui.components.JBLabel
import javax.swing.JComponent

class AppSettingsComponent {
    val panel: JPanel
    private val tokenField = JBTextField()

    init {
        panel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Open AI Token: "), tokenField, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    val preferredFocusedComponent: JComponent
        get() = tokenField
    var tokenText: String
        get() = tokenField.text
        set(newText) {
            tokenField.text = newText
        }
}