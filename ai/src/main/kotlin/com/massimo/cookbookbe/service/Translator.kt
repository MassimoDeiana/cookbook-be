//package com.massimo.cookbookbe.service
//
//import org.springframework.ai.chat.client.ChatClient
//import org.springframework.stereotype.Service
//
//@Service
//class Translator(
//    private val chatClient: ChatClient
//) {
//
//    fun translate(fieldToTranslate: String): String {
//        val content = chatClient
//            .prompt()
//            .user("Translate this text: $fieldToTranslate")
//            .call()
//            .content()
//        println("Translated text: $content")
//        return content
//    }
//}