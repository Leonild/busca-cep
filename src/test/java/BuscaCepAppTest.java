import configuration.BuscaCepConfig;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class BuscaCepAppTest {

    @ClassRule
    public static final DropwizardAppRule<BuscaCepConfig> RULE =
            new DropwizardAppRule(BuscaCepApp.class, "etc/config.yml");

    @Test
    public void getCEPValido() throws Exception {
        Client client = new JerseyClientBuilder(RULE.getEnvironment())
                .using(RULE.getConfiguration().getHttpClient())
                .build("client");

        Response response = client.target(String.format("http://localhost:%d/busca-cep/13566533", RULE.getLocalPort()))
                .request()
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
