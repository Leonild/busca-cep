//package model;

import model.Endereco;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class EnderecoTest {

    private static final String CEP = "13566-533";

    private static final String LOGRADOURO = "Alameda das Hortências";

    private static final String BAIRRO = "Cidade Jardim";

    private static final String CIDADE = "São Carlos";

    private static final String UF = "SP";

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        Endereco endereco = new Endereco(LOGRADOURO, BAIRRO, CIDADE, UF, CEP);

        String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/endereco.json"), Endereco.class));
        String actual = MAPPER.writeValueAsString(endereco);

        assertEquals(expected, actual);
    }
}
