/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.orionline.gui;

import br.com.orionline.controle.Conecta;
import br.com.orionline.entidade.CodigoAuxiliarProduto;
import br.com.orionline.entidade.Estoque;
import br.com.orionline.entidade.Grupo;
import br.com.orionline.entidade.Produto;
import br.com.orionline.entidade.Secao;
import br.com.orionline.gui.FrmBatuta.ProcessarOpcoesSelecionadas;
import br.com.orionline.gui.FrmBatuta.processarProgresso;
import com.alee.extended.progress.WebProgressOverlay;
import com.alee.global.StyleConstants;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.toolbar.WebToolBar;
import com.alee.managers.hotkey.ButtonHotkeyRunnable;
import com.alee.managers.hotkey.Hotkey;
import com.alee.managers.hotkey.HotkeyManager;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.style.skin.web.WebPanelPainter;
import com.alee.managers.tooltip.TooltipManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author ronald
 */
public class FrmBatuta extends WebFrame {
    private static final long serialVersionUID = 7046290417116240143L;

    Logger logger = Logger.getLogger(FrmBatuta.class);
    private static final Map<String, ImageIcon> iconsCache = new HashMap<>();
    //Componentes do laf weblaf
    private WebButton btnProcessar;
    private WebButton btnConfigDB;
    private WebButton btnSair;
    private WebProgressBar pBar;
    private WebProgressOverlay pBarOverLay;
    
    //Variáveis globais
    int total = 0;
    int regAtual = 0;
    
    //Variáveis das entidades
    Produto prodArq;
    CodigoAuxiliarProduto barrasArq;
    Secao secaoArq;
    Estoque estoqueArq;
    Grupo grupoArq;
    
    //Variáveis de conexão
    Conecta cx;
    Conecta cx2;
    Conecta cx3;
    
    //Threads
    ProcessarOpcoesSelecionadas opcoesSelecionadas = new ProcessarOpcoesSelecionadas();
    processarProgresso progresso = new processarProgresso();
    Thread thProgress;
    Thread thProcessOP = null;
    Thread thVerificaProcesso;
    /**
     * Creates new form frmPrincipal
     */
    public FrmBatuta() {
        logger.info("Carregando componentes da Janela Principal");
        initComponents();
        initComponentes2();
        itensToolBar();
        logger.info("Componentes da janela Principal carregados com sucesso");
        
    }
    private void initComponentes2(){
        //Botão processar e suas definições
        btnProcessar = WebButton.createIconWebButton(loadIcon("/br/com/orionline/imagens/process.png"), StyleConstants.smallRound, true);
        HotkeyManager.registerHotkey(this, btnProcessar, Hotkey.F5, new ButtonHotkeyRunnable(btnProcessar, 50), TooltipWay.trailing);
        TooltipManager.setTooltip(btnProcessar, "Processar Migração do itens Selecionados", TooltipWay.trailing);
        btnProcessar.addActionListener((ActionEvent e) -> {
            if ( !chCodigoBarras.isSelected() && !chEstoque.isSelected()
                    && !chProdutos.isSelected() && !chGrupo.isSelected() ){
                JOptionPane.showMessageDialog(this, "Por favor selecione uma opção para processar", 
                        "Migrador", JOptionPane.INFORMATION_MESSAGE);
            }else{
                try{
                    logger.info("Iniciando Threads");
                    //System.err.println(thProcessOP.getState());
                    this.thProcessOP = new Thread(opcoesSelecionadas);
                    this.thProcessOP.setName("Thread Processar Opções");
                    this.thProcessOP.start();
                    logger.info("Threads Inciadas com sucesso");
                }catch ( Exception ex){
                    logger.error("Erro ao inciar Threads", ex);
                }
            }
        });        
        
        //Botão ConfigDB e suas definições
        btnConfigDB = WebButton.createIconWebButton(loadIcon("/br/com/orionline/imagens/database_process.png"), StyleConstants.smallRound, true);
        HotkeyManager.registerHotkey(this, btnConfigDB, Hotkey.ALT_C, new ButtonHotkeyRunnable(btnConfigDB, 50), TooltipWay.trailing);
        TooltipManager.setTooltip(btnConfigDB, "Configurar Banco de Dados", TooltipWay.trailing, 0);
        btnConfigDB.addActionListener((ActionEvent e) -> {
            showConfigDB();
        });
        //Botão sair e sua definições
        btnSair = WebButton.createIconWebButton(loadIcon("/br/com/orionline/imagens/remove.png"), StyleConstants.smallRound, true);
        HotkeyManager.registerHotkey(this, btnSair, Hotkey.ESCAPE, new ButtonHotkeyRunnable(btnSair, 50), TooltipWay.trailing);
        TooltipManager.setTooltip(btnSair, "Fecha Migrador", TooltipWay.up, 0);
        btnSair.addActionListener((ActionEvent e) -> {
            if ( isFocused() == true ){
                System.exit(0);
            }
        });

        //Barra de progresso
        pBar = new WebProgressBar(0, 100);
        pBar.setMinimumWidth(450);
        pBar.setValue(0);
        pBar.setIndeterminate(false);
        pBar.setStringPainted(true);
        
        //Botão marcar todos e sua definições
        TooltipManager.setTooltip(btnMarcarTodos, "Marcar Todos os Itens", TooltipWay.down, 0);
        HotkeyManager.registerHotkey(this, btnMarcarTodos, Hotkey.ALT_M, new ButtonHotkeyRunnable(btnMarcarTodos, 50), TooltipWay.trailing);
    }
    private void showConfigDB(){
        logger.info("Carregando janela de configuração do banco de dados");
        frmConfigDB frm = new frmConfigDB(this, true);
        frm.pack();
        frm.setLocationRelativeTo(FrmBatuta.this);
        frm.setVisible(true);
        logger.info("Janela de configuração do banco de dados carregado com sucesso");
    }
    
    /**
     *
     * @param nearClass Classe Java
     * @param path Caminho para a imagem
     * @return Icone Carregado
     */
    public ImageIcon loadIcon(final Class nearClass, String path){
        final String key = nearClass.getCanonicalName() + ":" + path;
        if (!iconsCache.containsKey(key)) {
            iconsCache.put(key, new ImageIcon(nearClass.getResource(path)));
        }
        return iconsCache.get(key);
    }
    public ImageIcon loadIcon(String path){
        return loadIcon(getClass(), path);
        
    }
    
    private void itensToolBar(){
        //Adicionando componentes a barra de ferramentas
        barraFerramentas.addSeparator();
        barraFerramentas.add(btnProcessar);
        barraFerramentas.addSeparator();
        barraFerramentas.add(btnConfigDB);
        barraFerramentas.addSeparator();
        barraFerramentas.add(pBar);
        barraFerramentas.addSeparator();
        barraFerramentas.add(btnSair);
        barraFerramentas.addSeparator();
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
        jPanel2 = new WebPanel().setUndecorated(false).setMargin(20).setRound(StyleConstants.largeRound).setPainter(new WebPanelPainter());
        chCodigoBarras = new javax.swing.JCheckBox();
        chProdutos = new javax.swing.JCheckBox();
        chEstoque = new javax.swing.JCheckBox();
        chGrupo = new javax.swing.JCheckBox();
        btnMarcarTodos = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        chCodBarraEmDescricao = new javax.swing.JCheckBox();
        barraFerramentas = new WebToolBar(WebToolBar.HORIZONTAL);
        lblStatus = new WebLabel().setDrawShade(true).setShadeColor(Color.BLACK);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Migrador");
        setIconImages(WebLookAndFeel.getImages());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(102, 102, 102)));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Itens a Migrar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        chCodigoBarras.setText("Código de Barras");
        chCodigoBarras.setMultiClickThreshhold(1L);

        chProdutos.setText("Produtos");
        chProdutos.setMultiClickThreshhold(1L);

        chEstoque.setText("Estoque");

        chGrupo.setText("Grupo");
        chGrupo.setMultiClickThreshhold(1L);

        btnMarcarTodos.setText("Marcar Todos");
        btnMarcarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarTodosActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opções extras"));

        chCodBarraEmDescricao.setText("Juntar codigo de barras a descrição?");
        chCodBarraEmDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chCodBarraEmDescricaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chCodBarraEmDescricao)
                .addContainerGap(209, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chCodBarraEmDescricao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chCodigoBarras)
                    .addComponent(chProdutos)
                    .addComponent(chEstoque)
                    .addComponent(chGrupo))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMarcarTodos)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(chCodigoBarras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chProdutos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chEstoque)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chGrupo))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(btnMarcarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        barraFerramentas.setFloatable(false);
        barraFerramentas.setRollover(true);

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(51, 51, 255));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barraFerramentas, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(barraFerramentas, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
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

        getAccessibleContext().setAccessibleDescription("Migrador");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMarcarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcarTodosActionPerformed
        if ( btnMarcarTodos.isSelected() ){
            btnMarcarTodos.setText("Desmarcar Todos");
            TooltipManager.setTooltip(btnMarcarTodos, "Desmarcar Todos os Itens", TooltipWay.down, 0);
            
            if ( !chCodBarraEmDescricao.isSelected() ){
                chCodigoBarras.setSelected(true);
            }
            chEstoque.setSelected(true);
            chProdutos.setSelected(true);
            chGrupo.setSelected(true);
        }else{
            btnMarcarTodos.setText("Marcar Todos");
            TooltipManager.setTooltip(btnMarcarTodos, "Marcar Todos os Itens", TooltipWay.down, 0);
            
            if ( !chCodBarraEmDescricao.isSelected() ){
                chCodigoBarras.setSelected(!true);
            }
            
            chEstoque.setSelected(!true);
            chProdutos.setSelected(!true);
            chGrupo.setSelected(!true);
        }
    }//GEN-LAST:event_btnMarcarTodosActionPerformed

    private void chCodBarraEmDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chCodBarraEmDescricaoActionPerformed
        if ( chCodBarraEmDescricao.isSelected() ){
            chCodigoBarras.setSelected(false);
            chCodigoBarras.setEnabled(false);
        }else{
            chCodigoBarras.setSelected(false);
            chCodigoBarras.setEnabled(!false);
        }
    }//GEN-LAST:event_chCodBarraEmDescricaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraFerramentas;
    private javax.swing.JToggleButton btnMarcarTodos;
    private javax.swing.JCheckBox chCodBarraEmDescricao;
    private javax.swing.JCheckBox chCodigoBarras;
    private javax.swing.JCheckBox chEstoque;
    private javax.swing.JCheckBox chGrupo;
    private javax.swing.JCheckBox chProdutos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables

    class processarProgresso implements Runnable{

        @Override
        public void run() {
            pBar.setMaximum(total);
            while ( pBar.getValue() != total ){
                pBar.setValue(regAtual);
            }
            pBar.setValue(0);
            total = 0;
            regAtual = 0;
        }
        
    }
    
    class ProcessarOpcoesSelecionadas implements Runnable{

        
        @Override
        public void run() {
            if ( chCodigoBarras.isSelected() ){
                lblStatus.setText("Iniciando Migração dos Codigos de Barras");
                try {
                    Thread.sleep(1500);
                    cx = new Conecta();
                    cx2 = new Conecta();
                    cx.Conexao();
                    cx2.Conexao();
                    cx.executaSQL("select prodcod, prod_codbarra from produto where prod_codbarra is not null");
                    cx.executaSQL("select count(*) from produto where prod_codbarra is not nulll");
                    cx.rs.first();
                    cx2.rs.first();
                    total = cx2.rs.getInt(1);
                    cx2.desconecta();
                    
                    lblStatus.setText("Processando Produtos...");
                    thProgress = new Thread(progresso);
                    thProgress.start();
                    
                    do {
                        regAtual += 1;
                        
                        
                    } while (cx.rs.next());
                    
                    total = 0;
                    regAtual = 0;
                    cx.desconecta();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(FrmBatuta.this, "Ocorreu um erro ao Processar Codigo Barras", "Migrador", JOptionPane.ERROR_MESSAGE);
                    logger.error("Erro ao processar Codigo de Barras", ex);
                } catch (InterruptedException ex) {
                    logger.error("Erro no tempo de espera do codigo de barras", ex);
                }
            }
            if ( chProdutos.isSelected() ){
                logger.info("Processando Produtos");
                try {
                    lblStatus.setText("Iniciando a migração dos Produtos");
                    Thread.sleep(500);
                    File pasta = new File("arquivos");
                    File arqTxt = new File(pasta + "/SYSPPRO.txt");
                    if ( !pasta.exists() ){
                        pasta.mkdir();
                    }else{
                        if (arqTxt.exists()) {
                            arqTxt.delete();
                        }
                    }
                    //variaveis
                    try (FileWriter arquivo = new FileWriter(arqTxt, true)) {
                        //variaveis
                        DateFormat df = new SimpleDateFormat("ddMMyyyy");
                        df.setLenient(false);
                        Date dataAlteracao;
                        Date dataInclusao;
                        Date dataForLin;
                        Date dataUltReajustePrc1;
                        Date dataUltReajustePrc2;
                        Date dataUltReajustePrc3;
                        String dtaAlteracao = null;
                        String dtaInclusao = null;
                        String dtaForLin = null;
                        String dtaUltReajustePrc1 = null;
                        String dtaUltReajustePrc2 = null;
                        String dtaUltReajustePrc3 = null;
                        String cdprod = null;
                        String unidade = null;
                        String ncm;
                        String genero;
                        String situacao;
                        String tributa;
                        String descricao;
                        
                        Double Comissao;
                        int secao;
                        String envBalanca;
                        Double itensEmb;
                        
                        //Linha que será gravada
                        String linha;
                        
                        //Conexões com o banco de dados
                        cx = new Conecta();
                        cx2 = new Conecta();
                        cx3 = new Conecta();
                        cx.Conexao();
                        cx2.Conexao();
                        
                        cx.executaSQL("SELECT CASE WHEN SUBSTRING(p.PRODCOD FROM 1 FOR 1) = '0' and CHAR_LENGTH(P.PRODCOD) = 6 THEN\n"
                                + "         '1'||SUBSTRING(p.PRODCOD FROM 2 FOR CHAR_LENGTH(p.PRODCOD))\n"
                                + "       ELSE\n"
                                + "         P.PRODCOD\n"
                                + "       END AS CODIGO, p.*\n"
                                + "FROM PRODUTO P");
                        cx2.executaSQL("select count(*) from produto");
                        cx.rs.first();
                        cx2.rs.first();
                        total = cx2.rs.getInt(1);
                        cx2.desconecta();
                        lblStatus.setText("Processando Produtos...");
                        thProgress = new Thread(progresso);
                        thProgress.start();
                        
                        do {
                            regAtual += 1;
                            
                            prodArq = new Produto();
                            cdprod = cx.rs.getString("CODIGO");
                            prodArq.setProcod(cdprod);
                            descricao = cx.rs.getString("prodnome")
                                    .replaceAll("[áàâãÁÀÂÃ]", "A").replaceAll("[éèêÉÈÊ]", "E").replaceAll("[íìîÍÌÎ]", "I")
                                    .replaceAll("[óòôõÓÒÔÕ]", "O").replaceAll("[úùûÚÙÛ]", "U").replaceAll("[çÇ]", "C");
                            //Este trecho de codigo irá jogar os codigos de barras junto com a descrição do produto
                            // Se o chCodBarraEmDescricao estiver selecionado
                            if ( chCodBarraEmDescricao.isSelected() ){
                                cx3.Conexao();
                                cx3.executaSQL("SELECT\n"
                                        + "CASE WHEN COALESCE(PRODREFERENCIA, 0) > '0' THEN\n"
                                        + "  ' - '||PRODREFERENCIA\n"
                                        + "ELSE\n"
                                        + "  ''\n"
                                        + "END AS REF\n"
                                        + "FROM PRODUTO WHERE PRODCOD = '" + cx.rs.getString("prodcod"));
                                cx3.rs.first();
                                prodArq.setDescricao(descricao + " - " + cx3.rs.getString("ref"));
                                cx3.desconecta();
                            }else{
                                prodArq.setDescricao(descricao);
                            }
                            prodArq.setDescricaoReduzidsa(cx.rs.getString("prod_nome_abrev")
                                    .replaceAll("[áàâãÁÀÂÃ]", "A").replaceAll("[éèêÉÈÊ]", "E").replaceAll("[íìîÍÌÎ]", "I")
                                    .replaceAll("[óòôõÓÒÔÕ]", "O").replaceAll("[úùûÚÙÛ]", "U").replaceAll("[çÇ]", "C"));
                            prodArq.setCodSecao(01);
//                            secao = cx.rs.getInt("grpcod");
//                            if ( secao <= 98 ){
//                                prodArq.setCodSecao(secao);
//                            }else{
//                                prodArq.setCodSecao(99);
//                            }
                            
                            Comissao = cx.rs.getDouble("prod_perc_comissao");
                            if ( Comissao == 0.00){
                                prodArq.setPagaComissao("N");
                            }else{
                                prodArq.setPagaComissao("S");
                            }
                            tributa = cx.rs.getString("strib_cod");
                            if ( "T01".equalsIgnoreCase(tributa) ){
                                prodArq.setTributacao("T17");
                            }else if( "II".equalsIgnoreCase(tributa) ){
                                prodArq.setTributacao("I00");
                            }else if ( "NN".equalsIgnoreCase(tributa)){
                                prodArq.setTributacao("N00");
                            }
                            envBalanca = cx.rs.getString("utiliza_balanca");
                            if ( "S".equalsIgnoreCase(envBalanca)){
                                prodArq.setPesoVariavel("S");
                            }else{
                                prodArq.setPesoVariavel("N");
                            }
                            prodArq.setCodLocalImpressao(00);
                            prodArq.setComissao1(Comissao);
                            prodArq.setComissao2(cx.rs.getDouble("prod_perc_comissao2"));
                            prodArq.setComissao3(0.00);
                            prodArq.setDescontoMaximo(cx.rs.getDouble("prod_perc_desconto"));
                            prodArq.setPrecoVenda1(cx.rs.getDouble("prodpcovenda"));
                            prodArq.setPrecoOferta1(0.00);
                            prodArq.setDiasDeValidade(cx.rs.getInt("dias_validade"));
                            prodArq.setPrecoVariavel("N");
                            prodArq.setFrenteDeLoja("N");
                            prodArq.setEstoqueMinimo(cx.rs.getDouble("prod_estoque_minimo"));
                            prodArq.setEstoqueMaximo(0.00);
                            prodArq.setCodFornecedor("");
                            prodArq.setPrecoVenda2(cx.rs.getDouble("prodpcovenda2"));
                            prodArq.setPrecoOferta2(0.00);
                            prodArq.setPrecoVenda3(0.00);
                            prodArq.setPrecoOferta3(0.00);
                            prodArq.setTabelaA("0");
                            prodArq.setTipoBonificacao("");
                            prodArq.setFatorBonificacao(0.00);
                            prodArq.setDataAlteracao("");
                            prodArq.setQuantidadeEtiqueta(1);
                            unidade = cx.rs.getString("prounid");
                            prodArq.setUnidadeVenda(unidade);
                            prodArq.setIndentificaProdutoAlterado("A");
                            prodArq.setPrecoCusto(cx.rs.getDouble("prodpcocompra"));
                            prodArq.setControlaNumSerie("N");
                            prodArq.setControlaEstoque("S");
                            prodArq.setPermiteDesconto("S");
                            prodArq.setTipoEspecializacaoProduto("O");
                            prodArq.setComposicao("");
                            prodArq.setEnviaBalanca(envBalanca);
                            if("S".equalsIgnoreCase(envBalanca)){
                                prodArq.setControlaValidade("S");
                            }else{
                                prodArq.setControlaValidade("N");
                            }
                            prodArq.setMargemVenda1(cx.rs.getDouble("fator_lucro1"));
                            prodArq.setMargemVenda2(cx.rs.getDouble("fator_lucro2"));
                            prodArq.setMargemVenda3(0.00);
                            prodArq.setMixProduto("");
                            prodArq.setDataInclusao("");
                            prodArq.setDataForaDeLinha("");
                            prodArq.setDataUltimoReajustePreco1("");
                            prodArq.setDataUltimoReajustePreco2("");
                            prodArq.setDataUltimoReajustePreco3("");
                            prodArq.setDescricaVariavel("");
                            prodArq.setEnderecoEstoque("");
                            prodArq.setQuantidadeMinimaDeVendaPreco2(0.00);
                            prodArq.setQuantidadeMinimaDeVendaPreco3(0.00);
                            prodArq.setCodigoGrupo(cx.rs.getInt("grpcod"));
                            prodArq.setCodigoSubGrupo(0);
                            prodArq.setQuantidadeItensEmbalagem(1.00);
                            prodArq.setQuantidadeMaximaProdOferta(0.00);
                            prodArq.setPesoBruto(cx.rs.getDouble("peso_bruto"));
                            prodArq.setPesoLiquido(cx.rs.getDouble("prodpeso"));
                            prodArq.setUnidadeReferencia("");
                            prodArq.setMedidaReferencia(0.00);
                            genero = cx.rs.getString("ncm");
                            if ( genero == null || genero.length() < 8){
                                ncm = "62046200";
                                prodArq.setCodigoGenero("62");
                            }else{
                                ncm = genero;
                                prodArq.setCodigoGenero(genero.substring(0, 2));
                            }
                            prodArq.setComplementoDescProd("");
                            prodArq.setReservado("");
                            prodArq.setUnidadeCompra(unidade);
                            prodArq.setReservado2(0);
                            prodArq.setCodigoNaturezaReceita(999);
                            prodArq.setNcm(ncm);
                            prodArq.setNcmExcecao("");
                            
                            linha = String.format(Locale.US, "%014d%-45.45s%-20.20s%02d%1.1s%3.3s%1.1s%02d%5.2f%5.2f"
                                    + "%5.2f%5.2f%13.2f%13.2f%03d%1.1s%1.1s%13.2f%13.2f%-4.4s%13.2f%13.2f%13.2f%13.2f"
                                    + "%1.1s%1.1s%13.2f%8.8s%01d%-3.3s%1.1s%13.2f%1.1s%1.1s%1.1s%1.1s%1.1s%1.1s%1.1s%7.2f%7.2f%7.2f"
                                    + "%1.1s%8.8s%8.8s%8.8s%8.8s%8.8s%1.1s%-20.20s%9.2f%9.2f%03d%03d%13.2f%9.2f%9.3f"
                                    + "%9.3f%3.3s%13.2f%3.3s%-35.35s%-20.20s%3.3s%3d%3d%8.8s%2.2s\n", Integer.parseInt(prodArq.getProcod()),
                                    prodArq.getDescricao(), prodArq.getDescricaoReduzidsa(), prodArq.getCodSecao(),
                                    prodArq.getPagaComissao(), prodArq.getTributacao(), prodArq.getPesoVariavel(),
                                    prodArq.getCodLocalImpressao(), prodArq.getComissao1(), prodArq.getComissao2(),
                                    prodArq.getComissao3(), prodArq.getDescontoMaximo(), prodArq.getPrecoVenda1(),
                                    prodArq.getPrecoOferta1(), prodArq.getDiasDeValidade(), prodArq.getPrecoVariavel(),
                                    prodArq.getFrenteDeLoja(), prodArq.getEstoqueMinimo(), prodArq.getEstoqueMaximo(),
                                    prodArq.getCodFornecedor(), prodArq.getPrecoVenda2(),
                                    prodArq.getPrecoOferta2(), prodArq.getPrecoVenda3(), prodArq.getPrecoOferta3(),
                                    prodArq.getTabelaA(), prodArq.getTipoBonificacao(), prodArq.getFatorBonificacao(),
                                    prodArq.getDataAlteracao(), prodArq.getQuantidadeEtiqueta(), prodArq.getUnidadeVenda(),
                                    prodArq.getIndentificaProdutoAlterado(), prodArq.getPrecoCusto(), prodArq.getControlaNumSerie(),
                                    prodArq.getControlaEstoque(), prodArq.getPermiteDesconto(), prodArq.getTipoEspecializacaoProduto(),
                                    prodArq.getComposicao(), prodArq.getEnviaBalanca(), prodArq.getControlaValidade(),
                                    prodArq.getMargemVenda1(), prodArq.getMargemVenda2(), prodArq.getMargemVenda3(), prodArq.getMixProduto(),
                                    prodArq.getDataInclusao(), prodArq.getDataForaDeLinha(), prodArq.getDataUltimoReajustePreco1(),
                                    prodArq.getDataUltimoReajustePreco2(), prodArq.getDataUltimoReajustePreco3(), prodArq.getDescricaVariavel(),
                                    prodArq.getEnderecoEstoque(), prodArq.getQuantidadeMinimaDeVendaPreco2(), prodArq.getQuantidadeMinimaDeVendaPreco3(),
                                    prodArq.getCodigoGrupo(), prodArq.getCodigoSubGrupo(), prodArq.getQuantidadeItensEmbalagem(),
                                    prodArq.getQuantidadeMaximaProdOferta(), prodArq.getPesoBruto(), prodArq.getPesoLiquido(),
                                    prodArq.getUnidadeReferencia(), prodArq.getMedidaReferencia(), prodArq.getCodigoGenero(), prodArq.getComplementoDescProd(),
                                    prodArq.getReservado(), prodArq.getUnidadeCompra(), prodArq.getReservado2(), prodArq.getCodigoNaturezaReceita(),
                                    prodArq.getNcm(), prodArq.getNcmExcecao());
                            arquivo.write(linha);
                            arquivo.flush();
                        } while (cx.rs.next());
                        File arqTxt2 = new File(pasta+"/SYSPSEC.txt");
                        if ( arqTxt2.exists() ){
                            arqTxt2.delete();
                        }
                        try (FileWriter arquivoSecao = new FileWriter(arqTxt2, true)) {
                            arquivoSecao.write(String.format("%02d%-30.30s", 01, "Geral"));
                            arquivoSecao.flush();
                        }
                        
                        lblStatus.setText("Migração dos Produtos Concluida!");
                        Thread.sleep(1500);
                        cx.desconecta();
                        logger.info("Processo dos Produtos terminada!");
                    } catch (InterruptedException ex) {
                        logger.error("Erro no tempo de espera dos produtos", ex);
                    }
                }catch (SQLException ex) {
                    JOptionPane.showMessageDialog(FrmBatuta.this, "Ocorreu um erro ao Processar Produtos", "Migrador", JOptionPane.ERROR_MESSAGE);
                    logger.error("Erro ao processar Produtos", ex);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(FrmBatuta.this, "Ocorreu um erro na criação do arquivo SYSPPRO.txt do Produto", "Migrador", JOptionPane.ERROR_MESSAGE);
                    logger.error("Erro ao criar arquivo SYSPPRO.txt", ex);
                } catch (InterruptedException ex) {
                    logger.error("Erro no tempo de espera dos produtos", ex);
                }
            }
            if ( chEstoque.isSelected() ){
                logger.info("Processando Estoque...");
                try {
                    lblStatus.setText("Iniciando a Migração do Estoque");
                    Thread.sleep(1500);
                    File pasta = new File("arquivos");
                    File arqTxt = new File(pasta+"/SYSPEST.txt");
                    if ( !pasta.exists() ){
                        pasta.mkdir();
                    }else{
                        if ( arqTxt.exists() ){
                            arqTxt.delete();
                        }
                    }
                    try (FileWriter arquivo = new FileWriter(arqTxt, true)) {
                        DateFormat df = new SimpleDateFormat("ddMMyyyy");
                        df.setLenient(false);
                        Date dataEntrada;
                        Date dataSaida;
                        String dtaEntrada;
                        String dtaSaida;
                        
                        String linha;
                        
                        cx = new Conecta();
                        cx2 = new Conecta();
                        cx.Conexao();
                        cx2.Conexao();
                        
                        cx.executaSQL("select prodcod as procod, prodquantdisponivel as saldo, "
                                + "proddtultcompra as entrada, proddtultvenda as saida from produto");
                        cx2.executaSQL("select count(*) from produto");
                        cx.rs.first();
                        cx2.rs.first();
                        total = cx2.rs.getInt(1);
                        cx2.desconecta();
                        
                        lblStatus.setText("Processando Estoque...");
                        thProgress = new Thread(progresso);
                        thProgress.start();
                        
                        do {
                            regAtual += 1;
                            
                            estoqueArq = new Estoque();
                            estoqueArq.setProcod(cx.rs.getString("procod"));
                            estoqueArq.setSaldoEstoque(cx.rs.getDouble("saldo"));
                            dataEntrada = cx.rs.getDate("entrada");
                            if (dataEntrada == null) {
                                estoqueArq.setDataUltimaEntrada("");
                            } else {
                                dtaEntrada = df.format(dataEntrada);
                                estoqueArq.setDataUltimaEntrada(dtaEntrada);
                            }
                            dataSaida = cx.rs.getDate("saida");
                            if (dataSaida == null) {
                                estoqueArq.setDataUltimaSaida("");
                            } else {
                                dtaSaida = df.format(dataSaida);
                                estoqueArq.setDataUltimaSaida(dtaSaida);
                            }
                            
                            linha = String.format(Locale.US, "%014d%15.2f%8.8s%8.8s\n",
                                    Integer.parseInt(estoqueArq.getProcod()), estoqueArq.getSaldoEstoque(),
                                    estoqueArq.getDataUltimaEntrada(), estoqueArq.getDataUltimaSaida());
                            arquivo.write(linha);
                            arquivo.flush();
                        } while (cx.rs.next());
                    }
                    lblStatus.setText("Migração do Estoque Concluida!");
                    Thread.sleep(1500);
                    cx.desconecta();
                    logger.info("Processo do Estoque Terminado!");
                } catch (InterruptedException ex) {
                    logger.error("Erro no tempo de espera do Estoque", ex);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(FrmBatuta.this, "Ocorreu um erro na criação do arquivo SYSPEST.txt do Estoque", "Migrador", JOptionPane.ERROR_MESSAGE);
                    logger.error("Erro ao criar arquivo SYSPEST.txt", ex);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(FrmBatuta.this, "Ocorreu um erro ao Processar Estoque", "Migrador", JOptionPane.ERROR_MESSAGE);
                    logger.error("Erro ao processar Estoque", ex);
                }
            }
            if ( chGrupo.isSelected() ){
                logger.info("Processando Grupo...");
                try {
                    lblStatus.setText("Iniciando a Migração do Grupo");
                    Thread.sleep(1500);
                    File pasta = new File("arquivos");
                    File arqTxt = new File(pasta + "/SYSPGRP.txt");
                    if (!pasta.exists()) {
                        pasta.mkdir();
                    } else {
                        if (arqTxt.exists()) {
                            arqTxt.delete();
                        }
                    }
                    try (FileWriter arquivo = new FileWriter(arqTxt, true)) {
                        String linha;
                        
                        cx = new Conecta();
                        cx2 = new Conecta();
                        cx.Conexao();
                        cx2.Conexao();
                        
                        cx.executaSQL("select * from grupoprod");
                        cx2.executaSQL("select count(*) from grupoprod");
                        
                        cx.rs.first();
                        cx2.rs.first();
                        total = cx2.rs.getInt(1);
                        cx2.desconecta();
                        
                        lblStatus.setText("Processando Grupo...");
                        thProgress = new Thread(progresso);
                        thProgress.start();
                        
                        do {
                            regAtual += 1;
                            
                            grupoArq = new Grupo();
                            grupoArq.setCodSecao(01);
                            grupoArq.setCodGrupo(cx.rs.getInt("grpcod"));
                            grupoArq.setDescricao(cx.rs.getString("grpnome"));
                            
                            linha = String.format(Locale.US, "%02d%03d%-30.30s\n",
                                    grupoArq.getCodSecao(), grupoArq.getCodGrupo(), grupoArq.getDescricao());
                            
                            arquivo.write(linha);
                            arquivo.flush();
                        } while (cx.rs.next());
                    }
                    lblStatus.setText("Migração do Grupo Concluida!");
                    Thread.sleep(1500);
                    cx.desconecta();
                    logger.info("Processo do Grupo Terminado!");
                } catch (InterruptedException ex) {
                    logger.error("Erro no tempo de espera do Grupo", ex);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(FrmBatuta.this, "Ocorreu um erro na criação do arquivo SYSPGRP.txt do Grupo", "Migrador", JOptionPane.ERROR_MESSAGE);
                    logger.error("Erro ao criar arquivo SYSPGRP.txt", ex);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(FrmBatuta.this, "Ocorreu um erro ao Processar Grupo", "Migrador", JOptionPane.ERROR_MESSAGE);
                    logger.error("Erro ao processar Grupo", ex);
                }
            }
        }
    }
}
