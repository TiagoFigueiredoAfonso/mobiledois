package com.example.mobiledois;

public class Endereco {

    String complemento;
    String bairro;
    String cidade;
    String logradouro;
    String area_km_estado;
    String cod_ibge_estado;
    String nome;
    String cep;
    String area_km_cidade;
    String cod_ibge_cidade;
    String sigla_estado;

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getArea_km_estado() {
        return area_km_estado;
    }

    public void setArea_km_estado(String area_km_estado) {
        this.area_km_estado = area_km_estado;
    }

    public String getCod_ibge_estado() {
        return cod_ibge_estado;
    }

    public void setCod_ibge_estado(String cod_ibge_estado) {
        this.cod_ibge_estado = cod_ibge_estado;
    }

    public String getArea_km_cidade() {
        return area_km_cidade;
    }

    public void setArea_km_cidade(String area_km_cidade) {
        this.area_km_cidade = area_km_cidade;
    }

    public String getCod_ibge_cidade() {
        return cod_ibge_cidade;
    }

    public void setCod_ibge_cidade(String cod_ibge_cidade) {
        this.cod_ibge_cidade = cod_ibge_cidade;
    }

    public String getSigla_estado() {
        return sigla_estado;
    }

    public void setSigla_estado(String sigla_estado) {
        this.sigla_estado = sigla_estado;
    }

    @Override
    public String toString() {
        return "Cidade: " + getCidade() + "- Estado: " + getSigla_estado() + "Bairro: " + getBairro();
    }
}
