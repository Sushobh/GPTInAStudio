package com.sushobh.gptinstudio.ui

import com.intellij.lang.Language
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.LanguageTextField
import org.jetbrains.kotlin.idea.KotlinLanguage
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.datatransfer.StringSelection
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel


abstract class CodeDisplayDialog(val codeDisplayConfiguration: CodeDisplayConfiguration,val codeToDisplay : String,val project : Project,val langType : Language) : DialogWrapper(true) {
    init {
        title = codeDisplayConfiguration.title
        setOKButtonText("Copy to Clipboard")
        init()
    }

    override fun doOKAction() {
        CopyPasteManager.getInstance().setContents(StringSelection(codeToDisplay))
        super.doOKAction()
    }

    override fun createCenterPanel(): JComponent {
        val languageField = object : LanguageTextField(langType,project,codeToDisplay) {
            override fun createEditor(): EditorEx {
                return super.createEditor().also {
                    it.setHorizontalScrollbarVisible(true)
                    it.setVerticalScrollbarVisible(true)
                }
            }
        }
        languageField.setOneLineMode(false)
        languageField.preferredSize = codeDisplayConfiguration.displayDimenstion
        isResizable = codeDisplayConfiguration.risizable
        return languageField
    }
}