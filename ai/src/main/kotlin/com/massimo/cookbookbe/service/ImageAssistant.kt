package com.massimo.cookbookbe.service

import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.model.Media
import org.springframework.ai.ollama.OllamaChatModel
import org.springframework.ai.ollama.api.OllamaModel
import org.springframework.ai.ollama.api.OllamaOptions
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import org.springframework.util.MimeTypeUtils

@Service
class ImageAssistant(
    private val ollamaChatModel: OllamaChatModel
) {

    fun describeImage(imagePath: String) : String {
        val imageResource = ClassPathResource("/carbonara.png");

        val userMessage = UserMessage("You are an AI model trained to describe the contents of images accurately and in detail. Your task is to analyze an image containing an ingredient list and provide a clear and structured description of the items in the list. The description should include:\n" +
                "\n" +
                "The name of each ingredient as it appears in the image.\n" +
                "Any additional information related to the ingredient, such as quantities, measurements, or special notes (if provided in the image).\n" +
                "Formatting: Structure the response in a way that makes it easy to read, such as a bullet list or numbered format.\n" +
                "Important: If any text in the image is unclear or partially visible, note it explicitly and provide your best interpretation without making assumptions. Your goal is to provide a reliable and accurate summary of the image.",
            Media(MimeTypeUtils.IMAGE_PNG, imageResource))

        return try {
            val response = ollamaChatModel
                .call(Prompt(
                    userMessage,
                    OllamaOptions
                        .builder()
                        .withModel(OllamaModel.LLAVA)
                        .build()
                ))
            response.result.output.content
        } catch (e: Exception) {
            "Error: ${e.message}"
        }



    }

}