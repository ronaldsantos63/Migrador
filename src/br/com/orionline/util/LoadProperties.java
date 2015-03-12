/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.orionline.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author ronald
 */
public class LoadProperties {
    
    private static Properties defaultProps = null;
    Logger logger = Logger.getLogger(LoadProperties.class);

    // Local do arquivo mensagens.properties
    String localProperties = "properties/db.properties";

    /**
     * Construtor da Classe LoadProperties
     */
    public LoadProperties() {
        //ConectaBanco cx = new ConectaBanco();
        try {
            defaultProps = new Properties();

            // Aqui utilizamos um ClassLoader para carregar nosso arquivo
            // de propriedades.
            //InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(localProperties);
            FileInputStream stream = new FileInputStream(localProperties);

            // Lê os dados do arquivo de propriedade
            defaultProps.load(stream);
        } catch (IOException ex) {
            logger.error("Erro ao carregar arquivo de configuração do banco de dados", ex);
        }
    }

    /**
     * Recupera o valor da propriedade informada.
     *
     * @param Propriedade Caminho informado para recuperar seu Endereço.
     * @return Endereço do caminho.
     */
    public String getPropriedade(String Propriedade) {
        return defaultProps.getProperty(Propriedade);
    }
    
}
