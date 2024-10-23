package mx.edu.cetys.iso.kungfupanda;
import mx.edu.cetys.iso.kungfupanda.service.OpenAIService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import mx.edu.cetys.iso.kungfupanda.repository.Quote.QuoteRepository;
import mx.edu.cetys.iso.kungfupanda.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class KungFuPandaApplication implements CommandLineRunner {

    @Autowired
    private OpenAIService openAIService;

    @Autowired
    private QuoteRepository quoteRepository;

    public static void main(String[] args) {
        SpringApplication.run(KungFuPandaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String userPrompt;

        while (true) {
            System.out.println("Introduce el tema sobre el cual quieres una frase de Kung Fu Panda (o escribe 'exit' para salir):");
            userPrompt = scanner.nextLine();

            if ("exit".equalsIgnoreCase(userPrompt)) {
                System.out.println("Adiocito...");
                break;
            }

            String prompt = "Dame una frase de las peliculas de Kung Fu Panda sobre " + userPrompt;
            String response = openAIService.getResponse(prompt);

            System.out.println("Frase de Kung Fu Panda: " + response);

            Quote quote = new Quote();
            quote.setPrompt(prompt);
            quote.setResponse(response);

            quoteRepository.save(quote);
            System.out.println("Frase guardada en la base de datos.");
        }
    }
}
