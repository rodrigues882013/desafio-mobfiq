package com.example.felipe.desafiomobfiq.models;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "tbl_product")
public class Product {

    @DatabaseField(columnName = "id_product", generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private Double price;

    @DatabaseField
    private String image;

    public Product(){ }

    public Product(String name,
                   Double price,
                   String image){
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!name.equals(product.name)) return false;
        if (!price.equals(product.price)) return false;
        return image != null ? image.equals(product.image) : product.image == null;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
