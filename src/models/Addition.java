package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "getAdditionByKeyword",
            query = "SELECT a FROM Addition AS a WHERE a.searchkeyword LIKE :searchkeyword"
            )

})
@Table(name = "Addition")

public class Addition {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private AdditionCategory category;

    @Column(name = "itemname", length = 255, nullable = false)
    private String itemname;

    @Column(name = "caloriesper", nullable = false)
    private Integer caloriesper;

    @Column(name = "jpncnw", nullable = false)
    private String jpncnw;

    @Column(name = "searchkeyword", nullable = false)
    private String searchkeyword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AdditionCategory getCategory() {
        return category;
    }

    public void setCategory(AdditionCategory category) {
        this.category = category;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Integer getCaloriesper() {
        return caloriesper;
    }

    public void setCaloriesper(Integer caloriesper) {
        this.caloriesper = caloriesper;
    }

    public String getJpncnw() {
        return jpncnw;
    }

    public void setJpncnw(String jpncnw) {
        this.jpncnw = jpncnw;
    }

    public String getSearchkeyword() {
        return searchkeyword;
    }

    public void setSearchkeyword(String searchkeyword) {
        this.searchkeyword = searchkeyword;
    }

}