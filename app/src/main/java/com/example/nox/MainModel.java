package com.example.nox;

public class MainModel {
    Integer LangLogo;
    String LangName;

    public MainModel(Integer LangLogo,String LangName) {
        this.LangLogo = LangLogo;
        this.LangName = LangName;
    }
    public Integer getLangLogo(){
        return LangLogo;
    }

    public String getLangName() {
        return LangName;
    }
}
