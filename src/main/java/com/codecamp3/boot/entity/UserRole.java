package com.codecamp3.boot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_role", uniqueConstraints = { @UniqueConstraint(name = "user_role_uk", columnNames = { "user_id", "role_id" })})
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private AppRole appRole;

    // TODO: ประกาศ Default Constructor เพื่อให้ Autowired สามารถทำงานได้


    public UserRole() {

    }
    // TODO: ประกาศ Constructor ที่รับค่าตัวแปรสองชนิดคือ AppUser และ AppRole เพื่อให้สร้างตัวแปรในรูปแบบ Pattern Dependency Injection
    public UserRole(AppUser appUser, AppRole appRole) {
        this.appUser = appUser;
        this.appRole = appRole;
	}
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public AppUser getAppUser() {
        return appUser;
    }
    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
    public AppRole getAppRole() {
        return appRole;
    }
    public void setAppRole(AppRole appRole) {
        this.appRole = appRole;
    }}
  