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
public class Funcionario {
    
    private Integer codigo;
    private String apelido;
    private String reservado;
    private String cargo;
    private Double percentual_comissao1;
    private Double percentual_comissao2;
    private Double percentual_comissao3;
    private String nome_funcionario;
    private Integer nivel_acesso;
    private String senha;
    private Integer codigo_funcionario;
    private Double limite_desconto;

    /**
     * Codigo interno do funcionário ou vendedor
     * @return Retorna o código interno do funcionário ou vendedor
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Codigo interno do funcionário ou vendedor
     * @param codigo Recebe como parâmetro o codigo do funcionário ou vendedor
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getReservado() {
        return reservado;
    }

    public void setReservado(String reservado) {
        this.reservado = reservado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getPercentual_comissao1() {
        return percentual_comissao1;
    }

    public void setPercentual_comissao1(Double percentual_comissao1) {
        this.percentual_comissao1 = percentual_comissao1;
    }

    public Double getPercentual_comissao2() {
        return percentual_comissao2;
    }

    public void setPercentual_comissao2(Double percentual_comissao2) {
        this.percentual_comissao2 = percentual_comissao2;
    }

    public Double getPercentual_comissao3() {
        return percentual_comissao3;
    }

    public void setPercentual_comissao3(Double percentual_comissao3) {
        this.percentual_comissao3 = percentual_comissao3;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public Integer getNivel_acesso() {
        return nivel_acesso;
    }

    public void setNivel_acesso(Integer nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getCodigo_funcionario() {
        return codigo_funcionario;
    }

    public void setCodigo_funcionario(Integer codigo_funcionario) {
        this.codigo_funcionario = codigo_funcionario;
    }

    public Double getLimite_desconto() {
        return limite_desconto;
    }

    public void setLimite_desconto(Double limite_desconto) {
        this.limite_desconto = limite_desconto;
    }
    
    
}
