/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.orionline.main;

import br.com.orionline.gui.FrmBatuta;
import com.alee.laf.WebLookAndFeel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import org.apache.log4j.Logger;

/**
 *
 * @author ronald
 */
public class Migrador {

    private static Migrador instance = null;
    private static final Logger logger = Logger.getLogger(Migrador.class);
    
    public static Migrador getInstance(){
        if ( instance == null){
            instance = new Migrador();
        }
        return instance;
    }
    /**
     * Esse método é responsável apenas por definir a imagem da janela principal
     */
    public void load(){
        logger.info("Aplicando LookAndFeel WebLaf na Aplicação");
        try {
            UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
            logger.info("LookAndFeel Weblaf aplicado na aplicação");
        }catch (UnsupportedLookAndFeelException ex) {
            logger.error("LookAndFeel WebLaf não suportado", ex);
        } catch ( IllegalArgumentException ex){
            logger.error(ex);
            logger.info("Aplicando stylo Metal");
            for ( UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() ){
                if ( "Metal".equals(info.getName())){
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                        MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
                    } catch (ClassNotFoundException ex1) {
                        logger.warn("Classe LookAndFeel não encontrada", ex1);
                    } catch (InstantiationException ex1) {
                        logger.error("Erro ao instanciar classe LookAndFeel", ex1);
                    } catch (IllegalAccessException ex1) {
                        logger.error("Acesso Ilegal a classe LookAndFeel", ex1);
                    } catch (UnsupportedLookAndFeelException ex1) {
                        logger.warn("LookAndFeel não suportada", ex1);
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            logger.warn("Classe LookAndFeel não encontrada", ex);
        } catch (InstantiationException ex) {
            logger.error("Erro ao instanciar classe LookAndFeel", ex);
        } catch (IllegalAccessException ex) {
            logger.error("Acesso Ilegal a classe LookAndFeel", ex);
        }
        logger.info("Carregando janela principal da aplicação");
        WebLookAndFeel.setDecorateAllWindows(true);
        FrmBatuta frm = new FrmBatuta();
        frm.setIconImages(WebLookAndFeel.getImages());
        frm.setIconImage(new ImageIcon(getClass().getResource("/br/com/orionline/imagens/logo.png")).getImage());
        frm.setVisible(true);
        logger.info("Janela principal carregada");
    }
    private void verificaProperties(){
        logger.info("Iniciando verificação do arquivo de configuração do banco de dados");
        logger.info("Verificando se a pasta que guarda o arquivo de configuração existe");
        File pasta = new File("properties");
        File arqConf = new File("./properties/db.properties");
        if ( !pasta.exists() ){
            logger.info("Não foi encontrado a pasta que guarda o arquivo de configuração");
            logger.info("Criando pasta que armazenará o arquivo de configuração");
            pasta.mkdir();
            logger.info("Pasta criada com sucesso");
            logger.info("Criando arquivo de configuração");
            if ( !arqConf.exists() ){
                FileOutputStream out = null;
                try {
                    logger.info("Definindo valores padrão de configuração");
                    Properties dbProp = new Properties();
                    dbProp.setProperty("HostName", "localhost");
                    dbProp.setProperty("UserName", "SYSDBA");
                    dbProp.setProperty("Passwd", "masterkey");
                    dbProp.setProperty("Port", "3050");
                    dbProp.setProperty("dbName", "C:\\banco\\banco.fdb");
                    out = new FileOutputStream("./properties/db.properties");
                    dbProp.store(out, "##Arquivo de configuração gerado automaticamente\n##É aceito os bancos: .IB; .FDB; .GDB");
                    logger.info("Valores padrão do arquivo de configuração definidos com sucesso!");
                    logger.info("Arquivo de configuração criado com sucesso");
                } catch (FileNotFoundException ex) {
                    logger.error("Arquivo não encontrado", ex);
                } catch (IOException ex) {
                    logger.error(ex);
                } finally {
                    try {
                        out.close();
                    } catch (IOException ex) {
                        logger.error(ex);
                    }
                }
            }
        }else if( !arqConf.exists() ){
            logger.info("Pasta que armazena o arquivo de configuração existe");
            logger.info("Verificando se existe um arquivo de configuração");
            if (!arqConf.exists()) {
                logger.info("Não foi encontrado nenhum arquivo de configuração");
                logger.info("Criando arquivo de configuração com valores padrão");
                FileOutputStream out = null;
                try {
                    logger.info("Definindo valores padrão de configuração");
                    Properties dbProp = new Properties();
                    dbProp.setProperty("HostName", "localhost");
                    dbProp.setProperty("UserName", "SYSDBA");
                    dbProp.setProperty("Passwd", "masterkey");
                    dbProp.setProperty("Port", "3050");
                    dbProp.setProperty("dbName", "C:\\banco\\banco.fdb");
                    out = new FileOutputStream("./properties/db.properties");
                    dbProp.store(out, "##Arquivo de configuração gerado automaticamente\n##É aceito os bancos: .IB; .FDB; .GDB");
                    logger.info("Valores padrão do arquivo de configuração definidos com sucesso!");
                    logger.info("Arquivo de configuração criado com sucesso");
                } catch (FileNotFoundException ex) {
                    logger.error("Arquivo não encontrado", ex);
                } catch (IOException ex) {
                    logger.error(ex);
                } finally {
                    try {
                        out.close();
                    } catch (IOException ex) {
                        logger.error(ex);
                    }
                }
            }
        }else{
            logger.info("Pasta que armazena o arquivo de configuração existe");
            logger.info("Já existe um arquivo de configuração");
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        logger.info("Inciando a aplicação");
        getInstance().verificaProperties();
        getInstance().load();
    }
    
}
