package com.more.more50.services;

public class HelpService 
{
    public static double CalculateDistance(double latitudeDest, double longitudeDest,
                                 double latitudeUser, double longitudeUser)
                                 
    {

        int earthRad = 6371; //km

        double dLat = Math.toRadians(Math.abs(latitudeDest-latitudeUser));
        double dLon = Math.toRadians(Math.abs(longitudeDest - longitudeUser));

        latitudeDest = Math.toRadians(latitudeDest);
        latitudeUser = Math.toRadians(latitudeUser);

        double dist = Math.sin(dLat/2) * Math.sin(dLat/2) +
        Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(latitudeDest)*Math.cos(latitudeUser);

        System.out.println((2 * Math.atan2(Math.sqrt(dist), Math.sqrt(1-dist)))*earthRad);

        return (2 * Math.atan2(Math.sqrt(dist), Math.sqrt(1-dist)))*earthRad;
        
    }
}
