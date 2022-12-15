package com.sushobh.gptinstudio.repo

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import com.sushobh.gptinstudio.API_TOKEN
import com.theokanning.openai.OpenAiService
import com.theokanning.openai.completion.CompletionRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


object OpenAIRepo {

    private var openAI = OpenAiService(AppSettingsState.instance.tokenText)

    fun setService(token: String) {
        openAI = OpenAiService(token)
    }

    interface Callback<X> {
        fun onSuccess(data: X)
        fun onFailure()
    }

    init {

    }

    private fun onMain(code : () -> Unit){
        ApplicationManager.getApplication().invokeLater {
            code()
        }
    }

    fun writeTestCase(functionCode: String, project: Project, callback: Callback<String>) {

        ProgressManager.getInstance().runProcessWithProgressSynchronously(
            {
                try {
                    val data = getTestCaseForFunction(functionCode)
                    onMain { callback.onSuccess(data) }

                } catch (e: Exception) {
                    onMain { callback.onFailure() }
                }
            },
            "Loading",
            true,
            project
        )

    }

    fun analzeCodeAndReturnFindings(functionCode: String, project: Project, callback: Callback<String>) {

        ProgressManager.getInstance().runProcessWithProgressSynchronously(
            {
                try {
                    val data = complete(
                        "Analze the code. " +
                                "Suggest some improvements if any.Print everything in bullet points.Do not print any code." +
                                "\\n ${functionCode}"
                    )
                    onMain {  callback.onSuccess(data) }
                } catch (e: Exception) {
                   onMain {  callback.onFailure() }
                }
            },
            "Loading",
            true,
            project
        )
    }

    private fun getTestCaseForFunction(function: String): String {

        return complete(
            "Write a test case for this. " +
                    "Only print the code, no instructions. Check if anything can be mocked, make it exhaustive" +
                    "\\n ${function}"
        )
    }

    private fun complete(prompt: String): String {
        val completionRequest = CompletionRequest.builder()
            .prompt(prompt)
            .model("text-davinci-003")
            .n(1)
            .maxTokens(1200)
            .build()
        val response = openAI.createCompletion(completionRequest)

        return response.choices[0].text
    }

}