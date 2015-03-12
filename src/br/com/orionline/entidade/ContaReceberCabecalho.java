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
public class ContaReceberCabecalho {
    
    private String tipo;
    private Integer reservado1;
    private Integer reservado2;
    private Integer reservado3;
    private Integer reservado4;
    private String tipoJuros;
    private Double percentualJuros;
    private Double percentualMulta;
    private Double percentualDesconto;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getReservado1() {
        return reservado1;
    }

    public void setReservado1(Integer reservado1) {
        this.reservado1 = reservado1;
    }

    public Integer getReservado2() {
        return reservado2;
    }

    public void setReservado2(Integer reservado2) {
        this.reservado2 = reservado2;
    }

    public Integer getReservado3() {
        return reservado3;
    }

    public void setReservado3(Integer reservado3) {
        this.reservado3 = reservado3;
    }

    public Integer getReservado4() {
        return reservado4;
    }

    public void setReservado4(Integer reservado4) {
        this.reservado4 = reservado4;
    }

    public String getTipoJuros() {
        return tipoJuros;
    }

    public void setTipoJuros(String tipoJuros) {
        this.tipoJuros = tipoJuros;
    }

    public Double getPercentualJuros() {
        return percentualJuros;
    }

    public void setPercentualJuros(Double percentualJuros) {
        this.percentualJuros = percentualJuros;
    }

    public Double getPercentualMulta() {
        return percentualMulta;
    }

    public void setPercentualMulta(Double percentualMulta) {
        this.percentualMulta = percentualMulta;
    }

    public Double getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(Double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }
    
}
