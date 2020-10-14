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
package com.ericsson.geoflux.core.impl;


import com.ericsson.geoflux.core.CircleGeofence;
import com.ericsson.geoflux.core.Coordinate;
import com.google.common.geometry.S1Angle;
import com.google.common.geometry.S2Cap;
import com.google.common.geometry.S2LatLng;

import java.util.List;

/**
 * @author windhong
 */
public class CircleGeofenceImpl extends CircleGeofence {

    private transient S2Cap s2Cap;

    @Override
    public void setCenter(Coordinate center) {
        super.setCenter(center);
        this.s2Cap = null;
    }

    @Override
    public void setRadius(int radius) {
        if (super.getRadius() != radius) {
            super.setRadius(radius);
            this.s2Cap = null;
        }
    }

    @Override
    public List<String> getShardingKeys() {
        return S2Utils.getCellIds(this);
    }

    @Override
    public boolean contains(Coordinate location) {
        initS2Cap();
        return s2Cap.contains(location.getS2Point());
    }

    public S2Cap getS2Cap() {
        initS2Cap();
        return s2Cap;
    }

    private void initS2Cap() {
        if (s2Cap == null) {
            if (getCenter() == null || getRadius() <= 0) {
                throw new IllegalStateException("CircleGeofence cannot calculate S2Cap as properties are not properly set.");
            }
            S1Angle angle = S1Angle.radians(getRadius() / S2LatLng.EARTH_RADIUS_METERS);
            s2Cap = S2Cap.fromAxisAngle(getCenter().getS2Point(), angle);
        }
    }
}
