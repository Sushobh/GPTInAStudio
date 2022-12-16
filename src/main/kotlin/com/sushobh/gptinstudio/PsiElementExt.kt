package com.sushobh.gptinstudio

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.*

fun PsiElement?.isAKtFunctionDeclaration() : Boolean{
    val condition =  this != null && this is KtNamedFunction
    if(condition){
        return condition
    }
    return false
}