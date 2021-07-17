package com.example.geoIp.service;

import com.example.geoIp.entity.GeoIp;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;

@Service
public class RawDBDemoGeoIPLocationService {
    private DatabaseReader dbReader;


    public RawDBDemoGeoIPLocationService()  {
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName = "GeoLite2-City.mmdb";
        File database = new File(Objects.requireNonNull(classLoader.getResource("GeoLite2-City_20210713/"+fileName)).getFile());
        try {
            dbReader = new DatabaseReader.Builder(database).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GeoIp getLocation(String ip)
            throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        String cityName = response.getCity().getName();
        String nameCountry = response.getCountry().getName();
        String latitude =
                response.getLocation().getLatitude().toString();
        String longitude =
                response.getLocation().getLongitude().toString();
        return new GeoIp(ip, cityName, latitude, longitude, nameCountry);
    }


}
