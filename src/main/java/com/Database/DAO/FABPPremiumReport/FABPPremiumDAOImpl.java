/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Database.DAO.FABPPremiumReport;

import com.Report.Component.ME.FABPPremiumReport.container.FABPPremiumGroup;
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
public class FABPPremiumDAOImpl implements FABPPremiumDAO
{

    JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger( FABPPremiumDAO.class.getName() );

    private final String FABP_PREMIUM_QUERY = "SELECT "
            + "	p.group_id, "
            + "	p.underwriter_id, "
            + "	uw.under_name, "
            + "	SUM( p.tcm_prem ) AS gross_prem, "
            + "	SUM( p.admin_allow )::DOUBLE PRECISION AS admin_allow, "
            + "	SUM( p.commission )::DOUBLE PRECISION AS commission, "
            + "	SUM( p.net_prem )::DOUBLE PRECISION AS net_prem, "
            + "	SUM( COALESCE( pfpq.pst, 0 ))::DOUBLE PRECISION AS qc_pst, "
            + "	SUM( COALESCE( pfpo.pst, 0 ))::DOUBLE PRECISION AS on_pst, "
            + "	SUM( COALESCE( pfps.pst, 0 ))::DOUBLE PRECISION AS sk_pst, "
            + "	( "
            + "		CASE "
            + "			WHEN gs.bill_division IS NULL OR gs.bill_division = '' THEN gs.employer "
            + "			ELSE gs.employer || ' - ' || gs.bill_division "
            + "		END "
            + "	) AS employer, "
            + "	getdate() AS invoice_date, "
            + "	p.month_id AS premium_date "
            + "FROM "
            + "	premiums AS p "
            + "INNER JOIN group_summary AS gs ON "
            + "	p.group_id = gs.group_id "
            + "INNER JOIN underwriter AS uw ON "
            + "	p.underwriter_id = uw.underwriter_id "
            + "LEFT OUTER JOIN pstforPremiumreport AS pfpq ON "
            + "	p.group_id = pfpq.group_id "
            + "	AND p.policy_number = pfpq.policy_number "
            + "	AND p.underwriter_id = pfpq.underwriter_id "
            + "	AND pfpq.province = 'PQ' "
            + "	AND p.month_id = pfpq.month_id "
            + "LEFT OUTER JOIN pstforPremiumreport AS pfpo ON "
            + "	p.group_id = pfpo.group_id "
            + "	AND p.policy_number = pfpo.policy_number "
            + "	AND p.underwriter_id = pfpo.underwriter_id "
            + "	AND pfpo.province = 'ON' "
            + "	AND p.month_id = pfpo.month_id "
            + "LEFT OUTER JOIN pstforPremiumreport AS pfps ON "
            + "	p.group_id = pfps.group_id "
            + "	AND p.policy_number = pfps.policy_number "
            + "	AND p.underwriter_id = pfps.underwriter_id "
            + "	AND pfps.province = 'SK' "
            + "	AND p.month_id = pfps.month_id "
            + "WHERE "
//                        + "	p.month_id = 'January, 1, 2019' AND "
//            + "( SELECT dateval FROM settings WHERE var_id = 'CURRENT_MONTH' )"
            + "	 p.group_id IN ( SELECT group_id FROM group_summary WHERE assoc_id = 92031 ) "
            + "GROUP BY "
            + "	p.group_id, "
            + "	p.underwriter_id, "
            + "	uw.under_name, "
            + "	gs.employer, "
            + "	gs.bill_division, "
            + "	p.month_id "
            + "ORDER BY"
            + "	gs.employer,"
            + "	uw.under_name";

    @Autowired
    public FABPPremiumDAOImpl( DataSource dataSource )
    {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    @Override
    public ArrayList<FABPPremiumGroup> getFABPPremiumGroupList()
    {
        ArrayList<FABPPremiumGroup> fabpPremiumGroupList = new ArrayList<>();

        jdbcTemplate.query( FABP_PREMIUM_QUERY,
                resultSet ->
        {
            FABPPremiumGroup group = new FABPPremiumGroup();

            group.setGroupName( resultSet.getString( "employer" ) );
            group.setUnderwriter( resultSet.getString( "under_name" ) );
            group.setGrossPremiums( resultSet.getDouble( "gross_prem" ) );
            group.setCommission( resultSet.getDouble( "commission" ) );
            group.setAdmin( resultSet.getDouble( "admin_allow" ) );
            group.setNetPremiums( resultSet.getDouble( "net_prem" ) );
            group.setOnPst( resultSet.getDouble( "on_pst" ) );
            group.setQcPst( resultSet.getDouble( "qc_pst" ) );
            group.setSkPst( resultSet.getDouble( "sk_pst" ) );

            fabpPremiumGroupList.add( group );
        }
        );

        return fabpPremiumGroupList;
    }

}
