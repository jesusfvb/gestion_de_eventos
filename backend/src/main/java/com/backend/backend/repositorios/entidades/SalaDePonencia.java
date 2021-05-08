package com.backend.backend.repositorios.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class SalaDePonencia extends Entidad {

    @Column
    String nombre;

    @OneToMany
    List<Ponencia> ponencias;

}
