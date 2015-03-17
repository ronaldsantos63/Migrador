/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.orionline.entidade;

/**
 *
 * @author ronald
 */
public class ContaReceber {
    
    //cabecalho
    //O cabeçalho será a primeira linha
    // Todas as variaveis que começarem com C serão referente ao cabeçalho
    // da conta a receber
    private String c_tipo_registro;
    private String c_reservado_1;
    private String c_reservado_2;
    private String c_reservado_3;
    private String c_tipo_juros;
    private Double c_percentual_juros;
    private Double c_percentual_multa;
    private Double c_percentual_desconto;
    
    //Titulos
    // Os titulos serão a segunda linha
    // Todas as variáveis que começarem com T serão referente aos titulos
    private String t_tipo_registro;
    private String t_loja;
    private String t_nro_documento;
    private String t_tipo_pagamento;
    private String t_cod_agente;
    private String t_data_emissao;
    private String t_data_vencimento;
    private String t_data_ultimo_pagamento;
    private Double t_valor_nominal;
    private Double t_valor_recebido;
    private Double t_valor_devido;
    private String t_observacao;
    private String t_cod_auxiliar;
    private String t_data_calculo_valor_devido;
    private String t_sequencial_sysac;
    
    //Pagamentos parciais
    // Os pagamentos serão a terceira linha
    // Todas as variaveis que começarem com P serão referente aos pagamentos parciais
    private String p_tipo_registro;
    private String p_data_pagamento;
    private Double p_valor_recebido;
    private Double p_valor_juros;
    private Double p_valor_multa;
    private Double p_valor_desconto;
    private Double p_valor_no_pagamento;
    private String p_sequencial_sysac;

    /**
     * 
     * @return Retorna tipo de registro
     */
    public String getC_tipo_registro() {
        return c_tipo_registro;
    }

    /**
     * Este método será responsável por setar o tipo de registro como C
     * C = Cabeçalho do título
     * @param c_tipo_registro Tipo de Registro
     */
    public void setC_tipo_registro(String c_tipo_registro) {
        this.c_tipo_registro = c_tipo_registro;
    }

    public String getC_reservado_1() {
        return c_reservado_1;
    }

    public void setC_reservado_1(String c_reservado_1) {
        this.c_reservado_1 = c_reservado_1;
    }

    public String getC_reservado_2() {
        return c_reservado_2;
    }

    public void setC_reservado_2(String c_reservado_2) {
        this.c_reservado_2 = c_reservado_2;
    }

    public String getC_reservado_3() {
        return c_reservado_3;
    }

    public void setC_reservado_3(String c_reservado_3) {
        this.c_reservado_3 = c_reservado_3;
    }

    public String getC_tipo_juros() {
        return c_tipo_juros;
    }

    public void setC_tipo_juros(String c_tipo_juros) {
        this.c_tipo_juros = c_tipo_juros;
    }

    public Double getC_percentual_juros() {
        return c_percentual_juros;
    }

    public void setC_percentual_juros(Double c_percentual_juros) {
        this.c_percentual_juros = c_percentual_juros;
    }

    public Double getC_percentual_multa() {
        return c_percentual_multa;
    }

    public void setC_percentual_multa(Double c_percentual_multa) {
        this.c_percentual_multa = c_percentual_multa;
    }

    public Double getC_percentual_desconto() {
        return c_percentual_desconto;
    }

    public void setC_percentual_desconto(Double c_percentual_desconto) {
        this.c_percentual_desconto = c_percentual_desconto;
    }

    public String getT_tipo_registro() {
        return t_tipo_registro;
    }

    public void setT_tipo_registro(String t_tipo_registro) {
        this.t_tipo_registro = t_tipo_registro;
    }

    public String getT_loja() {
        return t_loja;
    }

    public void setT_loja(String t_loja) {
        this.t_loja = t_loja;
    }

    public String getT_nro_documento() {
        return t_nro_documento;
    }

    public void setT_nro_documento(String t_nro_documento) {
        this.t_nro_documento = t_nro_documento;
    }

    public String getT_tipo_pagamento() {
        return t_tipo_pagamento;
    }

    public void setT_tipo_pagamento(String t_tipo_pagamento) {
        this.t_tipo_pagamento = t_tipo_pagamento;
    }

    public String getT_cod_agente() {
        return t_cod_agente;
    }

    public void setT_cod_agente(String t_cod_agente) {
        this.t_cod_agente = t_cod_agente;
    }

    public String getT_data_emissao() {
        return t_data_emissao;
    }

    public void setT_data_emissao(String t_data_emissao) {
        this.t_data_emissao = t_data_emissao;
    }

    public String getT_data_vencimento() {
        return t_data_vencimento;
    }

    public void setT_data_vencimento(String t_data_vencimento) {
        this.t_data_vencimento = t_data_vencimento;
    }

    public String getT_data_ultimo_pagamento() {
        return t_data_ultimo_pagamento;
    }

    public void setT_data_ultimo_pagamento(String t_data_ultimo_pagamento) {
        this.t_data_ultimo_pagamento = t_data_ultimo_pagamento;
    }

    public Double getT_valor_nominal() {
        return t_valor_nominal;
    }

    public void setT_valor_nominal(Double t_valor_nominal) {
        this.t_valor_nominal = t_valor_nominal;
    }

    public Double getT_valor_recebido() {
        return t_valor_recebido;
    }

    public void setT_valor_recebido(Double t_valor_recebido) {
        this.t_valor_recebido = t_valor_recebido;
    }

    public Double getT_valor_devido() {
        return t_valor_devido;
    }

    public void setT_valor_devido(Double t_valor_devido) {
        this.t_valor_devido = t_valor_devido;
    }

    public String getT_observacao() {
        return t_observacao;
    }

    public void setT_observacao(String t_observacao) {
        this.t_observacao = t_observacao;
    }

    public String getT_cod_auxiliar() {
        return t_cod_auxiliar;
    }

    public void setT_cod_auxiliar(String t_cod_auxiliar) {
        this.t_cod_auxiliar = t_cod_auxiliar;
    }

    public String getT_data_calculo_valor_devido() {
        return t_data_calculo_valor_devido;
    }

    public void setT_data_calculo_valor_devido(String t_data_calculo_valor_devido) {
        this.t_data_calculo_valor_devido = t_data_calculo_valor_devido;
    }

    public String getT_sequencial_sysac() {
        return t_sequencial_sysac;
    }

    public void setT_sequencial_sysac(String t_sequencial_sysac) {
        this.t_sequencial_sysac = t_sequencial_sysac;
    }

    public String getP_tipo_registro() {
        return p_tipo_registro;
    }

    public void setP_tipo_registro(String p_tipo_registro) {
        this.p_tipo_registro = p_tipo_registro;
    }

    public String getP_data_pagamento() {
        return p_data_pagamento;
    }

    public void setP_data_pagamento(String p_data_pagamento) {
        this.p_data_pagamento = p_data_pagamento;
    }

    public Double getP_valor_recebido() {
        return p_valor_recebido;
    }

    public void setP_valor_recebido(Double p_valor_recebido) {
        this.p_valor_recebido = p_valor_recebido;
    }

    public Double getP_valor_juros() {
        return p_valor_juros;
    }

    public void setP_valor_juros(Double p_valor_juros) {
        this.p_valor_juros = p_valor_juros;
    }

    public Double getP_valor_multa() {
        return p_valor_multa;
    }

    public void setP_valor_multa(Double p_valor_multa) {
        this.p_valor_multa = p_valor_multa;
    }

    public Double getP_valor_desconto() {
        return p_valor_desconto;
    }

    public void setP_valor_desconto(Double p_valor_desconto) {
        this.p_valor_desconto = p_valor_desconto;
    }

    public Double getP_valor_no_pagamento() {
        return p_valor_no_pagamento;
    }

    public void setP_valor_no_pagamento(Double p_valor_no_pagamento) {
        this.p_valor_no_pagamento = p_valor_no_pagamento;
    }

    public String getP_sequencial_sysac() {
        return p_sequencial_sysac;
    }

    public void setP_sequencial_sysac(String p_sequencial_sysac) {
        this.p_sequencial_sysac = p_sequencial_sysac;
    }
    
    
}
