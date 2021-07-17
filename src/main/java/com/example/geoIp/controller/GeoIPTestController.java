package com.example.geoIp.controller;

import com.example.geoIp.entity.GeoIp;
import com.example.geoIp.service.RawDBDemoGeoIPLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoIPTestController {

    @Autowired
    private RawDBDemoGeoIPLocationService locationService;

    @PostMapping("/GeoIPTest")
    public GeoIp getLocation(@RequestParam(value="ipAddress") String ipAddress){
        GeoIp location = null;

        try {
            location = locationService.getLocation(ipAddress);
        } catch (Exception e){
            e.printStackTrace();
        }

        return location;
    }


}
