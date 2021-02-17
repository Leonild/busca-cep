package configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Classe para mapear os atributos retornados pela viacep
 * @author Leonildo Azevedo
 */
public class EndpointCEP {

    private String cep;
    private String logradouro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    @JsonProperty
    public void setCep(String cep) {
        this.cep = cep;
    }

    @JsonProperty
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @JsonProperty
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @JsonProperty
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @JsonProperty
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @JsonProperty
    public void setUf(String uf) {
        this.uf = uf;
    }

    @JsonProperty
    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    @JsonProperty
    public void setGia(String gia) {
        this.gia = gia;
    }

    @JsonProperty
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    @JsonProperty
    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }

    @JsonProperty
    private String complemento;
    private String bairro;

    @JsonProperty
    public String getCep() {
        return cep;
    }

    @JsonProperty
    public String getLogradouro() {
        return logradouro;
    }

    @JsonProperty
    public String getComplemento() {
        return complemento;
    }

    @JsonProperty
    public String getBairro() {
        return bairro;
    }

    @JsonProperty
    public String getLocalidade() {
        return localidade;
    }

    @JsonProperty
    public String getUf() {
        return uf;
    }

    @JsonProperty
    public String getIbge() {
        return ibge;
    }

    @JsonProperty
    public String getGia() {
        return gia;
    }

    @JsonProperty
    public String getDdd() {
        return ddd;
    }

    @JsonProperty
    public String getSiafi() {
        return siafi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndpointCEP endpointCEP = (EndpointCEP) o;
        return Objects.equals(cep, endpointCEP.cep) &&
                Objects.equals(logradouro, endpointCEP.logradouro) &&
                Objects.equals(bairro, endpointCEP.bairro) &&
                Objects.equals(localidade, endpointCEP.localidade) &&
                Objects.equals(uf, endpointCEP.uf) &&
                Objects.equals(ibge, endpointCEP.ibge) &&
                Objects.equals(gia, endpointCEP.gia) &&
                Objects.equals(siafi, endpointCEP.siafi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, logradouro, bairro, localidade, uf, ibge, gia, siafi);
    }

}
