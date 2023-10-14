package com.more.more50.services;

public class HelpService 
{
    public static double CalculateDistance(double latitudeUser, double longitudeUser, // широта, долгота точки от которой считаем
                                double latitudeDest, double longitudeDest) //широта, долгота назначение
    {
        int earthRad = 6371; //km

        double dLat = Math.toRadians(Math.abs(latitudeDest-latitudeUser));
        double dLon = Math.toRadians(Math.abs(longitudeDest - longitudeUser));

        // System.out.println(latitudeDest +" "+longitudeDest);
        // System.out.println(latitudeUser+" "+longitudeUser);

        latitudeDest = Math.toRadians(latitudeDest);
        latitudeUser = Math.toRadians(latitudeUser);

        double dist = Math.sin(dLat/2) * Math.sin(dLat/2) +
        Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(latitudeDest)*Math.cos(latitudeUser);
        
        // System.out.println((2 * Math.atan2(Math.sqrt(dist), Math.sqrt(1-dist)))*earthRad);

        // System.out.println();
        return (2 * Math.atan2(Math.sqrt(dist), Math.sqrt(1-dist)))*earthRad;
    }
}
