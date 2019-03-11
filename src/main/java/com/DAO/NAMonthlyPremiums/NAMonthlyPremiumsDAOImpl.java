/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO.NAMonthlyPremiums;

import com.Component.CS.NAMonthlyPremiums.container.NAMonthlyPremiumsGroup;
import java.util.ArrayList;
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
public class NAMonthlyPremiumsDAOImpl implements NAMonthlyPremiumsDAO
{

    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger( NAMonthlyPremiumsDAO.class.getName() );


    @Autowired
    public NAMonthlyPremiumsDAOImpl( DataSource dataSource )
    {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    @Override
    public ArrayList<NAMonthlyPremiumsGroup> getNAMonthlyPremiumsGroup()
    {
        ArrayList<NAMonthlyPremiumsGroup> naMonthlyGroup = new ArrayList<>();

        //            + "        AND var_id = 'CURRENT_DATE'\n"
        //            + "        AND co_op_premium_file.underwriter_id = 93047\n"
        String NA_MONTHLY_PREMIUM_QUERY = "SELECT\n"
                + "    co_op_premium_file.id,\n"
                + "    co_op_premium_file.month_id,\n"
                + "    co_op_premium_file.group_id,\n"
                + "    co_op_premium_file.benefit_id,\n"
                + "    co_op_premium_file.policy_number,\n"
                + "    co_op_premium_file.province,\n"
                + "    co_op_premium_file.premium_rate,\n"
                + "    co_op_premium_file.administration_rate,\n"
                + "    co_op_premium_file.commission_rate,\n"
                + "    co_op_premium_file.employee_count,\n"
                + "    co_op_premium_file.volume,\n"
                + "    co_op_premium_file.premium,\n"
                + "    co_op_premium_file.pst,\n"
                + "    co_op_premium_file.retroactive_premium,\n"
                + "    co_op_premium_file.retroactive_pst,\n"
                + "    co_op_premium_file.gross_premium,\n"
                + "    co_op_premium_file.taxed_gross_premium,\n"
                + "    co_op_premium_file.administration_amount,\n"
                + "    co_op_premium_file.commission_amount,\n"
                + "    co_op_premium_file.net_premium,\n"
                + "    co_op_premium_file.taxed_net_premium,\n"
                + "    co_op_premium_file.gst,\n"
                + "    group_summary.employer,\n"
                + "    group_summary.bill_division,\n"
                + "    CASE\n"
                + "        WHEN policy_number LIKE '%-%' THEN SUBSTRING( policy_number, 1, POSITION( '-' IN policy_number )- 1 )\n"
                + "        ELSE policy_number\n"
                + "    END AS policy_series,\n"
                + "    CASE\n"
                + "        WHEN policy_number LIKE '%-%-%' THEN SUBSTRING( SUBSTRING( policy_number, POSITION( '-' IN policy_number )+ 1, 15 ), 1, POSITION( '-' IN SUBSTRING( policy_number, POSITION( '-' IN policy_number )+ 1, 15 ))- 1 )\n"
                + "        WHEN co_op_premium_file.policy_number ~ '/*-*.' THEN SUBSTRING( SUBSTRING( co_op_premium_file.policy_number, POSITION( '-' IN co_op_premium_file.policy_number )+ 1, 15 ) FROM '/*[0-9]*' )\n"
                + "        WHEN policy_number LIKE '%-%' THEN SUBSTRING( policy_number, POSITION( '-' IN policy_number )+ 1, 15 )\n"
                + "        ELSE NULL\n"
                + "    END AS policy_account,\n"
                + "    co_op_premium_file.underwriter_id\n"
                + "FROM\n"
                + "    group_summary,\n"
                + "    settings,\n"
                + "    co_op_premium_file\n"
                + "LEFT JOIN co_op_premium_file_affiliates ON\n"
                + "    (\n"
                + "        co_op_premium_file.group_id = co_op_premium_file_affiliates.affiliate_id\n"
                + "    )\n"
                + "LEFT JOIN(\n"
                + "        SELECT\n"
                + "            inv.group_id,\n"
                + "            MIN( inv.month_id ) AS first_billing_month\n"
                + "        FROM\n"
                + "            invoice inv\n"
                + "        GROUP BY\n"
                + "            inv.group_id\n"
                + "    ) AS inv ON\n"
                + "    co_op_premium_file.group_id = inv.group_id\n"
                + "WHERE\n"
                + "    co_op_premium_file.group_id = group_summary.group_id\n"
                + "    AND(\n"
                + "        co_op_premium_file.month_id = settings.dateval\n"
                + "    )\n"
                + "    AND(\n"
                + "        affiliate_id IS NULL\n"
//            + "        AND var_id = 'CURRENT_DATE'\n"
//            + "        AND co_op_premium_file.underwriter_id = 93047\n"
                + "    )\n"
                + "    AND inv.first_billing_month < co_op_premium_file.month_id";
        jdbcTemplate.query(NA_MONTHLY_PREMIUM_QUERY,
                resultSet ->
                {
                    NAMonthlyPremiumsGroup naGroup = new NAMonthlyPremiumsGroup();

                    naGroup.setPolicySeries( resultSet.getString( "policy_series" ) );
                    naGroup.setPolicyAccount( resultSet.getString( "policy_account" ) );
                    naGroup.setEmployer( resultSet.getString( "employer" ) );
                    naGroup.setMonth( resultSet.getDate( "month_id" ) );
                    naGroup.setBenefitId( resultSet.getInt( "benefit_id" ) );
                    naGroup.setPremium( resultSet.getFloat( "premium") );
                    naGroup.setPremiumRate( resultSet.getFloat( "premium_rate"));
                    naGroup.setProvince( resultSet.getString( "province"));
                    naGroup.setEmployeeCount( resultSet.getInt( "employee_count"));
                    naGroup.setVolume( resultSet.getInt( "volume"));
                    naGroup.setPst( resultSet.getFloat( "pst"));
                    naGroup.setRetroactivePremium( resultSet.getFloat( "retroactive_premium"));
                    naGroup.setRetroactivePst( resultSet.getFloat( "retroactive_pst"));
                    naGroup.setGrossPremium( resultSet.getFloat( "gross_premium"));
                    naGroup.setAdministrationAmount( resultSet.getFloat( "administration_amount"));
                    naGroup.setCommissionAmount( resultSet.getFloat( "commission_amount"));
                    naGroup.setNetPremium( resultSet.getFloat( "net_premium"));
                    naGroup.setAdministrationRate( resultSet.getFloat( "administration_rate"));
                    naGroup.setCommissionRate( resultSet.getFloat( "commission_rate"));
                    
                    naMonthlyGroup.add( naGroup );
                }
        );     
        
        return naMonthlyGroup;
    }
    
    @Override
    public String getBenefitName(int benefitId)
    {
        StringBuilder sb = new StringBuilder();
        String GET_BENEFIT_NAME = "SELECT short_benefit_name FROM benefit_types WHERE id = ?";
        jdbcTemplate.query(GET_BENEFIT_NAME,
                ps ->
                {
                    ps.setInt( 1, benefitId);
                },
                resultSet ->
                {
                    sb.append( resultSet.getString( "short_benefit_name"));
                }
                );
        return sb.toString();
    }

}
