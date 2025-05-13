package br.com.ehmf.ReceitasMamae.controller;

import br.com.ehmf.ReceitasMamae.dto.MyQuestion;
import br.com.ehmf.ReceitasMamae.interfaces.Assistant;
import br.com.ehmf.ReceitasMamae.service.RAGConfiguration;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ia")
public class OpenAIController {

    @Value("${OPENAI_API_KEY}")
    private String apiKey;
    private ChatLanguageModel chatLanguageModel;
    private RAGConfiguration ragConfiguration;
    private Assistant assistant;

    public OpenAIController(ChatLanguageModel chatLanguageModel,
                             RAGConfiguration ragConfiguration) {
        this.chatLanguageModel = chatLanguageModel;
        this.ragConfiguration = ragConfiguration;
    }

    @PostMapping("/receita")
    public String chatComOpenAI(@RequestBody MyQuestion question) {
        try{
            if(assistant == null)
                assistant = ragConfiguration.configure();
            return assistant.answer(question.question());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao processar a pergunta: " + ex.getMessage());
        }
    }

    @PostMapping("/imagem")
    public String generateImage(@RequestBody MyQuestion question) {
        try {
            ImageModel imageModel = OpenAiImageModel.withApiKey(apiKey);
            return imageModel.generate(question.question())
                    .content().url().toURL().toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar imagem: " + e.getMessage());
        }
    }

}
