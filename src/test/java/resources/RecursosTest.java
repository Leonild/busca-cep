package resources;

import model.Endereco;
import service.BuscaCEP;
import resources.Recursos;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import javax.ws.rs.core.Response;
import java.util.Optional;
import org.mockito.Mockito.*;

public class RecursosTest {

    private static final String CEP = "13566533";

    private static final String LOGRADOURO = "Alameda das Hortências";

    private static final String BAIRRO = "Cidade Jardim";

    private static final String CIDADE = "São Carlos";

    private static final String UF = "SP";


    private static final BuscaCEP SERVICE = new BuscaCEP(null, null, null) {
        @Override
        public Endereco getCEPValido(String numero) {
            if (CEP.equals(numero)) {
                return new Endereco(LOGRADOURO,BAIRRO,CIDADE,UF,CEP);
            }
            return null;
        }
    };

    @ClassRule
    public static final ResourceTestRule RESOURCES = ResourceTestRule.builder()
            .addResource(new Recursos(SERVICE))
            .build();

    @Test
    public void testCepValido() throws Exception {
        Response response = RESOURCES.client().target("/busca-cep/" + CEP).request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCepInexistente() throws Exception {
        Response response = RESOURCES.client().target("/busca-cep/00000000").request().get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCepInvalidoPequeno() throws Exception {
        Response response = RESOURCES.client().target("/busca-cep/123").request().get();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCepInvalidoGrande() throws Exception {
        Response response = RESOURCES.client().target("/busca-cep/12345678910").request().get();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCepInvalidoFormato() throws Exception {
        Response response = RESOURCES.client().target("/busca-cep/leonildo").request().get();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
