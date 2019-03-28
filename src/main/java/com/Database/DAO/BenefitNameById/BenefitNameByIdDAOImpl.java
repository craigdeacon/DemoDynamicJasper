/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Database.DAO.BenefitNameById;

import java.util.HashMap;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author craig.deacon
 */
@Component 
public class BenefitNameByIdDAOImpl implements BenefitNameByIdDAO
{
    
    private final String GET_BENEFIT_NAMES = "SELECT id, short_benefit_name FROM benefit_types";
    
    JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger( BenefitNameByIdDAOImpl.class.getName() );
    
    @Autowired
    public BenefitNameByIdDAOImpl( DataSource dataSource )
    {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    @Override
    public HashMap<Integer, String> getBenefitNameById()
    {
        HashMap<Integer, String> benefitNamesById = new HashMap<>();
        
         jdbcTemplate.query( GET_BENEFIT_NAMES,
                
                resultSet ->
                {
                    benefitNamesById.put(resultSet.getInt( "id" ),resultSet.getString( "short_benefit_name"));
                }
                );
        
        
        return benefitNamesById;
    }
    
}
