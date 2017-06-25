package com.apps.movilidadcb.bitacorav2.Entitys;

/**
 * Created by jcarl on 05/11/2016.
 */

public class UsuarioEntity {
    public int Id;
    public String Key;
    public String Value;
    public String Question;

    public UsuarioEntity(int id, String key, String value, String question) {
        Id = id;
        Key = key;
        Value = value;
        Question = question;
    }

    public int getId() {

        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

}
