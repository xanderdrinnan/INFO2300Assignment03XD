package springassignments.xdlwCensusApp.models;

public class XDLWAgeModel {
    public int getAgeId() {
        return ageId;
    }

    public void setAgeId(int ageId) {
        this.ageId = ageId;
    }

    public int getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(int ageGroup) {
        this.ageGroup = ageGroup;
    }

    public int getCensusYear() {
        return censusYear;
    }

    public void setCensusYear(int censusYear) {
        this.censusYear = censusYear;
    }

    public int getGeographicArea() {
        return geographicArea;
    }

    public void setGeographicArea(int geographicArea) {
        this.geographicArea = geographicArea;
    }

    public int getCombined() {
        return combined;
    }

    public void setCombined(int combined) {
        this.combined = combined;
    }

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getFemale() {
        return female;
    }

    public void setFemale(int female) {
        this.female = female;
    }

    public XDLWGeographyModel getXDLWGeographModel() {
        return XDLWGeographModel;
    }

    public void setXDLWGeographModel(XDLWGeographyModel XDLWGeographModel) {
        this.XDLWGeographModel = XDLWGeographModel;
    }


    public int ageId;
    public int ageGroup;
    public int censusYear;
    public int geographicArea;
    public int combined;
    public int male;
    public int female;

    public XDLWGeographyModel XDLWGeographModel;
}
