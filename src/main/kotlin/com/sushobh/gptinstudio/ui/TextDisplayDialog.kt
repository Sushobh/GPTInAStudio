package com.sushobh.gptinstudio.ui

import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.EditorTextField
import com.intellij.ui.LanguageTextField
import javax.swing.JComponent

class TextDisplayDialog(val codeDisplayConfiguration: CodeDisplayConfiguration,val codeToDisplay : String,
                        val project : Project) :
    DialogWrapper(true) {

    init {
        title = codeDisplayConfiguration.title
        init()
    }


    override fun createCenterPanel(): JComponent {
        val editorTextField = object : EditorTextField(codeToDisplay) {
            override fun createEditor(): EditorEx {
                return super.createEditor().also {
                    it.setHorizontalScrollbarVisible(true)
                    it.setVerticalScrollbarVisible(true)
                }
            }
        }
        editorTextField.setOneLineMode(false)
        editorTextField.preferredSize = codeDisplayConfiguration.displayDimenstion
        isResizable = codeDisplayConfiguration.risizable
        return editorTextField
    }

}