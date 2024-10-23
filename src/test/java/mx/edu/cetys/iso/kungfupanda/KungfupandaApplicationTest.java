package mx.edu.cetys.iso.kungfupanda;
import org.junit.jupiter.api.Test;
import mx.edu.cetys.iso.kungfupanda.model.Quote;
import mx.edu.cetys.iso.kungfupanda.repository.Quote.QuoteRepository;
import mx.edu.cetys.iso.kungfupanda.service.OpenAIService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import java.io.ByteArrayInputStream;
class KungFuPandaApplicationTest {

	@Mock
	private OpenAIService openAIService;

	@Mock
	private QuoteRepository quoteRepository;

	@InjectMocks
	private KungFuPandaApplication kungFuPandaApplication;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testRunSaveQuote() throws Exception {
		String simulatedUserInput = "sabiduría\nexit\n";
		System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

		String userPrompt = "sabiduría";
		String prompt = "Dame una frase de las peliculas de Kung Fu Panda sobre " + userPrompt;
		String mockResponse = "La sabiduría no se mide por el conocimiento, sino por la práctica.";

		when(openAIService.getResponse(anyString())).thenReturn(mockResponse);

		Quote mockQuote = new Quote();
		mockQuote.setPrompt(prompt);
		mockQuote.setResponse(mockResponse);

		when(quoteRepository.save(any(Quote.class))).thenReturn(mockQuote);

		kungFuPandaApplication.run();

		verify(openAIService).getResponse(prompt);
		verify(quoteRepository).save(any(Quote.class));
	}
}
