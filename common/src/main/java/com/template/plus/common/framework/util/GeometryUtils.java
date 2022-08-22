package com.template.plus.common.framework.util;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.operation.buffer.BufferOp;
import com.vividsolutions.jts.operation.buffer.BufferParameters;


/**
 * 封装一些 几何图形操作方法
 */
public class GeometryUtils {

    private static GeometryFactory geometryFactory = new GeometryFactory();

    public static void main(String[] args) throws ParseException {
        String geometry = "LINESTRING (91.14617139753909 29.668177772660414, 91.17123395857425 29.655566326048955)";
        WKTReader reader = new WKTReader(geometryFactory);
        LineString lineString = (LineString) reader.read(geometry);
        Geometry bufferGeometry = lineString.buffer(100 / (2 * Math.PI * 6371004) * 360, 8, 2);
        System.out.printf(bufferGeometry.toString());
    }

    /**
     * 判断一个点是否在线的缓冲区内
     *
     * @param x
     * @param y
     * @param geometry
     * @param bufferMetre
     * @return
     * @throws ParseException
     */
    public static boolean withinLineString(Double x, Double y, String geometry, double bufferMetre) throws ParseException {
        Coordinate coord = new Coordinate(x, y);
        Point point = geometryFactory.createPoint(coord);

        WKTReader reader = new WKTReader(geometryFactory);
        LineString lineString = (LineString) reader.read(geometry);
        BufferParameters bufferParameters = new BufferParameters();
        bufferParameters.setEndCapStyle(bufferParameters.getEndCapStyle());
        Geometry bufferGeometry = lineString.buffer(bufferMetre / (2 * Math.PI * 6371004) * 360, BufferParameters.DEFAULT_QUADRANT_SEGMENTS, BufferParameters.CAP_FLAT);

        return point.within(bufferGeometry);
    }


    /**
     * 判断一个点是否在多边形内
     *
     * @param x
     * @param y
     * @param geometry
     * @return
     * @throws ParseException
     */
    public static boolean withinGeo(Double x, Double y, String geometry) throws ParseException {
        Coordinate coord = new Coordinate(x, y);
        Point point = geometryFactory.createPoint(coord);

        WKTReader reader = new WKTReader(geometryFactory);
        Polygon polygon = (Polygon) reader.read(geometry);
        Geometry buffer = polygon.buffer(250 / (2 * Math.PI * 6371004) * 360, BufferParameters.DEFAULT_QUADRANT_SEGMENTS, BufferParameters.CAP_FLAT);
        return point.within(polygon);
    }

}
