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
            name = "getAllCalorieConsumption",
            query = "SELECT m FROM CalorieIntake AS m ORDER BY m.id DESC"
            ),
    @NamedQuery(
            name = "getCalorieConsumptionCount",
            query = "SELECT COUNT(m) FROM CalorieConsumption AS m"
            ),
    @NamedQuery(
            name = "getCalorieConsumptionCountByAddress", // ☆ここを増やす
            query = "SELECT COUNT(m) FROM CalorieConsumption AS m WHERE m.userid = :userid"
            ),
    @NamedQuery(
            name = "getUseridAndDayByCalorieConsumption", //
            query = "SELECT m FROM CalorieConsumption AS m WHERE m.userid = :userid AND m.day = :day"
            ),
    @NamedQuery(
            name = "getStepcountByCalorieConsumption", //
            query = "SELECT m FROM CalorieConsumption AS m WHERE m.stepcount = :stepcount"
            )

})
@Table(name = "CalorieConsumption")

public class CalorieConsumption {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userid", length = 255, nullable = false)
    private Integer userid;

    @Column(name = "day", length = 255, nullable = false)
    private String day;

    // ★ここInteger からDoubleに変更
    @Column(name = "caloriesburned", nullable = false)
    private Double caloriesburned;

    @Column(name = "stepcount", nullable = false)
    private Integer stepcount;

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

    public Double getCaloriesburned() {
        return caloriesburned;
    }

    public void setCaloriesburned(Double caloriesburned) {
        this.caloriesburned = caloriesburned;
    }

    public Integer getStepcount() {
        return stepcount;
    }

    public void setStepcount(Integer stepcount) {
        this.stepcount = stepcount;
    }

}