package com.Database.DAO.IAPPremiumReport;

import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumADDGroup;
import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumDSAIGroup;
import com.Database.DAO.EAPReport.EAPReportDAOImpl;
import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumGroup;
import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumOptionalADDGroup;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.logging.Logger;

@Component
public class IAPPremiumReportDAOImpl implements IAPPremiumReportDAO
{
    private JdbcTemplate jdbcTemplate;

    private static final String IAP_PREMIUM_QRY = "SELECT " +
            " polnumseries( premium.ptunderwriter_id, premium.ptpolicy_number ) AS pol_no, " +
            " premium.group_id, " +
            " premium.ptpolicy_number, " +
            " polnumsequence( premium.ptunderwriter_id, premium.ptpolicy_number )::TEXT ||  " +
            " polnumextension( premium.ptunderwriter_id, premium.ptpolicy_number )::TEXT AS div_number, " +
            " premium.employer AS division_name, " +
            " COALESCE( premium.vol010, 0::NUMERIC ) AS current_volume_of_ins, " +
            " getlivessplit( premium.group_id, premium.ptpolicy_number, COALESCE( premium.liv010, 0 ) ) AS current_no_of_lives, " +
            " COALESCE( premium.liv010, 0 ) AS lives, " +
            " COALESCE( premium.vol010spouse, 0 ) AS spouse_volume, " +
            " COALESCE( premium.vol010dep, 0 ) AS dependent_unit, " +
            " CASE " +
            "  WHEN premium.admin_rate010 IS NOT NULL " +
            "  OR premium.admin_rate010 <> 0::DOUBLE PRECISION THEN premium.admin_rate010 * 100::DOUBLE PRECISION " +
            "  ELSE 0::DOUBLE PRECISION " +
            " END AS adminrate, " +
            " CASE " +
            "  WHEN premium.comm_rate010 IS NOT NULL " +
            "  OR premium.comm_rate010 <> 0::DOUBLE PRECISION THEN premium.comm_rate010 * 100::DOUBLE PRECISION " +
            "  ELSE 0::DOUBLE PRECISION " +
            " END AS commrate, " +
            " COALESCE( " +
            "  ( " +
            "   SELECT " +
            "    MIN( ben010h.month_id ) AS MIN " +
            "   FROM " +
            "    ben010h " +
            "   WHERE " +
            "    ben010h.group_id = premium.group_id " +
            "    AND ben010h.policy_number::TEXT = premium.ptpolicy_number::TEXT " +
            "  ), " +
            "  geteffdates( premium.group_id, premium.ptpolicy_number ) " +
            " ) AS effdate, " +
            " CASE " +
            "  WHEN premium.liv010 = 0 THEN 'T'::TEXT " +
            "  ELSE 'A'::TEXT " +
            " END AS status, " +
            " COALESCE( " +
            "  ( " +
            "   ( " +
            "    SELECT " +
            "     MIN( ben010.next_renew ) AS MIN " +
            "    FROM " +
            "     ben010 " +
            "    WHERE " +
            "     ben010.group_id = premium.group_id " +
            "     AND ben010.policy_number::TEXT = premium.ptpolicy_number::TEXT " +
            "   ) " +
            "  )::TIMESTAMP WITH TIME ZONE, " +
            "  to_timestamp( " +
            "   0::DOUBLE PRECISION " +
            "  ) " +
            " ) AS nextrenew, " +
            " getempprovsplit( premium.group_id, premium.ptpolicy_number ) AS empprovsplit, " +
            " getbenefitsplit( premium.group_id, premium.ptpolicy_number ) AS benefitamount, " +
            " getspousebenefitsplit( premium.group_id, premium.ptpolicy_number ) AS spousebenefitamount, " +
            " getdependentbenefitsplit( premium.group_id, premium.ptpolicy_number ) AS dependentbenefitamount, " +
            " getspouselivessplit( premium.group_id, premium.ptpolicy_number ) AS spouselives, " +
            " getdependentlivessplit( premium.group_id, premium.ptpolicy_number ) AS dependentlives, " +
            " getoptionalcoverrate( premium.group_id, premium.ptpolicy_number, 520 ) AS spouserate, " +
            " getspousetotalpremium( premium.group_id, premium.ptpolicy_number ) AS spousetotalpremium, " +
            " ( " +
            "  CASE " +
            "   WHEN getoptionalcoverrate( " +
            "    premium.group_id, " +
            "    premium.ptpolicy_number, " +
            "    521 " +
            "   )= '' THEN NULL " +
            "   ELSE getoptionalcoverrate( " +
            "    premium.group_id, " +
            "    premium.ptpolicy_number, " +
            "    521 " +
            "   ) " +
            "  END " +
            " )::DOUBLE PRECISION AS dependentrate, " +
            " COALESCE( getdependenttotalpremium( premium.group_id, premium.ptpolicy_number ), 0::NUMERIC ) AS dependenttotalpremium, " +
            " ( " +
            "  SELECT " +
            "   ben010.rate " +
            "  FROM " +
            "   ben010 " +
            "  WHERE " +
            "   ben010.group_id = premium.group_id " +
            "   AND ben010.policy_number::TEXT = premium.ptpolicy_number::TEXT LIMIT 1 " +
            " ) AS grossrate, " +
            " COALESCE( premium.gross010, 0::NUMERIC ) -  " +
            " COALESCE( getspousetotalpremium( premium.group_id, premium.ptpolicy_number ), 0::NUMERIC ) -  " +
            " COALESCE( getdependenttotalpremium( premium.group_id, premium.ptpolicy_number ), 0::NUMERIC ) AS gross_prem, " +
            " COALESCE( premium.gross010, 0::NUMERIC ) AS total_gross_prem, " +
            " COALESCE( premium.admin010, 0::NUMERIC ) AS admin_fee, " +
            " COALESCE( premium.comm010, 0::NUMERIC ) AS commission, " +
            " COALESCE( premium.raprem010, 0::NUMERIC ) +  " +
            " COALESCE( premium.rcprem010, 0::NUMERIC ) +  " +
            " COALESCE( premium.rtprem010, 0::NUMERIC ) AS adjust, " +
            " COALESCE( premium.admin010, 0::NUMERIC ) +  " +
            " COALESCE( premium.comm010, 0::NUMERIC ) AS totalcommission, " +
            " COALESCE( premium.gross010, 0::NUMERIC ) +  " +
            " COALESCE( premium.raprem010, 0::NUMERIC ) +  " +
            " COALESCE( premium.rcprem010, 0::NUMERIC ) +  " +
            " COALESCE( premium.rtprem010, 0::NUMERIC ) -  " +
            " COALESCE( premium.admin010, 0::NUMERIC ) -  " +
            " COALESCE( premium.comm010, 0::NUMERIC ) +  " +
            " COALESCE( pst.prov1_010, 0::NUMERIC ) +  " +
            " COALESCE( pst.prov2_010, 0::NUMERIC ) +  " +
            " COALESCE( pst.prov3_010, 0::NUMERIC ) AS net_prem_paid, " +
            " adjustcomments( " +
            "  COALESCE( premium.raprem010, 0::NUMERIC ), " +
            "  COALESCE( premium.rcprem010, 0::NUMERIC ), " +
            "  COALESCE( premium.rtprem010, 0::NUMERIC ) " +
            " ) AS adjustcomments, " +
            " pst.prov1_010 AS on_tax, " +
            " pst.prov2_010 AS qc_tax, " +
            " pst.prov3_010 AS mb_tax " +
            "FROM " +
            " ( " +
            "  SELECT " +
            "   pt.group_id, " +
            "   pt.policy_number AS ptpolicy_number, " +
            "   pt.underwriter_id AS ptunderwriter_id, " +
            "   pt.month_id, " +
            "   invoice.employer, " +
            "   premium010.vol010, " +
            "   premium010.liv010, " +
            "   premium010.vol010spouse, " +
            "   premium010.vol010dep, " +
            "   premium010.admin_rate010, " +
            "   premium010.comm_rate010, " +
            "   premium010.gross010, " +
            "   ra.prem010 AS raprem010, " +
            "   rc.prem010 AS rcprem010, " +
            "   rt.prem010 AS rtprem010, " +
            "   premium010.admin010, " +
            "   premium010.comm010 " +
            "  FROM " +
            "   ( " +
            "    SELECT " +
            "     premiums.underwriter_id, " +
            "     premiums.policy_number, " +
            "     premiums.month_id, " +
            "     premiums.group_id, " +
            "     SUM( premiums.gross_prem ) AS sumofgross_prem, " +
            "     SUM( premiums.admin_allow ) AS sumofadmin_allow, " +
            "     SUM( premiums.commission ) AS sumofcommission, " +
            "     SUM( premiums.net_prem ) AS sumofnet_prem, " +
            "     SUM( premiums.gross_prem + COALESCE( premiums.adjud_per_lives, 0::NUMERIC )+ COALESCE( premiums.admin_per_lives, 0::NUMERIC )+ COALESCE( premiums.comm_per_lives, 0::NUMERIC )) AS gross, " +
            "     SUM( premiums.admin_allow + COALESCE( premiums.admin_per_lives, 0::NUMERIC )) AS admin, " +
            "     SUM( premiums.commission + COALESCE( premiums.comm_per_lives, 0::NUMERIC )) AS comm, " +
            "     SUM( premiums.net_prem + COALESCE( premiums.adjud_per_lives, 0::NUMERIC )) AS net " +
            "    FROM " +
            "     premiums " +
            "    GROUP BY " +
            "     premiums.underwriter_id, " +
            "     premiums.policy_number, " +
            "     premiums.month_id, " +
            "     premiums.group_id " +
            "    HAVING " +
            "     premiums.month_id = getcurrentbillingdate() " +
            "   ) pt " +
            "  JOIN underwriter ON " +
            "   pt.underwriter_id = underwriter.underwriter_id " +
            "  JOIN invoice ON " +
            "   pt.group_id = invoice.group_id " +
            "   AND pt.month_id = invoice.month_id " +
            "  LEFT JOIN( " +
            "    SELECT " +
            "     pat.group_id, " +
            "     pat.policy_number, " +
            "     pat.underwriter_id, " +
            "     v_premiumadjust010.prem010 " +
            "    FROM " +
            "     ( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.month_id = getcurrentbillingdate() " +
            "      GROUP BY " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.month_id, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "     ) pat " +
            "    LEFT JOIN( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id, " +
            "       premiums_adjust.gross_prem AS prem010 " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.ben_num = 10 " +
            "       AND premiums_adjust.month_id = getcurrentbillingdate() " +
            "     ) v_premiumadjust010 ON " +
            "     pat.prem_code = v_premiumadjust010.prem_code " +
            "     AND pat.policy_number::TEXT = v_premiumadjust010.policy_number::TEXT " +
            "    WHERE " +
            "     pat.prem_code = 'RA'::BPCHAR " +
            "   ) ra ON " +
            "   pt.group_id = ra.group_id " +
            "   AND pt.policy_number::TEXT = ra.policy_number::TEXT " +
            "   AND pt.underwriter_id = ra.underwriter_id " +
            "  LEFT JOIN( " +
            "    SELECT " +
            "     pat.group_id, " +
            "     pat.policy_number, " +
            "     pat.underwriter_id, " +
            "     v_premiumadjust010.prem010 " +
            "    FROM " +
            "     ( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.month_id = getcurrentbillingdate() " +
            "      GROUP BY " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.month_id, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "     ) pat " +
            "    LEFT JOIN( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id, " +
            "       premiums_adjust.gross_prem AS prem010 " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.ben_num = 10 " +
            "       AND premiums_adjust.month_id = getcurrentbillingdate() " +
            "     ) v_premiumadjust010 ON " +
            "     pat.prem_code = v_premiumadjust010.prem_code " +
            "     AND pat.policy_number::TEXT = v_premiumadjust010.policy_number::TEXT " +
            "    WHERE " +
            "     pat.prem_code = 'RC'::BPCHAR " +
            "   ) rc ON " +
            "   pt.group_id = rc.group_id " +
            "   AND pt.policy_number::TEXT = rc.policy_number::TEXT " +
            "   AND pt.underwriter_id = rc.underwriter_id " +
            "  LEFT JOIN( " +
            "    SELECT " +
            "     pat.group_id, " +
            "     pat.policy_number, " +
            "     pat.underwriter_id, " +
            "     v_premiumadjust010.prem010 " +
            "    FROM " +
            "     ( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.month_id = getcurrentbillingdate() " +
            "      GROUP BY " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.month_id, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "     ) pat " +
            "    LEFT JOIN( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id, " +
            "       premiums_adjust.gross_prem AS prem010 " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.ben_num = 10 " +
            "       AND premiums_adjust.month_id = getcurrentbillingdate() " +
            "     ) v_premiumadjust010 ON " +
            "     pat.prem_code = v_premiumadjust010.prem_code " +
            "     AND pat.policy_number::TEXT = v_premiumadjust010.policy_number::TEXT " +
            "    WHERE " +
            "     pat.prem_code = 'RT'::BPCHAR " +
            "   ) rt ON " +
            "   pt.group_id = rt.group_id " +
            "   AND pt.policy_number::TEXT = rt.policy_number::TEXT " +
            "   AND pt.underwriter_id = rt.underwriter_id " +
            "  LEFT JOIN( " +
            "    SELECT " +
            "     premiums.underwriter_id, " +
            "     premiums.policy_number, " +
            "     premiums.group_id, " +
            "     premiums.vol_doll AS vol010, " +
            "     premiums.single_rate AS rate010, " +
            "     premiums.couple_rate AS rate010c, " +
            "     premiums.family_rate AS rate010f, " +
            "     premiums.gross_prem AS gross010, " +
            "     premiums.admin_allow AS admin010, " +
            "     premiums.commission AS comm010, " +
            "     premiums.net_prem AS net010, " +
            "     premiums.vol_single AS liv010, " +
            "     premiums.comm_rate AS comm_rate010, " +
            "     premiums.admin_rate AS admin_rate010, " +
            "     CASE " +
            "      WHEN premiums.vol_couple = 0 " +
            "      OR premiums.vol_couple IS NULL THEN NULL::INTEGER " +
            "      ELSE premiums.vol_couple " +
            "     END AS vol010spouse, " +
            "     CASE " +
            "      WHEN premiums.vol_family = 0 " +
            "      OR premiums.vol_family IS NULL THEN NULL::INTEGER " +
            "      ELSE premiums.vol_family " +
            "     END AS vol010dep " +
            "    FROM " +
            "     premiums " +
            "    WHERE " +
            "     premiums.ben_num = 10 " +
            "     AND premiums.month_id = getcurrentbillingdate() " +
            "   ) premium010 ON " +
            "   pt.group_id = premium010.group_id " +
            "   AND pt.policy_number::TEXT = premium010.policy_number::TEXT " +
            "   AND pt.underwriter_id = premium010.underwriter_id " +
            " ) premium " +
            "LEFT JOIN( " +
            "  SELECT " +
            "   pstforpremiumreport.underwriter_id, " +
            "   pstforpremiumreport.policy_number, " +
            "   pstforpremiumreport.group_id, " +
            "   SUM( CASE WHEN pstforpremiumreport.ben_num = 10 AND pstforpremiumreport.province = 'ON'::BPCHAR THEN pstforpremiumreport.pst ELSE 0::NUMERIC END ) AS prov1_010, " +
            "   SUM( CASE WHEN pstforpremiumreport.ben_num = 10 AND pstforpremiumreport.province = 'QC'::BPCHAR THEN pstforpremiumreport.pst ELSE 0::NUMERIC END ) AS prov2_010, " +
            "   'MB'::TEXT AS prov3, " +
            "   SUM( CASE WHEN pstforpremiumreport.ben_num = 10 AND pstforpremiumreport.province = 'MB'::BPCHAR THEN pstforpremiumreport.pst ELSE 0::NUMERIC END ) AS prov3_010 " +
            "  FROM " +
            "   pstforpremiumreport " +
            "  WHERE " +
            "   pstforpremiumreport.month_id = getcurrentbillingdate() " +
            "  GROUP BY " +
            "   pstforpremiumreport.underwriter_id, " +
            "   pstforpremiumreport.policy_number, " +
            "   pstforpremiumreport.group_id " +
            " ) pst ON " +
            " premium.group_id = pst.group_id " +
            " AND premium.ptpolicy_number::TEXT = pst.policy_number::TEXT " +
            " AND premium.ptunderwriter_id = pst.underwriter_id " +
            "WHERE " +
            " premium.ptunderwriter_id = 93032 " +
            " AND( " +
            "  hasiapci( premium.group_id, premium.ptpolicy_number, 10 ) " +
            "  OR( " +
            "   premium.ptpolicy_number::TEXT IN( " +
            "    SELECT " +
            "     premiums.policy_number " +
            "    FROM " +
            "     premiums " +
            "    WHERE " +
            "     premiums.policy_number::TEXT = premium.ptpolicy_number::TEXT " +
            "     AND premiums.ben_num = 10 " +
            "     AND premiums.month_id = premium.month_id " +
            "   ) " +
            "  ) " +
            " )";

    private static final String IAP_OPTIONAL_ADD_PREMIUM_QRY = "SELECT  " +
            " polnumseries( premium.ptunderwriter_id, premium.ptpolicy_number ) AS pol_no,  " +
            " premium.group_id,  " +
            " premium.ptpolicy_number,  " +
            " polnumsequence( premium.ptunderwriter_id, premium.ptpolicy_number )::TEXT ||   " +
            " polnumextension( premium.ptunderwriter_id, premium.ptpolicy_number )::TEXT AS div_number,  " +
            " premium.employer AS division_name,  " +
            " COALESCE( bensinglevol.volume, 0::NUMERIC ) AS currentsinglevolume,  " +
            " COALESCE( benfamilyvol.volume, 0::NUMERIC ) AS currentfamilyvolume,  " +
            " COALESCE( bensinglevol.single_rate, benfamilyvol.single_rate ) AS currentsinglerate,  " +
            " COALESCE( bensinglevol.family_rate, benfamilyvol.family_rate ) AS currentfamilyrate,  " +
            " COALESCE( premium.liv09, 0 ) AS current_no_of_lives,  " +
            " CASE  " +
            "  WHEN premium.admin_rate09 IS NOT NULL  " +
            "  OR premium.admin_rate09 <> 0::DOUBLE PRECISION THEN premium.admin_rate09 * 100::DOUBLE PRECISION  " +
            "  ELSE 0::DOUBLE PRECISION  " +
            " END AS admin_rate, " +
            " CASE  " +
            "  WHEN premium.comm_rate09 IS NOT NULL  " +
            "  OR premium.comm_rate09 <> 0::DOUBLE PRECISION THEN premium.comm_rate09 * 100::DOUBLE PRECISION  " +
            "  ELSE 0::DOUBLE PRECISION  " +
            " END AS comm_rate,  " +
            " geteffdates( premium.group_id, premium.ptpolicy_number, 9 ) AS effdate,  " +
            " CASE  " +
            "  WHEN premium.liv09 = 0 THEN 'T'::TEXT  " +
            "  ELSE 'A'::TEXT  " +
            " END AS status,  " +
            " getempprovsplit( premium.group_id, premium.ptpolicy_number, 9 ) AS empprovsplit,  " +
            " COALESCE( premium.gross09, 0::NUMERIC ) AS gross_prem,  " +
            " COALESCE( premium.gross09, 0::NUMERIC ) +   " +
            " COALESCE( premium.raprem09, 0::NUMERIC ) +   " +
            " COALESCE( premium.rcprem09, 0::NUMERIC ) +   " +
            " COALESCE( premium.rtprem09, 0::NUMERIC ) AS total_gross_prem,  " +
            " COALESCE( premium.admin09, 0::NUMERIC ) AS admin_fee,  " +
            " COALESCE( premium.comm09, 0::NUMERIC ) AS commission,  " +
            " COALESCE( premium.raprem09, 0::NUMERIC ) +   " +
            " COALESCE( premium.rcprem09, 0::NUMERIC ) +   " +
            " COALESCE( premium.rtprem09, 0::NUMERIC ) AS adjust,  " +
            " COALESCE( premium.admin09, 0::NUMERIC ) +   " +
            " COALESCE( premium.comm09, 0::NUMERIC ) AS totalcommission,  " +
            " COALESCE( premium.gross09, 0::NUMERIC ) +   " +
            " COALESCE( premium.raprem09, 0::NUMERIC ) +   " +
            " COALESCE( premium.rcprem09, 0::NUMERIC ) +   " +
            " COALESCE( premium.rtprem09, 0::NUMERIC ) -   " +
            " COALESCE( premium.admin09, 0::NUMERIC ) -   " +
            " COALESCE( premium.comm09, 0::NUMERIC ) +   " +
            " COALESCE( pst.prov1_09, 0::NUMERIC ) +   " +
            " COALESCE( pst.prov2_09, 0::NUMERIC ) AS net_prem_paid,  " +
            " adjustcomments(  " +
            "  COALESCE( premium.raprem09, 0::NUMERIC ),  " +
            "  COALESCE( premium.rcprem09, 0::NUMERIC ),  " +
            "  COALESCE( premium.rtprem09, 0::NUMERIC )  " +
            " ) AS adjustcomments,  " +
            " pst.prov1_09 AS on_tax,  " +
            " pst.prov2_09 AS qc_tax  " +
            "FROM  " +
            " (  " +
            "  SELECT  " +
            "   pt.group_id,  " +
            "   pt.policy_number AS ptpolicy_number,  " +
            "   pt.underwriter_id AS ptunderwriter_id,  " +
            "   pt.underwriter_id,  " +
            "   pt.month_id,  " +
            "   invoice.employer,  " +
            "   premium09.gross09,  " +
            "   premium09.admin09,  " +
            "   premium09.comm09,  " +
            "   premium09.liv09,  " +
            "   premium09.comm_rate09,  " +
            "   premium09.admin_rate09,  " +
            "   ra.prem09 AS raprem09,  " +
            "   rc.prem09 AS rcprem09,  " +
            "   rt.prem09 AS rtprem09  " +
            "  FROM  " +
            "   (  " +
            "    SELECT  " +
            "     premiums.group_id,  " +
            "     premiums.policy_number,  " +
            "     premiums.underwriter_id,  " +
            "     premiums.month_id  " +
            "    FROM  " +
            "     premiums  " +
            "    GROUP BY  " +
            "     premiums.underwriter_id,  " +
            "     premiums.policy_number,  " +
            "     premiums.month_id,  " +
            "     premiums.group_id  " +
            "    HAVING  " +
            "     premiums.month_id = getcurrentbillingdate()  " +
            "   ) pt  " +
            "  LEFT JOIN(  " +
            "    SELECT  " +
            "     premiums.underwriter_id,  " +
            "     premiums.policy_number,  " +
            "     premiums.group_id,  " +
            "     premiums.gross_prem AS gross09,  " +
            "     premiums.admin_allow AS admin09,  " +
            "     premiums.commission AS comm09,  " +
            "     premiums.vol_single AS liv09,  " +
            "     premiums.comm_rate AS comm_rate09,  " +
            "     premiums.admin_rate AS admin_rate09  " +
            "    FROM  " +
            "     premiums  " +
            "    WHERE  " +
            "     premiums.ben_num = 9  " +
            "     AND premiums.month_id = getcurrentbillingdate()  " +
            "   ) premium09 ON  " +
            "   pt.group_id = premium09.group_id  " +
            "   AND pt.policy_number::TEXT = premium09.policy_number::TEXT  " +
            "   AND pt.underwriter_id = premium09.underwriter_id  " +
            "  JOIN underwriter ON  " +
            "   pt.underwriter_id = underwriter.underwriter_id  " +
            "  JOIN invoice ON  " +
            "   pt.group_id = invoice.group_id  " +
            "   AND pt.month_id = invoice.month_id  " +
            "  LEFT JOIN(  " +
            "    SELECT  " +
            "     pat.underwriter_id,  " +
            "     pat.policy_number,  " +
            "     pat.group_id,  " +
            "     v_premiumadjust09.prem09  " +
            "    FROM  " +
            "     (  " +
            "      SELECT  " +
            "       premiums_adjust.underwriter_id,  " +
            "       premiums_adjust.policy_number,  " +
            "       premiums_adjust.prem_code,  " +
            "       premiums_adjust.group_id,  " +
            "       SUM( premiums_adjust.gross_prem ) AS sumofgross_prem  " +
            "      FROM  " +
            "       premiums_adjust  " +
            "      WHERE  " +
            "       premiums_adjust.month_id = getcurrentbillingdate()  " +
            "      GROUP BY  " +
            "       premiums_adjust.underwriter_id,  " +
            "       premiums_adjust.policy_number,  " +
            "       premiums_adjust.month_id,  " +
            "       premiums_adjust.prem_code,  " +
            "       premiums_adjust.group_id  " +
            "     ) pat  " +
            "    LEFT JOIN(  " +
            "      SELECT  " +
            "       premiums_adjust.underwriter_id,  " +
            "       premiums_adjust.policy_number,  " +
            "       premiums_adjust.prem_code,  " +
            "       premiums_adjust.group_id,  " +
            "       premiums_adjust.gross_prem AS prem09  " +
            "      FROM  " +
            "       premiums_adjust  " +
            "      WHERE  " +
            "       premiums_adjust.ben_num = 9  " +
            "       AND premiums_adjust.month_id = getcurrentbillingdate()  " +
            "     ) v_premiumadjust09 ON  " +
            "     pat.prem_code = v_premiumadjust09.prem_code  " +
            "     AND pat.policy_number::TEXT = v_premiumadjust09.policy_number::TEXT  " +
            "     AND pat.underwriter_id = v_premiumadjust09.underwriter_id  " +
            "     AND pat.group_id = v_premiumadjust09.group_id  " +
            "    WHERE  " +
            "     pat.prem_code = 'RA'::BPCHAR  " +
            "   ) ra ON  " +
            "   pt.group_id = ra.group_id  " +
            "   AND pt.policy_number::TEXT = ra.policy_number::TEXT  " +
            "   AND pt.underwriter_id = ra.underwriter_id  " +
            "  LEFT JOIN(  " +
            "    SELECT  " +
            "     pat.underwriter_id,  " +
            "     pat.policy_number,  " +
            "     pat.group_id,  " +
            "     v_premiumadjust09.prem09  " +
            "    FROM  " +
            "     (  " +
            "      SELECT  " +
            "       premiums_adjust.underwriter_id,  " +
            "       premiums_adjust.policy_number,  " +
            "       premiums_adjust.prem_code,  " +
            "       premiums_adjust.group_id,  " +
            "       SUM( premiums_adjust.gross_prem ) AS sumofgross_prem  " +
            "      FROM  " +
            "       premiums_adjust  " +
            "      WHERE  " +
            "       premiums_adjust.month_id = getcurrentbillingdate()  " +
            "      GROUP BY  " +
            "       premiums_adjust.underwriter_id,  " +
            "       premiums_adjust.policy_number,  " +
            "       premiums_adjust.month_id,  " +
            "       premiums_adjust.prem_code,  " +
            "       premiums_adjust.group_id  " +
            "     ) pat  " +
            "    LEFT JOIN(  " +
            "      SELECT  " +
            "       premiums_adjust.underwriter_id,  " +
            "       premiums_adjust.policy_number,  " +
            "       premiums_adjust.prem_code,  " +
            "       premiums_adjust.group_id,  " +
            "       premiums_adjust.gross_prem AS prem09  " +
            "      FROM  " +
            "       premiums_adjust  " +
            "      WHERE  " +
            "       premiums_adjust.ben_num = 9  " +
            "       AND premiums_adjust.month_id = getcurrentbillingdate()  " +
            "     ) v_premiumadjust09 ON  " +
            "     pat.prem_code = v_premiumadjust09.prem_code  " +
            "     AND pat.policy_number::TEXT = v_premiumadjust09.policy_number::TEXT  " +
            "     AND pat.underwriter_id = v_premiumadjust09.underwriter_id  " +
            "     AND pat.group_id = v_premiumadjust09.group_id  " +
            "    WHERE  " +
            "     pat.prem_code = 'RC'::BPCHAR  " +
            "   ) rc ON  " +
            "   pt.group_id = rc.group_id  " +
            "   AND pt.policy_number::TEXT = rc.policy_number::TEXT  " +
            "   AND pt.underwriter_id = rc.underwriter_id  " +
            "  LEFT JOIN(  " +
            "    SELECT  " +
            "     pat.underwriter_id,  " +
            "     pat.policy_number,  " +
            "     pat.group_id,  " +
            "     v_premiumadjust09.prem09  " +
            "    FROM  " +
            "     (  " +
            "      SELECT  " +
            "       premiums_adjust.underwriter_id,  " +
            "       premiums_adjust.policy_number,  " +
            "       premiums_adjust.prem_code,  " +
            "       premiums_adjust.group_id,  " +
            "       SUM( premiums_adjust.gross_prem ) AS sumofgross_prem  " +
            "      FROM  " +
            "       premiums_adjust  " +
            "      WHERE  " +
            "       premiums_adjust.month_id = getcurrentbillingdate()  " +
            "      GROUP BY  " +
            "       premiums_adjust.underwriter_id,  " +
            "       premiums_adjust.policy_number,  " +
            "       premiums_adjust.month_id,  " +
            "       premiums_adjust.prem_code,  " +
            "       premiums_adjust.group_id  " +
            "     ) pat  " +
            "    LEFT JOIN(  " +
            "      SELECT  " +
            "       premiums_adjust.underwriter_id,  " +
            "       premiums_adjust.policy_number,  " +
            "       premiums_adjust.prem_code,  " +
            "       premiums_adjust.group_id,  " +
            "       premiums_adjust.gross_prem AS prem09  " +
            "      FROM  " +
            "       premiums_adjust  " +
            "      WHERE  " +
            "       premiums_adjust.ben_num = 9  " +
            "       AND premiums_adjust.month_id = getcurrentbillingdate()  " +
            "     ) v_premiumadjust09 ON  " +
            "     pat.prem_code = v_premiumadjust09.prem_code  " +
            "     AND pat.policy_number::TEXT = v_premiumadjust09.policy_number::TEXT  " +
            "     AND pat.underwriter_id = v_premiumadjust09.underwriter_id  " +
            "     AND pat.group_id = v_premiumadjust09.group_id  " +
            "    WHERE  " +
            "     pat.prem_code = 'RT'::BPCHAR  " +
            "   ) rt ON  " +
            "   pt.group_id = rt.group_id  " +
            "   AND pt.policy_number::TEXT = rt.policy_number::TEXT  " +
            "   AND pt.underwriter_id = rt.underwriter_id  " +
            " ) premium  " +
            "LEFT JOIN(  " +
            "  SELECT  " +
            "   pstforpremiumreport.underwriter_id,  " +
            "   pstforpremiumreport.policy_number,  " +
            "   pstforpremiumreport.group_id,  " +
            "   SUM( CASE WHEN pstforpremiumreport.ben_num = 9 AND pstforpremiumreport.province = 'ON'::BPCHAR THEN pstforpremiumreport.pst ELSE 0::NUMERIC END ) AS prov1_09,  " +
            "   SUM( CASE WHEN pstforpremiumreport.ben_num = 9 AND pstforpremiumreport.province = 'QC'::BPCHAR THEN pstforpremiumreport.pst ELSE 0::NUMERIC END ) AS prov2_09  " +
            "  FROM  " +
            "   pstforpremiumreport  " +
            "  WHERE  " +
            "   pstforpremiumreport.month_id = getcurrentbillingdate()  " +
            "  GROUP BY  " +
            "   pstforpremiumreport.underwriter_id,  " +
            "   pstforpremiumreport.policy_number,  " +
            "   pstforpremiumreport.group_id  " +
            " ) pst ON  " +
            " premium.group_id = pst.group_id  " +
            " AND premium.ptpolicy_number::TEXT = pst.policy_number::TEXT  " +
            " AND premium.ptunderwriter_id = pst.underwriter_id  " +
            "LEFT JOIN(  " +
            "  SELECT  " +
            "   v_ben09.group_id,  " +
            "   v_ben09.underwriter_id,  " +
            "   v_ben09.policy_number,  " +
            "   v_ben09.single_rate,  " +
            "   v_ben09.family_rate,  " +
            "   SUM( employee.oad_amount ) AS volume  " +
            "  FROM  " +
            "   (  " +
            "    SELECT  " +
            "     other.group_id,  " +
            "     self.class_id AS benclass,  " +
            "     other.underwriter_id,  " +
            "     other.policy_number,  " +
            "     other.single_rate,  " +
            "     other.family_rate  " +
            "    FROM  " +
            "     ben09 SELF  " +
            "    JOIN ben09 other ON  " +
            "     self.group_id = other.group_id  " +
            "     AND self.option_id = other.option_id  " +
            "     AND(  " +
            "      self.coverage = 2  " +
            "      AND self.same_class_id = other.class_id  " +
            "      OR self.coverage <> 2  " +
            "      AND self.class_id = other.class_id  " +
            "     )  " +
            "     AND self.plan_id = other.plan_id  " +
            "   ) v_ben09  " +
            "  JOIN employee ON  " +
            "   v_ben09.group_id = employee.group_id  " +
            "   AND v_ben09.benclass = employee.class_id  " +
            "  WHERE  " +
            "   employee.oad_amount > 0::NUMERIC  " +
            "   AND employee.oad_cover = 196  " +
            "  GROUP BY  " +
            "   v_ben09.group_id,  " +
            "   v_ben09.underwriter_id,  " +
            "   v_ben09.policy_number,  " +
            "   v_ben09.single_rate,  " +
            "   v_ben09.family_rate  " +
            " ) bensinglevol ON  " +
            " premium.group_id = bensinglevol.group_id  " +
            " AND premium.underwriter_id = bensinglevol.underwriter_id  " +
            " AND premium.ptpolicy_number::TEXT = bensinglevol.policy_number::TEXT  " +
            "LEFT JOIN(  " +
            "  SELECT  " +
            "   v_ben09.group_id,  " +
            "   v_ben09.underwriter_id,  " +
            "   v_ben09.policy_number,  " +
            "   v_ben09.single_rate,  " +
            "   v_ben09.family_rate,  " +
            "   SUM( employee.oad_amount ) AS volume  " +
            "  FROM  " +
            "   (  " +
            "    SELECT  " +
            "     other.group_id,  " +
            "     self.class_id AS benclass,  " +
            "     other.underwriter_id,  " +
            "     other.policy_number,  " +
            "     other.single_rate,  " +
            "     other.family_rate  " +
            "    FROM  " +
            "     ben09 SELF  " +
            "    JOIN ben09 other ON  " +
            "     self.group_id = other.group_id  " +
            "     AND self.option_id = other.option_id  " +
            "     AND(  " +
            "      self.coverage = 2  " +
            "      AND self.same_class_id = other.class_id  " +
            "      OR self.coverage <> 2  " +
            "      AND self.class_id = other.class_id  " +
            "     )  " +
            "     AND self.plan_id = other.plan_id  " +
            "   ) v_ben09  " +
            "  JOIN employee ON  " +
            "   v_ben09.group_id = employee.group_id  " +
            "   AND v_ben09.benclass = employee.class_id  " +
            "  WHERE  " +
            "   employee.oad_amount > 0::NUMERIC  " +
            "   AND employee.oad_cover = 197  " +
            "  GROUP BY  " +
            "   v_ben09.group_id,  " +
            "   v_ben09.underwriter_id,  " +
            "   v_ben09.policy_number,  " +
            "   v_ben09.single_rate,  " +
            "   v_ben09.family_rate  " +
            " ) benfamilyvol ON  " +
            " premium.group_id = benfamilyvol.group_id  " +
            " AND premium.underwriter_id = benfamilyvol.underwriter_id  " +
            " AND premium.ptpolicy_number::TEXT = benfamilyvol.policy_number::TEXT  " +
            "WHERE  " +
            " premium.ptunderwriter_id = 93032  " +
            " AND(  " +
            "  hasiapci( premium.group_id, premium.ptpolicy_number, 9 )  " +
            "  OR(  " +
            "   premium.ptpolicy_number::TEXT IN(  " +
            "    SELECT  " +
            "     premiums.policy_number  " +
            "    FROM  " +
            "     premiums  " +
            "    WHERE  " +
            "     premiums.policy_number::TEXT = premium.ptpolicy_number::TEXT  " +
            "     AND premiums.ben_num = 9  " +
            "     AND premiums.month_id = premium.month_id  " +
            "   )  " +
            "  )  " +
            " )";

    private static final String IAP_ADD_PREMIUM_QRY = "SELECT " +
            " polnumseries( premium.ptunderwriter_id, premium.ptpolicy_number ) AS pol_no, " +
            " premium.group_id, " +
            " premium.ptpolicy_number, " +
            " polnumsequence( premium.ptunderwriter_id, premium.ptpolicy_number )::TEXT ||  " +
            " polnumextension( premium.ptunderwriter_id, premium.ptpolicy_number)::TEXT AS div_number, " +
            " premium.employer AS division_name, " +
            " COALESCE( premium.vol02, 0::NUMERIC ) AS current_volume_of_ins, " +
            " COALESCE( premium.liv02, 0 ) AS current_no_of_lives, " +
            " CASE " +
            "  WHEN premium.admin_rate02 IS NOT NULL " +
            "  OR premium.admin_rate02 <> 0::DOUBLE PRECISION THEN premium.admin_rate02 * 100::DOUBLE PRECISION " +
            "  ELSE 0::DOUBLE PRECISION " +
            " END AS adminrate, " +
            " CASE " +
            "  WHEN premium.comm_rate02 IS NOT NULL " +
            "  OR premium.comm_rate02 <> 0::DOUBLE PRECISION THEN premium.comm_rate02 * 100::DOUBLE PRECISION " +
            "  ELSE 0::DOUBLE PRECISION " +
            " END AS commrate, " +
            " CASE " +
            "  WHEN premium.liv02 = 0 THEN 'T'::TEXT " +
            "  ELSE 'A'::TEXT " +
            " END AS status, " +
            " COALESCE( " +
            "  ( " +
            "   ( " +
            "    SELECT " +
            "     MIN( ben02.next_renew ) AS MIN " +
            "    FROM " +
            "     ben02 " +
            "    WHERE " +
            "     ben02.group_id = premium.group_id " +
            "     AND ben02.policy_number::TEXT = premium.ptpolicy_number::TEXT " +
            "   ) " +
            "  )::TIMESTAMP WITH TIME ZONE, " +
            "  to_timestamp( 0::DOUBLE PRECISION ) " +
            " ) AS nextrenew, " +
            " ''::CHARACTER VARYING AS empprovsplit, " +
            " ( " +
            "  SELECT " +
            "   ben02.rate " +
            "  FROM " +
            "   ben02 " +
            "  WHERE " +
            "   ben02.group_id = premium.group_id " +
            "   AND ben02.policy_number::TEXT = premium.ptpolicy_number::TEXT LIMIT 1 " +
            " ) AS grossrate, " +
            " COALESCE( premium.gross02, 0::NUMERIC ) AS gross_prem, " +
            " COALESCE( premium.gross02, 0::NUMERIC ) +  " +
            " COALESCE( premium.raprem02, 0::NUMERIC ) +  " +
            " COALESCE( premium.rcprem02, 0::NUMERIC ) +  " +
            " COALESCE( premium.rtprem02, 0::NUMERIC ) AS total_gross_prem, " +
            " COALESCE( premium.admin02, 0::NUMERIC ) AS admin_fee, " +
            " COALESCE( premium.comm02, 0::NUMERIC ) AS commission, " +
            " COALESCE( premium.raprem02, 0::NUMERIC ) +  " +
            " COALESCE( premium.rcprem02, 0::NUMERIC ) +  " +
            " COALESCE( premium.rtprem02, 0::NUMERIC ) AS adjust, " +
            " COALESCE( premium.admin02, 0::NUMERIC ) +  " +
            " COALESCE( premium.comm02, 0::NUMERIC ) AS totalcommission, " +
            " COALESCE( premium.gross02, 0::NUMERIC ) +  " +
            " COALESCE( premium.raprem02, 0::NUMERIC ) +  " +
            " COALESCE( premium.rcprem02, 0::NUMERIC ) +  " +
            " COALESCE( premium.rtprem02, 0::NUMERIC ) -  " +
            " COALESCE( premium.admin02, 0::NUMERIC ) -  " +
            " COALESCE( premium.comm02, 0::NUMERIC ) +  " +
            " COALESCE( pst.prov1_02, 0::NUMERIC ) +  " +
            " COALESCE( pst.prov2_02, 0::NUMERIC ) +  " +
            " COALESCE( pst.prov3_02, 0::NUMERIC ) AS net_prem_paid, " +
            " adjustcomments( " +
            "  COALESCE( premium.raprem02, 0::NUMERIC ), " +
            "  COALESCE( premium.rcprem02, 0::NUMERIC ), " +
            "  COALESCE( premium.rtprem02, 0::NUMERIC ) " +
            " ) AS adjustcomments, " +
            " pst.prov1_02 AS on_tax, " +
            " pst.prov2_02 AS qc_tax, " +
            " pst.prov3_02 AS mb_tax " +
            "FROM " +
            " ( " +
            "  SELECT " +
            "   pt.group_id, " +
            "   pt.policy_number AS ptpolicy_number, " +
            "   pt.underwriter_id AS ptunderwriter_id, " +
            "   invoice.employer, " +
            "   premium02.vol02, " +
            "   premium02.liv02, " +
            "   premium02.admin_rate02, " +
            "   premium02.comm_rate02, " +
            "   premium02.gross02, " +
            "   ra.prem02 AS raprem02, " +
            "   rc.prem02 AS rcprem02, " +
            "   rt.prem02 AS rtprem02, " +
            "   premium02.admin02, " +
            "   premium02.comm02, " +
            "   pt.month_id " +
            "  FROM " +
            "   ( " +
            "    SELECT " +
            "     premiums.underwriter_id, " +
            "     premiums.policy_number, " +
            "     premiums.month_id, " +
            "     premiums.group_id, " +
            "     SUM( premiums.gross_prem ) AS sumofgross_prem, " +
            "     SUM( premiums.admin_allow ) AS sumofadmin_allow, " +
            "     SUM( premiums.commission ) AS sumofcommission, " +
            "     SUM( premiums.net_prem ) AS sumofnet_prem, " +
            "     SUM( premiums.gross_prem + COALESCE( premiums.adjud_per_lives, 0::NUMERIC )+ COALESCE( premiums.admin_per_lives, 0::NUMERIC )+ COALESCE( premiums.comm_per_lives, 0::NUMERIC )) AS gross, " +
            "     SUM( premiums.admin_allow + COALESCE( premiums.admin_per_lives, 0::NUMERIC )) AS admin, " +
            "     SUM( premiums.commission + COALESCE( premiums.comm_per_lives, 0::NUMERIC )) AS comm, " +
            "     SUM( premiums.net_prem + COALESCE( premiums.adjud_per_lives, 0::NUMERIC )) AS net " +
            "    FROM " +
            "     premiums " +
            "    GROUP BY " +
            "     premiums.underwriter_id, " +
            "     premiums.policy_number, " +
            "     premiums.month_id, " +
            "     premiums.group_id " +
            "    HAVING " +
            "     premiums.month_id = getcurrentbillingdate() " +
            "   ) pt " +
            "  LEFT JOIN( " +
            "    SELECT " +
            "     premiums.group_id, " +
            "     premiums.policy_number, " +
            "     premiums.underwriter_id, " +
            "     premiums.vol_doll AS vol02, " +
            "     premiums.single_rate AS rate02, " +
            "     premiums.gross_prem AS gross02, " +
            "     premiums.admin_allow AS admin02, " +
            "     premiums.commission AS comm02, " +
            "     premiums.net_prem AS net02, " +
            "     premiums.vol_single AS liv02, " +
            "     premiums.comm_rate AS comm_rate02, " +
            "     premiums.admin_rate AS admin_rate02 " +
            "    FROM " +
            "     premiums " +
            "    WHERE " +
            "     premiums.ben_num = 2 " +
            "     AND premiums.month_id = getcurrentbillingdate() " +
            "   ) premium02 ON " +
            "   pt.group_id = premium02.group_id " +
            "   AND pt.policy_number::TEXT = premium02.policy_number::TEXT " +
            "   AND pt.underwriter_id = premium02.underwriter_id " +
            "  JOIN underwriter ON " +
            "   pt.underwriter_id = underwriter.underwriter_id " +
            "  JOIN invoice ON " +
            "   pt.group_id = invoice.group_id " +
            "   AND pt.month_id = invoice.month_id " +
            "  LEFT JOIN( " +
            "    SELECT " +
            "     pat.group_id, " +
            "     pat.policy_number, " +
            "     pat.underwriter_id, " +
            "     v_premiumadjust02.prem02 " +
            "    FROM " +
            "     ( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.month_id = getcurrentbillingdate() " +
            "      GROUP BY " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.month_id, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "     ) pat " +
            "    LEFT JOIN( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id, " +
            "       premiums_adjust.vol_doll AS vol02, " +
            "       premiums_adjust.gross_prem AS prem02 " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.ben_num = 2 " +
            "       AND premiums_adjust.month_id = getcurrentbillingdate() " +
            "     ) v_premiumadjust02 ON " +
            "     pat.prem_code = v_premiumadjust02.prem_code " +
            "     AND pat.policy_number::TEXT = v_premiumadjust02.policy_number::TEXT " +
            "     AND pat.underwriter_id = v_premiumadjust02.underwriter_id " +
            "     AND pat.group_id = v_premiumadjust02.group_id " +
            "    WHERE " +
            "     pat.prem_code = 'RA'::BPCHAR " +
            "   ) ra ON " +
            "   pt.group_id = ra.group_id " +
            "   AND pt.policy_number::TEXT = ra.policy_number::TEXT " +
            "   AND pt.underwriter_id = ra.underwriter_id " +
            "  LEFT JOIN( " +
            "    SELECT " +
            "     pat.group_id, " +
            "     pat.policy_number, " +
            "     pat.underwriter_id, " +
            "     v_premiumadjust02.prem02 " +
            "    FROM " +
            "     ( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.month_id = getcurrentbillingdate() " +
            "      GROUP BY " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.month_id, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "     ) pat " +
            "    LEFT JOIN( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id, " +
            "       premiums_adjust.vol_doll AS vol02, " +
            "       premiums_adjust.gross_prem AS prem02 " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.ben_num = 2 " +
            "       AND premiums_adjust.month_id = getcurrentbillingdate() " +
            "     ) v_premiumadjust02 ON " +
            "     pat.prem_code = v_premiumadjust02.prem_code " +
            "     AND pat.policy_number::TEXT = v_premiumadjust02.policy_number::TEXT " +
            "     AND pat.underwriter_id = v_premiumadjust02.underwriter_id " +
            "     AND pat.group_id = v_premiumadjust02.group_id " +
            "    WHERE " +
            "     pat.prem_code = 'RC'::BPCHAR " +
            "   ) rc ON " +
            "   pt.group_id = rc.group_id " +
            "   AND pt.policy_number::TEXT = rc.policy_number::TEXT " +
            "   AND pt.underwriter_id = rc.underwriter_id " +
            "  LEFT JOIN( " +
            "    SELECT " +
            "     pat.group_id, " +
            "     pat.policy_number, " +
            "     pat.underwriter_id, " +
            "     v_premiumadjust02.prem02 " +
            "    FROM " +
            "     ( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.month_id = getcurrentbillingdate() " +
            "      GROUP BY " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.month_id, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "     ) pat " +
            "    LEFT JOIN( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id, " +
            "       premiums_adjust.vol_doll AS vol02, " +
            "       premiums_adjust.gross_prem AS prem02 " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.ben_num = 2 " +
            "       AND premiums_adjust.month_id = getcurrentbillingdate() " +
            "     ) v_premiumadjust02 ON " +
            "     pat.prem_code = v_premiumadjust02.prem_code " +
            "     AND pat.policy_number::TEXT = v_premiumadjust02.policy_number::TEXT " +
            "     AND pat.underwriter_id = v_premiumadjust02.underwriter_id " +
            "     AND pat.group_id = v_premiumadjust02.group_id " +
            "    WHERE " +
            "     pat.prem_code = 'RT'::BPCHAR " +
            "   ) rt ON " +
            "   pt.group_id = rt.group_id " +
            "   AND pt.policy_number::TEXT = rt.policy_number::TEXT " +
            "   AND pt.underwriter_id = rt.underwriter_id " +
            " ) premium " +
            "LEFT JOIN( " +
            "  SELECT " +
            "   pstforpremiumreport.underwriter_id, " +
            "   pstforpremiumreport.policy_number, " +
            "   pstforpremiumreport.group_id, " +
            "   SUM( CASE WHEN pstforpremiumreport.ben_num = 2 AND pstforpremiumreport.province = 'ON'::BPCHAR THEN pstforpremiumreport.pst ELSE 0::NUMERIC END ) AS prov1_02, " +
            "   SUM( CASE WHEN pstforpremiumreport.ben_num = 2 AND pstforpremiumreport.province = 'QC'::BPCHAR THEN pstforpremiumreport.pst ELSE 0::NUMERIC END ) AS prov2_02, " +
            "   'MB'::TEXT AS prov3, " +
            "   SUM( CASE WHEN pstforpremiumreport.ben_num = 2 AND pstforpremiumreport.province = 'MB'::BPCHAR THEN pstforpremiumreport.pst ELSE 0::NUMERIC END ) AS prov3_02 " +
            "  FROM " +
            "   pstforpremiumreport " +
            "  WHERE " +
            "   pstforpremiumreport.month_id = getcurrentbillingdate() " +
            "  GROUP BY " +
            "   pstforpremiumreport.underwriter_id, " +
            "   pstforpremiumreport.policy_number, " +
            "   pstforpremiumreport.group_id " +
            " ) pst ON " +
            " premium.group_id = pst.group_id " +
            " AND premium.ptpolicy_number::TEXT = pst.policy_number::TEXT " +
            " AND premium.ptunderwriter_id = pst.underwriter_id " +
            "WHERE " +
            " premium.ptunderwriter_id = 93032 " +
            " AND( " +
            "  hasiapci( premium.group_id, premium.ptpolicy_number, 2 ) " +
            "  OR( " +
            "   premium.ptpolicy_number::TEXT IN( " +
            "    SELECT " +
            "     premiums.policy_number " +
            "    FROM " +
            "     premiums " +
            "    WHERE " +
            "     premiums.policy_number::TEXT = premium.ptpolicy_number::TEXT " +
            "     AND premiums.ben_num = 2 " +
            "     AND premiums.month_id = premium.month_id " +
            "   ) " +
            "  ) " +
            " )";
    private static final String IAP_DSAI_PREMIUM_QRY = "SELECT " +
            " polnumseries( premium.ptunderwriter_id, premium.ptpolicy_number ) AS pol_no, " +
            " premium.group_id, " +
            " premium.ptpolicy_number, " +
            " polnumsequence( premium.ptunderwriter_id, premium.ptpolicy_number )::TEXT || polnumextension( premium.ptunderwriter_id, premium.ptpolicy_number)::TEXT AS div_number, " +
            " premium.employer AS division_name, " +
            " COALESCE( premium.livs014, 0 ) AS lives_single, " +
            " COALESCE( premium.livc014, 0 ) AS lives_couple, " +
            " COALESCE( premium.gross014, 0::NUMERIC ) -  " +
            " COALESCE( premium.admin014, 0::NUMERIC ) -  " +
            " COALESCE( premium.comm014, 0::NUMERIC ) AS current_prem, " +
            " COALESCE( premium.raprem014, 0::NUMERIC ) +  " +
            " COALESCE( premium.rcprem014, 0::NUMERIC ) +  " +
            " COALESCE( premium.rtprem014, 0::NUMERIC ) AS retro_prem, " +
            " COALESCE( pst.prov1_014, 0::NUMERIC ) +  " +
            " COALESCE( pst.prov2_014, 0::NUMERIC ) AS pst, " +
            " premium.raprem014, " +
            " premium.rcprem014, " +
            " premium.rtprem014 " +
            "FROM " +
            " ( " +
            "  SELECT " +
            "   pt.group_id, " +
            "   pt.policy_number AS ptpolicy_number, " +
            "   pt.underwriter_id AS ptunderwriter_id, " +
            "   pt.month_id, " +
            "   invoice.employer, " +
            "   premium014.livs014, " +
            "   premium014.livc014, " +
            "   premium014.gross014, " +
            "   premium014.admin014, " +
            "   premium014.comm014, " +
            "   ra.prem014 AS raprem014, " +
            "   rc.prem014 AS rcprem014, " +
            "   rt.prem014 AS rtprem014 " +
            "  FROM " +
            "   ( " +
            "    SELECT " +
            "     premiums.underwriter_id, " +
            "     premiums.policy_number, " +
            "     premiums.month_id, " +
            "     premiums.group_id, " +
            "     SUM( premiums.gross_prem ) AS sumofgross_prem, " +
            "     SUM( premiums.admin_allow ) AS sumofadmin_allow, " +
            "     SUM( premiums.commission ) AS sumofcommission, " +
            "     SUM( premiums.net_prem ) AS sumofnet_prem, " +
            "     SUM( premiums.gross_prem + COALESCE( premiums.adjud_per_lives, 0::NUMERIC )+ COALESCE( premiums.admin_per_lives, 0::NUMERIC )+ COALESCE( premiums.comm_per_lives, 0::NUMERIC )) AS gross, " +
            "     SUM( premiums.admin_allow + COALESCE( premiums.admin_per_lives, 0::NUMERIC )) AS admin, " +
            "     SUM( premiums.commission + COALESCE( premiums.comm_per_lives, 0::NUMERIC )) AS comm, " +
            "     SUM( premiums.net_prem + COALESCE( premiums.adjud_per_lives, 0::NUMERIC )) AS net " +
            "    FROM " +
            "     premiums " +
            "    GROUP BY " +
            "     premiums.underwriter_id, " +
            "     premiums.policy_number, " +
            "     premiums.month_id, " +
            "     premiums.group_id " +
            "    HAVING " +
            "     premiums.month_id = getcurrentbillingdate() " +
            "   ) pt " +
            "  JOIN underwriter ON " +
            "   pt.underwriter_id = underwriter.underwriter_id " +
            "  JOIN invoice ON " +
            "   pt.group_id = invoice.group_id " +
            "   AND pt.month_id = invoice.month_id " +
            "  LEFT JOIN( " +
            "    SELECT " +
            "     pat.group_id, " +
            "     pat.policy_number, " +
            "     pat.underwriter_id, " +
            "     v_premiumadjust014.prem014 " +
            "    FROM " +
            "     ( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.month_id = getcurrentbillingdate() " +
            "      GROUP BY " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.month_id, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "     ) pat " +
            "    LEFT JOIN( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id, " +
            "       premiums_adjust.vol_single AS vol014, " +
            "       premiums_adjust.gross_prem AS prem014 " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.ben_num = 14 " +
            "       AND premiums_adjust.month_id = getcurrentbillingdate() " +
            "     ) v_premiumadjust014 ON " +
            "     pat.prem_code = v_premiumadjust014.prem_code " +
            "     AND pat.policy_number::TEXT = v_premiumadjust014.policy_number::TEXT " +
            "     AND pat.underwriter_id = v_premiumadjust014.underwriter_id " +
            "     AND pat.group_id = v_premiumadjust014.group_id " +
            "    WHERE " +
            "     pat.prem_code = 'RA'::BPCHAR " +
            "   ) ra ON " +
            "   pt.group_id = ra.group_id " +
            "   AND pt.policy_number::TEXT = ra.policy_number::TEXT " +
            "   AND pt.underwriter_id = ra.underwriter_id " +
            "  LEFT JOIN( " +
            "    SELECT " +
            "     pat.group_id, " +
            "     pat.policy_number, " +
            "     pat.underwriter_id, " +
            "     v_premiumadjust014.prem014 " +
            "    FROM " +
            "     ( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.month_id = getcurrentbillingdate() " +
            "      GROUP BY " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.month_id, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "     ) pat " +
            "    LEFT JOIN( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id, " +
            "       premiums_adjust.vol_single AS vol014, " +
            "       premiums_adjust.gross_prem AS prem014 " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.ben_num = 14 " +
            "       AND premiums_adjust.month_id = getcurrentbillingdate() " +
            "     ) v_premiumadjust014 ON " +
            "     pat.prem_code = v_premiumadjust014.prem_code " +
            "     AND pat.policy_number::TEXT = v_premiumadjust014.policy_number::TEXT " +
            "     AND pat.underwriter_id = v_premiumadjust014.underwriter_id " +
            "     AND pat.group_id = v_premiumadjust014.group_id " +
            "    WHERE " +
            "     pat.prem_code = 'RC'::BPCHAR " +
            "   ) rc ON " +
            "   pt.group_id = rc.group_id " +
            "   AND pt.policy_number::TEXT = rc.policy_number::TEXT " +
            "   AND pt.underwriter_id = rc.underwriter_id " +
            "  LEFT JOIN( " +
            "    SELECT " +
            "     pat.group_id, " +
            "     pat.policy_number, " +
            "     pat.underwriter_id, " +
            "     v_premiumadjust014.prem014 " +
            "    FROM " +
            "     ( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.month_id = getcurrentbillingdate() " +
            "      GROUP BY " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.month_id, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id " +
            "     ) pat " +
            "    LEFT JOIN( " +
            "      SELECT " +
            "       premiums_adjust.underwriter_id, " +
            "       premiums_adjust.policy_number, " +
            "       premiums_adjust.prem_code, " +
            "       premiums_adjust.group_id, " +
            "       premiums_adjust.vol_single AS vol014, " +
            "       premiums_adjust.gross_prem AS prem014 " +
            "      FROM " +
            "       premiums_adjust " +
            "      WHERE " +
            "       premiums_adjust.ben_num = 14 " +
            "       AND premiums_adjust.month_id = getcurrentbillingdate() " +
            "     ) v_premiumadjust014 ON " +
            "     pat.prem_code = v_premiumadjust014.prem_code " +
            "     AND pat.policy_number::TEXT = v_premiumadjust014.policy_number::TEXT " +
            "     AND pat.underwriter_id = v_premiumadjust014.underwriter_id " +
            "     AND pat.group_id = v_premiumadjust014.group_id " +
            "    WHERE " +
            "     pat.prem_code = 'RT'::BPCHAR " +
            "   ) rt ON " +
            "   pt.group_id = rt.group_id " +
            "   AND pt.policy_number::TEXT = rt.policy_number::TEXT " +
            "   AND pt.underwriter_id = rt.underwriter_id " +
            "  LEFT JOIN( " +
            "    SELECT " +
            "     premiums.underwriter_id, " +
            "     premiums.policy_number, " +
            "     premiums.group_id, " +
            "     premiums.vol_single AS livs014, " +
            "     premiums.vol_couple AS livc014, " +
            "     premiums.gross_prem AS gross014, " +
            "     premiums.admin_allow AS admin014, " +
            "     premiums.commission AS comm014, " +
            "     premiums.net_prem AS net014, " +
            "     premiums.comm_rate AS comm_rate014, " +
            "     premiums.admin_rate AS admin_rate014 " +
            "    FROM " +
            "     premiums " +
            "    WHERE " +
            "     premiums.ben_num = 14 " +
            "     AND premiums.month_id = getcurrentbillingdate() " +
            "   ) premium014 ON " +
            "   pt.group_id = premium014.group_id " +
            "   AND pt.policy_number::TEXT = premium014.policy_number::TEXT " +
            "   AND pt.underwriter_id = premium014.underwriter_id " +
            " ) premium " +
            "LEFT JOIN( " +
            "  SELECT " +
            "   pstforpremiumreport.group_id, " +
            "   pstforpremiumreport.policy_number, " +
            "   pstforpremiumreport.underwriter_id, " +
            "   SUM( CASE WHEN pstforpremiumreport.ben_num = 14 AND pstforpremiumreport.province = 'ON'::BPCHAR THEN pstforpremiumreport.pst ELSE 0::NUMERIC END ) AS prov1_014, " +
            "   SUM( CASE WHEN pstforpremiumreport.ben_num = 14 AND pstforpremiumreport.province = 'QC'::BPCHAR THEN pstforpremiumreport.pst ELSE 0::NUMERIC END ) AS prov2_014 " +
            "  FROM " +
            "   pstforpremiumreport " +
            "  WHERE " +
            "   pstforpremiumreport.month_id = getcurrentbillingdate() " +
            "  GROUP BY " +
            "   pstforpremiumreport.underwriter_id, " +
            "   pstforpremiumreport.policy_number, " +
            "   pstforpremiumreport.group_id " +
            " ) pst ON " +
            " premium.group_id = pst.group_id " +
            " AND premium.ptpolicy_number::TEXT = pst.policy_number::TEXT " +
            " AND premium.ptunderwriter_id = pst.underwriter_id " +
            "WHERE " +
            " premium.ptunderwriter_id = 93032 " +
            " AND hasiapci( premium.group_id, premium.ptpolicy_number, 14 ) " +
            " OR( " +
            "  premium.ptpolicy_number::TEXT IN( " +
            "   SELECT " +
            "    premiums.policy_number " +
            "   FROM " +
            "    premiums " +
            "   WHERE " +
            "    premiums.policy_number::TEXT = premium.ptpolicy_number::TEXT " +
            "    AND premiums.ben_num = 14 " +
            "    AND premiums.month_id = premium.month_id " +
            "  ) " +
            " ) ORDER BY division_name";

    public IAPPremiumReportDAOImpl( DataSource dataSource )
    {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    @Override
    public ArrayList<IAPPremiumDSAIGroup> getIAPPremiumReportDSAIGroupList()
    {
        ArrayList<IAPPremiumDSAIGroup> iapDSAIGroupList = new ArrayList<>();
        //pol_no, group_id, ptpolicy_number, div_number, division_name, lives_single, lives_couple, current_prem, retro_prem, pst, raprem014, rcprem014, rtprem014
        jdbcTemplate.query(IAP_DSAI_PREMIUM_QRY,
                           rs ->
                           {
                               iapDSAIGroupList.add(new IAPPremiumDSAIGroup(rs.getString("division_name"),
                                                                            rs.getString("pol_no"),
                                                                            rs.getInt("div_number"),
                                                                            rs.getInt("lives_single"),
                                                                            rs.getInt("lives_couple"),
                                                                            rs.getFloat("current_prem"),
                                                                            rs.getFloat("retro_prem"),
                                                                            rs.getFloat("pst")));
                           });
        return iapDSAIGroupList;
    }

    @Override
    public ArrayList<IAPPremiumADDGroup> getIAPPremiumReportADDGroupList()
    {
        ArrayList<IAPPremiumADDGroup> iapADDGroupList = new ArrayList<>();

        jdbcTemplate.query(IAP_ADD_PREMIUM_QRY,
                           rs ->
                           {
                               iapADDGroupList.add(new IAPPremiumADDGroup(rs.getString("pol_no"),
                                                                                  rs.getString("div_number"),
                                                                                  rs.getString("division_name"),
                                                                                  rs.getFloat("adminrate"),
                                                                                  rs.getFloat("commrate"),
                                                                                  null,
                                                                                  rs.getDate("nextrenew"),
                                                                                  rs.getString("status"),
                                                                                  rs.getString("empprovsplit"),
                                                                                  null,
                                                                                  rs.getInt("current_no_of_lives"),
                                                                                  rs.getFloat("current_volume_of_ins"),
                                                                                  rs.getFloat("grossrate"),
                                                                                  null,
                                                                                  null,
                                                                                  null,
                                                                                  null,
                                                                                  null,
                                                                                  null,
                                                                                  null,
                                                                                  null,
                                                                                  null,
                                                                                  null,
                                                                                  rs.getFloat("gross_prem"),
                                                                                  rs.getFloat("adjust"),
                                                                                  rs.getFloat("admin_fee"),
                                                                                  rs.getFloat("commission"),
                                                                                  rs.getFloat("totalcommission"),
                                                                                  rs.getFloat("on_tax"),
                                                                                  rs.getFloat("qc_tax"),
                                                                                  rs.getFloat("mb_tax"),
                                                                                  rs.getFloat("net_prem_paid"),
                                                                                  rs.getString("adjustcomments")));
                           });
        return iapADDGroupList;
    }

    @Override
    public ArrayList<IAPPremiumOptionalADDGroup> getIAPPremiumReportOptionalADDGroupList()
    {
        ArrayList<IAPPremiumOptionalADDGroup> iapOptionalADDGroupList = new ArrayList<>();
        jdbcTemplate.query(IAP_OPTIONAL_ADD_PREMIUM_QRY,
                           rs ->
                           {
                               iapOptionalADDGroupList.add(new IAPPremiumOptionalADDGroup(rs.getString("pol_no"),
                                                                                          rs.getString("div_number"),
                                                                                          rs.getString("division_name"),
                                                                                          rs.getFloat("admin_rate"),
                                                                                          rs.getFloat("comm_rate"),
                                                                                          rs.getFloat("totalcommission"),
                                                                                          rs.getDate("effdate"),
                                                                                          rs.getString("status"),
                                                                                          rs.getString("empprovsplit"),
                                                                                          rs.getInt("current_no_of_lives"),
                                                                                          rs.getFloat("currentsinglevolume"),
                                                                                          rs.getFloat("currentfamilyvolume"),
                                                                                          rs.getFloat("currentsinglerate"),
                                                                                          rs.getFloat("currentfamilyrate"),
                                                                                          rs.getFloat("total_gross_prem"),
                                                                                          rs.getFloat("adjust"),
                                                                                          rs.getFloat("admin_fee"),
                                                                                          rs.getFloat("commission"),
                                                                                          rs.getFloat("on_tax"),
                                                                                          rs.getFloat("qc_tax"),
                                                                                          rs.getFloat("net_prem_paid"),
                                                                                          rs.getString("adjustcomments")));
                           });

        return iapOptionalADDGroupList;
    }

    @Override
    public ArrayList<IAPPremiumGroup> getIAPPremiumReportGroupList()
    {
        ArrayList<IAPPremiumGroup> iapGroupList = new ArrayList<>();

        jdbcTemplate.query(IAP_PREMIUM_QRY,
                           rs ->
                           {
                               iapGroupList.add(new IAPPremiumGroup(rs.getString("pol_no"),
                                                                    rs.getString("div_number"),
                                                                    rs.getString("division_name"),
                                                                    rs.getFloat("commrate"),
                                                                    rs.getDate("effdate"),
                                                                    rs.getDate("nextrenew"),
                                                                    rs.getString("status"),
                                                                    rs.getString("empprovsplit"),
                                                                    rs.getString("benefitamount"),
                                                                    rs.getInt("lives"),
                                                                    rs.getFloat("current_volume_of_ins"),
                                                                    rs.getFloat("grossrate"),
                                                                    rs.getFloat("gross_prem"),
                                                                    rs.getString("spousebenefitamount"),
                                                                    rs.getInt("spouselives"),
                                                                    rs.getFloat("spouse_volume"),
                                                                    rs.getFloat("spousetotalpremium"),
                                                                    rs.getString("spouserate"),
                                                                    rs.getString("dependentbenefitamount"),
                                                                    rs.getInt("dependent_unit"),
                                                                    rs.getFloat("dependentrate"),
                                                                    rs.getFloat("dependenttotalpremium"),
                                                                    rs.getFloat("gross_prem"),
                                                                    rs.getFloat("adjust"),
                                                                    rs.getFloat("admin_fee"),
                                                                    rs.getFloat("commission"),
                                                                    rs.getFloat("totalcommission"),
                                                                    rs.getFloat("on_tax"),
                                                                    rs.getFloat("qc_tax"),
                                                                    rs.getFloat("mb_tax"),
                                                                    rs.getFloat("net_prem_paid"),
                                                                    rs.getString("adjustcomments")));
                           });
        return iapGroupList;
    }
}
