package springassignments.xdlwCensusApp.XDLWGeographyDAL;

import springassignments.xdlwCensusApp.models.XDLWAgeModel;
import springassignments.xdlwCensusApp.models.XDLWGeographyModel;

import java.util.List;

public interface XDLWGeoDAO {
    public List<XDLWGeographyModel> geoList(Integer getLevel);
    public List<XDLWGeographyModel> keywordList(String getKeyword);
    public List<XDLWAgeModel> populationList(Integer altCode);
}
