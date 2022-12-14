package com.sushobh.gptinstudio

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.psi.util.elementType
import com.sushobh.gptinstudio.repo.OpenAIRepo
import com.sushobh.gptinstudio.ui.CodeDisplayConfiguration
import com.sushobh.gptinstudio.ui.KotlinCodeDialog
import com.sushobh.gptinstudio.ui.TextDisplayDialog
import org.jetbrains.kotlin.psi.KtNamedFunction
import java.awt.Dimension

class GPTAnalyseCodeAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val psiElement = e.getData(LangDataKeys.PSI_ELEMENT)
        if(psiElement.elementType?.debugName.equals("FUN")){
            if(psiElement is KtNamedFunction){
                e.project?.let {
                    val analysis = OpenAIRepo.analzeCodeAndReturnFindings(psiElement.text)
                    val displayConfig = CodeDisplayConfiguration(displayDimenstion = Dimension(500,500),
                        risizable = false,title = "Code analysis for ${psiElement.name}")
                    val dialog = TextDisplayDialog(displayConfig,analysis,it)
                    dialog.show()
                }
            }
        }
    }
}