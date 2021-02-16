package resources;

import com.codahale.metrics.annotation.Timed;
import org.hibernate.validator.constraints.Length;
import model.Endereco;
import service.BuscaCEP;

import javax.validation.constraints.Digits;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/busca-cep")
@Produces("application/json; charset=UTF-8")
public class Recursos {

    private final BuscaCEP buscaCEP;

    /**
     * Instanciando serviço
     * @param buscaCEP serviço instanciado na aplicação
     */
    public Recursos(BuscaCEP buscaCEP) {
        this.buscaCEP = buscaCEP;
    }

    /**
     * Faz validacao e busca do CEP
     * @param numero numero correspondente ao CEP
     * @return endpoint da aplicação
     */
    @GET
    @Path("/{numero}")
    @Timed
    public Response getCEPValido(@Length(min = 8, max = 8, message = "CEP inválida")
                                    @Digits(integer = 8, fraction = 0, message = "CEP inválido")
                                    @PathParam("numero") String numero) {
        Endereco endereco = buscaCEP.getCEPValido(numero);
        if (endereco == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(endereco).build();
    }
}