package by.kozlov.jdbc.starter.entity;

public class Requirement {

    private Integer id;
    private Set set;
    private Material material;
    private Double unitCost;
    private Integer totalSets;

    public Requirement(Integer id, Set set, Material material, Double unitCost, Integer totalSets) {
        this.id = id;
        this.set = set;
        this.material = material;
        this.unitCost = unitCost;
        this.totalSets = totalSets;
    }

    public Requirement(Set set, Material material, Double unitCost, Integer totalSets) {
        this.set = set;
        this.material = material;
        this.unitCost = unitCost;
        this.totalSets = totalSets;
    }

    public Requirement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Integer getTotalSets() {
        return totalSets;
    }

    public void setTotalSets(Integer totalSets) {
        this.totalSets = totalSets;
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "id=" + id +
                ", set=" + set +
                ", material=" + material +
                ", unitCost=" + unitCost +
                ", total_sets=" + totalSets +
                '}';
    }
}
