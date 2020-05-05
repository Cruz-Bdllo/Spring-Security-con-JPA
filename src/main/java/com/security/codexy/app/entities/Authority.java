package com.security.codexy.app.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authority implements Serializable {
    private final long serialVersionUID = 2L;
    /*      P R O P E R T I E S
    --------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAutho;

    private String name;

    private boolean enabled;

    private String description;


    /*        M E T H O D S
    --------------------------------- */
    public Authority() {
    }
    public Authority(String name, boolean enabled, String description) {
        this.name = name;
        this.enabled = enabled;
        this.description = description;
    } // end constructors

    public int getIdAutho() {
        return idAutho;
    }

    public void setIdAutho(int idAutho) {
        this.idAutho = idAutho;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
} // end class
