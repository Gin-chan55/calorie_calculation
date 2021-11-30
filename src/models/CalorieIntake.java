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
            name = "getAllCalorieIntake",
            query = "SELECT m FROM CalorieIntake AS m ORDER BY m.id DESC"
            ),
    @NamedQuery(
            name = "getCalorieIntakeCount",
            query = "SELECT COUNT(m) FROM CalorieIntake AS m"
            ),
    @NamedQuery(
            name = "getCalorieIntakeCountByAddress", // ☆ここを増やす
            query = "SELECT COUNT(m) FROM CalorieIntake AS m WHERE m.userid = :userid"
            ),
    @NamedQuery(
            name = "getTotalCalorieIntakeByCalorieIntake", //
            query = "SELECT m FROM CalorieIntake AS m WHERE m.totalcalorieintake = :totalcalorieintake"
            )

})
@Table(name = "CalorieIntake")

public class CalorieIntake {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userid", length = 255, nullable = false)
    private Integer userid;

    @Column(name = "day", length = 255, nullable = false)
    private String day;

    @Column(name = "totalcalorieintake", nullable = false)
    private Integer totalcalorieintake;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getTotalcalorieintake() {
        return totalcalorieintake;
    }

    public void setTotalcalorieintake(Integer totalcalorieintake) {
        this.totalcalorieintake = totalcalorieintake;
    }

}
