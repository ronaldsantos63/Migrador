/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.orionline.entidade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ronald santos
 */
public class Cliente {
    
    private String codigo_cli;
    private String descricao = "";
    private String cpf_cnpj = "";
    private String endereco = "";
    private String bairro = "";
    private String cidade = "";
    private String uf = "";
    private String cep = "";
    private String telefone = "";
    private Double limite_principal;
    private Double limite_principal_utilizado;
    private String status = "01";
    private String tabela_prazo = "PRZ";
    private Integer prazo = 30;
    private String fantasia = "";
    private String rg_ie = "";
    private String data_cadastro = "";
    private String data_nascimento = "";
    private String data_bloqueio = "";
    private String nome_pai = "";
    private String nome_mae = "";
    private String tipo_pessoa = "F";
    private String telefone_secundario = "";
    private String fax = "";
    private String pessoa_contato = "";
    private String endereco_cob = "";
    private String bairro_cob = "";
    private String cep_cob = "";
    private String cidade_cob = "";
    private String uf_cob = "";
    private String desconto = "";
    private String observacao = "";
    private String restricoes = "";
    private String adm_cartao_credito = "";
    private String num_cartao_credito = "";
    private String validade_cartao_credito = "";
    private String email = "";
    private String data_ultima_alteracao = "";
    private String cnae = "";
    private String sexo = "E";
    private String tipo_residencia = "P";
    private String tempo_residencia = "";
    private String veiculo = "";
    private String ponto_referencia = "";
    private String comp_residencia = "N";
    private String comp_renda = "N";
    private String com_renda_conj = "N";
    private String empresa_trabalho = "";
    private String telefone_trabalho = "";
    private String cargo_trabalho = "";
    private String tempo_trabalho = "";
    private String endereco_trabalho = "";
    private String nome_chefe_trabalho = "";
    private Double salario = 0.0;
    private String outras_renda = "";
    private String estado_civil = "O";
    private String nome_conjuge = "";
    private String data_nascimento_conj = "";
    private String empresa_trabalho_conj = "";
    private String telefone_trabalho_conj = "";
    private String cargo_conj = "";
    private String nome_chefe_trabalho_conj = "";
    private Double salario_conj = 0.0;
    private String referencia_pessoal_nome1 = "";
    private String telefone_referencia_1 = "";
    private String endereco_referencia_1 = "";
    private String referencia_pessoal_nome2 = "";
    private String telefone_referencia_2 = "";
    private String endereco_referencia_2 = "";
    private String referencia_comercial_nome1 = "";
    private String telefone_referencia_comercial_1 = "";
    private String referencia_comercial_nome_2 = "";
    private String telefone_referencia_comercial_2 = "";
    private String referencia_bancaria_1 = "";
    private String referencia_bancaria_1_agencia = "";
    private String referencia_bancaria_1_conta = "";
    private String referencia_tipo_conta_bancaria_1 = "";
    private String referencia_bancaria_2 = "";
    private String referencia_bancaria_2_agencia = "";
    private String referencia_bancaria_2_conta = "";
    private String referencia_tipo_conta_bancaria_2 = "";
    private String ticket = "";
    private String dependente_1 = "";
    private String dependente_1_grau_parentesco = "";
    private String dependente_1_telefone = "";
    private String dependente_2 = "";
    private String dependente_2_grau_parentesco = "";
    private String dependente_2_telefone = "";
    private String situacao_spc = "";
    private String nome_contato_pessoa_spc = "";
    private String situacao_tele_cheque = "";
    private String nome_pessoa_tele_cheque = "";
    private String observacao_situacao = "";
    private String situacao_aprovacao_cad = "P";
    private String pessoa_autorizou_cadastro = "";
    private String dia_fecha_fatura = "";
    private String naturalidade = "";
    private String orgao_rg = "SSPPA";
    private String tipo_preco = "1";
    private String ramo_atividade = "000";
    private String complemento_bairro = "";
    private String complemento_bairro_end_cob = "";
    private String numero_endereco_cliente = "";
    private String numero_endereco_cob = "";
    private String complemento_end_cliente = "";
    private String complemento_end_cob = "";
    private String vendedor = "";
    private Double limite_secundario = 0.0;
    private Double limite_secundario_utilizado = 0.0;
    private String codigo_interno = "";
    private String codigo_vendedor = "";

    //Variavéis para formatação de datas
    DateFormat dfEUA = new SimpleDateFormat("yyyyMMdd");
    
    public String getCodigo_cli() {
        return codigo_cli;
    }

    public void setCodigo_cli(String codigo_cli) {
        this.codigo_cli = codigo_cli;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        try{
            if (!descricao.trim().isEmpty()) {
                descricao = descricao.toUpperCase().replaceAll("[ÁÀÂÃ]", "A")
                        .replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÌ]", "I")
                        .replaceAll("[ÓÒÕÕ]", "O").replaceAll("[ÚÙ]", "U")
                        .replaceAll("[Ç]", "C");
            }
            this.descricao = descricao;
        } catch ( NullPointerException ex){
            this.descricao = "";
        } catch ( Exception ex){
            this.descricao = "";
        }
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        try {
            if ( !cpf_cnpj.trim().isEmpty()) {
                cpf_cnpj = cpf_cnpj.replaceAll("[^0-9]", "");
            }
            this.cpf_cnpj = cpf_cnpj;
        } catch (Exception e) {
            this.cpf_cnpj = "";
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        try {
            if ( !endereco.trim().isEmpty() ){
                endereco = endereco.toUpperCase().replaceAll("[ÁÀÂÃ]", "A")
                        .replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÌ]", "I")
                        .replaceAll("[ÓÒÔO]", "O").replaceAll("[ÚÙ]", "U")
                        .replaceAll("[Ç]", "C").replaceAll("[ºª]", "");
            }
            this.endereco = endereco;
        } catch (Exception e) {
            this.endereco = "";
        }
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        try {
            if ( !bairro.trim().isEmpty()){
                bairro = bairro.toUpperCase().replaceAll("[ÁÀÂÃ]", "A")
                        .replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÌ]", "I")
                        .replaceAll("[ÓÒÔO]", "O").replaceAll("[ÚÙ]", "U")
                        .replaceAll("[Ç]", "C").replaceAll("[ºª]", "");
            }
            this.bairro = bairro;
        } catch (Exception e) {
            this.bairro = "";
        }
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        try {
            if ( !cidade.trim().isEmpty() ){
                cidade = cidade.toUpperCase().replaceAll("[ÁÀÂÃ]", "A")
                        .replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÌ]", "I")
                        .replaceAll("[ÓÒÔO]", "O").replaceAll("[ÚÙ]", "U")
                        .replaceAll("[Ç]", "C").replaceAll("[ºª]", "");
            }
            this.cidade = cidade;
        } catch (Exception e) {
            this.cidade = "";
        }
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        try {
            if ( ! cep.trim().isEmpty() ){
                cep = cep.replaceAll("[^0-9]", "");
            }
            this.cep = cep;
        } catch (Exception e) {
            this.cep = "";
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        try {
            if ( ! telefone.trim().isEmpty() ){
                telefone = telefone.replaceAll("[^0-9]", "");
            }
            this.telefone = telefone;
        } catch (Exception e) {
            this.telefone = "";
        }
    }

    public Double getLimite_principal() {
        return limite_principal;
    }

    public void setLimite_principal(Double limite_principal) {
        try {
            if ( limite_principal.isNaN() || limite_principal.toString().trim().isEmpty() ){
                this.limite_principal = 0.0;
            }else{
                this.limite_principal = limite_principal;
            }
        } catch (Exception e) {
            this.limite_principal = 0.0;
        }
    }

    public Double getLimite_principal_utilizado() {
        return limite_principal_utilizado;
    }

    public void setLimite_principal_utilizado(Double limite_principal_utilizado) {
        try {
            if ( limite_principal_utilizado.isNaN() || limite_principal_utilizado.toString().trim().isEmpty() ){
                this.limite_principal_utilizado = 0.0;
            }else{
                this.limite_principal_utilizado = limite_principal_utilizado;
            }
        } catch (Exception e) {
            this.limite_principal_utilizado = 0.0;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        try {
            if ( !status.trim().isEmpty() ){
                switch (status){
                    case "A":
                        this.status = "01";
                        break;
                    case "I":
                        this.status = "02";
                        break;
                    default:
                        this.status = "01";
                }
            }else{
                this.status = "01";
            }
        } catch (Exception e) {
            this.status = "01";
        }
    }

    public String getTabela_prazo() {
        return tabela_prazo;
    }

    public void setTabela_prazo(String tabela_prazo) {
        this.tabela_prazo = tabela_prazo;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        try {
            if (! fantasia.trim().isEmpty() ){
                fantasia = fantasia.replaceAll("[ÁÀÂÃ]", "A")
                        .replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÌ]", "I")
                        .replaceAll("[ÓÒÔO]", "O").replaceAll("[ÚÙ]", "U")
                        .replaceAll("[Ç]", "C").replaceAll("[ºª]", "");
            }
            this.fantasia = fantasia;
        } catch (Exception e) {
            this.fantasia = "";
        }
    }

    public String getRg_ie() {
        return rg_ie;
    }

    public void setRg_ie(String rg_ie) {
        try {
            if ( rg_ie.trim().isEmpty() ){
                rg_ie = rg_ie.replaceAll("[^0-9]", "");
                this.rg_ie = rg_ie;
            }else{
                this.rg_ie = "";
            }
        } catch (Exception e) {
            this.rg_ie = "";
        }
    }

    public String getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        dfEUA.setLenient(false);
        if ( data_cadastro == null ){
            this.data_cadastro = "";
        }else{
            this.data_cadastro = dfEUA.format(data_cadastro);
        }
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        if ( data_nascimento == null ){
            this.data_nascimento = "";
        }else{
            this.data_nascimento = dfEUA.format(data_nascimento);
        }
    }

    public String getData_bloqueio() {
        return data_bloqueio;
    }

    public void setData_bloqueio(Date data_bloqueio) {
        if ( data_bloqueio == null ){
            this.data_bloqueio = "";
        }else{
            this.data_bloqueio = dfEUA.format(data_bloqueio);
        }
    }

    public String getNome_pai() {
        return nome_pai;
    }

    public void setNome_pai(String nome_pai) {
        try {
            if ( !nome_pai.trim().isEmpty() ){
                this.nome_pai = nome_pai;
            }else{
                this.nome_pai = "";
            }
        } catch (Exception e) {
            this.nome_pai = "";
        }
    }

    public String getNome_mae() {
        return nome_mae;
    }

    public void setNome_mae(String nome_mae) {
        try {
            if ( !nome_mae.trim().isEmpty() ){
                this.nome_mae = nome_mae;
            }else{
                this.nome_mae = "";
            }
        } catch (Exception e) {
            this.nome_mae = "";
        }
    }

    public String getTipo_pessoa() {
        return tipo_pessoa;
    }

    public void setTipo_pessoa(String tipo_pessoa) {
        this.tipo_pessoa = tipo_pessoa;
    }

    public String getTelefone_secundario() {
        return telefone_secundario;
    }

    public void setTelefone_secundario(String telefone_secundario) {
        try {
            if (! telefone_secundario.trim().isEmpty() ){
                telefone_secundario = telefone_secundario.replaceAll("[^0-9]", "");
                this.telefone_secundario = telefone_secundario;
            }else{
                this.telefone_secundario = "";
            }
        } catch (Exception e) {
            this.telefone_secundario = "";
        }
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        try {
            if (! fax.trim().isEmpty() ){
                fax = fax.replaceAll("[^0-9]", "");
                this.fax = fax;
            }else{
                this.fax = "";
            }
        } catch (Exception e) {
            this.fax = "";
        }
    }

    public String getPessoa_contato() {
        return pessoa_contato;
    }

    public void setPessoa_contato(String pessoa_contato) {
        try {
            if (! pessoa_contato.trim().isEmpty() ){
                this.pessoa_contato = pessoa_contato;
            }else{
                this.pessoa_contato = "";
            }
        } catch (Exception e) {
            this.pessoa_contato = "";
        }
    }

    public String getEndereco_cob() {
        return endereco_cob;
    }

    public void setEndereco_cob(String endereco_cob) {
        try {
            if ( ! endereco_cob.trim().isEmpty() ){
                endereco_cob = endereco_cob.replaceAll("[ÁÀÂÃ]", "A")
                        .replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÌ]", "I")
                        .replaceAll("[ÓÒÔO]", "O").replaceAll("[ÚÙ]", "U")
                        .replaceAll("[Ç]", "C").replaceAll("[ºª]", "");
            }
            this.endereco_cob = endereco_cob;
        } catch (Exception e) {
            this.endereco_cob = "";
        }
    }

    public String getBairro_cob() {
        return bairro_cob;
    }

    public void setBairro_cob(String bairro_cob) {
        try {
            if (! bairro_cob.trim().isEmpty() ){
                bairro_cob = bairro_cob.replaceAll("[ÁÀÂÃ]", "A")
                        .replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÌ]", "I")
                        .replaceAll("[ÓÒÔO]", "O").replaceAll("[ÚÙ]", "U")
                        .replaceAll("[Ç]", "C").replaceAll("[ºª]", "");
            }
            this.bairro_cob = bairro_cob;
        } catch (Exception e) {
            this.bairro_cob = "";
        }
    }

    public String getCep_cob() {
        return cep_cob;
    }

    public void setCep_cob(String cep_cob) {
        try {
            if ( ! cep_cob.trim().isEmpty() ){
                cep_cob = cep_cob.replaceAll("[^0-9]", "");
            }
            this.cep_cob = cep_cob;
        } catch (Exception e) {
            this.cep_cob = "";
        }
    }

    public String getCidade_cob() {
        return cidade_cob;
    }

    public void setCidade_cob(String cidade_cob) {
        try {
            if (! cidade_cob.trim().isEmpty() ){
                cidade_cob = cidade_cob.toUpperCase().replaceAll("[ÁÀÂÃ]", "A")
                        .replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÌ]", "I")
                        .replaceAll("[ÓÒÔO]", "O").replaceAll("[ÚÙ]", "U")
                        .replaceAll("[Ç]", "C").replaceAll("[ºª]", "");
            }
            this.cidade_cob = cidade_cob;
        } catch (Exception e) {
            this.cidade_cob = "";
        }
    }

    public String getUf_cob() {
        return uf_cob;
    }

    public void setUf_cob(String uf_cob) {
        this.uf_cob = uf_cob;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        try {
            if (observacao.trim().isEmpty()) {
                this.observacao = "";
            } else {
                this.observacao = observacao.toUpperCase().replaceAll("[ÁÀÂÃ]", "A")
                        .replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÌ]", "I")
                        .replaceAll("[ÓÒÔO]", "O").replaceAll("[ÚÙ]", "U")
                        .replaceAll("[Ç]", "C").replaceAll("[ºª]", "")
                        .replaceAll("[\\s+]", " ");
            }
        } catch (Exception e) {
            this.observacao = "";
        }
    }

    public String getRestricoes() {
        return restricoes;
    }

    public void setRestricoes(String restricoes) {
        this.restricoes = restricoes;
    }

    public String getAdm_cartao_credito() {
        return adm_cartao_credito;
    }

    public void setAdm_cartao_credito(String adm_cartao_credito) {
        this.adm_cartao_credito = adm_cartao_credito;
    }

    public String getNum_cartao_credito() {
        return num_cartao_credito;
    }

    public void setNum_cartao_credito(String num_cartao_credito) {
        this.num_cartao_credito = num_cartao_credito;
    }

    public String getValidade_cartao_credito() {
        return validade_cartao_credito;
    }

    public void setValidade_cartao_credito(String validade_cartao_credito) {
        this.validade_cartao_credito = validade_cartao_credito;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        try {
            if ( !email.trim().isEmpty() ){
                this.email = email;
            }else{
                this.email = "";
            }
        } catch (Exception e) {
            this.email = "";
        }
    }

    public String getData_ultima_alteracao() {
        return data_ultima_alteracao;
    }

    public void setData_ultima_alteracao(Date data_ultima_alteracao) {
        try {
            if ( data_ultima_alteracao == null ){
                this.data_ultima_alteracao = "";
            }else{
                this.data_ultima_alteracao = dfEUA.format(data_ultima_alteracao);
            }
        } catch (Exception e) {
            this.data_ultima_alteracao = "";
        }
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipo_residencia() {
        return tipo_residencia;
    }

    public void setTipo_residencia(String tipo_residencia) {
        this.tipo_residencia = tipo_residencia;
    }

    public String getTempo_residencia() {
        return tempo_residencia;
    }

    public void setTempo_residencia(String tempo_residencia) {
        this.tempo_residencia = tempo_residencia;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getPonto_referencia() {
        return ponto_referencia;
    }

    public void setPonto_referencia(String ponto_referencia) {
        this.ponto_referencia = ponto_referencia;
    }

    public String getComp_residencia() {
        return comp_residencia;
    }

    public void setComp_residencia(String comp_residencia) {
        this.comp_residencia = comp_residencia;
    }

    public String getComp_renda() {
        return comp_renda;
    }

    public void setComp_renda(String comp_renda) {
        this.comp_renda = comp_renda;
    }

    public String getCom_renda_conj() {
        return com_renda_conj;
    }

    public void setCom_renda_conj(String com_renda_conj) {
        this.com_renda_conj = com_renda_conj;
    }

    public String getEmpresa_trabalho() {
        return empresa_trabalho;
    }

    public void setEmpresa_trabalho(String empresa_trabalho) {
        this.empresa_trabalho = empresa_trabalho;
    }

    public String getTelefone_trabalho() {
        return telefone_trabalho;
    }

    public void setTelefone_trabalho(String telefone_trabalho) {
        this.telefone_trabalho = telefone_trabalho;
    }

    public String getCargo_trabalho() {
        return cargo_trabalho;
    }

    public void setCargo_trabalho(String cargo_trabalho) {
        this.cargo_trabalho = cargo_trabalho;
    }

    public String getTempo_trabalho() {
        return tempo_trabalho;
    }

    public void setTempo_trabalho(String tempo_trabalho) {
        this.tempo_trabalho = tempo_trabalho;
    }

    public String getEndereco_trabalho() {
        return endereco_trabalho;
    }

    public void setEndereco_trabalho(String endereco_trabalho) {
        this.endereco_trabalho = endereco_trabalho;
    }

    public String getNome_chefe_trabalho() {
        return nome_chefe_trabalho;
    }

    public void setNome_chefe_trabalho(String nome_chefe_trabalho) {
        this.nome_chefe_trabalho = nome_chefe_trabalho;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getOutras_renda() {
        return outras_renda;
    }

    public void setOutras_renda(String outras_renda) {
        this.outras_renda = outras_renda;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getNome_conjuge() {
        return nome_conjuge;
    }

    public void setNome_conjuge(String nome_conjuge) {
        this.nome_conjuge = nome_conjuge;
    }

    public String getData_nascimento_conj() {
        return data_nascimento_conj;
    }

    public void setData_nascimento_conj(Date data_nascimento_conj) {
        try {
            if ( data_nascimento_conj == null ){
                this.data_nascimento_conj = "";
            }else{
                this.data_nascimento_conj = dfEUA.format(data_nascimento_conj);
            }
        } catch (Exception e) {
            this.data_nascimento_conj = "";
        }
    }

    public String getEmpresa_trabalho_conj() {
        return empresa_trabalho_conj;
    }

    public void setEmpresa_trabalho_conj(String empresa_trabalho_conj) {
        this.empresa_trabalho_conj = empresa_trabalho_conj;
    }

    public String getTelefone_trabalho_conj() {
        return telefone_trabalho_conj;
    }

    public void setTelefone_trabalho_conj(String telefone_trabalho_conj) {
        this.telefone_trabalho_conj = telefone_trabalho_conj;
    }

    public String getCargo_conj() {
        return cargo_conj;
    }

    public void setCargo_conj(String cargo_conj) {
        this.cargo_conj = cargo_conj;
    }

    public String getNome_chefe_trabalho_conj() {
        return nome_chefe_trabalho_conj;
    }

    public void setNome_chefe_trabalho_conj(String nome_chefe_trabalho_conj) {
        this.nome_chefe_trabalho_conj = nome_chefe_trabalho_conj;
    }

    public Double getSalario_conj() {
        return salario_conj;
    }

    public void setSalario_conj(Double salario_conj) {
        this.salario_conj = salario_conj;
    }

    public String getReferencia_pessoal_nome1() {
        return referencia_pessoal_nome1;
    }

    public void setReferencia_pessoal_nome1(String referencia_pessoal_nome1) {
        this.referencia_pessoal_nome1 = referencia_pessoal_nome1;
    }

    public String getTelefone_referencia_1() {
        return telefone_referencia_1;
    }

    public void setTelefone_referencia_1(String telefone_referencia_1) {
        this.telefone_referencia_1 = telefone_referencia_1;
    }

    public String getEndereco_referencia_1() {
        return endereco_referencia_1;
    }

    public void setEndereco_referencia_1(String endereco_referencia_1) {
        this.endereco_referencia_1 = endereco_referencia_1;
    }

    public String getReferencia_pessoal_nome2() {
        return referencia_pessoal_nome2;
    }

    public void setReferencia_pessoal_nome2(String referencia_pessoal_nome2) {
        this.referencia_pessoal_nome2 = referencia_pessoal_nome2;
    }

    public String getTelefone_referencia_2() {
        return telefone_referencia_2;
    }

    public void setTelefone_referencia_2(String telefone_referencia_2) {
        this.telefone_referencia_2 = telefone_referencia_2;
    }

    public String getEndereco_referencia_2() {
        return endereco_referencia_2;
    }

    public void setEndereco_referencia_2(String endereco_referencia_2) {
        this.endereco_referencia_2 = endereco_referencia_2;
    }

    public String getReferencia_comercial_nome1() {
        return referencia_comercial_nome1;
    }

    public void setReferencia_comercial_nome1(String referencia_comercial_nome1) {
        this.referencia_comercial_nome1 = referencia_comercial_nome1;
    }

    public String getTelefone_referencia_comercial_1() {
        return telefone_referencia_comercial_1;
    }

    public void setTelefone_referencia_comercial_1(String telefone_referencia_comercial_1) {
        this.telefone_referencia_comercial_1 = telefone_referencia_comercial_1;
    }

    public String getReferencia_comercial_nome_2() {
        return referencia_comercial_nome_2;
    }

    public void setReferencia_comercial_nome_2(String referencia_comercial_nome_2) {
        this.referencia_comercial_nome_2 = referencia_comercial_nome_2;
    }

    public String getTelefone_referencia_comercial_2() {
        return telefone_referencia_comercial_2;
    }

    public void setTelefone_referencia_comercial_2(String telefone_referencia_comercial_2) {
        this.telefone_referencia_comercial_2 = telefone_referencia_comercial_2;
    }

    public String getReferencia_bancaria_1() {
        return referencia_bancaria_1;
    }

    public void setReferencia_bancaria_1(String referencia_bancaria_1) {
        this.referencia_bancaria_1 = referencia_bancaria_1;
    }

    public String getReferencia_bancaria_1_agencia() {
        return referencia_bancaria_1_agencia;
    }

    public void setReferencia_bancaria_1_agencia(String referencia_bancaria_1_agencia) {
        this.referencia_bancaria_1_agencia = referencia_bancaria_1_agencia;
    }

    public String getReferencia_bancaria_1_conta() {
        return referencia_bancaria_1_conta;
    }

    public void setReferencia_bancaria_1_conta(String referencia_bancaria_1_conta) {
        this.referencia_bancaria_1_conta = referencia_bancaria_1_conta;
    }

    public String getReferencia_tipo_conta_bancaria_1() {
        return referencia_tipo_conta_bancaria_1;
    }

    public void setReferencia_tipo_conta_bancaria_1(String referencia_tipo_conta_bancaria_1) {
        this.referencia_tipo_conta_bancaria_1 = referencia_tipo_conta_bancaria_1;
    }

    public String getReferencia_bancaria_2() {
        return referencia_bancaria_2;
    }

    public void setReferencia_bancaria_2(String referencia_bancaria_2) {
        this.referencia_bancaria_2 = referencia_bancaria_2;
    }

    public String getReferencia_bancaria_2_agencia() {
        return referencia_bancaria_2_agencia;
    }

    public void setReferencia_bancaria_2_agencia(String referencia_bancaria_2_agencia) {
        this.referencia_bancaria_2_agencia = referencia_bancaria_2_agencia;
    }

    public String getReferencia_bancaria_2_conta() {
        return referencia_bancaria_2_conta;
    }

    public void setReferencia_bancaria_2_conta(String referencia_bancaria_2_conta) {
        this.referencia_bancaria_2_conta = referencia_bancaria_2_conta;
    }

    public String getReferencia_tipo_conta_bancaria_2() {
        return referencia_tipo_conta_bancaria_2;
    }

    public void setReferencia_tipo_conta_bancaria_2(String referencia_tipo_conta_bancaria_2) {
        this.referencia_tipo_conta_bancaria_2 = referencia_tipo_conta_bancaria_2;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getDependente_1() {
        return dependente_1;
    }

    public void setDependente_1(String dependente_1) {
        this.dependente_1 = dependente_1;
    }

    public String getDependente_1_grau_parentesco() {
        return dependente_1_grau_parentesco;
    }

    public void setDependente_1_grau_parentesco(String dependente_1_grau_parentesco) {
        this.dependente_1_grau_parentesco = dependente_1_grau_parentesco;
    }

    public String getDependente_1_telefone() {
        return dependente_1_telefone;
    }

    public void setDependente_1_telefone(String dependente_1_telefone) {
        this.dependente_1_telefone = dependente_1_telefone;
    }

    public String getDependente_2() {
        return dependente_2;
    }

    public void setDependente_2(String dependente_2) {
        this.dependente_2 = dependente_2;
    }

    public String getDependente_2_grau_parentesco() {
        return dependente_2_grau_parentesco;
    }

    public void setDependente_2_grau_parentesco(String dependente_2_grau_parentesco) {
        this.dependente_2_grau_parentesco = dependente_2_grau_parentesco;
    }

    public String getDependente_2_telefone() {
        return dependente_2_telefone;
    }

    public void setDependente_2_telefone(String dependente_2_telefone) {
        this.dependente_2_telefone = dependente_2_telefone;
    }

    public String getSituacao_spc() {
        return situacao_spc;
    }

    public void setSituacao_spc(String situacao_spc) {
        this.situacao_spc = situacao_spc;
    }

    public String getNome_contato_pessoa_spc() {
        return nome_contato_pessoa_spc;
    }

    public void setNome_contato_pessoa_spc(String nome_contato_pessoa_spc) {
        this.nome_contato_pessoa_spc = nome_contato_pessoa_spc;
    }

    public String getSituacao_tele_cheque() {
        return situacao_tele_cheque;
    }

    public void setSituacao_tele_cheque(String situacao_tele_cheque) {
        this.situacao_tele_cheque = situacao_tele_cheque;
    }

    public String getNome_pessoa_tele_cheque() {
        return nome_pessoa_tele_cheque;
    }

    public void setNome_pessoa_tele_cheque(String nome_pessoa_tele_cheque) {
        this.nome_pessoa_tele_cheque = nome_pessoa_tele_cheque;
    }

    public String getObservacao_situacao() {
        return observacao_situacao;
    }

    public void setObservacao_situacao(String observacao_situacao) {
        this.observacao_situacao = observacao_situacao;
    }

    public String getSituacao_aprovacao_cad() {
        return situacao_aprovacao_cad;
    }

    public void setSituacao_aprovacao_cad(String situacao_aprovacao_cad) {
        this.situacao_aprovacao_cad = situacao_aprovacao_cad;
    }

    public String getPessoa_autorizou_cadastro() {
        return pessoa_autorizou_cadastro;
    }

    public void setPessoa_autorizou_cadastro(String pessoa_autorizou_cadastro) {
        try {
            if (! pessoa_autorizou_cadastro.trim().isEmpty() ){
                this.pessoa_autorizou_cadastro = pessoa_autorizou_cadastro;
            }else{
                this.pessoa_autorizou_cadastro = "";
            }
        } catch (Exception e) {
            this.pessoa_autorizou_cadastro = "";
        }
    }

    public String getDia_fecha_fatura() {
        return dia_fecha_fatura;
    }

    public void setDia_fecha_fatura(String dia_fecha_fatura) {
        this.dia_fecha_fatura = dia_fecha_fatura;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getOrgao_rg() {
        return orgao_rg;
    }

    public void setOrgao_rg(String orgao_rg) {
        this.orgao_rg = orgao_rg;
    }

    public String getTipo_preco() {
        return tipo_preco;
    }

    public void setTipo_preco(String tipo_preco) {
        this.tipo_preco = tipo_preco;
    }

    public String getRamo_atividade() {
        return ramo_atividade;
    }

    public void setRamo_atividade(String ramo_atividade) {
        this.ramo_atividade = ramo_atividade;
    }

    public String getComplemento_bairro() {
        return complemento_bairro;
    }

    public void setComplemento_bairro(String complemento_bairro) {
        this.complemento_bairro = complemento_bairro;
    }

    public String getComplemento_bairro_end_cob() {
        return complemento_bairro_end_cob;
    }

    public void setComplemento_bairro_end_cob(String complemento_bairro_end_cob) {
        this.complemento_bairro_end_cob = complemento_bairro_end_cob;
    }

    public String getNumero_endereco_cliente() {
        return numero_endereco_cliente;
    }

    public void setNumero_endereco_cliente(String numero_endereco_cliente) {
        this.numero_endereco_cliente = numero_endereco_cliente;
    }

    public String getNumero_endereco_cob() {
        return numero_endereco_cob;
    }

    public void setNumero_endereco_cob(String numero_endereco_cob) {
        this.numero_endereco_cob = numero_endereco_cob;
    }

    public String getComplemento_end_cliente() {
        return complemento_end_cliente;
    }

    public void setComplemento_end_cliente(String complemento_end_cliente) {
        try {
            if ( ! complemento_end_cliente.trim().isEmpty() ){
                complemento_end_cliente = complemento_end_cliente.replaceAll("[ÁÀÂÃ]", "A")
                        .replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÌ]", "I")
                        .replaceAll("[ÓÒÔO]", "O").replaceAll("[ÚÙ]", "U")
                        .replaceAll("[Ç]", "C").replaceAll("[ºª]", "");
            }
            this.complemento_end_cliente = complemento_end_cliente;
        } catch (Exception e) {
            this.complemento_end_cliente = "";
        }
    }

    public String getComplemento_end_cob() {
        return complemento_end_cob;
    }

    public void setComplemento_end_cob(String complemento_end_cob) {
        try {
            if (! complemento_end_cob.trim().isEmpty() ){
                complemento_end_cob = complemento_end_cob.replaceAll("[ÁÀÂÃ]", "A")
                        .replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÌ]", "I")
                        .replaceAll("[ÓÒÔO]", "O").replaceAll("[ÚÙ]", "U")
                        .replaceAll("[Ç]", "C").replaceAll("[ºª]", "");
            }
            this.complemento_end_cob = complemento_end_cob;
        } catch (Exception e) {
            this.complemento_end_cob = "";
        }
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Double getLimite_secundario() {
        return limite_secundario;
    }

    public void setLimite_secundario(Double limite_secundario) {
        this.limite_secundario = limite_secundario;
    }

    public Double getLimite_secundario_utilizado() {
        return limite_secundario_utilizado;
    }

    public void setLimite_secundario_utilizado(Double limite_secundario_utilizado) {
        this.limite_secundario_utilizado = limite_secundario_utilizado;
    }

    public String getCodigo_interno() {
        return codigo_interno;
    }

    public void setCodigo_interno(String codigo_interno) {
        this.codigo_interno = codigo_interno;
    }

    public String getCodigo_vendedor() {
        return codigo_vendedor;
    }

    public void setCodigo_vendedor(String codigo_vendedor) {
        this.codigo_vendedor = codigo_vendedor;
    }
    
}
