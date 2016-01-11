package com.shinelw.securitypay.untils;

import com.baidu.mapapi.model.LatLng;

/**
 * Created by shinelw on 1/5/16.
 */
public class DistanceUntils {

    private static final double EARTH_RADIUS = 6378137;//赤道半径(单位m)

    /**
     * 转化为弧度(rad)
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下
     *
     * @param lon1 第一点的精度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的精度
     * @param lat2 第二点的纬度
     * @return 返回的距离，单位km
     */
    public static double getDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        //s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 计算地球上任意两点(经纬度)距离
     *
     * @param long1 第一点经度
     * @param lat1  第一点纬度
     * @param long2 第二点经度
     * @param lat2  第二点纬度
     * @return 返回距离 单位：米
     */
    public static double Distance(double long1, double lat1, double long2,
                                  double lat2) {
        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

    /**
     * 计算两点之间距离
     * @param start
     * @param end
     * @return 米
     */
    public static double getDistance(LatLng start,LatLng end){
        double lat1 = (Math.PI/180)*start.latitude;
        double lat2 = (Math.PI/180)*end.latitude;

        double lon1 = (Math.PI/180)*start.longitude;
        double lon2 = (Math.PI/180)*end.longitude;

//      double Lat1r = (Math.PI/180)*(gp1.getLatitudeE6()/1E6);
//      double Lat2r = (Math.PI/180)*(gp2.getLatitudeE6()/1E6);
//      double Lon1r = (Math.PI/180)*(gp1.getLongitudeE6()/1E6);
//      double Lon2r = (Math.PI/180)*(gp2.getLongitudeE6()/1E6);

        //地球半径
        double R = 6371;

        //两点间距离 km，如果想要米的话，结果*1000就可以了
        double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;

        return d*1000;
    }
}
