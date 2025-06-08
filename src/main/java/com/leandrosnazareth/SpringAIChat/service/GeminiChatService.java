package com.leandrosnazareth.SpringAIChat.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiChatService {

    private final ChatClient chatClient;

    // Injetamos o ChatClient.Builder para construir um cliente configurado
    public GeminiChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    /**
     * Envia uma mensagem para a IA e retorna a resposta.
     * 
     * @param message A mensagem do usuário.
     * @return A resposta gerada pelo Gemini.
     */
    public String getChatResponse(String message) {
        try {
            return chatClient.prompt()
                    .user(message) // Define o prompt do usuário
                    .call() // Chama a API
                    .content(); // Extrai o conteúdo da resposta
        } catch (Exception e) {
            // Log do erro para depuração
            e.printStackTrace();
            return "Desculpe, ocorreu um erro ao processar sua solicitação.";
        }
    }
}