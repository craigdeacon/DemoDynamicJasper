package com.DAO.CignaUpload;

import com.Component.ME.CignaUpload.container.CignaUploadGroup;
import com.DAO.EAPReport.EAPReportDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;

@Component
public class CignaUploadDAOImpl implements CignaUploadDAO
{
    JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(CignaUploadDAOImpl.class.getName());
    
    private static final String CIGNA_UPLOAD_LIST_QRY_STR = "SELECT " +
            " vw.policy_number, " +
            " vw.month_id, " +
            " short_benefit_name AS TYPE, " +
            " liv01 AS lives, " +
            " ( " +
            "  vol01 + COALESCE( " +
            "   retro_vol, " +
            "   0 " +
            "  ) " +
            " )::DOUBLE PRECISION AS volume, " +
            " net01::DOUBLE PRECISION AS net_prem, " +
            " rate, " +
            " admin01::DOUBLE PRECISION AS admin_allow, " +
            " comm01::DOUBLE PRECISION AS comm_rate, " +
            " gross_total01 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 1 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " )::DOUBLE PRECISION AS PST " +
            "FROM " +
            " vw_Premium01 vw " +
            "INNER JOIN ben01 ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "LEFT JOIN( " +
            "  SELECT " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num, " +
            "   SUM( vol_doll ) AS retro_vol " +
            "  FROM " +
            "   premiums_adjust " +
            "  WHERE " +
            "   ben_num = 1 " +
            "  GROUP BY " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num " +
            " ) AS pa ON " +
            " vw.group_id = pa.group_id " +
            " AND vw.month_id = pa.month_id " +
            " AND vw.underwriter_id = pa.underwriter_id " +
            " AND vw.policy_number = pa.policy_number " +
            " AND vw.ben_num = pa.ben_num " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            " AND rate IS NOT NULL " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " vw.month_id, " +
            " short_benefit_name AS TYPE, " +
            " liv02 AS lives, " +
            " ( " +
            "  vol02 + COALESCE( " +
            "   retro_vol, " +
            "   0 " +
            "  ) " +
            " ) AS volume, " +
            " net02 AS net_prem, " +
            " rate, " +
            " admin02 AS admin_allow, " +
            " comm02 AS comm_rate, " +
            " gross_total02 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 2 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium02 vw " +
            "INNER JOIN ben02 ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "LEFT JOIN( " +
            "  SELECT " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num, " +
            "   SUM( vol_doll ) AS retro_vol " +
            "  FROM " +
            "   premiums_adjust " +
            "  WHERE " +
            "   ben_num = 2 " +
            "  GROUP BY " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num " +
            " ) AS pa ON " +
            " vw.group_id = pa.group_id " +
            " AND vw.month_id = pa.month_id " +
            " AND vw.underwriter_id = pa.underwriter_id " +
            " AND vw.policy_number = pa.policy_number " +
            " AND vw.ben_num = pa.ben_num " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            " AND rate IS NOT NULL " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " month_id, " +
            " short_benefit_name AS TYPE, " +
            " liv03 AS lives, " +
            " NULL AS volume, " +
            " net03 AS net_prem, " +
            " rate, " +
            " admin03 AS admin_allow, " +
            " comm03 AS comm_rate, " +
            " gross_total03 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 3 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium03 vw " +
            "INNER JOIN ben03 ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            " AND rate IS NOT NULL " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " vw.month_id, " +
            " short_benefit_name AS TYPE, " +
            " liv04 AS lives, " +
            " ( " +
            "  vol04 + COALESCE( " +
            "   retro_vol, " +
            "   0 " +
            "  ) " +
            " ) AS volume, " +
            " net04 AS net_prem, " +
            " rate, " +
            " admin04 AS admin_allow, " +
            " comm04 AS comm_rate, " +
            " gross_total04 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 4 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium04 vw " +
            "INNER JOIN ben04 ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "LEFT JOIN( " +
            "  SELECT " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num, " +
            "   SUM( vol_doll ) AS retro_vol " +
            "  FROM " +
            "   premiums_adjust " +
            "  WHERE " +
            "   ben_num = 4 " +
            "  GROUP BY " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num " +
            " ) AS pa ON " +
            " vw.group_id = pa.group_id " +
            " AND vw.month_id = pa.month_id " +
            " AND vw.underwriter_id = pa.underwriter_id " +
            " AND vw.policy_number = pa.policy_number " +
            " AND vw.ben_num = pa.ben_num " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            " AND rate IS NOT NULL " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " vw.month_id, " +
            " short_benefit_name AS TYPE, " +
            " liv05 AS lives, " +
            " ( " +
            "  vol05 + COALESCE( " +
            "   retro_vol, " +
            "   0 " +
            "  ) " +
            " ) AS volume, " +
            " net05 AS net_prem, " +
            " ( " +
            "  CASE " +
            "   WHEN( " +
            "    SELECT " +
            "     rate " +
            "    FROM " +
            "     ben05 " +
            "    WHERE " +
            "     group_id = vw.group_id " +
            "     AND option_id = 0 " +
            "     AND coverage = 3 LIMIT 1 " +
            "   ) IS NULL THEN( " +
            "    SELECT " +
            "     rate " +
            "    FROM " +
            "     ben05h " +
            "    WHERE " +
            "     group_id = vw.group_id " +
            "     AND option_id = 0 " +
            "     AND coverage = 3 " +
            "    ORDER BY " +
            "     month_id DESC LIMIT 1 " +
            "   ) " +
            "   ELSE( " +
            "    SELECT " +
            "     rate " +
            "    FROM " +
            "     ben05 " +
            "    WHERE " +
            "     group_id = vw.group_id " +
            "     AND option_id = 0 " +
            "     AND coverage = 3 LIMIT 1 " +
            "   ) " +
            "  END " +
            " ) AS rate, " +
            " admin05 AS admin_allow, " +
            " comm05 AS comm_rate, " +
            " gross_total05 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 5 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium05 vw " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " vw.group_id = group_summary.group_id " +
            "LEFT JOIN( " +
            "  SELECT " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num, " +
            "   SUM( vol_doll ) AS retro_vol " +
            "  FROM " +
            "   premiums_adjust " +
            "  WHERE " +
            "   ben_num = 5 " +
            "  GROUP BY " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num " +
            " ) AS pa ON " +
            " vw.group_id = pa.group_id " +
            " AND vw.month_id = pa.month_id " +
            " AND vw.underwriter_id = pa.underwriter_id " +
            " AND vw.policy_number = pa.policy_number " +
            " AND vw.ben_num = pa.ben_num " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND( " +
            "  ( " +
            "   CASE " +
            "    WHEN( " +
            "     SELECT " +
            "      rate " +
            "     FROM " +
            "      ben05 " +
            "     WHERE " +
            "      group_id = vw.group_id " +
            "      AND option_id = 0 " +
            "      AND coverage = 3 LIMIT 1 " +
            "    ) IS NULL THEN( " +
            "     SELECT " +
            "      rate " +
            "     FROM " +
            "      ben05h " +
            "     WHERE " +
            "      group_id = vw.group_id " +
            "      AND option_id = 0 " +
            "      AND coverage = 3 " +
            "     ORDER BY " +
            "      month_id DESC LIMIT 1 " +
            "    ) " +
            "    ELSE( " +
            "     SELECT " +
            "      rate " +
            "     FROM " +
            "      ben05 " +
            "     WHERE " +
            "      group_id = vw.group_id " +
            "      AND option_id = 0 " +
            "      AND coverage = 3 LIMIT 1 " +
            "    ) " +
            "   END " +
            "  ) " +
            " ) IS NOT NULL " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " month_id, " +
            " short_benefit_name AS TYPE, " +
            " NULL AS lives, " +
            " NULL AS volume, " +
            " net06 AS net_prem, " +
            " NULL AS rate, " +
            " admin06 AS admin_allow, " +
            " comm06 AS comm_rate, " +
            " gross_total06 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 6 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium06 vw " +
            "INNER JOIN ben06 ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " month_id, " +
            " short_benefit_name AS TYPE, " +
            " NULL AS lives, " +
            " NULL AS volume, " +
            " net07 AS net_prem, " +
            " NULL AS rate, " +
            " admin07 AS admin_allow, " +
            " comm07 AS comm_rate, " +
            " gross_total07 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 7 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium07 vw " +
            "INNER JOIN ben07 ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " vw.month_id, " +
            " short_benefit_name AS TYPE, " +
            " liv08 AS lives, " +
            " ( " +
            "  vol08 + COALESCE( " +
            "   retro_vol, " +
            "   0 " +
            "  ) " +
            " ) AS volume, " +
            " net08 AS net_prem, " +
            " NULL AS rate, " +
            " admin08 AS admin_allow, " +
            " comm08 AS comm_rate, " +
            " gross_total08 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 8 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium08 vw " +
            "INNER JOIN ben08 ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "LEFT JOIN( " +
            "  SELECT " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num, " +
            "   SUM( vol_doll ) AS retro_vol " +
            "  FROM " +
            "   premiums_adjust " +
            "  WHERE " +
            "   ben_num = 8 " +
            "  GROUP BY " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num " +
            " ) AS pa ON " +
            " vw.group_id = pa.group_id " +
            " AND vw.month_id = pa.month_id " +
            " AND vw.underwriter_id = pa.underwriter_id " +
            " AND vw.policy_number = pa.policy_number " +
            " AND vw.ben_num = pa.ben_num " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " vw.month_id, " +
            " short_benefit_name AS TYPE, " +
            " liv09 AS lives, " +
            " ( " +
            "  vol09 + COALESCE( " +
            "   retro_vol, " +
            "   0 " +
            "  ) " +
            " ) AS volume, " +
            " net09 AS net_prem, " +
            " NULL AS rate, " +
            " admin09 AS admin_allow, " +
            " comm09 AS comm_rate, " +
            " gross_total09 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 9 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium09 vw " +
            "INNER JOIN ben09 ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "LEFT JOIN( " +
            "  SELECT " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num, " +
            "   SUM( vol_doll ) AS retro_vol " +
            "  FROM " +
            "   premiums_adjust " +
            "  WHERE " +
            "   ben_num = 9 " +
            "  GROUP BY " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num " +
            " ) AS pa ON " +
            " vw.group_id = pa.group_id " +
            " AND vw.month_id = pa.month_id " +
            " AND vw.underwriter_id = pa.underwriter_id " +
            " AND vw.policy_number = pa.policy_number " +
            " AND vw.ben_num = pa.ben_num " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " vw.month_id, " +
            " short_benefit_name AS TYPE, " +
            " liv010 AS lives, " +
            " ( " +
            "  vol010 + COALESCE( " +
            "   retro_vol, " +
            "   0 " +
            "  ) " +
            " ) AS volume, " +
            " net010 AS net_prem, " +
            " rate010 AS rate, " +
            " admin010 AS admin_allow, " +
            " comm010 AS comm_rate, " +
            " gross_total010 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 10 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium010 vw " +
            "INNER JOIN ben010 ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "LEFT JOIN( " +
            "  SELECT " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num, " +
            "   SUM( COALESCE( vol_doll, 0 )+ COALESCE( vol_couple, 0 )) AS retro_vol " +
            "  FROM " +
            "   premiums_adjust " +
            "  WHERE " +
            "   ben_num = 10 " +
            "  GROUP BY " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num " +
            " ) AS pa ON " +
            " vw.group_id = pa.group_id " +
            " AND vw.month_id = pa.month_id " +
            " AND vw.underwriter_id = pa.underwriter_id " +
            " AND vw.policy_number = pa.policy_number " +
            " AND vw.ben_num = pa.ben_num " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            " AND rate IS NOT NULL " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " vw.month_id, " +
            " short_benefit_name AS TYPE, " +
            " NULL AS lives, " +
            " ( " +
            "  vol011 + COALESCE( " +
            "   retro_vol, " +
            "   0 " +
            "  ) " +
            " ) AS volume, " +
            " net011 AS net_prem, " +
            " NULL AS rate, " +
            " admin011 AS admin_allow, " +
            " comm011 AS comm_rate, " +
            " gross_total011 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 11 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium011 vw " +
            "INNER JOIN ben011 ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "LEFT JOIN( " +
            "  SELECT " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num, " +
            "   SUM( vol_doll ) AS retro_vol " +
            "  FROM " +
            "   premiums_adjust " +
            "  WHERE " +
            "   ben_num = 11 " +
            "  GROUP BY " +
            "   group_id, " +
            "   month_id, " +
            "   policy_number, " +
            "   underwriter_id, " +
            "   ben_num " +
            " ) AS pa ON " +
            " vw.group_id = pa.group_id " +
            " AND vw.month_id = pa.month_id " +
            " AND vw.underwriter_id = pa.underwriter_id " +
            " AND vw.policy_number = pa.policy_number " +
            " AND vw.ben_num = pa.ben_num " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " month_id, " +
            " short_benefit_name AS TYPE, " +
            " liv012 AS lives, " +
            " NULL AS volume, " +
            " net012 AS net_prem, " +
            " NULL AS rate, " +
            " admin012 AS admin_allow, " +
            " comm012 AS comm_rate, " +
            " gross_total012 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 12 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium012 vw " +
            "INNER JOIN benvar ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            " AND ben.ben_num = 12 " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " month_id, " +
            " short_benefit_name AS TYPE, " +
            " liv013 AS lives, " +
            " NULL AS volume, " +
            " net013 AS net_prem, " +
            " NULL AS rate, " +
            " admin013 AS admin_allow, " +
            " comm013 AS comm_rate, " +
            " gross_total013 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 13 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium013 vw " +
            "INNER JOIN benvar ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            " AND ben.ben_num = 13 " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " month_id, " +
            " short_benefit_name AS TYPE, " +
            " NULL AS lives, " +
            " NULL AS volume, " +
            " net014 AS net_prem, " +
            " NULL AS rate, " +
            " admin014 AS admin_allow, " +
            " comm014 AS comm_rate, " +
            " gross_total014 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 14 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium014 vw " +
            "INNER JOIN benvar ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            " AND ben.ben_num = 14 " +
            "UNION SELECT " +
            " vw.policy_number, " +
            " month_id, " +
            " short_benefit_name AS TYPE, " +
            " NULL AS lives, " +
            " NULL AS volume, " +
            " net015 AS net_prem, " +
            " NULL AS rate, " +
            " admin015 AS admin_allow, " +
            " comm015 AS comm_rate, " +
            " gross_total015 AS gross_prem, " +
            " employer, " +
            " ( " +
            "  SELECT " +
            "   COALESCE( " +
            "    SUM( pst ), " +
            "    0 " +
            "   ) " +
            "  FROM " +
            "   pstforPremiumreport AS pfp " +
            "  WHERE " +
            "   vw.group_id = pfp.group_id " +
            "   AND vw.policy_number = pfp.policy_number " +
            "   AND vw.underwriter_id = pfp.underwriter_id " +
            "   AND pfp.ben_num = 15 " +
            "   AND month_id =( " +
            "    SELECT " +
            "     dateval " +
            "    FROM " +
            "     settings " +
            "    WHERE " +
            "     var_id = 'CURRENT_MONTH' " +
            "   ) " +
            " ) AS PST " +
            "FROM " +
            " vw_Premium015 vw " +
            "INNER JOIN benvar ben ON " +
            " vw.group_id = ben.group_id " +
            "INNER JOIN benefit_types ON " +
            " vw.ben_num = benefit_types.id " +
            "INNER JOIN group_summary ON " +
            " ben.group_id = group_summary.group_id " +
            "WHERE " +
            " vw.underwriter_id = 601072 " +
            " AND ben.option_id = 0 " +
            " AND ben.ben_num = 15";

    @Autowired
    public CignaUploadDAOImpl( DataSource dataSource )
    {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    @Override
    public ArrayList<CignaUploadGroup> getCignaUploadGroups()
    {
        ArrayList<CignaUploadGroup> cignaUploadList = new ArrayList<>();
        jdbcTemplate.query(CIGNA_UPLOAD_LIST_QRY_STR,
                           rs ->
                           {
                               Date month_id = rs.getDate("month_id");
                               Calendar cal = Calendar.getInstance();
                               cal.setTime(month_id);

                               cignaUploadList.add(new CignaUploadGroup(cal.get(Calendar.MONTH),
                                                                        cal.get(Calendar.YEAR),
                                                                        rs.getString("type"),
                                                                        rs.getString("policy_number"),
                                                                        rs.getInt("lives"),
                                                                        rs.getFloat("volume"),
                                                                        rs.getFloat("gross_prem"),
                                                                        rs.getFloat("pst"),
                                                                        rs.getFloat("rate"),
                                                                        rs.getString("employer"),
                                                                        rs.getFloat("comm_rate"),
                                                                        rs.getFloat("admin_allow"),
                                                                        rs.getFloat("net_prem")));
                           });
        return cignaUploadList;
    }
}
