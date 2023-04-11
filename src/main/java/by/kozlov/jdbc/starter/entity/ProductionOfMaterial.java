package by.kozlov.jdbc.starter.entity;

import by.kozlov.jdbc.starter.dao.Dao;

import java.time.LocalDateTime;

public class ProductionOfMaterial {

    private Integer id;
    private Material material;
    private Brigade brigade;
    private Double quantity;
    private LocalDateTime dateOfProduction;

    public ProductionOfMaterial() {
    }

    public ProductionOfMaterial(Integer id, Material material, Brigade brigade, Double quantity,
                                LocalDateTime dateOfProduction) {
        this.id = id;
        this.material = material;
        this.brigade = brigade;
        this.quantity = quantity;
        this.dateOfProduction = dateOfProduction;
    }

    public ProductionOfMaterial(Material material, Brigade brigade, Double quantity,
                                LocalDateTime dateOfProduction) {
        this.material = material;
        this.brigade = brigade;
        this.quantity = quantity;
        this.dateOfProduction = dateOfProduction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(LocalDateTime dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    @Override
    public String toString() {
        return "ProductionOfMaterial{" +
                "id=" + id +
                ", material=" + material +
                ", brigade=" + brigade +
                ", quantity=" + quantity +
                ", dateOfProduction=" + dateOfProduction +
                '}';
    }
}
