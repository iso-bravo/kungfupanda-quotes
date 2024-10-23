package mx.edu.cetys.iso.kungfupanda.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuoteTest {

    @Test
    public void testIdGetterAndSetter() {
        Quote quote = new Quote();

        Long id = 1L;
        quote.setId(id);

        assertEquals(id, quote.getId(), "El ID debe coincidir con el valor establecido");
    }

    @Test
    public void testPromptGetterAndSetter() {
        Quote quote = new Quote();

        String prompt = "Dame una frase de las peliculas de Kung Fu Panda sobre valentía";
        quote.setPrompt(prompt);

        assertEquals(prompt, quote.getPrompt(), "El prompt debe coincidir con el valor establecido");
    }

    @Test
    public void testResponseGetterAndSetter() {
        Quote quote = new Quote();

        String response = "La valentía no es la ausencia de miedo, sino la capacidad de actuar a pesar de él.";
        quote.setResponse(response);

        assertEquals(response, quote.getResponse(), "La respuesta debe coincidir con el valor establecido");
    }

    @Test
    public void testConstructor() {
        Quote quote = new Quote();

        assertNull(quote.getId(), "El ID inicial debe ser nulo");
        assertNull(quote.getPrompt(), "El prompt inicial debe ser nulo");
        assertNull(quote.getResponse(), "La respuesta inicial debe ser nula");
    }
}
