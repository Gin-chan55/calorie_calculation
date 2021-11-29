package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "getAllAddition",
            query = "SELECT m FROM CalorieConsumption AS m ORDER BY m.id DESC"
            ),
    @NamedQuery(
            name = "getAdditionCount",
            query = "SELECT COUNT(m) FROM CalorieConsumption AS m"
            ),
    @NamedQuery(
            name = "getAdditionCountByAddress", // ☆ここを増やす
            query = "SELECT COUNT(m) FROM CalorieConsumption AS m WHERE m.userid = :userid"
            ),
    @NamedQuery(
            name = "getUseridAndDayByAddition", //
            query = "SELECT m FROM CalorieConsumption AS m WHERE m.userid = :userid AND m.day = :day"
            )

})
@Table(name = "Addition")

public class Addition {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "masterid", length = 255, nullable = false)
    private Integer masterid;

    @Column(name = "itemname", length = 255, nullable = false)
    private String itemname;

    @Column(name = "caloriesper", nullable = false)
    private Integer caloriesper;

    @Column(name = "searchkeyword", nullable = false)
    private String searchkeyword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMasterid() {
        return masterid;
    }

    public void setMasterid(Integer masterid) {
        this.masterid = masterid;
    }

    public String getDay() {
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

    public String getSearchkeyword() {
        return searchkeyword;
    }

    public void setSearchkeyword(String searchkeyword) {
        this.searchkeyword = searchkeyword;
    }

}