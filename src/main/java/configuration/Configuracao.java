package configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotEmpty;

/**
 * Classe de configuração, especificando os parametros da aplicação
 */
public class Configuracao extends Configuration {

    @NotEmpty
    private String logradouro;

    @NotEmpty
    private String complemento;

    @NotEmpty
    private String complemento2;

    @NotEmpty
    private String bairro;

    @NotEmpty
    private String cidade;

    @NotEmpty
    private String uf;

    @NotEmpty
    private String cep;

    @JsonProperty
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @JsonProperty
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @JsonProperty
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @JsonProperty
    public void setUf(String uf) {
        this.uf = uf;
    }

    @JsonProperty
    public void setCep(String cep) {
        this.cep = cep;
    }

    @JsonProperty
    public String getLogradouro() {
        return logradouro;
    }

    @JsonProperty
    public String getBairro() {
        return bairro;
    }

    @JsonProperty
    public String getCidade() {
        return cidade;
    }

    @JsonProperty
    public String getUf() {
        return uf;
    }

    @JsonProperty
    public String getCep() {
        return cep;
    }
}
