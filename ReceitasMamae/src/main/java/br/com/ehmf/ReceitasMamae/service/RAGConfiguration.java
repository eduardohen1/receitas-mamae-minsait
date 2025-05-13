package br.com.ehmf.ReceitasMamae.service;

import br.com.ehmf.ReceitasMamae.interfaces.Assistant;
import br.com.ehmf.ReceitasMamae.model.Receita;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RAGConfiguration {

    //importar a chave da API do OpenAI
    @Value("${OPENAI_API_KEY}")
    private String apiKey;

    private final ReceitaService receitaService;

    public RAGConfiguration(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    //criando contexto
    public Assistant configure() throws Exception{
        //buscar todas as receitas:
        List<Receita> receitas = receitaService.buscarTodasReceitas();
        List<Document> documents = receitas.stream()
                .map(receita -> new Document(
                        "Nome: " + receita.getNome() + "\n" +
                                "Ingredientes: " + receita.getIngredientes().toString() + "\n" +
                                "Modo de Preparo: " + receita.getModoPreparo().toString()
                ))
                .collect(Collectors.toList());

        // criar o assistente:
        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(OpenAiChatModel.withApiKey(apiKey))
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .contentRetriever(createContentRetriever(documents))
                .build();

        return assistant;

    }

    private ContentRetriever createContentRetriever(List<Document> documents) {
        // criar um EmbeddingStore para armazenar os documentos em memória
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        // converter os documentos em vetores numéricos
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);
        // criar um ContentRetriever para recuperar os documentos relevantes
        return EmbeddingStoreContentRetriever.from(embeddingStore);
    }


}
