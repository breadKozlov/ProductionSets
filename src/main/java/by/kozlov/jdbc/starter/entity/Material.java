package by.kozlov.jdbc.starter.entity;

public class Material {


    private Integer id;
    private String nameOfMaterial;
    private String description;

    public Material() {
    }

    public Material(String nameOfMaterial, String description) {
        this.nameOfMaterial = nameOfMaterial;
        this.description = description;
    }

    public Material(Integer id, String nameOfMaterial, String description) {
        this.id = id;
        this.nameOfMaterial = nameOfMaterial;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfMaterial() {
        return nameOfMaterial;
    }

    public void setNameOfMaterial(String nameOfMaterial) {
        this.nameOfMaterial = nameOfMaterial;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", nameOfMaterial='" + nameOfMaterial + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
