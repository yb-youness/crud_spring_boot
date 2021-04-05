package com.advance.adv.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;


@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank (message = "Name Of The Department Is Required")
    private String name;
    @NotBlank (message = "Desc Of The Department Is Required")
    @Column(name="Discription")
    private String desc;

    @ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "departmentId")
    private List<User> users;
    public Department(){}
    public Department(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Department [desc=" + desc + ", id=" + id + ", name=" + name + ", users=" + users + "]";
    }

}
