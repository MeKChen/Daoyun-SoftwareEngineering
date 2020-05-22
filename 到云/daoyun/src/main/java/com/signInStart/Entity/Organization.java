package com.signInStart.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "origanization",schema = "dbo",catalog = "et")
public class Organization implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}