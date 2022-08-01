package com.example.jasperreport1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emp_table")
public class Employee {

    @Id
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String designation;
    @Column
    private Double salary;

}
