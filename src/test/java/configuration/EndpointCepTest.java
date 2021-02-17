package configuration;

import configuration.EndpointCEP;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class EndpointCepTest {

    private static final String CEP = "13566-533";

    private static final String LOGRADOURO = "Alameda das Hortências";

    private static final String BAIRRO = "Cidade Jardim";

    private static final String CIDADE = "São Carlos";

    private static final String UF = "SP";

    private static final String IBGE = "3548906";

    private static final String GIA = "6373";

    private static final String DDD = "16";

    private static final String SIAFI = "7079";

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void deserializesFromJSON() throws Exception {
        EndpointCEP endpointCEP = new EndpointCEP();
        endpointCEP.setCep(CEP);
        endpointCEP.setLogradouro(LOGRADOURO);
        endpointCEP.setBairro(BAIRRO);
        endpointCEP.setLocalidade(CIDADE);
        endpointCEP.setUf(UF);
        endpointCEP.setIbge(IBGE);
        endpointCEP.setGia(GIA);
        endpointCEP.setDdd(DDD);
        endpointCEP.setSiafi(SIAFI);

        EndpointCEP desserializado = MAPPER.readValue(fixture("fixtures/cep-endpoint.json"), EndpointCEP.class);
        assertEquals(endpointCEP, desserializado);
    }
}
