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
public class Grupo {
    
    private Integer codSecao;
    private Integer codGrupo;
    private String descricao;

    public Integer getCodSecao() {
        return codSecao;
    }

    public void setCodSecao(Integer codSecao) {
        this.codSecao = codSecao;
    }

    public Integer getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(Integer codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
