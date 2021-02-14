package service;

import configuration.EndpointCEP;
import model.Endereco;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.containsOnly;

/**
 * Classe que implementa o serviço, basicamente consulta a base da viacep e retorna um objeto correspondente a esta aplicação
 * @author Leonildo Azevedo
 */
public class BuscaCEP {

    //estruturando logs para o serviço
    private static final Logger logger = LoggerFactory.getLogger(BuscaCEP.class);

    private final String target;
    private final Client client;
    private final ObjectMapper objectMapper;

    public BuscaCEP(String target, Client client, ObjectMapper objectMapper) {
        this.target = target;
        this.client = client;
        this.objectMapper = objectMapper;
    }

    /**
     * Metodo para buscar um CEP, caso invalido substitui os valores a direita por zero
     * @param numero numero referente ao CEP
     * @return objeto do tipo endereço para manipulação
     */
    public Endereco getCEPValido(String numero) {
        StringBuilder cep = new StringBuilder(numero);
        String response = buscar(cep.toString());
        int cepIndice = cep.length();
        //substitui os valores a direita por zero enquanto a resposta for nula ou ainda houver valores para substituir
        while (response == null && !containsOnly(cep, '0')) {
            logger.debug("CEP não encontrado [{}]", cep);
            cep.setCharAt(--cepIndice, '0');
            response = buscar(cep.toString());
        }

        if (response == null) {
            return null;
        }

        try{
            return converter(objectMapper.readValue(response, EndpointCEP.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Busca endereço na base de dados da viacep
     * @param numero numero correspondente ao CEP
     * @return um string correspondente ao json da consulta na API viacep
     */
    protected String buscar(String numero) {
        logger.debug("Buscando cep [{}] em viacep", numero);
        Response response = client.target(target)
                .path(numero + "/json/")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
            return null;
        }

        String body = response.readEntity(String.class);

        return body;
    }

    /**
     * Converte o endpoint da viacep para o formato desta API
     * @param cepEndPoint objeto mapeado do retorno da API viacep
     * @return objeto Endereco correspondente ao endpointe desta API
     */
    protected Endereco converter(EndpointCEP cepEndPoint) {
        return new Endereco(cepEndPoint.getLogradouro(),
                cepEndPoint.getBairro(),
                cepEndPoint.getLocalidade(),
                cepEndPoint.getUf(),
                cepEndPoint.getCep());
    }
}
