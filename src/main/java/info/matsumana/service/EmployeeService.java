package info.matsumana.service;


import info.matsumana.entity.Employee;
import info.matsumana.logger.MyJdbcLogger;
import info.matsumana.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmployeeService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    EmployeeRepository employeeRepository;

    @Inject
    MyJdbcLogger myJdbcLogger;

    public Employee findById(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId);

        logger.debug("last sql={}", myJdbcLogger.getLast().getFormattedSql());

        return employee;
    }
}
