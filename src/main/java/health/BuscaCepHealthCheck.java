package health;

import model.Endereco;
import service.BuscaCEP;
import com.codahale.metrics.health.HealthCheck;

/**
 * Classe para gerenciar a saúde da aplicação
 * @author Leonildo Azevedo
 */
public class BuscaCepHealthCheck extends HealthCheck {

    private final BuscaCEP buscaCEP;

    public BuscaCepHealthCheck(BuscaCEP buscaCEP) {
        this.buscaCEP = buscaCEP;
    }

    @Override
    protected Result check() throws Exception {
        Endereco endereco = buscaCEP.getCEPValido("13566533");
        if (endereco == null) {
            return Result.unhealthy("Problema com a aplicação: não foi possível localizar um CEP válido!");
        }
        return Result.healthy();
    }

}
