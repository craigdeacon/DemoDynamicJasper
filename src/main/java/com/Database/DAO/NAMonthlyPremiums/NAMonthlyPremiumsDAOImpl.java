/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Database.DAO.NAMonthlyPremiums;



import com.Report.Component.CS.NAMonthlyPremiums.container.NAMonthlyPremiumsGroup;
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

    JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(NAMonthlyPremiumsDAO.class.getName() );

    private final String NA_MONTHLY_PREMIUM_QUERY = "SELECT "
            + "    co_op_premium_file.id, "
            + "    co_op_premium_file.month_id, "
            + "    co_op_premium_file.group_id, "
            + "    co_op_premium_file.benefit_id, "
            + "    co_op_premium_file.policy_number, "
            + "    co_op_premium_file.province, "
            + "    co_op_premium_file.premium_rate, "
            + "    co_op_premium_file.administration_rate, "
            + "    co_op_premium_file.commission_rate, "
            + "    co_op_premium_file.employee_count, "
            + "    co_op_premium_file.volume, "
            + "    co_op_premium_file.premium, "
            + "    co_op_premium_file.pst, "
            + "    co_op_premium_file.retroactive_premium, "
            + "    co_op_premium_file.retroactive_pst, "
            + "    co_op_premium_file.gross_premium, "
            + "    co_op_premium_file.taxed_gross_premium, "
            + "    co_op_premium_file.administration_amount, "
            + "    co_op_premium_file.commission_amount, "
            + "    co_op_premium_file.net_premium, "
            + "    co_op_premium_file.taxed_net_premium, "
            + "    co_op_premium_file.gst, "
            + "    group_summary.employer, "
            + "    group_summary.bill_division, "
            + "    CASE "
            + "        WHEN policy_number LIKE '%-%' THEN SUBSTRING( policy_number, 1, POSITION( '-' IN policy_number )- 1 ) "
            + "        ELSE policy_number "
            + "    END AS policy_series, "
            + "    CASE "
            + "        WHEN policy_number LIKE '%-%-%' THEN SUBSTRING( SUBSTRING( policy_number, POSITION( '-' IN policy_number )+ 1, 15 ), 1, POSITION( '-' IN SUBSTRING( policy_number, POSITION( '-' IN policy_number )+ 1, 15 ))- 1 ) "
            + "        WHEN co_op_premium_file.policy_number ~ '/*-*.' THEN SUBSTRING( SUBSTRING( co_op_premium_file.policy_number, POSITION( '-' IN co_op_premium_file.policy_number )+ 1, 15 ) FROM '/*[0-9]*' ) "
            + "        WHEN policy_number LIKE '%-%' THEN SUBSTRING( policy_number, POSITION( '-' IN policy_number )+ 1, 15 ) "
            + "        ELSE NULL "
            + "    END AS policy_account, "
            + "    co_op_premium_file.underwriter_id "
            + "FROM "
            + "    group_summary, "
            + "    settings, "
            + "    co_op_premium_file "
            + "LEFT JOIN co_op_premium_file_affiliates ON "
            + "    ( "
            + "        co_op_premium_file.group_id = co_op_premium_file_affiliates.affiliate_id "
            + "    ) "
            + "LEFT JOIN( "
            + "        SELECT "
            + "            inv.group_id, "
            + "            MIN( inv.month_id ) AS first_billing_month "
            + "        FROM "
            + "            invoice inv "
            + "        GROUP BY "
            + "            inv.group_id "
            + "    ) AS inv ON "
            + "    co_op_premium_file.group_id = inv.group_id "
            + "WHERE "
            + "    co_op_premium_file.group_id = group_summary.group_id "
            + "    AND( "
            + "        co_op_premium_file.month_id = settings.dateval "
            + "    ) "
            + "    AND( "
            + "        affiliate_id IS NULL "
//            + "        AND var_id = 'CURRENT_DATE' "
//            + "        AND co_op_premium_file.underwriter_id = 93047 "
            + "    ) "
            + "    AND inv.first_billing_month < co_op_premium_file.month_id";
    
    
//    private final String GET_BENEFIT_NAME = "SELECT short_benefit_name FROM benefit_types WHERE id = ?";
    
    @Autowired
    public NAMonthlyPremiumsDAOImpl( DataSource dataSource )
    {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    @Override
    public ArrayList<NAMonthlyPremiumsGroup> getNAMonthlyPremiumsGroup()
    {
        ArrayList<NAMonthlyPremiumsGroup> naMonthlyGroup = new ArrayList<>();
        
        jdbcTemplate.query(NA_MONTHLY_PREMIUM_QUERY,
                resultSet ->
                {
                    NAMonthlyPremiumsGroup naGroup = new NAMonthlyPremiumsGroup();

                    naGroup.setPolicySeries( resultSet.getString( "policy_series" ) );
                    naGroup.setPolicyAccount( resultSet.getString( "policy_account" ) );
                    naGroup.setEmployer( resultSet.getString( "employer" ) );
                    naGroup.setMonth( resultSet.getDate( "month_id" ) );
                    naGroup.setBenefitId( resultSet.getInt( "benefit_id" ) );
                    naGroup.setPremium( resultSet.getDouble( "premium") );
                    naGroup.setPremiumRate( resultSet.getDouble( "premium_rate"));
                    naGroup.setProvince( resultSet.getString( "province"));
                    naGroup.setEmployeeCount( resultSet.getInt( "employee_count"));
                    naGroup.setVolume( resultSet.getInt( "volume"));
                    naGroup.setPst( resultSet.getDouble( "pst"));
                    naGroup.setRetroactivePremium( resultSet.getDouble( "retroactive_premium"));
                    naGroup.setRetroactivePst( resultSet.getDouble( "retroactive_pst"));
                    naGroup.setGrossPremium( resultSet.getDouble( "gross_premium"));
                    naGroup.setAdministrationAmount( resultSet.getDouble( "administration_amount"));
                    naGroup.setCommissionAmount( resultSet.getDouble( "commission_amount"));
                    naGroup.setNetPremium( resultSet.getDouble( "net_premium"));
                    naGroup.setAdministrationRate( resultSet.getDouble( "administration_rate"));
                    naGroup.setCommissionRate( resultSet.getDouble( "commission_rate"));
                    naGroup.setGst( resultSet.getDouble( "gst"));
                    
                    naMonthlyGroup.add( naGroup );
                }
        );     
        
        return naMonthlyGroup;
    }
    
}
