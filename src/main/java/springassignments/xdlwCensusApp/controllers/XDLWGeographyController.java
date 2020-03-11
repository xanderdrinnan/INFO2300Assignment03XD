package springassignments.xdlwCensusApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springassignments.xdlwCensusApp.XDLWGeographyDAL.XDLWGeoDAO;
import springassignments.xdlwCensusApp.models.XDLWAgeModel;
import springassignments.xdlwCensusApp.models.XDLWGeographyModel;

import java.io.IOException;
import java.util.List;

@Controller
public class XDLWGeographyController {

    @Autowired
    private XDLWGeoDAO geoDAO;

    @GetMapping(value = "XDLWFindGeoAreaByLevel")
    public String findGeoLevel()
    {
        return "XDLWFindGeoAreaByLevel";
    }



    @RequestMapping(value = "/Results")
    public ModelAndView listGeographicArea(@RequestParam(required = false, name = "getLevel") Integer getLevel, ModelAndView model) throws IOException {
        List<XDLWGeographyModel> geoList = geoDAO.geoList(getLevel);
        model.addObject("geoList", geoList);
        model.setViewName("/XDLWFindGeoAreaByLevel");

        return model;
    }

    @GetMapping(value = "XDLWFindGeoAreaByKeyword")
    public String findGeoKeyword()
    {
        return "XDLWFindGeoAreaByKeyword";
    }



    @RequestMapping(value = "/KeywordResults")
    public ModelAndView listGeographicArea(@RequestParam(required = false, name = "getKeyword") String getKeyword, ModelAndView model) throws IOException {
        List<XDLWGeographyModel> keywordList = geoDAO.keywordList(getKeyword);
        model.addObject("keywordList", keywordList);
        model.setViewName("/XDLWFindGeoAreaByKeyword");

        return model;
    }

    @GetMapping(value = "XDLWFindGeoByPopulation")
    public String findGeoPopKeyword()
    {
        return "XDLWFindGeoByPopulation";
    }



    @RequestMapping(value = "/PopulationResults")
    public ModelAndView listPopulationArea(@RequestParam(required = false, name = "altCode") Integer altCode, ModelAndView model) throws IOException {
        List<XDLWAgeModel> populationList = geoDAO.populationList(altCode);
        model.addObject("populationList", populationList);
        model.setViewName("/XDLWFindGeoByPopulation");

        return model;
    }
}
