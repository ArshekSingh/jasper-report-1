package com.example.jasperreport1.controller;

import com.example.jasperreport1.entity.Employee;
import com.example.jasperreport1.exception.ObjectNotFoundException;
import com.example.jasperreport1.request.EmployeeDTO;
import com.example.jasperreport1.service.EmployeeService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class MyController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    public Employee saveEmployee(@RequestBody EmployeeDTO request) {
        return employeeService.saveEmployee(request);
    }
    @GetMapping("/getEmployeeBYId/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Integer id) throws ObjectNotFoundException {
        return employeeService.getEmployeeById(id);
    }


    @GetMapping("/getEmployees")
    public List<EmployeeDTO> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return employeeService.exportReport(format);
    }


}
