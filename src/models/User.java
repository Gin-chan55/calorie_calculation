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
            name = "getAllUser",
            query = "SELECT m FROM User AS m ORDER BY m.id DESC"
            ),
    @NamedQuery(
            name = "getUserCount",
            query = "SELECT COUNT(m) FROM User AS m"
            ),
    @NamedQuery(
            name = "getUserCountByAddress", // ☆ここを増やす
            query = "SELECT COUNT(m) FROM User AS m WHERE m.address = :address"
            )
})
@Table(name = "user")

public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "height", nullable = false)
    private String height;

    @Column(name = "weight", nullable = false)
    private String weight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


}


