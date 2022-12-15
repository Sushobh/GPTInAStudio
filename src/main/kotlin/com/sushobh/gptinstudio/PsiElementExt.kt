package com.sushobh.gptinstudio

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtFunction

fun PsiElement?.isAKtFunction() : Boolean{
    return this != null && this is KtFunction
}