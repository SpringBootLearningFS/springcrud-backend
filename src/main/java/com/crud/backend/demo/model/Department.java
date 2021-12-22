package com.crud.backend.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Department")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long depId;
	private String depHead;
	private String depName;
	private long salary;
	
	

}
