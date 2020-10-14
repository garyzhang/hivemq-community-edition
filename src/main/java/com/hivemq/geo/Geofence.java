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

import java.io.Serializable;
import java.util.*;

/**
 * @author windhong
 */
public abstract class Geofence implements Serializable {

    private boolean active = true;
    private Date expired;
    private Double altitude;
    private Set<String> tags = new HashSet<>();
    private String id;
    private Date created = new Date();
    private Date lastModified;
    private Map<String, String> metas = new HashMap<>();

    //if raised by device, values should be device id
    private String raisedBy;

    public String getRaisedBy() {
        return raisedBy;
    }

    public void setRaisedBy(String raisedBy) {
        this.raisedBy = raisedBy;
    }


    public abstract boolean contains(Coordinate location);

    public abstract List<String> getShardingKeys();

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Set<String> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void setTags(Set<String> tags) {
        this.tags.clear();
        this.tags.addAll(tags);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Map<String, String> getMetas() {
        return Collections.unmodifiableMap(metas);
    }

    public void setMetas(Map<String, String> metas) {
       this.metas.clear();
        this.metas.putAll(metas);
    }

    public String getMeta(String key) {
        return metas.get(key);
    }

    public void setMeta(String key, String value) {
        this.metas.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Geofence)) return false;

        Geofence geofence = (Geofence) o;

        if (isActive() != geofence.isActive()) return false;
        if (getExpired() != null ? !getExpired().equals(geofence.getExpired()) : geofence.getExpired() != null)
            return false;
        if (getAltitude() != null ? !getAltitude().equals(geofence.getAltitude()) : geofence.getAltitude() != null)
            return false;
        if (getTags() != null ? !getTags().equals(geofence.getTags()) : geofence.getTags() != null) return false;
        if (getId() != null ? !getId().equals(geofence.getId()) : geofence.getId() != null) return false;
        if (getCreated() != null ? !getCreated().equals(geofence.getCreated()) : geofence.getCreated() != null)
            return false;
        if (getLastModified() != null ? !getLastModified().equals(geofence.getLastModified()) : geofence.getLastModified() != null)
            return false;
        return getMetas() != null ? getMetas().equals(geofence.getMetas()) : geofence.getMetas() == null;
    }

    @Override
    public int hashCode() {
        int result = (isActive() ? 1 : 0);
        result = 31 * result + (getExpired() != null ? getExpired().hashCode() : 0);
        result = 31 * result + (getAltitude() != null ? getAltitude().hashCode() : 0);
        result = 31 * result + (getTags() != null ? getTags().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getCreated() != null ? getCreated().hashCode() : 0);
        result = 31 * result + (getLastModified() != null ? getLastModified().hashCode() : 0);
        result = 31 * result + (getMetas() != null ? getMetas().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Geofence{" +
                "active=" + active +
                ", expired=" + expired +
                ", altitude=" + altitude +
                ", tags=" + tags +
                ", id='" + id + '\'' +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", metas=" + metas +
                '}';
    }
}
