/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.orionline.gui;

import br.com.orionline.controle.Conecta;
import br.com.orionline.util.LoadProperties;
import com.alee.extended.filechooser.WebPathField;
import com.alee.laf.button.WebButton;
import com.alee.laf.filechooser.WebFileChooser;
import com.alee.laf.label.WebLabel;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import com.alee.managers.hotkey.ButtonHotkeyRunnable;
import com.alee.managers.hotkey.Hotkey;
import com.alee.managers.hotkey.HotkeyManager;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;
import com.alee.utils.FileUtils;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.log4j.Logger;

/**
 *
 * @author ronald
 */
public class frmConfigDB extends javax.swing.JDialog {
    private static final long serialVersionUID = 3959594839143093506L;
    
    Logger logger = Logger.getLogger(frmConfigDB.class.getClass());
    Properties dbProp;
    LoadProperties props;
    
    Conecta cx;

    private WebFileChooser dbChooser = null;
    private File file = null;
    //webTextFields
    private WebTextField webTxtServidor;
    private WebTextField webTxtUsuario;
    private WebPasswordField webTxtSenha;
    private WebTextField webTxtPorta;
    private WebTextField webTxtBanco;
    private WebPathField webTxtPathBanco;
    //webButton
    private WebButton webBtnSair;
    private WebButton webBtnCancelar;
    private WebButton webBtnEditar;
    private WebButton webBtnGravar;
    private WebButton webBtnTestar;
    
    //webLabel
    private WebLabel webLblFoto;
    /**
     * Creates new form frmConfigDB
     * @param parent Conteiner Pai
     * @param modal Se Verdadeiro a janela ficará travada
     */
    public frmConfigDB(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        logger.info("Carregando componentes da janela de Configuração do banco de dados");
        personalizandoComponents();
        initComponents();
        logger.info("Componentes da janela de configuração do banco de dados carregado com sucesso");
        logger.info("Registrando eventos da janela de configuração de banco de dados");
        eventos();
        logger.info("Eventos da janela de configuração de banco de dados registrado com sucesso");
        logger.info("Carregando Configuração de banco de dados existente");
        carregaConf();
        logger.info("Arquivo de configuração existente carregado com sucesso");
    }
    private void carregaConf(){
        File arqConf = new File("./properties/db.properties");
        props = new LoadProperties();
        if (arqConf.exists()) {
            String hostName = props.getPropriedade("HostName");
            String userName = props.getPropriedade("UserName");
            String passwd = props.getPropriedade("Passwd");
            String port = props.getPropriedade("Port");
            String dbName = props.getPropriedade("dbName");
            if (!hostName.isEmpty()) {
                txtServidor.setText(hostName);
            }
            if ( !userName.isEmpty() ){
                txtUsuario.setText(userName);
            }
            if ( !passwd.isEmpty() ){
                txtSenha.setText(passwd);
            }
            if ( !port.isEmpty() ){
                txtPorta.setText(port);
            }
            if ( !dbName.isEmpty() ){
                txtBanco.setText(dbName);
            }
        }
    }
    private void personalizandoComponents(){
        //convertendo JtextFields para webTextFields
        webTxtServidor = new WebTextField();
        webTxtServidor.setInputPrompt("IP ou Nome do Servidor de Banco");
        webTxtServidor.setInputPromptFont(webTxtServidor.getFont().deriveFont(Font.ITALIC));
        webTxtServidor.setHideInputPromptOnFocus(false);
        webTxtUsuario = new WebTextField();
        webTxtUsuario.setInputPrompt("Usuário do banco de dados");
        webTxtUsuario.setInputPromptFont(webTxtUsuario.getFont().deriveFont(Font.ITALIC));
        webTxtUsuario.setHideInputPromptOnFocus(false);
        webTxtSenha = new WebPasswordField();
        webTxtSenha.setInputPrompt("Senha do banco de dados");
        webTxtSenha.setHideInputPromptOnFocus(false);
        webTxtSenha.setInputPromptFont(webTxtSenha.getFont().deriveFont(Font.ITALIC));
        webTxtPorta = new WebTextField();
        webTxtPorta.setInputPrompt("Porta do banco de dados");
        webTxtPorta.setInputPromptFont(webTxtPorta.getFont().deriveFont(Font.ITALIC));
        webTxtPorta.setHideInputPromptOnFocus(false);
        webTxtBanco = new WebTextField();
        webTxtBanco.setInputPrompt("Banco de dados");
        webTxtBanco.setInputPromptFont(webTxtBanco.getFont().deriveFont(Font.ITALIC));
        webTxtBanco.setHideInputPromptOnFocus(false);
        webTxtPathBanco = new WebPathField(FileUtils.getDiskRoots()[0]);
        
        //Convertendo JButtons para WebButon
        //Botão sair
        webBtnSair = new WebButton();
        webBtnSair.setAnimate(true);
        webBtnSair.setRolloverDecoratedOnly(true);
        webBtnSair.setRolloverShine(true);
        webBtnSair.setRound(3);
        //Botão cancelar
        webBtnCancelar = new WebButton();
        webBtnCancelar.setAnimate(true);
        webBtnCancelar.setRolloverDecoratedOnly(true);
        webBtnCancelar.setRolloverShine(true);
        webBtnCancelar.setRound(3);
        //Botão editar
        webBtnEditar = new WebButton();
        webBtnEditar.setAnimate(true);
        webBtnEditar.setRolloverDecoratedOnly(true);
        webBtnEditar.setRolloverShine(true);
        webBtnEditar.setRound(3);
        //Botão Gravar
        webBtnGravar = new WebButton();
        webBtnGravar.setAnimate(true);
        webBtnGravar.setRolloverDecoratedOnly(true);
        webBtnGravar.setRolloverShine(true);
        webBtnGravar.setRound(3);
        //Botão Testar
        webBtnTestar = new WebButton();
        webBtnTestar.setAnimate(true);
        webBtnTestar.setRolloverDecoratedOnly(true);
        webBtnTestar.setRolloverShine(true);
        webBtnTestar.setRound(3);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtServidor = webTxtServidor;
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = webTxtUsuario;
        jLabel3 = new javax.swing.JLabel();
        txtSenha = webTxtSenha;
        jLabel4 = new javax.swing.JLabel();
        txtPorta = webTxtPorta;
        jLabel5 = new javax.swing.JLabel();
        txtBanco = webTxtBanco;
        btnPesquisar = new javax.swing.JButton();
        lblFoto = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnSair = webBtnSair;
        btnCancelar = webBtnCancelar;
        btnEditar = webBtnEditar;
        btnGravar = webBtnGravar;
        btnTestar = webBtnTestar;

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configurar Banco de Dados");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Servidor: ");

        txtServidor.setEnabled(false);
        txtServidor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtServidorKeyPressed(evt);
            }
        });

        jLabel2.setText("Usuário: ");

        txtUsuario.setEnabled(false);
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });

        jLabel3.setText("Senha: ");

        txtSenha.setEnabled(false);
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });

        jLabel4.setText("Porta: ");

        txtPorta.setEnabled(false);
        txtPorta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPortaKeyPressed(evt);
            }
        });

        jLabel5.setText("Banco: ");

        txtBanco.setEnabled(false);
        txtBanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBancoKeyPressed(evt);
            }
        });

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.setEnabled(false);
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        lblFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtServidor)
                    .addComponent(txtUsuario)
                    .addComponent(txtSenha)
                    .addComponent(txtPorta)
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/orionline/imagens/Exit.png"))); // NOI18N
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/orionline/imagens/Stop sign.png"))); // NOI18N
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/orionline/imagens/Notes.png"))); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/orionline/imagens/Save.png"))); // NOI18N
        btnGravar.setEnabled(false);
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnTestar.setText("Testar");
        btnTestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTestar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSair)
                    .addComponent(btnCancelar)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTestar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        if ( dbChooser == null ){
            dbChooser = new WebFileChooser();
            dbChooser.setMultiSelectionEnabled(false);
            dbChooser.setAcceptAllFileFilterUsed(false);
            dbChooser.setFileFilter(new FileNameExtensionFilter("Banco Firebird", "gdb", "fdb", "ib"));
            dbChooser.setDialogTitle("Localizar Banco de Dados");
        }
        if ( file != null ){
            dbChooser.setSelectedFile(file);
        }
        if ( dbChooser.showOpenDialog(this) == WebFileChooser.APPROVE_OPTION){
            file = dbChooser.getSelectedFile();
            System.out.println(file);
            txtBanco.setText(file.getPath());
            txtBanco.selectAll();
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtServidorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServidorKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtUsuario.requestFocus();
            txtUsuario.selectAll();
        }
    }//GEN-LAST:event_txtServidorKeyPressed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
       if ( evt.getKeyCode() == KeyEvent.VK_ENTER ){
           txtSenha.requestFocus();
           txtSenha.selectAll();
       }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ){
            txtPorta.requestFocus();
            txtPorta.selectAll();
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void txtBancoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBancoKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ){
            btnGravarActionPerformed(null);
        }
    }//GEN-LAST:event_txtBancoKeyPressed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        ativaCampos();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        desativaCampos();
        carregaConf();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        if ( txtServidor.getText().isEmpty() || txtUsuario.getText().isEmpty() || 
                txtSenha.getText().isEmpty() || txtPorta.getText().isEmpty() ||
                txtBanco.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor preencha todos os campos", "Migrador", JOptionPane.INFORMATION_MESSAGE);
        }else{
            int op = JOptionPane.showConfirmDialog(this, "Deseja salvar as configurações?", "Migrador", JOptionPane.YES_NO_OPTION);
            if ( op == 0 ){
                try {
                    FileOutputStream out = new FileOutputStream("./properties/db.properties");
                    logger.info("Salvando configuração do banco de dados");
                    dbProp = new Properties();
                    dbProp.setProperty("HostName", txtServidor.getText());
                    dbProp.setProperty("UserName", txtUsuario.getText());
                    dbProp.setProperty("Passwd", txtSenha.getText());
                    dbProp.setProperty("Port", txtPorta.getText());
                    dbProp.setProperty("dbName", txtBanco.getText());
                    dbProp.store(out, "##Arquivo de configuração gerado automaticamente\n###É aceito os bancos: .IB; .FDB; .GDB");
                    logger.info("Arquivo de configuração criado com sucesso");
                    JOptionPane.showMessageDialog(this, "Salvo com sucesso!", "Migrador", JOptionPane.INFORMATION_MESSAGE);
                    desativaCampos();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar configuração", "Migrador", JOptionPane.ERROR_MESSAGE);
                    logger.error("Arquivo não encontrado", ex);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar configuração", "Migrador", JOptionPane.ERROR_MESSAGE);
                    logger.error(ex);
                }
            }else{
                op = JOptionPane.showConfirmDialog(this, "Deseja cancelar as alterações?", "Migrador", JOptionPane.YES_NO_OPTION);
                if ( op == 0 ){
                    desativaCampos();
                    carregaConf();
                }else{
                    txtServidor.requestFocus();
                    if ( !txtServidor.getText().isEmpty() ){
                        txtServidor.selectAll();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void txtPortaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPortaKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ){
            txtBanco.requestFocus();
            txtBanco.selectAll();
        }
    }//GEN-LAST:event_txtPortaKeyPressed

    private void btnTestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestarActionPerformed
        logger.info("Testando conexão com o banco de dados");
        cx = new Conecta();
        try{
            logger.info("Abrindo conexão com o banco de dados");
            cx.Conexao();
            logger.info("Conexão aberta com sucesso!");
        }catch ( SQLException ex){
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao conectar ao banco de dados", "Migrador", JOptionPane.ERROR_MESSAGE);
            logger.error("Ocorreu um erro ao abrir conexão com o banco de dados",ex);
        }
        try{
            logger.info("Fechando conexão com o banco de dados");
            cx.desconecta();
            logger.info("Conexão fechada com sucesso");
        }catch ( SQLException ex){
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao conectar ao banco de dados", "Migrador", JOptionPane.ERROR_MESSAGE);
            logger.error("Ocorreu um erro ao abrir conexão com o banco de dados", ex);
        }
        JOptionPane.showMessageDialog(this, "Conexão realizada com sucesso", "Migrador", JOptionPane.INFORMATION_MESSAGE);
        logger.info("Teste de conexão realizada com sucesso");
    }//GEN-LAST:event_btnTestarActionPerformed

    private void ativaCampos(){
        txtServidor.setEnabled(true);
        txtServidor.selectAll();
        txtUsuario.setEnabled(true);
        txtSenha.setEnabled(true);
        txtPorta.setEnabled(true);
        txtBanco.setEnabled(true);
        btnPesquisar.setEnabled(true);
        btnGravar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEditar.setEnabled(false);
        btnTestar.setEnabled(false);
        btnSair.setEnabled(false);

        txtServidor.requestFocus();
    }
    
    private void desativaCampos(){
        txtServidor.setEnabled(!true);
        txtUsuario.setEnabled(!true);
        txtSenha.setEnabled(!true);
        txtPorta.setEnabled(!true);
        txtBanco.setEnabled(!true);
        btnPesquisar.setEnabled(!true);
        btnGravar.setEnabled(!true);
        btnCancelar.setEnabled(!true);
        btnEditar.setEnabled(!false);
        btnTestar.setEnabled(!false);
        btnSair.setEnabled(!false);

        btnTestar.requestFocus();
    }
    private void eventos(){
        HotkeyManager.registerHotkey(this, btnSair, Hotkey.ESCAPE, new ButtonHotkeyRunnable(btnSair, 50), TooltipWay.trailing);
        TooltipManager.setTooltip(btnSair, "Voltar para janela Principal", TooltipWay.down);
        HotkeyManager.registerHotkey(this, btnPesquisar, Hotkey.F2, new ButtonHotkeyRunnable(btnPesquisar, 50), TooltipWay.trailing);
        TooltipManager.setTooltip(btnPesquisar, "Localizar Banco de Dados", TooltipWay.down);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnTestar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JTextField txtBanco;
    private javax.swing.JTextField txtPorta;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JTextField txtServidor;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
