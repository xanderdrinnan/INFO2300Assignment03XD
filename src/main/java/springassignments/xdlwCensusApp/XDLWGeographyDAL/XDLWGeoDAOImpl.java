package springassignments.xdlwCensusApp.XDLWGeographyDAL;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springassignments.xdlwCensusApp.models.XDLWAgeModel;
import springassignments.xdlwCensusApp.models.XDLWGeographyModel;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class XDLWGeoDAOImpl implements XDLWGeoDAO {

    private JdbcTemplate jdbcTemp;

    public XDLWGeoDAOImpl(DataSource dataSource) {
        jdbcTemp = new JdbcTemplate(dataSource);
    }

    @Override
    public List<XDLWGeographyModel> geoList(Integer getLevel) {
        List<XDLWGeographyModel> list = jdbcTemp.query("SELECT * FROM geographicarea WHERE level = " + getLevel, new RowMapper<XDLWGeographyModel>() {

            @Override
            public XDLWGeographyModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                XDLWGeographyModel geo = new XDLWGeographyModel();

                geo.setGeographicAreaID(rs.getInt("geographicAreaID"));
                geo.setCode(rs.getInt("code"));
                geo.setLevel(rs.getInt("level"));
                geo.setName(rs.getString("name"));
                geo.setAlternativeCode(rs.getInt("alternativeCode"));

                return geo;
            }

        });

        return list;
    }

    @Override
    public List<XDLWGeographyModel> keywordList(String getKeyword) {
        List<XDLWGeographyModel> list = jdbcTemp.query("SELECT * FROM geographicarea WHERE name LIKE '%" + getKeyword + "%'", new RowMapper<XDLWGeographyModel>() {

            @Override
            public XDLWGeographyModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                XDLWGeographyModel geo = new XDLWGeographyModel();

                geo.setGeographicAreaID(rs.getInt("geographicAreaID"));
                geo.setCode(rs.getInt("code"));
                geo.setLevel(rs.getInt("level"));
                geo.setName(rs.getString("name"));
                geo.setAlternativeCode(rs.getInt("alternativeCode"));

                return geo;
            }

        });

        return list;
    }

    @Override
    public List<XDLWAgeModel> populationList(Integer altCode) {
        List<XDLWAgeModel> list = jdbcTemp.query("SELECT geo.name, geo.code, geo.alternativeCode, geo.level,\n" + "\n" +
                "(SELECT FORMAT(SUM(a.combined), 0)\n" +
                "FROM AGE a\n" +
                "JOIN GEOGRAPHICAREA geo\n" +
                "ON a.geographicArea = geo.geographicAreaID\n" +
                "WHERE a.censusYear = 1\n" +
                "AND a.ageGroup = 1\n" +
                "AND geo.alternativeCode = " + altCode + ") AS 'Total Population'\n" +
                "FROM GEOGRAPHICAREA geo\n" +
                "WHERE geo.alternativeCode = " + altCode, new RowMapper<XDLWAgeModel>() {

            @Override
            public XDLWAgeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                XDLWGeographyModel geo = new XDLWGeographyModel();
                XDLWAgeModel age = new XDLWAgeModel();

                age.setXDLWGeographModel(geo);
                age.getXDLWGeographModel().setName(rs.getString("name"));
                age.getXDLWGeographModel().setAlternativeCode(rs.getInt("alternativeCode"));
                age.getXDLWGeographModel().setCode(rs.getInt("code"));
                age.getXDLWGeographModel().setLevel(rs.getInt("level"));
                age.getXDLWGeographModel().setGetTotalPop(rs.getString("Total Population"));


                return age;
            }

        });

        return list;
    }
}
