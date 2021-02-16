import configuration.BuscaCepConfig;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;
import service.BuscaCEP;
import resources.Recursos;
import health.BuscaCepHealthCheck;

import javax.ws.rs.client.Client;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

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
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin,Authorization");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        Client client = new JerseyClientBuilder(environment).using(buscaCepConfig.getHttpClient()).build(getName());
        BuscaCEP buscaCEP = new BuscaCEP(buscaCepConfig.getTarget(), client, environment.getObjectMapper());

        Recursos cepResource = new Recursos(buscaCEP);
        environment.jersey().register(cepResource);

        BuscaCepHealthCheck buscaCepHealthCheck = new BuscaCepHealthCheck(buscaCEP);
        environment.healthChecks().register("BuscaCepHealthCheck", buscaCepHealthCheck);
    }

}
