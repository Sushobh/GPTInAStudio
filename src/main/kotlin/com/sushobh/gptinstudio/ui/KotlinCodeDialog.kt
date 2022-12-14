package com.sushobh.gptinstudio.ui

import com.intellij.lang.Language
import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.idea.KotlinLanguage
import java.awt.Dimension

class KotlinCodeDialog(codeToDisplay : String, title : String , project : Project) :
    CodeDisplayDialog(
        CodeDisplayConfiguration(displayDimenstion = Dimension(500,500),
            risizable = false,title = title),
        codeToDisplay,project,KotlinLanguage.INSTANCE){
}