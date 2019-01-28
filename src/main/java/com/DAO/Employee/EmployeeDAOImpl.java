package com.DAO.Employee;

import com.Component.TestReportComponent.secondReport.container.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;

@Component
public class EmployeeDAOImpl implements EmployeeDAO
{

    JdbcTemplate jdbcTemplate;

    private final String SQL_GET_ALL = "SELECT employee_id, name_first, name_last, life_prem, add_prem, add_prem, dlife_prem, std_prem, " +
                                        "ltd_prem, ci_prem, ehb_prem, dental_prem, hsa_prem, psa_prem, ci_dep_prem FROM employee WHERE group_id = ?";

    @Autowired
    public EmployeeDAOImpl(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ArrayList<Employee> getAllEmployees(Integer groupId)
    {
        ArrayList<Employee> listEmployee = new ArrayList<>();
        jdbcTemplate.query(SQL_GET_ALL,
                           ps ->
                           {
                                ps.setInt(1, groupId);
                           },
                           resultSet ->
                           {
                               Employee employee = new Employee();

                               employee.setName(resultSet.getString("name_first") + " " + resultSet.getString("name_last"));
                               employee.setLife(resultSet.getFloat("life_prem"));
                               employee.setAdd(resultSet.getFloat("add_prem"));
                               employee.setdLife(resultSet.getFloat("dlife_prem"));
                               employee.setStd(resultSet.getFloat("std_prem"));
                               employee.setLtd(resultSet.getFloat("ltd_prem"));
                               employee.setEhb(resultSet.getFloat("ehb_prem"));
                               employee.setDental(resultSet.getFloat("dental_prem"));
                               employee.setCi(resultSet.getFloat("ci_prem"));
                               employee.setDepCi(resultSet.getFloat("ci_dep_prem"));
                               employee.setHcsa(resultSet.getFloat("hsa_prem"));
                               employee.setPsa(resultSet.getFloat("psa_prem"));
//                               employee.setDsai(resultSet.getFloat("dsai_prem"));
//                               employee.setEap(resultSet.getFloat("eap_prem"));

                               listEmployee.add(employee);
                           });
        return listEmployee;
    }
}
