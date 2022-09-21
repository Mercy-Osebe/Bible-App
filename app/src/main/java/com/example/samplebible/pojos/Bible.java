package com.example.samplebible.pojos;


public class Bible {
    private String id;
    private String dblId;
    private String relatedDbl = null;
    private String name;
    private String nameLocal;
    private String abbreviation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDblId() {
        return dblId;
    }

    public void setDblId(String dblId) {
        this.dblId = dblId;
    }

    public String getRelatedDbl() {
        return relatedDbl;
    }

    public void setRelatedDbl(String relatedDbl) {
        this.relatedDbl = relatedDbl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameLocal() {
        return nameLocal;
    }

    public void setNameLocal(String nameLocal) {
        this.nameLocal = nameLocal;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviationLocal() {
        return abbreviationLocal;
    }

    public void setAbbreviationLocal(String abbreviationLocal) {
        this.abbreviationLocal = abbreviationLocal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionLocal() {
        return descriptionLocal;
    }

    public void setDescriptionLocal(String descriptionLocal) {
        this.descriptionLocal = descriptionLocal;
    }

    private String abbreviationLocal;
    private String description;
    private String descriptionLocal;
    // Getter Methods
}
