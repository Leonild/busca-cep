import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

import model.Endereco;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class Recursos {

    private final String logradouro;
    private final String complemento;
    private final String complemento2;
    private final String bairro;
    private final String cidade;
    private final String uf;
    private final String cep;

    public Recursos(String logradouro, String complemento, String complemento2, String bairro, String cidade, String uf, String cep) {
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.complemento2 = complemento2;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    @GET
    @Timed
    public Endereco getEndereco(@QueryParam("cep") Optional<String> cep){
        return new Endereco(firstName.orElse(defaultFirstName), lastName.orElse(defaultLastName));
    }
}