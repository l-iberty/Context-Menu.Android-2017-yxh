package com.yalantis.contextmenu.sample;

import com.avos.avoscloud.AVObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liberty on 2017/7/20.
 */

public class BestConditions {
    private static Map<String, Object> values = new HashMap<>();

    public static double getTemperature() {
        if (values.containsKey("temperature")) {
            return (double) values.get("temperature");
        } else {
            return 0.0;
        }
    }

    public static double getSoilHumidity() {
        if (values.containsKey("soil_humidity")) {
            return (double) values.get("soil_humidity");
        } else {
            return 0.0;
        }
    }

    public static double getSoilTemperature() {
        if (values.containsKey("soil_temperature")) {
            return (double) values.get("soil_temperature");
        } else {
            return 0.0;
        }
    }

    public static double getAirHumidity() {
        if (values.containsKey("air_humidity")) {
            return (double) values.get("air_humidity");
        } else {
            return 0.0;
        }
    }

    public static double getLight() {
        if (values.containsKey("light")) {
            return (double) values.get("light");
        } else {
            return 0.0;
        }
    }

    public static String getErrMsg() {
        if (values.containsKey("errMsg")) {
            return (String) values.get("errMsg");
        } else {
            return "";
        }
    }

    public static void setTemperature(double temperature) {
        values.put("temperature", temperature);
    }

    public static void setSoilHumidity(double soilHumidity) {
        values.put("soil_humidity", soilHumidity);
    }

    public static void setSoilTemperature(double soilTemperature) {
        values.put("soil_temperature", soilTemperature);
    }

    public static void setAirHumidity(double airHumidity) {
        values.put("air_humidity", airHumidity);
    }

    public static void setLight(double light) {
        values.put("light", light);
    }

    public static void setErrMsg(String errMsg) {
        values.put("errMsg", errMsg);
    }

    public static void updateTemperature(double temperature) {
        values.remove("temperature");
        setTemperature(temperature);
    }

    public static void updateSoilHumidity(double soilHumidity) {
        values.remove("soil_humidity");
        setSoilHumidity(soilHumidity);
    }

    public static void updateSoilTemperature(double soilTemperature) {
        values.remove("soil_temperature");
        setSoilTemperature(soilTemperature);
    }

    public static void updateAirHumidity(double airHumidity) {
        values.remove("air_humidity");
        setAirHumidity(airHumidity);
    }

    public static void updateLight(double light) {
        values.remove("light");
        setLight(light);
    }

    public static void updateErrMsg(String errMsg) {
        values.remove("errMsg");
        setErrMsg(errMsg);
    }

    public static void updateData(AVObject avObject) {
        BestConditions.updateTemperature(avObject.getDouble("temperature"));
        BestConditions.updateSoilHumidity(avObject.getDouble("soil_humidity"));
        BestConditions.updateSoilTemperature(avObject.getDouble("soil_temperature"));
        BestConditions.updateAirHumidity(avObject.getDouble("air_humidity"));
        BestConditions.updateLight(avObject.getDouble("light"));
        BestConditions.updateErrMsg("");
    }

    public static String getFormatDataString(String text) {
        String fmtDataString = String.format(text,
                BestConditions.getTemperature(),
                BestConditions.getSoilHumidity(),
                BestConditions.getAirHumidity(),
                BestConditions.getSoilTemperature(),
                BestConditions.getLight(),
                BestConditions.getErrMsg());
        return fmtDataString;
    }

    public static Object get(String key) {
        if (values.containsKey(key)) {
            return values.get(key);
        } else {
            return null;
        }
    }
}
