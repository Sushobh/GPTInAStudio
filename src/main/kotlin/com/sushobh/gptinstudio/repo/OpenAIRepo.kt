package com.sushobh.gptinstudio.repo

import com.sushobh.gptinstudio.API_TOKEN
import com.theokanning.openai.OpenAiService
import com.theokanning.openai.completion.CompletionRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


object OpenAIRepo {

    private var openAI = OpenAiService(AppSettingsState.instance.tokenText)

    fun setService(token : String){
        openAI = OpenAiService(token)
    }

    init {

    }

    fun writeTestCase(functionCode : String) : String{
        return getTestCaseForFunction(functionCode)
    }

    fun analzeCodeAndReturnFindings(functionCode: String) : String {
        return complete("Analze the code. " +
                "Suggest some improvements if any.Print everything in bullet points.Do not print any code." +
                "\\n ${functionCode}")
    }

    private fun getTestCaseForFunction(function : String) : String {

        return complete("Write a test case for this. " +
                "Only print the code, no instructions. " +
                "\\n ${function}")
    }

    private fun complete(prompt : String) : String {
        val completionRequest = CompletionRequest.builder()
            .prompt(prompt)
            .model("text-davinci-003")
            .n(1)
            .maxTokens(1200)
            .build()
        val response =  openAI.createCompletion(completionRequest)

        return response.choices[0].text
    }

}