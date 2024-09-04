package com.android.recetarioaleatrio;

public class Recipe {
    private int id;
    private String name;
    private String preparation;
    private byte[] image;

    public Recipe(int id, String name, String preparation, byte[] image) {
        this.id = id;
        this.name = name;
        this.preparation = preparation;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPreparation() {
        return preparation;
    }

    public byte[] getImage() {
        return image;
    }
}


