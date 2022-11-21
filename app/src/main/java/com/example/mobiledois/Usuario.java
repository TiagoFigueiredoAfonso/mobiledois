package com.example.mobiledois;

public class Usuario {
    String id;
    String nome;
    String sobrenome;
    String email;
    String endereco_rua;
    String endereco_complemento;
    String endereco_cidade;

    public String getEndereco_cidade() {
        return endereco_cidade;
    }

    public void setEndereco_cidade(String endereco_cidade) {
        this.endereco_cidade = endereco_cidade;
    }

    String endereco_cep;
    String geo_lat;
    String geo_lon;
    String tel;
    String site;
    String empresa_nome;
    String empresa_slogan;
    String empresa_bs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco_rua() {
        return endereco_rua;
    }

    public void setEndereco_rua(String endereco_rua) {
        this.endereco_rua = endereco_rua;
    }

    public String getEndereco_complemento() {
        return endereco_complemento;
    }

    public void setEndereco_complemento(String endereco_complemento) {
        this.endereco_complemento = endereco_complemento;
    }

    public String getEndereco_cep() {
        return endereco_cep;
    }

    public void setEndereco_cep(String endereco_cep) {
        this.endereco_cep = endereco_cep;
    }

    public String getGeo_lat() {
        return geo_lat;
    }

    public void setGeo_lat(String geo_lat) {
        this.geo_lat = geo_lat;
    }

    public String getGeo_lon() {
        return geo_lon;
    }

    public void setGeo_lon(String geo_lon) {
        this.geo_lon = geo_lon;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmpresa_nome() {
        return empresa_nome;
    }

    public void setEmpresa_nome(String empresa_nome) {
        this.empresa_nome = empresa_nome;
    }

    public String getEmpresa_slogan() {
        return empresa_slogan;
    }

    public void setEmpresa_slogan(String empresa_slogan) {
        this.empresa_slogan = empresa_slogan;
    }

    public String getEmpresa_bs() {
        return empresa_bs;
    }

    public void setEmpresa_bs(String empresa_bs) {
        this.empresa_bs = empresa_bs;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ".\nE-mail: " + email + ".\nTelefone: " + tel;
    }
}
