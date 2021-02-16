import configuration.BuscaCepConfig;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;
import service.BuscaCEP;
import resources.Recursos;
import health.BuscaCepHealthCheck;

import javax.ws.rs.client.Client;

/**
 * Classe main da aplicação
 * @author Leonildo
 */
public class BuscaCepApp extends Application<BuscaCepConfig> {

    public static void main(String[] args) throws Exception {
        new BuscaCepApp().run(args);
    }

    @Override
    public String getName() {
        return "busca-cep";
    }

    @Override
    public void run(BuscaCepConfig buscaCepConfig, Environment environment) throws Exception {
        Client client = new JerseyClientBuilder(environment).using(buscaCepConfig.getHttpClient()).build(getName());
        BuscaCEP buscaCEP = new BuscaCEP(buscaCepConfig.getTarget(), client, environment.getObjectMapper());

        Recursos cepResource = new Recursos(buscaCEP);
        environment.jersey().register(cepResource);

        BuscaCepHealthCheck buscaCepHealthCheck = new BuscaCepHealthCheck(buscaCEP);
        environment.healthChecks().register("BuscaCepHealthCheck", buscaCepHealthCheck);
    }

}
