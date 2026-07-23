package com.smartmeal.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Meal {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private User user;
 @Column(nullable=false) private String name;
 private String mealType; private Integer calories; private Integer protein; private Integer carbs; private Integer fat; private Integer fiber;
 private LocalDateTime eatenAt=LocalDateTime.now();
 public Long getId(){return id;} public void setId(Long v){id=v;} public User getUser(){return user;} public void setUser(User v){user=v;}
 public String getName(){return name;} public void setName(String v){name=v;} public String getMealType(){return mealType;} public void setMealType(String v){mealType=v;}
 public Integer getCalories(){return calories;} public void setCalories(Integer v){calories=v;} public Integer getProtein(){return protein;} public void setProtein(Integer v){protein=v;}
 public Integer getCarbs(){return carbs;} public void setCarbs(Integer v){carbs=v;} public Integer getFat(){return fat;} public void setFat(Integer v){fat=v;}
 public Integer getFiber(){return fiber;} public void setFiber(Integer v){fiber=v;} public LocalDateTime getEatenAt(){return eatenAt;} public void setEatenAt(LocalDateTime v){eatenAt=v;}
}
