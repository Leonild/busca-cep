package model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe destinada a armazenamento das informações do endereço
 * @author Leonildo Azevedo
 */
public class Endereco {

    private int contador;
    private String logradouro;
    private String complemento;
    private String complemento2;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    /**
     * Construtor para incializar o contador de zeros a direita
     */
    public Endereco(){
        this.contador = 0;
    }

    /**
     * Atribui o logradouro do endereço
     * @param logradouro logradouro correspondente ao CEP informado
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * Atribui o nome do bairro do endereço
     * @param bairro bairro correspondente ao CEP informado
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Atribui o nome da cidade do endereço
     * @param cidade nome da cidade correspondente ao CEP informado
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Atribui o nome do estado ao endereço
     * @param uf sigla do estado correspondente ao CEP
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * Atribui o CEP correspondente ao endereço
     * @param cep CEP propriamente dito
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * toda vez que um número a direita é substituido por zero, incrementa o contador para controle
     */
    public void incrementaContador(){
        this.contador++;
    }

    /**
     *
     * @return o logradouro correspondente ao CEP
     */
    @JsonProperty
    public String getLogradouro() {
        return logradouro;
    }

    /**
     *
     * @return o bairro correspondete ao CEP
     */
    @JsonProperty
    public String getBairro() {
        return bairro;
    }

    /**
     *
     * @return nome da cidade correspodente ao CEP
     */
    @JsonProperty
    public String getCidade() {
        return cidade;
    }

    /**
     *
     * @return a sigla do Estado correspondente ao CEP
     */
    @JsonProperty
    public String getUf() {
        return uf;
    }

    /**
     *
     * @return o CEP correspondente ao endereço
     */
    @JsonProperty
    public String getCep() {
        return cep;
    }

    /**
     *
     * @return quantas vezes já substituiu o valor a direita por zero
     */
    public int getContador(){
        return contador;
    }

}
