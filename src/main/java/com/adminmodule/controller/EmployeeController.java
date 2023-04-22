package com.adminmodule.controller;

import com.adminmodule.security.JWTUtility;
import com.adminmodule.service.EmployeeService;
import com.adminmodule.service.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    private final JWTUtility jwtUtility;

    @Autowired
    public EmployeeController(EmployeeService employeeService, JWTUtility jwtUtility) {
        this.employeeService = employeeService;
        this.jwtUtility = jwtUtility;
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        EmployeeDTO employeeDTO = employeeService.getEmployeeByEmail(username);
        if (employeeDTO.getId() != id) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeDTO,
                                            @RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        EmployeeDTO employeeDTO1 = employeeService.getEmployeeByEmail(username);
        if (employeeDTO1.getId() != id) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id,
                                            @RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        EmployeeDTO employeeDTO1 = employeeService.getEmployeeByEmail(username);
        if (employeeDTO1.getId() != id) {
            return ResponseEntity.status(403).build();
        }
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
