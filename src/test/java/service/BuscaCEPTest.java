package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.EndpointCEP;
import model.Endereco;
import service.BuscaCEP;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class BuscaCEPTest {

    private static final String CEP = "13566-533";

    private static final String LOGRADOURO = "Alameda das Hortências";

    private static final String BAIRRO = "Cidade Jardim";

    private static final String CIDADE = "São Carlos";

    private static final String UF = "SP";

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/endereco.json"), Endereco.class));

        final Endereco endereco = new Endereco(LOGRADOURO, BAIRRO,CIDADE,UF,CEP);

        assertThat(MAPPER.writeValueAsString(endereco)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Endereco endereco = new Endereco(LOGRADOURO, BAIRRO,CIDADE,UF,CEP);
        assertThat(MAPPER.readValue(fixture("fixtures/endereco.json"), Endereco.class))
                .isEqualTo(endereco);
    }

    @Test
    public void testConverter() throws Exception {
        EndpointCEP endpointCEP = new EndpointCEP();
        endpointCEP.setCep(CEP);
        endpointCEP.setLogradouro(LOGRADOURO);
        endpointCEP.setLocalidade(CIDADE);
        endpointCEP.setBairro(BAIRRO);
        endpointCEP.setUf(UF);

        Endereco endereco = new BuscaCEP(null, null, null).converter(endpointCEP);
        assertEquals(CEP, endereco.getCep());
        assertEquals(LOGRADOURO, endereco.getLogradouro());
        assertEquals(BAIRRO, endereco.getBairro());
        assertEquals(CIDADE, endereco.getCidade());
        assertEquals(UF, endereco.getUf());
    }
}
