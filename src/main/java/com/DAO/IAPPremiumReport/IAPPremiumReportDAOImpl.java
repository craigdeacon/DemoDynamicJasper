package com.DAO.IAPPremiumReport;

import com.Component.ME.IAPPremiumReport.container.IAPPremiumADDGroup;
import com.Component.ME.IAPPremiumReport.container.IAPPremiumDSAIGroup;
import com.DAO.EAPReport.EAPReportDAOImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.logging.Logger;

@Component
public class IAPPremiumReportDAOImpl implements IAPPremiumReportDAO
{
    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(EAPReportDAOImpl.class.getName());

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
}
