/*
 * COPYRIGHT Ericsson 2019
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *
 */
package com.ericsson.geoflux.core;

/**
 * @author windhong
 */
public abstract class CircleGeofence extends Geofence {

    private Coordinate center;
    private int radius;

    public Coordinate getCenter() {
        return center;
    }

    public void setCenter(Coordinate center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CircleGeofence)) return false;
        if (!super.equals(o)) return false;

        CircleGeofence that = (CircleGeofence) o;

        if (getRadius() != that.getRadius()) return false;
        return getCenter() != null ? getCenter().equals(that.getCenter()) : that.getCenter() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCenter() != null ? getCenter().hashCode() : 0);
        result = 31 * result + getRadius();
        return result;
    }
}
