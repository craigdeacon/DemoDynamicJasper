package com.DAO.Employee;

import com.Component.CS.EmployeeCostBreakdown.container.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;

/**
 *
 * @author craig.deacon
 */
@Component
public class EmployeeDAOImpl implements EmployeeDAO
{

    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param dataSource
     */
    @Autowired
    public EmployeeDAOImpl(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     *
     * @param groupId
     * @return
     */
    public ArrayList<Employee> getEmployeeCostBreakdownList(Integer groupId)
    {
        ArrayList<Employee> listEmployee = new ArrayList<>();
        String SQL_GET_ALL = "SELECT " +
                " vw_Employee_CostBreakdown_CS.group_id, " +
                " vw_Employee_CostBreakdown_CS.province, " +
                " vw_Employee_CostBreakdown_CS.class_id, " +
                " vw_Employee_CostBreakdown_CS.prov_res, " +
                " vw_Employee_CostBreakdown_CS.sin, " +
                " vw_Employee_CostBreakdown_CS.bill_division, " +
                " vw_Employee_CostBreakdown_CS.hsa_adjudication, " +
                " vw_Employee_CostBreakdown_CS.eap_prem, " +
                " vw_Employee_CostBreakdown_CS.eap_vol, " +
                " vw_Employee_CostBreakdown_CS.am_prem, " +
                " vw_Employee_CostBreakdown_CS.am_vol, " +
                " vw_Employee_CostBreakdown_CS.dsai_prem, " +
                " vw_Employee_CostBreakdown_CS.travel_prem, " +
                " vw_Employee_CostBreakdown_CS.eapUW, " +
                " vw_Employee_CostBreakdown_CS.amUW, " +
                " vw_Employee_CostBreakdown_CS.dsaiUW, " +
                " vw_Employee_CostBreakdown_CS.travelUW, " +
                " vw_Employee_CostBreakdown_CS.AdminFee, " +
                " vw_Employee_CostBreakdown_CS.hsaAdminFee, " +
                " vw_Employee_CostBreakdown_CS.premiumTax, " +
                " vw_Employee_CostBreakdown_CS.gst, " +
                " vw_Employee_CostBreakdown_CS.employer, " +
                " vw_Employee_CostBreakdown_CS.emp_name, " +
                " vw_Employee_CostBreakdown_CS.life_volume, " +
                " vw_Employee_CostBreakdown_CS.add_volume, " +
                " vw_Employee_CostBreakdown_CS.dlife_volume, " +
                " vw_Employee_CostBreakdown_CS.std_volume, " +
                " vw_Employee_CostBreakdown_CS.ltd_volume, " +
                " vw_Employee_CostBreakdown_CS.ci_volume, " +
                " vw_Employee_CostBreakdown_CS.ci_dep_volume, " +
                " vw_Employee_CostBreakdown_CS.ci_dep_lives, " +
                " vw_Employee_CostBreakdown_CS.ehb_volume, " +
                " vw_Employee_CostBreakdown_CS.dental_volume, " +
                " vw_Employee_CostBreakdown_CS.hsa_volume, " +
                " vw_Employee_CostBreakdown_CS.life_prem, " +
                " vw_Employee_CostBreakdown_CS.add_prem, " +
                " vw_Employee_CostBreakdown_CS.dlife_prem, " +
                " vw_Employee_CostBreakdown_CS.std_prem, " +
                " vw_Employee_CostBreakdown_CS.ltd_prem, " +
                " vw_Employee_CostBreakdown_CS.gsi_prem, " +
                " vw_Employee_CostBreakdown_CS.ci_prem, " +
                " vw_Employee_CostBreakdown_CS.ci_dep_prem, " +
                " vw_Employee_CostBreakdown_CS.ehb_prem, " +
                " vw_Employee_CostBreakdown_CS.dental_prem, " +
                " vw_Employee_CostBreakdown_CS.hsa_prem, " +
                " vw_Employee_CostBreakdown_CS.employee_id, " +
                " vw_Employee_CostBreakdown_CS.ole_volume, " +
                " vw_Employee_CostBreakdown_CS.ole_premium, " +
                " vw_Employee_CostBreakdown_CS.ols_volume, " +
                " vw_Employee_CostBreakdown_CS.ols_premium, " +
                " vw_Employee_CostBreakdown_CS.oad_volume, " +
                " vw_Employee_CostBreakdown_CS.oad_premium, " +
                " vw_Employee_CostBreakdown_CS.status, " +
                " vw_Employee_CostBreakdown_CS.termination, " +
                " vw_Employee_CostBreakdown_CS.reinstate, " +
                " vw_Employee_CostBreakdown_CS.admin_pct, " +
                " vw_Employee_CostBreakdown_CS.admin_per_emp, " +
                " vw_Employee_CostBreakdown_CS.admin_min, " +
                " vw_Employee_CostBreakdown_CS.eh_special_plan_type, " +
                " vw_Employee_CostBreakdown_CS.den_special_plan_type, " +
                " vw_Employee_CostBreakdown_CS.hsa_admin, " +
                " vw_Employee_CostBreakdown_CS.hsa_comm, " +
                " vw_Employee_CostBreakdown_CS.adjudication_fee, " +
                " vw_Employee_CostBreakdown_CS.hsa_admin_pct, " +
                " vw_Employee_CostBreakdown_CS.hsa_comm_pct, " +
                " vw_Employee_CostBreakdown_CS.adjudication_pct, " +
                " vw_Employee_CostBreakdown_CS.emp_prov_hst_rate, " +
                " vw_Employee_CostBreakdown_CS.gst_rate, " +
                " vw_Employee_CostBreakdown_CS.prem_tax, " +
                " vw_Employee_CostBreakdown_CS.psa_volume, " +
                " vw_Employee_CostBreakdown_CS.psa_prem, " +
                " vw_Employee_CostBreakdown_CS.dsai_vol, " +
                " vw_Employee_CostBreakdown_CS.travel_vol, " +
                " vw_Employee_CostBreakdown_CS.empTotal, " +
                " vw_Employee_CostBreakdown_CS.dlife_volume_rpt, " +
                " vw_Employee_CostBreakdown_CS.ehb_volume_rpt, " +
                " vw_Employee_CostBreakdown_CS.dental_volume_rpt, " +
                " vw_Employee_CostBreakdown_CS.eap_vol_rpt, " +
                " vw_Employee_CostBreakdown_CS.hst, " +
                " vw_Employee_CostBreakdown_CS.ci_dep_volume_rpt, " +
                " vw_Employee_CostBreakdown_CS.dsai_volume_rpt, " +
                " vw_Employee_CostBreakdown_CS.am_volume_rpt, " +
                " ( " +
                "  CASE " +
                "   WHEN msp_prem = 0 THEN NULL " +
                "   ELSE msp_prem " +
                "  END " +
                " ) AS msp_prem, " +
                " msp_vol, " +
                " msp_volume_rpt, " +
                " admin_fee_prem, " +
                " admin_fee_vol, " +
                " admin_fee_volume_rpt, " +
                " vw_Employee_CostBreakdown_CS.drug_insurance_pooling_premium, " +
                " ( " +
                "  SELECT " +
                "   description " +
                "  FROM " +
                "   lookup " +
                "  WHERE " +
                "   lookup_id = vw_Employee_CostBreakdown_CS.drug_insurance_pooling_volume " +
                " ) AS drug_insurance_pooling_volume, " +
                " ( " +
                "  SELECT " +
                "   COALESCE(assoc_id,0) " +
                "  FROM " +
                "   group_summary " +
                "  WHERE " +
                "   group_id = vw_Employee_CostBreakdown_CS.group_id " +
                " ) assoc_id " +
                "FROM " +
                " ( " +
                "  SELECT " +
                "   *, " +
                "   ( " +
                "    CASE " +
                "     WHEN dlife_volume IS NOT NULL THEN( " +
                "      CASE " +
                "       WHEN dlife_volume = FALSE THEN 'No' " +
                "       ELSE 'Yes' " +
                "      END " +
                "     ) " +
                "     ELSE NULL " +
                "    END " +
                "   ) AS dlife_volume_rpt, " +
                "   ( " +
                "    SELECT " +
                "     description " +
                "    FROM " +
                "     lookup " +
                "    WHERE " +
                "     lookup_id = ehb_volume " +
                "   ) AS ehb_volume_rpt, " +
                "   ( " +
                "    SELECT " +
                "     description " +
                "    FROM " +
                "     lookup " +
                "    WHERE " +
                "     lookup_id = dental_volume " +
                "   ) AS dental_volume_rpt, " +
                "   ( " +
                "    SELECT " +
                "     description " +
                "    FROM " +
                "     lookup " +
                "    WHERE " +
                "     lookup_id = dsai_vol " +
                "   ) AS dsai_volume_rpt, " +
                "   ( " +
                "    CASE " +
                "     WHEN eap_vol IS NULL THEN '' " +
                "     ELSE( " +
                "      CASE " +
                "       WHEN eap_vol = 1 THEN 'Yes' " +
                "       ELSE 'No' " +
                "      END " +
                "     ) " +
                "    END " +
                "   ) AS eap_vol_rpt, " +
                "   ( " +
                "    CASE " +
                "     WHEN ci_dep_volume IS NULL THEN '' " +
                "     ELSE( " +
                "      CASE " +
                "       WHEN ci_dep_volume = 1 THEN 'Yes' " +
                "       ELSE 'No' " +
                "      END " +
                "     ) " +
                "    END " +
                "   ) AS ci_dep_volume_rpt, " +
                "   ( " +
                "    CASE " +
                "     WHEN am_vol = 1 THEN 'Yes' " +
                "     WHEN am_vol = 0 THEN 'No' " +
                "     ELSE NULL " +
                "    END " +
                "   ) AS am_volume_rpt, " +
                "   ( " +
                "    SELECT " +
                "     description " +
                "    FROM " +
                "     lookup " +
                "    WHERE " +
                "     lookup_id = msp_vol " +
                "   ) AS msp_volume_rpt, " +
                "   ( " +
                "    CASE " +
                "     WHEN admin_fee_vol = 1 THEN 'Yes' " +
                "     WHEN admin_fee_vol = 0 THEN 'No' " +
                "     ELSE NULL " +
                "    END " +
                "   ) AS admin_fee_volume_rpt, " +
                "   BankersRound(COALESCE(hsa_prem,0)*(adjudication_pct + hsa_admin_pct + hsa_comm_pct),2,NULL) AS hsaAdminFee, " +
                "   ( " +
                "    BankersRound(fn_tax_amount_generator(province,prov_res,3,8,getDate(),0,COALESCE(hsa_prem,0)),2,NULL) + " +
                "    BankersRound(fn_tax_amount_generator(province,prov_res,4,8,getDate(),0,(COALESCE(hsa_prem,0)* adjudication_pct)::NUMERIC),2,NULL) + " +
                "    BankersRound(fn_tax_amount_generator(province,prov_res,5,8,getDate(),0,(COALESCE(hsa_prem,0)* hsa_admin_pct)::NUMERIC),2,NULL) " +
                "   ) AS premiumTax, " +
                "   ( " +
                "    BankersRound(fn_tax_amount_generator(province,prov_res,4,6,getDate(),0,(COALESCE(hsa_prem,0)* adjudication_pct)::NUMERIC),2,NULL) + " +
                "    BankersRound(fn_tax_amount_generator(province,prov_res,5,6,getDate(),0,(COALESCE(hsa_prem,0)* hsa_admin_pct)::NUMERIC),2,NULL) + " +
                "    fn_tax_amount_generator(province,prov_res,2,6,getDate(),eapUW,eap_prem) + " +
                "    fn_tax_amount_generator(province,prov_res,2,6,getDate(),amUW,am_prem) + " +
                "    fn_tax_amount_generator(province,prov_res,2,6,getDate(),dsaiUW,dsai_prem) + " +
                "    fn_tax_amount_generator(province,prov_res,2,6,getDate(),travelUW,travel_prem) + " +
                "    fn_tax_amount_generator(province,prov_res,5,6,getDate(),0,adminFee) " +
                "   ) AS hst, " +
                "   ( " +
                "    BankersRound(fn_tax_amount_generator(province,prov_res,4,7,getDate(),0,(COALESCE(hsa_prem,0)* adjudication_pct)::NUMERIC),2,NULL) + " +
                "    BankersRound(fn_tax_amount_generator(province,prov_res,5,7,getDate(),0,(COALESCE(hsa_prem,0)* hsa_admin_pct)::NUMERIC),2,NULL) + " +
                "    fn_tax_amount_generator(province,prov_res,2,7,getDate(),eapUW,eap_prem) + " +
                "    fn_tax_amount_generator(province,prov_res,2,7,getDate(),amUW,am_prem) + " +
                "    fn_tax_amount_generator(province,prov_res,2,7,getDate(),dsaiUW,dsai_prem) + " +
                "    fn_tax_amount_generator(province,prov_res,2,7,getDate(),travelUW,travel_prem) + " +
                "    fn_tax_amount_generator(province,prov_res,5,7,getDate(),0,adminFee) " +
                "   ) AS gst " +
                "  FROM " +
                "   ( " +
                "    SELECT " +
                "     cost.*, " +
                "     empbv.eap_prem, " +
                "     empbv.eap_vol, " +
                "     empbv.am_prem, " +
                "     empbv.am_vol, " +
                "     empbv.dsai_prem, " +
                "     empbv.dsai_vol, " +
                "     empbv.travel_prem, " +
                "     empbv.travel_vol, " +
                "     empbv.msp_prem, " +
                "     empbv.msp_vol, " +
                "     empbv.admin_fee_prem, " +
                "     empbv.admin_fee_vol, " +
                "     employee.drug_insurance_pooling_premium, " +
                "     employee.drug_insurance_pooling_volume, " +
                "     COALESCE(ben012.underwriter_id,0) AS eapUW, " +
                "     COALESCE(ben013.underwriter_id,0) AS amUW, " +
                "     COALESCE(ben014.underwriter_id,0) AS dsaiUW, " +
                "     COALESCE(ben015.underwriter_id,0) AS travelUW, " +
                "     fn_Calculate_Admin_Fee( " +
                "      admin_min, " +
                "      admin_pct, " +
                "      COALESCE(cost.life_prem,0) + " +
                "      COALESCE(cost.add_prem,0) + " +
                "      COALESCE(cost.dlife_prem,0) + " +
                "      COALESCE(cost.std_prem,0) + " +
                "      COALESCE(cost.ltd_prem,0) + " +
                "      COALESCE(cost.ci_prem,0) + " +
                "      COALESCE(cost.ci_dep_prem,0) + " +
                "      COALESCE(cost.ehb_prem,0) + " +
                "      COALESCE(cost.dental_prem,0) + " +
                "      COALESCE(cost.hsa_prem,0) + " +
                "      COALESCE(eap_prem,0) + " +
                "      COALESCE(am_prem,0) + " +
                "      COALESCE(dsai_prem,0) + " +
                "      COALESCE(travel_prem,0) + " +
                "      COALESCE(cost.psa_prem,0) + " +
                "      COALESCE(cost.ole_premium,0) + " +
                "      COALESCE(cost.ols_premium,0) + " +
                "      COALESCE(cost.oad_premium,0) + " +
                "      COALESCE(msp_prem,0) + " +
                "      COALESCE(admin_fee_prem,0), " +
                "      admin_per_emp, " +
                "      1 " +
                "     ) AS AdminFee, " +
                "     COALESCE(cost.life_prem,0) + " +
                "     COALESCE(cost.add_prem,0) + " +
                "     COALESCE(cost.dlife_prem,0) + " +
                "     COALESCE(cost.std_prem,0) + " +
                "     COALESCE(cost.ltd_prem,0) + " +
                "     COALESCE(cost.ci_prem,0) + " +
                "     COALESCE(cost.ci_dep_prem,0) + " +
                "     COALESCE(cost.ehb_prem,0) + " +
                "     COALESCE(cost.dental_prem,0) + " +
                "     COALESCE(cost.hsa_prem,0) + " +
                "     COALESCE(eap_prem,0) + " +
                "     COALESCE(am_prem,0) + " +
                "     COALESCE(dsai_prem,0) + " +
                "     COALESCE(travel_prem,0) + " +
                "     COALESCE(cost.psa_prem,0) + " +
                "     COALESCE(cost.ole_premium,0) + " +
                "     COALESCE(cost.ols_premium,0) + " +
                "     COALESCE(cost.oad_premium,0) + " +
                "     COALESCE(msp_prem,0) + " +
                "     COALESCE(admin_fee_prem,0) + " +
                "     COALESCE(drug_insurance_pooling_premium,0) AS empTotal " +
                "    FROM " +
                "     vw_ad_CostSummary AS cost " +
                "    INNER JOIN employee ON " +
                "     cost.employee_id = employee.employee_id " +
                "    LEFT JOIN( " +
                "      SELECT " +
                "       employee_id, " +
                "       SUM(( CASE WHEN ben_num = 12 THEN benvar_prem ELSE NULL END )) AS eap_prem, " +
                "       SUM(( CASE WHEN ben_num = 12 THEN benvar_ivol ELSE NULL END )) AS eap_vol, " +
                "       SUM(( CASE WHEN ben_num = 13 THEN benvar_prem ELSE NULL END )) AS am_prem, " +
                "       SUM(( CASE WHEN ben_num = 13 THEN benvar_ivol ELSE NULL END )) AS am_vol, " +
                "       SUM(( CASE WHEN ben_num = 14 THEN benvar_prem ELSE NULL END )) AS dsai_prem, " +
                "       SUM(( CASE WHEN ben_num = 14 THEN benvar_ivol ELSE NULL END )) AS dsai_vol, " +
                "       SUM(( CASE WHEN ben_num = 15 THEN benvar_prem ELSE NULL END )) AS travel_prem, " +
                "       SUM(( CASE WHEN ben_num = 15 THEN benvar_ivol ELSE NULL END )) AS travel_vol, " +
                "       SUM(( CASE WHEN ben_num = 17 THEN benvar_prem ELSE NULL END )) AS msp_prem, " +
                "       SUM(( CASE WHEN ben_num = 17 THEN benvar_ivol ELSE NULL END )) AS msp_vol, " +
                "       SUM(( CASE WHEN ben_num = 18 THEN benvar_prem ELSE NULL END )) AS admin_fee_prem, " +
                "       SUM(( CASE WHEN ben_num = 18 THEN benvar_ivol ELSE NULL END )) AS admin_fee_vol " +
                "      FROM " +
                "       employee_benvar " +
                "      GROUP BY " +
                "       employee_id " +
                "     ) AS empbv ON " +
                "     cost.employee_id = empbv.employee_id " +
                "    LEFT JOIN( " +
                "      SELECT " +
                "       ben.group_id AS benGroupId, " +
                "       ben.class_id AS origClassId, " +
                "       benc.underwriter_id " +
                "      FROM " +
                "       benvar ben " +
                "      INNER JOIN benvar benc ON " +
                "       ben.group_id = benc.group_id " +
                "       AND ben.option_id = benc.option_id " +
                "       AND ben.ben_num = benc.ben_num " +
                "       AND ben.ben_num = 12 " +
                "       AND ben.option_id = 0 " +
                "       AND( " +
                "        ( " +
                "         ben.coverage = 2 " +
                "         AND ben.same_class_id = benc.class_id " +
                "        ) " +
                "        OR( " +
                "         ben.coverage <> 2 " +
                "         AND ben.class_id = benc.class_id " +
                "        ) " +
                "       ) " +
                "     ) AS ben012 ON " +
                "     ben012.benGroupId = cost.group_id " +
                "     AND ben012.origClassId = cost.class_id " +
                "    LEFT JOIN( " +
                "      SELECT " +
                "       ben.group_id AS benGroupId, " +
                "       ben.class_id AS origClassId, " +
                "       benc.underwriter_id " +
                "      FROM " +
                "       benvar ben " +
                "      INNER JOIN benvar benc ON " +
                "       ben.group_id = benc.group_id " +
                "       AND ben.option_id = benc.option_id " +
                "       AND ben.ben_num = benc.ben_num " +
                "       AND ben.ben_num = 13 " +
                "       AND ben.option_id = 0 " +
                "       AND( " +
                "        ( " +
                "         ben.coverage = 2 " +
                "         AND ben.same_class_id = benc.class_id " +
                "        ) " +
                "        OR( " +
                "         ben.coverage <> 2 " +
                "         AND ben.class_id = benc.class_id " +
                "        ) " +
                "       ) " +
                "     ) AS ben013 ON " +
                "     ben013.benGroupId = cost.group_id " +
                "     AND ben013.origClassId = cost.class_id " +
                "    LEFT JOIN( " +
                "      SELECT " +
                "       ben.group_id AS benGroupId, " +
                "       ben.class_id AS origClassId, " +
                "       benc.underwriter_id " +
                "      FROM " +
                "       benvar ben " +
                "      INNER JOIN benvar benc ON " +
                "       ben.group_id = benc.group_id " +
                "       AND ben.option_id = benc.option_id " +
                "       AND ben.ben_num = benc.ben_num " +
                "       AND ben.ben_num = 14 " +
                "       AND ben.option_id = 0 " +
                "       AND( " +
                "        ( " +
                "         ben.coverage = 2 " +
                "         AND ben.same_class_id = benc.class_id " +
                "        ) " +
                "        OR( " +
                "         ben.coverage <> 2 " +
                "         AND ben.class_id = benc.class_id " +
                "        ) " +
                "       ) " +
                "     ) AS ben014 ON " +
                "     ben014.benGroupId = cost.group_id " +
                "     AND ben014.origClassId = cost.class_id " +
                "    LEFT JOIN( " +
                "      SELECT " +
                "       ben.group_id AS benGroupId, " +
                "       ben.class_id AS origClassId, " +
                "       benc.underwriter_id " +
                "      FROM " +
                "       benvar ben " +
                "      INNER JOIN benvar benc ON " +
                "       ben.group_id = benc.group_id " +
                "       AND ben.option_id = benc.option_id " +
                "       AND ben.ben_num = benc.ben_num " +
                "       AND ben.ben_num = 15 " +
                "       AND ben.option_id = 0 " +
                "       AND( " +
                "        ( " +
                "         ben.coverage = 2 " +
                "         AND ben.same_class_id = benc.class_id " +
                "        ) " +
                "        OR( " +
                "         ben.coverage <> 2 " +
                "         AND ben.class_id = benc.class_id " +
                "        ) " +
                "       ) " +
                "     ) AS ben015 ON " +
                "     ben015.benGroupId = cost.group_id " +
                "     AND ben015.origClassId = cost.class_id " +
                "    LEFT JOIN( " +
                "      SELECT " +
                "       ben.group_id AS benGroupId, " +
                "       ben.class_id AS origClassId, " +
                "       benc.underwriter_id " +
                "      FROM " +
                "       benvar ben " +
                "      INNER JOIN benvar benc ON " +
                "       ben.group_id = benc.group_id " +
                "       AND ben.option_id = benc.option_id " +
                "       AND ben.ben_num = benc.ben_num " +
                "       AND ben.ben_num = 17 " +
                "       AND ben.option_id = 0 " +
                "       AND( " +
                "        ( " +
                "         ben.coverage = 2 " +
                "         AND ben.same_class_id = benc.class_id " +
                "        ) " +
                "        OR( " +
                "         ben.coverage <> 2 " +
                "         AND ben.class_id = benc.class_id " +
                "        ) " +
                "       ) " +
                "     ) AS ben017 ON " +
                "     ben017.benGroupId = cost.group_id " +
                "     AND ben017.origClassId = cost.class_id " +
                "    LEFT JOIN( " +
                "      SELECT " +
                "       ben.group_id AS benGroupId, " +
                "       ben.class_id AS origClassId, " +
                "       benc.underwriter_id " +
                "      FROM " +
                "       benvar ben " +
                "      INNER JOIN benvar benc ON " +
                "       ben.group_id = benc.group_id " +
                "       AND ben.option_id = benc.option_id " +
                "       AND ben.ben_num = benc.ben_num " +
                "       AND ben.ben_num = 18 " +
                "       AND ben.option_id = 0 " +
                "       AND( " +
                "        ( " +
                "         ben.coverage = 2 " +
                "         AND ben.same_class_id = benc.class_id " +
                "        ) " +
                "        OR( " +
                "         ben.coverage <> 2 " +
                "         AND ben.class_id = benc.class_id " +
                "        ) " +
                "       ) " +
                "     ) AS ben018 ON " +
                "     ben018.benGroupId = cost.group_id " +
                "     AND ben018.origClassId = cost.class_id " +
                "    WHERE " +
                "     employee.occ_code != 23 " +
                "   ) AS main " +
                " ) AS vw_Employee_CostBreakdown_CS " +
                "WHERE " +
                " ( " +
                "  group_id = ?" +
                " )";
        jdbcTemplate.query(SQL_GET_ALL,
                           ps ->
                           {
                                ps.setInt(1, groupId);
                           },
                           resultSet ->
                           {
                               Employee employee = new Employee();

                               employee.setName(resultSet.getString("emp_name"));
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
                               employee.setDsai(resultSet.getFloat("dsai_prem"));
                               employee.setEap(resultSet.getFloat("eap_prem"));

                               listEmployee.add(employee);
                           });
        return listEmployee;
    }
}
