package com.sushobh.gptinstudio

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.psi.util.elementType
import com.sushobh.gptinstudio.repo.OpenAIRepo
import com.sushobh.gptinstudio.ui.CodeDisplayConfiguration
import com.sushobh.gptinstudio.ui.ErrorNotifier
import com.sushobh.gptinstudio.ui.TextDisplayDialog
import org.jetbrains.kotlin.psi.KtNamedFunction
import java.awt.Dimension

class GPTAnalyseCodeAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val psiElement = e.getData(LangDataKeys.PSI_ELEMENT)
        val project = e.project!!
        if(!psiElement.isAKtFunctionDeclaration()){
            ErrorNotifier.incompatibleApiCallError(project)
            return
        }
        if(psiElement.elementType?.debugName.equals("FUN")){
            if(psiElement is KtNamedFunction){
                e.project?.let {
                    OpenAIRepo.analzeCodeAndReturnFindings(psiElement.text,it, object : OpenAIRepo.Callback<String>{
                        override fun onSuccess(data: String) {
                            val displayConfig = CodeDisplayConfiguration(displayDimenstion = Dimension(500,500),
                                risizable = false,title = "Code analysis for ${psiElement.name}")
                            val dialog = TextDisplayDialog(displayConfig,data,it)
                            dialog.show()
                        }
                        override fun onFailure() {
                               ErrorNotifier.apiError(it)
                        }
                    })
                }
            }
        }
    }
}