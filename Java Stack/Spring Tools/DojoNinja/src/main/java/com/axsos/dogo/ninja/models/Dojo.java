package com.axsos.dogo.ninja.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name= "dojos")
public class Dojo {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotNull(message = "Dojo Name is required")
private String dojoName;


@Column(updatable = false)
@DateTimeFormat(pattern = "yyyy-MM-dd")
private Date createdAt;

@DateTimeFormat(pattern = "yyyy-MM-dd")
private Date updatedAt;

@OneToMany(mappedBy = "dojo",fetch = FetchType.LAZY)
private List<Ninja> ninjas;

//Constructor
public Dojo() {
}
//Getter & Setter
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getDojoName() {
	return dojoName;
}

public void setDojoName(String dojoName) {
	this.dojoName = dojoName;
}

@PrePersist
protected void onCreate() {
    this.createdAt = new Date();
}

@PreUpdate
protected void onUpdate() {
    this.updatedAt = new Date();
}
public Date getCreatedAt() {
    return createdAt;
}

public Date getUpdatedAt() {
    return updatedAt;
}

public List<Ninja> getNinjas() {
    return ninjas;
}

public void setNinjas(List<Ninja> ninjas) {
    this.ninjas = ninjas;
}

}
