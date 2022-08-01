package com.example.jasperreport1.assembler;

import com.example.jasperreport1.entity.Employee;
import com.example.jasperreport1.request.EmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeAssebler {
    public Employee dtoToEntity(EmployeeDTO request) {
        Employee employee = new Employee();
        employee.setId(request.getId());
        employee.setName(request.getName());
        employee.setDesignation(request.getDesignation());
        employee.setSalary(request.getSalary());
        return employee;
    }
    public EmployeeDTO dtoToEntity(Employee request) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(request.getId());
        employeeDTO.setName(request.getName());
        employeeDTO.setDesignation(request.getDesignation());
        employeeDTO.setSalary(request.getSalary());
        return employeeDTO;

    }
    public List<EmployeeDTO> entityListToDtoList(List<Employee> requests) {
        return requests.stream().map(o->dtoToEntity(o)).collect(Collectors.toList());
//        List<EmployeeDTO> response = new ArrayList<>();
//        for (Employee employee: requests) {
//            EmployeeDTO employeeDTO = dtoToEntity(employee);
//            response.add(employeeDTO);
//        }
//        return response;
    }
}
