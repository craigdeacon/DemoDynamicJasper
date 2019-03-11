package com.DAO.EAPReport;

import com.Component.ME.EAPReport.container.EapGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.logging.Logger;

@Component
public class EAPReportDAOImpl implements EAPReportDAO
{
    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(EAPReportDAOImpl.class.getName());
    
    private static final String EAP_GROUP_LIST_QRY_STR = "SELECT " +
            " row_number() OVER (ORDER BY employer NULLS LAST) AS rownum," +
            " premiums.group_id, " +
            " premiums.policy_number, " +
            " group_summary.employer, " +
            " premiums.month_id::DATE, " +
            " eap_effective.effective::DATE, " +
            " employee.prov_res, " +
            " COUNT( 1 ) AS lives, " +
            " NOW()::DATE AS current_date " +
            "FROM " +
            " employee " +
            "INNER JOIN benvar ON " +
            " employee.group_id = benvar.group_id " +
            " AND employee.class_id = benvar.class_id " +
            " AND benvar.ben_num = 12 " +
            "INNER JOIN premiums ON " +
            " benvar.group_id = premiums.group_id " +
            " AND benvar.policy_number = premiums.policy_number " +
            " AND premiums.ben_num = 12 " +
            "INNER JOIN group_summary ON " +
            " group_summary.group_id = employee.group_id, " +
            " LATERAL( " +
            "  SELECT " +
            "   effective " +
            "  FROM " +
            "   benvar " +
            "  WHERE " +
            "   group_id = premiums.group_id " +
            "   AND coverage = 3 " +
            "   AND option_id = 0 " +
            "   AND class_id = employee.class_id " +
            "   AND ben_num = 12 LIMIT 1 " +
            " ) AS eap_effective " +
            "WHERE " +
            " premiums.underwriter_id = ? " +
            " AND premiums.month_id = ( SELECT dateval FROM settings WHERE var_id = 'CURRENT_MONTH' ) " +
            " AND( " +
            "  employee.termination IS NULL " +
            "  OR employee.reinstate > employee.termination " +
            "  OR employee.termination > getdate() " +
            " ) " +
            " AND COALESCE( employee.ltd_prem, 0 )> 0 " +
            "GROUP BY " +
            " premiums.group_id, " +
            " premiums.policy_number, " +
            " employer, " +
            " month_id, " +
            " eap_effective.effective, " +
            " employee.prov_res, " +
            " current_date " +
            "ORDER BY " +
            " employer";

    @Autowired
    public EAPReportDAOImpl( DataSource dataSource )
    {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    @Override
    public ArrayList<EapGroup> getGroupList(int underwriterId, final String insurer)
    {
        ArrayList<EapGroup> eapList = new ArrayList<>();

        jdbcTemplate.query(EAP_GROUP_LIST_QRY_STR,
                           ps ->
                           {
                                ps.setInt(1, underwriterId);
                           },
                           rs ->
                           {
                               eapList.add(new EapGroup(rs.getInt("rownum"),
                                                        rs.getDate("current_date"),
                                                        rs.getInt("group_id"),
                                                        rs.getString("policy_number"),
                                                        rs.getString("employer"),
                                                        rs.getDate("month_id"),
                                                        rs.getDate("effective"),
                                                        insurer,
                                                        "EAP",
                                                        rs.getString("prov_res"),
                                                        rs.getInt("lives")));
                           });
        return eapList;
    }
}
