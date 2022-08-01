package com.example.jasperreport1.service;

import com.example.jasperreport1.entity.Employee;
import com.example.jasperreport1.exception.ObjectNotFoundException;
import com.example.jasperreport1.request.EmployeeDTO;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public interface EmployeeService {
    List<Employee> getData();


    //void fetchData();

   // void saveData();

    String exportReport(String format) throws FileNotFoundException, JRException;

    Employee saveEmployee(EmployeeDTO emp);

    List<EmployeeDTO> findAll();

    EmployeeDTO getEmployeeById(Integer id) throws ObjectNotFoundException;
}
