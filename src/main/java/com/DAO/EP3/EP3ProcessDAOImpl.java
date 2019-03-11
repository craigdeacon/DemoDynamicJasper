/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO.EP3;

import com.Component.CS.EP3.container.EP3ProcessEntry;
import com.utilities.DatabaseUtilities;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author craig.deacon
 */
@Component
public class EP3ProcessDAOImpl implements EP3ProcessDAO
{
     private JdbcTemplate jdbcTemplate;
     private static final Logger LOGGER = Logger.getLogger(EP3ProcessDAO.class.getName());

    @Autowired
    public EP3ProcessDAOImpl( DataSource dataSource )
    {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    @Override
    public ArrayList<EP3ProcessEntry> getGroupList( Date invoiceDate )
    {
        ArrayList<EP3ProcessEntry> ep3List = new ArrayList();
        
        String startDate="2019-01-01";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try
        {
            date = sdf1.parse( startDate );
        }
        catch (ParseException ex)
        {
            Logger.getLogger( EP3ProcessDAOImpl.class.getName() ).log( Level.SEVERE, null, ex );
        }
        java.sql.Date referenceDate = new java.sql.Date( date.getTime() );


        String GROUP_LIST_QRY_STR = "SELECT b.policy_number AS green_shield_division," +
                "               gs.group_id," +
                "               ( gs.employer + CASE WHEN gs.bill_division IS NULL OR gs.bill_division = '' THEN '' ELSE ', ' " +
                "               + gs.bill_division END ) AS billing_division_name, " +
                "               b.next_renew AS renewal_month, " +
                "               b.effective AS initial_effective, " +
                "               slt.description AS pooling_threshold, " +
                "               '' AS pooling_basis, " +
                "               '' AS exclusions, " +
                "               gs.termination AS termination_date, " +
                "               gs.termination_entered_date AS date_entered " +
                "        FROM group_summary gs" +
                "               LEFT JOIN ben06 b " +
                "                      ON gs.group_id = b.group_id " +
                "               LEFT JOIN stop_loss_types slt " +
                "                      ON b.stop_loss_pooling = slt.id " +
                "        WHERE b.option_id = 0 " +
                "               AND ( b.special_plan_type = FALSE " +
                "                  OR b.special_plan_type IS NULL ) " +
                "               AND b.underwriter_id = 93026 " +
                "               AND ( b.drug_max = 0 " +
                "                      OR b.drug_max IS NULL ) " +
                "               AND ( b.travel_only = FALSE " +
                "                      OR b.travel_only IS NULL ) " +
                "               AND ( b.health_aso = FALSE " +
                "                      OR b.health_aso IS NULL ) " +
                "               AND (( gs.termination_entered_date BETWEEN ? AND ?)) " +
                "        ORDER BY gs.termination_entered_date, " +
                "                  gs.employer";
        jdbcTemplate.query(GROUP_LIST_QRY_STR,
                           ps ->
                           {
                               ps.setDate(1, referenceDate); 
                               ps.setDate(2, invoiceDate);
                           },
                           rs ->
                           {
                               EP3ProcessEntry entry = new EP3ProcessEntry();
                               entry.setGroupId(rs.getInt("group_id"));
                               entry.setBillingDivisionName(rs.getString("billing_division_name"));
                               entry.setDateEntered(rs.getDate("date_entered"));
                               entry.setExclusion(rs.getString("exclusions"));
                               entry.setGreenShieldDivision(rs.getString("green_shield_division"));
                               entry.setInitialEffective(rs.getDate("initial_effective"));
                               entry.setPoolingBasis(rs.getString("pooling_basis"));
                               entry.setPoolingThreshold(rs.getString("pooling_threshold"));
                               entry.setRenewalMonth(rs.getDate("renewal_month"));
                               entry.setTerminationDate(rs.getDate("termination_date"));
                               ep3List.add(entry);

                           });
        return ep3List;
    }
    
    public boolean getGroupHasActiveAffiliation( String policyNumber, int groupId )
    {
        LOGGER.info( "getGroupHasActiveAffiliation Policy Number: " + policyNumber + " GroupId: " + groupId );
        boolean groupHasActiveAffiliate = false;
        try
        {
            String IS_GROUP_ACTIVE_AFFILIATED = "SELECT " +
                    "  CASE WHEN count(1) != 0 THEN TRUE ELSE FALSE END AS active_affiliated_group_exists " +
                    "FROM " +
                    "  group_summary " +
                    "WHERE " +
                    "  group_id IN( " +
                    "    SELECT " +
                    "      group_id " +
                    "    FROM " +
                    "      ben06 " +
                    "    WHERE " +
                    "      policy_number LIKE ? " +
                    "      AND group_id != ? " +
                    "  ) " +
                    "  AND termination IS NULL AND status = 'A'";
            groupHasActiveAffiliate = jdbcTemplate.query(IS_GROUP_ACTIVE_AFFILIATED,
                                      ps ->
                                      {
                                          ps.setString(1, DatabaseUtilities.getFilterWithWildcards( policyNumber ));
                                          ps.setInt(2, groupId);
                                      },
                                      rs ->
                                      {
                                          boolean activeAffiliatedGroup = false;
                                          while(rs.next())
                                          {
                                              activeAffiliatedGroup =  rs.getBoolean("active_affiliated_group_exists");
                                          }
                                          return activeAffiliatedGroup;
                                      });
        }
        catch(DataAccessException e)
        {
            LOGGER.log(Level.SEVERE, "getGroupHasActiveAffiliation Failed(" + policyNumber + ", " + groupId + ")", e);
        }

        return groupHasActiveAffiliate;
    }
}
