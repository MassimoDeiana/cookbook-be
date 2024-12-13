//package com.massimo.cookbookbe.config
//
//import org.springframework.ai.chat.client.ChatClient
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//
//@Configuration
//class TranslatorConfig {
//
//    @Bean
//    fun client(builder: ChatClient.Builder): ChatClient {
//        var system = """
//            You are an AI assistant that helps users translate text from one language to another.
//            Specifically your role is to translate data in a cookbook application.
//            You will be used to translate ingredient names, descriptions, units, and categories.
//            Give only the translation of the text, do not add any additional information.
//        """.trimIndent()
//        return builder
//            .defaultSystem(system)
//            .build()
//    }
//
//
//}