/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Database.DAO.GstHstByRevenueTypeDAO;

import com.Report.Component.ME.GstHstByRevenueTypeReport.container.GstHstByRevenueTypeGroup;
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
public class GstHstByRevenueTypeDAOImpl implements GstHstByRevenueTypeDAO
{

    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(GstHstByRevenueTypeDAO.class.getName());
    
    private final String GSTHST_QUERY = "SELECT " +
"	COALESCE( gs.employer || ', ' || gs.bill_division, gs.employer ) AS groupName, " +
"	i.group_id, " +
"	i.month_id, " +
"	gs.province, " +
"	COALESCE( hsa.taxable_premium, 0 )::DOUBLE PRECISION AS hsa_premium, " +
"	COALESCE( hsa_gst, 0 ) AS hsa_gst, " +
"	COALESCE( aso.taxable_premium, 0 )::DOUBLE PRECISION AS aso_premium, " +
"	COALESCE( aso_gst, 0 )::DOUBLE PRECISION AS aso_gst, " +
"	( " +
"		COALESCE( other_gst.taxable_premium, 0 ) + COALESCE( other_hst.taxable_premium, 0 ) " +
"	) AS other_premium, " +
"	( " +
"		COALESCE( other_gst, 0 ) + COALESCE( other_hst, 0 ) " +
"	)::DOUBLE PRECISION AS other_gst, " +
"	( " +
"		COALESCE( aso_gst, 0 ) +  " +
"		COALESCE( other_gst, 0 ) +  " +
"		COALESCE( other_hst, 0 ) +  " +
"		COALESCE( hsa_gst, 0 ) " +
"	)::DOUBLE PRECISION AS total_gst " +
"FROM " +
"	( " +
"		( " +
"			invoice i " +
"		LEFT JOIN( " +
"				SELECT " +
"					group_id, " +
"					month_id, " +
"					SUM( amount_subject_to_tax ) AS taxable_premium, " +
"					SUM( total_amount )::DOUBLE PRECISION AS hsa_gst " +
"				FROM " +
"					invoice_aso_fees_and_taxes iaft " +
"				INNER JOIN lookup_fees_and_taxes lfat ON " +
"					iaft.fee_and_tax_id = lfat.id " +
"				WHERE " +
"					fee_tax_type IN( 'GST', 'HST' ) " +
"					AND benefit_number = 11 " +
"				GROUP BY " +
"					group_id, " +
"					month_id " +
"			) hsa ON " +
"			i.group_id = hsa.group_id " +
"			AND i.month_id = hsa.month_id " +
"		) " +
"	LEFT JOIN( " +
"			SELECT " +
"				group_id, " +
"				month_id, " +
"				SUM( amount_subject_to_tax ) AS taxable_premium, " +
"				SUM( total_amount ) AS aso_gst " +
"			FROM " +
"				invoice_aso_fees_and_taxes iaft " +
"			INNER JOIN lookup_fees_and_taxes lfat ON " +
"				iaft.fee_and_tax_id = lfat.id " +
"			WHERE " +
"				fee_tax_type IN( 'GST', 'HST' ) " +
"				AND benefit_number IN( 6, 7 ) " +
"			GROUP BY " +
"				group_id, " +
"				month_id " +
"		) aso ON " +
"		i.group_id = aso.group_id " +
"		AND i.month_id = aso.month_id " +
"	) " +
"LEFT JOIN( " +
"		SELECT " +
"			group_id, " +
"			month_id, " +
"			NULL::DOUBLE PRECISION AS taxable_premium, " +
"			SUM( other_amount ) AS other_gst " +
"		FROM " +
"			invoice_detail3 id3 " +
"		WHERE " +
"			other_amount <> 0 " +
"			AND other_desc LIKE 'GST (%' " +
"		GROUP BY " +
"			group_id, " +
"			month_id " +
"	) other_gst ON " +
"	i.group_id = other_gst.group_id " +
"	AND i.month_id = other_gst.month_id " +
"LEFT JOIN( " +
"		SELECT " +
"			group_id, " +
"			month_id, " +
"			SUM( sub2hst ) AS taxable_premium, " +
"			SUM( hst ) AS other_hst " +
"		FROM " +
"			invoice_hst " +
"		WHERE " +
"			hst <> 0 " +
"			AND source_id = 0 " +
"		GROUP BY " +
"			group_id, " +
"			month_id " +
"	) other_hst ON " +
"	i.group_id = other_hst.group_id " +
"	AND i.month_id = other_hst.month_id " +
"INNER JOIN group_summary gs ON " +
"	i.group_id = gs.group_id " +
"WHERE " +
"	( COALESCE( aso_gst, 0 ) + COALESCE( other_gst, 0 )+ COALESCE( other_hst, 0 ) + COALESCE( hsa_gst, 0 ) )<> 0 AND i.month_id = (SELECT dateval FROM settings WHERE var_id = 'CURRENT_MONTH')";
    
    
    
    @Autowired
    public GstHstByRevenueTypeDAOImpl( DataSource dataSource )
    {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }
    
    
    @Override
    public ArrayList<GstHstByRevenueTypeGroup> getGstHstByRevenueTypeList()
    {
        ArrayList<GstHstByRevenueTypeGroup> gstHstByRevenueTypeList = new ArrayList<>();
        
        jdbcTemplate.query( GSTHST_QUERY,
                resultSet ->
                {
                    GstHstByRevenueTypeGroup group = new GstHstByRevenueTypeGroup();
                    
                    group.setGroupName( resultSet.getString( "groupname"));
                    group.setProvince( resultSet.getString( "province"));
                    group.setHsaPremium( resultSet.getDouble( "hsa_premium"));
                    group.setHsaGst( resultSet.getDouble( "hsa_gst"));
                    group.setAsoPremium( resultSet.getDouble( "aso_premium"));
                    group.setAsoGst(resultSet.getDouble( "aso_gst"));
                    group.setOtherPremium( resultSet.getDouble( "other_premium"));
                    group.setOtherGst( resultSet.getDouble( "other_gst"));
                    group.setTotalGst( resultSet.getDouble( "total_gst"));
                    
                    gstHstByRevenueTypeList.add( group );
                    
                }
        );
        
        return gstHstByRevenueTypeList;
    }
    
}
