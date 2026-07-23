package com.smartmeal.model;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity @Table(name="users")
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String fullName;
 @Column(nullable=false,unique=true) private String email;
 @Column(nullable=false) private String passwordHash;
 private Integer age; private String gender; private Double heightCm; private Double weightKg;
 private String goal="Maintain Weight"; private Integer dailyCalorieGoal=2200; private String dietPreference="No Preference";
 private LocalDate createdAt=LocalDate.now();
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getPasswordHash(){return passwordHash;} public void setPasswordHash(String v){passwordHash=v;}
 public Integer getAge(){return age;} public void setAge(Integer v){age=v;} public String getGender(){return gender;} public void setGender(String v){gender=v;}
 public Double getHeightCm(){return heightCm;} public void setHeightCm(Double v){heightCm=v;} public Double getWeightKg(){return weightKg;} public void setWeightKg(Double v){weightKg=v;}
 public String getGoal(){return goal;} public void setGoal(String v){goal=v;} public Integer getDailyCalorieGoal(){return dailyCalorieGoal;} public void setDailyCalorieGoal(Integer v){dailyCalorieGoal=v;}
 public String getDietPreference(){return dietPreference;} public void setDietPreference(String v){dietPreference=v;} public LocalDate getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDate v){createdAt=v;}
}
