/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.orionline.entidade;
/**
 *
 * @author ronald santos
 */
public class ProdutoFornecedor {
    
    private String procod;
    private Integer codigoFornecedor;
    private String referenciaFornecedor;
    private String unidadeCompra;
    private Integer itensEmbalagem;
    private String nivelPreferencia;

    public String getProcod() {
        return procod;
    }

    public void setProcod(String procod) {
        this.procod = procod;
    }

    public Integer getCodigoFornecedor() {
        return codigoFornecedor;
    }

    public void setCodigoFornecedor(Integer codigoFornecedor) {
        this.codigoFornecedor = codigoFornecedor;
    }

    public String getReferenciaFornecedor() {
        return referenciaFornecedor;
    }

    public void setReferenciaFornecedor(String referenciaFornecedor) {
        this.referenciaFornecedor = referenciaFornecedor;
    }

    public String getUnidadeCompra() {
        return unidadeCompra;
    }

    public void setUnidadeCompra(String unidadeCompra) {
        this.unidadeCompra = unidadeCompra;
    }

    public Integer getItensEmbalagem() {
        return itensEmbalagem;
    }

    public void setItensEmbalagem(Integer itensEmbalagem) {
        this.itensEmbalagem = itensEmbalagem;
    }

    public String getNivelPreferencia() {
        return nivelPreferencia;
    }

    public void setNivelPreferencia(String nivelPreferencia) {
        this.nivelPreferencia = nivelPreferencia;
    }
    
}
