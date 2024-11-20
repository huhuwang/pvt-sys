package com.hayes.pvtsys.util;

public class ServerPath {
    private static final String OUT_PATH = "D:/study/tomcat/apache-tomcat/webapps/";
    private static final String TOMCAT_URL = "http://localhost:8090/";

    public static String relativePath(String deploymentId, String ticketNo){

        return "pvt-source/deployment-" + deploymentId + "/" + ticketNo;
    }

    public static String outPath(String deploymentId, String ticketNo){

        return OUT_PATH + relativePath(deploymentId, ticketNo);
    }

    public static String tomcatUrl(String deploymentId, String ticketNo){

        return TOMCAT_URL + relativePath(deploymentId, ticketNo);
    }

    public static String partOutPath(){
        return OUT_PATH;
    }

    public static String partTomcat(){
        return TOMCAT_URL;
    }
}
