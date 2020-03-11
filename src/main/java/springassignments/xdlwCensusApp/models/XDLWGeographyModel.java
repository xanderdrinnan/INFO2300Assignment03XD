package springassignments.xdlwCensusApp.models;

import org.springframework.stereotype.Component;

@Component
public class XDLWGeographyModel {

    private int geographicAreaID;
    private int code;
    private int level;
    private int alternativeCode;
    private String name;

    public int getGeographicAreaID() {
        return geographicAreaID;
    }

    public void setGeographicAreaID(int newGeoId) {
        this.geographicAreaID = newGeoId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int newCode) {
        this.code = newCode;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int newLevel) {
        this.level = newLevel;
    }

    public int getAlternativeCode() {
        return alternativeCode;
    }

    public void setAlternativeCode(int newAlternativeCode) {
        this.alternativeCode = newAlternativeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getGetTotalPop() {
        return getTotalPop;
    }

    public void setGetTotalPop(String getTotalPop) {
        this.getTotalPop = getTotalPop;
    }

    public String getTotalPop;



}
