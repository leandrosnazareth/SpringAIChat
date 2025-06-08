package com.leandrosnazareth.SpringAIChat.view;

import com.leandrosnazareth.SpringAIChat.service.GeminiChatService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("") // Mapeia esta view para a raiz da aplicação
public class ChatView extends VerticalLayout {

    private final GeminiChatService chatService;

    public ChatView(GeminiChatService chatService) {
        this.chatService = chatService;

        // Configurações do layout principal
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // Título da aplicação
        H1 title = new H1("🤖 Chat com Gemini AI");

        // Layout para as mensagens do chat
        VerticalLayout chatMessages = new VerticalLayout();
        chatMessages.setWidth("60%");
        chatMessages.getStyle().set("border", "1px solid #ccc");
        chatMessages.getStyle().set("border-radius", "8px");
        chatMessages.setPadding(true);
        chatMessages.setHeight("400px");
        chatMessages.getStyle().set("overflow-y", "auto"); // Adiciona scroll

        // Campo de texto para a entrada do usuário
        TextField userInput = new TextField();
        userInput.setPlaceholder("Digite sua mensagem aqui...");
        userInput.setWidth("50%");

        // Botão de envio
        Button sendButton = new Button("Enviar");
        sendButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Layout para o campo de entrada e o botão
        HorizontalLayout inputLayout = new HorizontalLayout(userInput, sendButton);
        inputLayout.setWidth("60%");
        inputLayout.setAlignItems(Alignment.BASELINE);

        // Lógica do clique do botão
        sendButton.addClickListener(click -> {
            String userMessage = userInput.getValue();
            if (!userMessage.isBlank()) {
                // Adiciona a mensagem do usuário à UI
                chatMessages.add(new Paragraph("Você: " + userMessage));
                userInput.clear();

                // Chama o serviço de IA e obtém a resposta
                String aiResponse = chatService.getChatResponse(userMessage);

                // Adiciona a resposta da IA à UI
                chatMessages.add(new Paragraph("Gemini: " + aiResponse));
            }
        });

        // Adiciona o atalho "Enter" ao campo de texto
        userInput.addKeyDownListener(Key.ENTER, event -> sendButton.click());

        // Adiciona todos os componentes ao layout principal
        add(title, chatMessages, inputLayout);
    }
}