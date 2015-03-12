/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.orionline.controle;

import br.com.orionline.util.LoadProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 *
 * @author ronald
 */
public class Conecta {

    Logger logger = Logger.getLogger(Conecta.class);
    LoadProperties props = new LoadProperties();

    public Statement stm;
    public ResultSet rs;
    public Connection conn;

    private final String driver = "org.firebirdsql.jdbc.FBDriver";
    String hostName = props.getPropriedade("HostName");
    String userName = props.getPropriedade("UserName");
    String passwd = props.getPropriedade("Passwd");
    String port = props.getPropriedade("Port");
    String dbName = props.getPropriedade("dbName");
    String url = "jdbc:firebirdsql:" + hostName + "/" + port + ":" + dbName + "?encoding=WIN1252";

    public void Conexao() throws SQLException {
        logger.info("Inciando conexão com o banco de dados");
        logger.info("Setando driver de conexão com banco de dados");
        System.setProperty("jdbc.Drivers", driver);
        logger.info("Tentando Conectar ao banco de dados");
        conn = DriverManager.getConnection(url, userName, passwd);
        logger.info("Conectado com sucesso ao banco de dados");
    }

    public void executaSQL(String sql) {
        try {
            logger.info("Executando instrução SQL");
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
            logger.info("SQL Executado: " + sql);
            logger.info("SQL Executado com sucesso");
        } catch (SQLException ex) {
            logger.error("Erro ao executar SQL: " + sql, ex);
        }
    }

    public void desconecta() throws SQLException {
        logger.info("Tentando fechar conexão com o banco de dados");
        conn.close();
        logger.info("Conexão fechada com sucesso");
    }
}
