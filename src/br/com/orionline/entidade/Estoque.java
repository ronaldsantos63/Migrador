/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.orionline.entidade;
/**
 *
 * @author rayanne nascimento
 */
public class Estoque {
    
    private String procod;
    private Double saldoEstoque;
    private String dataUltimaEntrada;
    private String dataUltimaSaida;

    public String getProcod() {
        return procod;
    }

    public void setProcod(String procod) {
        this.procod = procod;
    }

    public Double getSaldoEstoque() {
        return saldoEstoque;
    }

    public void setSaldoEstoque(Double saldoEstoque) {
        this.saldoEstoque = saldoEstoque;
    }

    public String getDataUltimaEntrada() {
        return dataUltimaEntrada;
    }

    public void setDataUltimaEntrada(String dataUltimaEntrada) {
        this.dataUltimaEntrada = dataUltimaEntrada;
    }

    public String getDataUltimaSaida() {
        return dataUltimaSaida;
    }

    public void setDataUltimaSaida(String dataUltimaSaida) {
        this.dataUltimaSaida = dataUltimaSaida;
    }
    
}
