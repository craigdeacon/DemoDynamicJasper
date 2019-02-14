/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO.ProvincialSalesTax;

import com.Component.ME.ProvincialSalesTaxReport.container.ProvincialGroup;
import com.Component.ME.RevenueReportByProduct.container.Group;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 *
 * @author craig.deacon
 */
@Component
public class ProvincialSalesTaxDAOImpl implements ProvincialSalesTaxDAO
{

    JdbcTemplate jdbcTemplate;

    private final String SQL_GET_PROVINCIAL_GROUPS = "SELECT\n"
            + "	settings.strval,\n"
            + "	settings.var_id,\n"
            + "	pst_report.month_id,\n"
            + "	pst_report.group_id,\n"
            + "	pst_report.employer,\n"
            + "	pst_report.policy_number,\n"
            + "	pst_report.ontario_tax_premium,\n"
            + "	pst_report.ontario_pst,\n"
            + "	pst_report.quebec_tax_premium,\n"
            + "	pst_report.quebec_pst,\n"
            + "	pst_report.mb_tax_premium,\n"
            + "	pst_report.mb_pst,\n"
            + "	pst_report.sk_tax_premium,\n"
            + "	pst_report.sk_pst,\n"
            + "	pst_report.underwriter_id,\n"
            + "	pst_report.under_name\n"
            + "FROM\n"
            + "	settings,\n"
            + "	(SELECT qry_sub_pst.month_id,\n"
            + "    invoice.group_id,\n"
            + "    invoice.employer,\n"
            + "    qry_sub_pst.policy_number,\n"
            + "    qry_sub_pst.ontario_tax_premium,\n"
            + "    qry_sub_pst.ontario_pst,\n"
            + "    qry_sub_pst.quebec_tax_premium,\n"
            + "    qry_sub_pst.quebec_pst,\n"
            + "    qry_sub_pst.mb_tax_premium,\n"
            + "    qry_sub_pst.mb_pst,\n"
            + "    qry_sub_pst.sk_tax_premium,\n"
            + "    qry_sub_pst.sk_pst,\n"
            + "    underwriter.underwriter_id,\n"
            + "    underwriter.under_name\n"
            + "   FROM ( SELECT invoice_detail5.group_id,\n"
            + "            invoice_detail5.underwriter_id,\n"
            + "            invoice_detail5.policy_number,\n"
            + "            invoice_detail5.month_id,\n"
            + "            sum(\n"
            + "                CASE invoice_detail5.province\n"
            + "                    WHEN 'ON'::bpchar THEN invoice_detail5.sub2pst::float\n"
            + "                    ELSE 0\n"
            + "                END) AS ontario_tax_premium,\n"
            + "            sum(\n"
            + "                CASE invoice_detail5.province\n"
            + "                    WHEN 'QC'::bpchar THEN invoice_detail5.sub2pst::float\n"
            + "                    ELSE 0\n"
            + "                END) AS quebec_tax_premium,\n"
            + "            sum(\n"
            + "                CASE invoice_detail5.province\n"
            + "                    WHEN 'MB'::bpchar THEN invoice_detail5.sub2pst::float\n"
            + "                    ELSE 0\n"
            + "                END) AS mb_tax_premium,\n"
            + "            sum(\n"
            + "                CASE invoice_detail5.province\n"
            + "                    WHEN 'SK'::bpchar THEN invoice_detail5.sub2pst::float\n"
            + "                    ELSE 0\n"
            + "                END) AS sk_tax_premium,\n"
            + "            sum(\n"
            + "                CASE invoice_detail5.province\n"
            + "                    WHEN 'ON'::bpchar THEN invoice_detail5.pst::float\n"
            + "                    ELSE 0\n"
            + "                END) AS ontario_pst,\n"
            + "            sum(\n"
            + "                CASE invoice_detail5.province\n"
            + "                    WHEN 'QC'::bpchar THEN invoice_detail5.pst::float\n"
            + "                    ELSE 0\n"
            + "                END) AS quebec_pst,\n"
            + "            sum(\n"
            + "                CASE invoice_detail5.province\n"
            + "                    WHEN 'MB'::bpchar THEN invoice_detail5.pst::float\n"
            + "                    ELSE 0\n"
            + "                END) AS mb_pst,\n"
            + "            sum(\n"
            + "                CASE invoice_detail5.province\n"
            + "                    WHEN 'SK'::bpchar THEN invoice_detail5.pst::float\n"
            + "                    ELSE 0\n"
            + "                END) AS sk_pst\n"
            + "           FROM invoice_detail5\n"
            + "          WHERE invoice_detail5.month_id = 'December, 1, 2018'\n"
            + "          GROUP BY invoice_detail5.group_id, invoice_detail5.underwriter_id, invoice_detail5.policy_number, invoice_detail5.month_id) qry_sub_pst\n"
            + "     JOIN invoice ON qry_sub_pst.month_id = invoice.month_id AND qry_sub_pst.group_id = invoice.group_id\n"
            + "     JOIN underwriter ON qry_sub_pst.underwriter_id = underwriter.underwriter_id\n"
            + "  WHERE qry_sub_pst.month_id = 'December, 1, 2018' AND (qry_sub_pst.ontario_tax_premium IS NOT NULL OR qry_sub_pst.quebec_tax_premium IS NOT NULL OR qry_sub_pst.mb_tax_premium IS NOT NULL OR qry_sub_pst.sk_tax_premium IS NOT NULL)) AS pst_report\n"
            + "WHERE\n"
            + "	(\n"
            + "		settings.var_id = 'COMPANY_NAME'\n"
            + "		AND(\n"
            + "			(\n"
            + "				pst_report.quebec_pst <> 0\n"
            + "				AND pst_report.quebec_tax_premium <> 0\n"
            + "			)\n"
            + "			OR(\n"
            + "				pst_report.ontario_pst <> 0\n"
            + "				AND pst_report.ontario_tax_premium <> 0\n"
            + "			)\n"
            + "			OR(\n"
            + "				pst_report.mb_pst <> 0\n"
            + "				AND pst_report.mb_tax_premium <> 0\n"
            + "			)\n"
            + "			OR(\n"
            + "				pst_report.sk_pst <> 0\n"
            + "				AND pst_report.sk_tax_premium <> 0\n"
            + "			)\n"
            + "		)\n"
            + "	)\n"
            + "ORDER BY\n"
            + "	employer";

    @Autowired
    public ProvincialSalesTaxDAOImpl( DataSource dataSource )
    {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    @Override
    public ArrayList<ProvincialGroup> getGroupsProvincialSalesTax()
    {
        ArrayList<ProvincialGroup> provincialGroupList = new ArrayList<>();

        jdbcTemplate.query( SQL_GET_PROVINCIAL_GROUPS,
                resultSet ->
        {
            ProvincialGroup group = new ProvincialGroup();
            group.setGroupName( resultSet.getString( "employer" ) );
            group.setPolicyNum( resultSet.getString( "policy_number" ) );
            group.setTaxPremiumOn(resultSet.getFloat( "ontario_tax_premium"));
            group.setSalesTaxOn( resultSet.getFloat( "ontario_pst"));
            group.setTaxPremiumQc( resultSet.getFloat( "quebec_tax_premium"));
            group.setSalesTaxQc( resultSet.getFloat( "quebec_pst") );
            group.setTaxPremiumMa(resultSet.getFloat( "mb_tax_premium"));
            group.setSalesTaxMa( resultSet.getFloat( "mb_pst") );
            group.setTaxPremiumSa(resultSet.getFloat( "sk_tax_premium"));
            group.setSalesTaxSa(resultSet.getFloat( "sk_pst") );
            group.setUnderwriterId( resultSet.getInt( "underwriter_id"));

            provincialGroupList.add( group );

        } );
     
        return provincialGroupList;
    }

}
