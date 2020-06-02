package com.halocom.Exercises.Model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int userId;

    @Column(name = "username", length = 36, nullable = false, unique = true)
    private String userName;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name="create_date")
    Timestamp createDate;

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}

