package com.crud.backend.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.crud.backend.demo.exception.DepartmentNotFoundException;
import com.crud.backend.demo.model.Department;
import com.crud.backend.demo.repository.DepartmentRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dep/")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepo;
	
	@GetMapping("/department")
	public List<Department> getAllDepartment()
	{
		return departmentRepo.findAll();
	}
	
	@PostMapping("/department")
	public Department createDepartment(@RequestBody Department department) {
		return departmentRepo.save(department);
	}
	
	@GetMapping("/department/{depId}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long depId) throws DepartmentNotFoundException
	{
		Department department=departmentRepo.findById(depId)
				.orElseThrow(()->new DepartmentNotFoundException("department Id not found"+depId)); 
		return ResponseEntity.ok(department);
	}
	
	@PutMapping("/department/{depId}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Long depId, @RequestBody Department departmentDet) throws DepartmentNotFoundException{
		Department department=departmentRepo.findById(depId)
				.orElseThrow(()->new DepartmentNotFoundException("department Id not found"+depId));
		
		department.setDepHead(departmentDet.getDepHead());
		department.setDepName(departmentDet.getDepName());
		department.setSalary(departmentDet.getSalary());
		Department updatedDepartment=departmentRepo.save(department);
		return ResponseEntity.ok(updatedDepartment);
		
	}
	@DeleteMapping("/department/{depId}")
	public ResponseEntity<Map<String, Boolean>> deleteDepartment(@PathVariable Long depId) throws DepartmentNotFoundException{
		Department department=departmentRepo.findById(depId)
				.orElseThrow(()->new DepartmentNotFoundException("department Id not found"+depId));
		
		departmentRepo.delete(department);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
		
}
