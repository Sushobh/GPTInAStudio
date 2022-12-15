package com.sushobh.gptinstudio

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.psi.util.elementType
import com.sushobh.gptinstudio.repo.OpenAIRepo
import com.sushobh.gptinstudio.ui.CodeDisplayDialog
import com.sushobh.gptinstudio.ui.ErrorNotifier
import com.sushobh.gptinstudio.ui.KotlinCodeDialog
import org.jetbrains.kotlin.psi.KtNamedFunction

class GPTWriteTestAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val psiElement = e.getData(LangDataKeys.PSI_ELEMENT)
        if(!psiElement.isAKtFunction()){
            ErrorNotifier.incompatibleApiCallError(e.project)
            return
        }
        if(psiElement.elementType?.debugName.equals("FUN")){
            if(psiElement is KtNamedFunction){
                e.project?.let {
                    OpenAIRepo.writeTestCase(psiElement.text,it, object : OpenAIRepo.Callback<String>{
                        override fun onSuccess(data: String) {
                            val dialog = KotlinCodeDialog(
                                data,
                                "Generated test for ${psiElement.name.orEmpty()}",
                                it)
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