package model;

import javafx.util.StringConverter;

import javax.persistence.*;
import java.util.List;

/**
 * The KitchenModel class implements the different kitchen units at Madservice Aalborg.
 *
 * The class is mapped using Hibernate JPA.
 * For more information, see https://docs.jboss.org/hibernate/stable/annotations/reference/en/html/entity.html#entity-mapping
 */

// Maps the class as an entity to the table 'kitchen' in the database
@Entity
@Table(name = "kitchen")
public class KitchenModel {

    // --- Properties ---
    // Primary key for the entity
    @Id
    @Column(name = "id")
    // Generates a unique value for every identity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // Maps a one-to-many relation between kitchen and calculation using 'kitchenId' as foreign key
    // Cascades all Hibernate actions from the kitchen entity to its related calculations
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchenId", referencedColumnName = "id")
    private List<CalculationModel> calcList;

    // --- Constructors ----
    /**
     * Empty constructor
     */
    public KitchenModel() {

    }

    //TODO - constructor overloading mangler


    // --- Getters and setters ---
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

    public List<CalculationModel> getCalcList() {
        return calcList;
    }

    public void setCalcList(List<CalculationModel> calcList) {
        this.calcList = calcList;
    }


    // --- Instance methods ---
    /**
     * Method that returns kitchen names as a list of strings
     * @return a list of strings equal to the names of all kitchens in the list
     */
    public static List<String> getString(){
        //TODO
        List<String> list = null;
        return list;
    }


    @Override
    public String toString(){
        return name;
    }



    // --- Static methods ---

    /**
     * Returns a StringConverter object that converts a Kitchen object to a
     * String representing the kitchen name - i.e. "Gug".
     * @return a StringConverter object
     */
    public static StringConverter<KitchenModel> getStringConverter() {
        // Create instance of StringConverter and declare class anonymously to implement abstract methods
        StringConverter<KitchenModel> stringConverter = new StringConverter<>() {
            @Override
            public String toString(KitchenModel kitchen) {
                if(kitchen != null) {
                    return kitchen.getName();
                }
                return "";
            }

            @Override
            public KitchenModel fromString(String s) {
                return null;
            }
        };

        // Return stringConverter
        return stringConverter;
    }


}
