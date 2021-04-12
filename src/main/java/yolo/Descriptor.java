package yolo;

import javax.persistence.*;

@Entity
@Table(name = "descriptor")
public class Descriptor {
@Id
@Column(name = "id")
@GeneratedValue(
        strategy = GenerationType.IDENTITY
)

    private Integer id;
    private String name;
    private String supplier;
    private Integer itemNumber;
    private String primaryCategory;
    private String secondaryCategory;
    private Integer concitoId;

    public Descriptor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public String getSecondaryCategory() {
        return secondaryCategory;
    }

    public void setSecondaryCategory(String secondaryCategory) {
        this.secondaryCategory = secondaryCategory;
    }

    public Integer getConcitoId() {
        return concitoId;
    }

    public void setConcitoId(Integer concitoId) {
        this.concitoId = concitoId;
    }

}
