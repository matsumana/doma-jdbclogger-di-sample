package info.matsumana.controller;

import info.matsumana.entity.Employee;
import info.matsumana.service.EmployeeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Inject
    EmployeeService employeeService;

    @RequestMapping(value = "{employeeId}", method = RequestMethod.GET)
    public Employee index(@PathVariable Integer employeeId) {
        return employeeService.findById(employeeId);
    }
}
