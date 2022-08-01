package com.example.jasperreport1.service;

import com.example.jasperreport1.assembler.EmployeeAssebler;
import com.example.jasperreport1.entity.Employee;
import com.example.jasperreport1.exception.ObjectNotFoundException;
import com.example.jasperreport1.repository.EmployeeRepository;
import com.example.jasperreport1.request.EmployeeDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;
    @Autowired
    EmployeeAssebler employeeAssebler;

    public List<Employee> getData() {
        return employeeRepo.findAll();
    }

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "D:\\Jasper Project\\Project files";
        List<Employee> employees = employeeRepo.findAll();

        //load file and compile it
        File file = ResourceUtils.getFile("classpath:employees.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
        }

        return "report generated in path : " + path;
    }

    @Override
    public Employee saveEmployee(EmployeeDTO dto) {
        Employee employee = employeeAssebler.dtoToEntity(dto);
        return employeeRepo.save(employee);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> all = employeeRepo.findAll();
       return  employeeAssebler.entityListToDtoList(all);
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer id) throws ObjectNotFoundException {
        Optional<Employee> byId = employeeRepo.findById(id);
        if(!byId.isPresent()){
            throw new ObjectNotFoundException("Employee doesn't exit with id "+id, HttpStatus.OK);
        }
        Employee employee = byId.get();
        return employeeAssebler.dtoToEntity(employee);
    }


}
