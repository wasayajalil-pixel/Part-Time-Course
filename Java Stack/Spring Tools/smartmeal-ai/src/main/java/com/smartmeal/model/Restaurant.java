package com.smartmeal.model;
import jakarta.persistence.*;
@Entity
public class Restaurant {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String name; private String cuisine; private String address; private Double rating; private Double distanceKm; private String tags;
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getName(){return name;} public void setName(String v){name=v;}
 public String getCuisine(){return cuisine;} public void setCuisine(String v){cuisine=v;} public String getAddress(){return address;} public void setAddress(String v){address=v;}
 public Double getRating(){return rating;} public void setRating(Double v){rating=v;} public Double getDistanceKm(){return distanceKm;} public void setDistanceKm(Double v){distanceKm=v;}
 public String getTags(){return tags;} public void setTags(String v){tags=v;}
}
