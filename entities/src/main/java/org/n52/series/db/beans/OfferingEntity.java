/*
 * Copyright 2015-2017 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.n52.series.db.beans;

import java.util.Date;
import java.util.Set;

import org.n52.series.db.beans.HibernateRelations.HasFeatureTypes;
import org.n52.series.db.beans.HibernateRelations.HasObservationTypes;
import org.n52.series.db.beans.HibernateRelations.HasPhenomenonTime;
import org.n52.series.db.beans.HibernateRelations.HasRelatedFeatures;
import org.n52.series.db.beans.HibernateRelations.HasResultTimes;
import org.n52.series.db.beans.HibernateRelations.HasValidTime;
import org.n52.series.db.common.Utils;

import com.vividsolutions.jts.geom.Geometry;

public class OfferingEntity extends HierarchicalEntity<OfferingEntity>
        implements HasObservationTypes<OfferingEntity>, HasFeatureTypes<OfferingEntity>,
        HasRelatedFeatures<OfferingEntity>, HasPhenomenonTime<OfferingEntity>, HasResultTimes<OfferingEntity>,
        HasValidTime<OfferingEntity> {

    public static final String PROPERTY_PHENOMENON_TIME_START = "phenomenonTimeStart";
    public static final String PROPERTY_PHENOMENON_TIME_END = "phenomenonTimeEnd";
    public static final String PROPERTY_RESULT_TIME_START = "resultTimeStart";
    public static final String PROPERTY_RESULT_TIME_END = "resultTimeEnd";

    private static final long serialVersionUID = 5862607025737865794L;

    private GeometryEntity geometryEntity;

    private Set<ObservationTypeEntity> observationTypes;

    private Set<FeatureTypeEntity> featureTypes;

    private Date phenomenonTimeStart;

    private Date phenomenonTimeEnd;

    private Date resultTimeStart;

    private Date resultTimeEnd;

    private Set<RelatedFeatureEntity> relatedFeatures;
    private Date validTimeStart;
    private Date validTimeEnd;

    public Geometry getGeometry() {
        return geometryEntity != null
                ? geometryEntity.getGeometry()
                : null;
    }

    public OfferingEntity setGeometry(Geometry geometry) {
        this.geometryEntity = new GeometryEntity();
        this.geometryEntity.setGeometry(geometry);
        this.geometryEntity.setSrid(geometry.getSRID());
        return this;
    }

    public void setGeometryEntity(GeometryEntity geometryEntity) {
        this.geometryEntity = geometryEntity;
    }

    public GeometryEntity getGeometryEntity() {
        return geometryEntity;
    }

    public boolean isSetGeometry() {
        return geometryEntity != null;
    }

    /**
     * @return the observationTypes
     */
    @Override
    public Set<ObservationTypeEntity> getObservationTypes() {
        return observationTypes;
    }

    /**
     * @param observationTypes
     *        the observationTypes to set
     */
    @Override
    public OfferingEntity setObservationTypes(Set<ObservationTypeEntity> observationTypes) {
        this.observationTypes = observationTypes;
        return this;
    }

    /**
     * @return the featureTypes
     */
    @Override
    public Set<FeatureTypeEntity> getFeatureTypes() {
        return featureTypes;
    }

    /**
     * @param featureTypes
     *        the featureTypes to set
     */
    @Override
    public OfferingEntity setFeatureTypes(Set<FeatureTypeEntity> featureTypes) {
        this.featureTypes = featureTypes;
        return this;
    }

    /**
     * @return the phenomenonTimeStart
     */
    @Override
    public Date getPhenomenonTimeStart() {
        return Utils.createUnmutableTimestamp(phenomenonTimeStart);
    }

    /**
     * @param phenomenonTimeStart
     *        the phenomenonTimeStart to set
     */
    @Override
    public OfferingEntity setPhenomenonTimeStart(Date phenomenonTimeStart) {
        this.phenomenonTimeStart = Utils.createUnmutableTimestamp(phenomenonTimeStart);
        return this;
    }

    /**
     * @return the phenomenonTimeEnd
     */
    @Override
    public Date getPhenomenonTimeEnd() {
        return Utils.createUnmutableTimestamp(phenomenonTimeEnd);
    }

    /**
     * @param phenomenonTimeEnd
     *        the phenomenonTimeEnd to set
     */
    @Override
    public OfferingEntity setPhenomenonTimeEnd(Date phenomenonTimeEnd) {
        this.phenomenonTimeEnd = Utils.createUnmutableTimestamp(phenomenonTimeEnd);
        return this;
    }

    /**
     * @return the resultTimeStart
     */
    @Override
    public Date getResultTimeStart() {
        return Utils.createUnmutableTimestamp(resultTimeStart);
    }

    /**
     * @param resultTimeStart
     *        the resultTimeStart to set
     */
    @Override
    public OfferingEntity setResultTimeStart(Date resultTimeStart) {
        this.resultTimeStart = Utils.createUnmutableTimestamp(resultTimeStart);
        return this;
    }

    /**
     * @return the resultTimeEnd
     */
    @Override
    public Date getResultTimeEnd() {
        return Utils.createUnmutableTimestamp(resultTimeEnd);
    }

    /**
     * @param resultTimeEnd
     *        the resultTimeEnd to set
     */
    @Override
    public OfferingEntity setResultTimeEnd(Date resultTimeEnd) {
        this.resultTimeEnd = Utils.createUnmutableTimestamp(resultTimeEnd);
        return this;
    }

    /**
     * @return the validTimeStart
     */
    @Override
    public Date getValidTimeStart() {
        return Utils.createUnmutableTimestamp(validTimeStart);
    }

    /**
     * @param validTimeStart
     *        the validTimeStart to set
     */
    @Override
    public OfferingEntity setValidTimeStart(Date validTimeStart) {
        this.validTimeStart = Utils.createUnmutableTimestamp(validTimeStart);
        return this;
    }

    /**
     * @return the validTimeEnd
     */
    @Override
    public Date getValidTimeEnd() {
        return Utils.createUnmutableTimestamp(validTimeEnd);
    }

    /**
     * @param validTimeEnd
     *        the validTimeEnd to set
     */
    @Override
    public OfferingEntity setValidTimeEnd(Date validTimeEnd) {
        this.validTimeEnd = Utils.createUnmutableTimestamp(validTimeEnd);
        return this;
    }

    @Override
    public boolean isSetValidTime() {
        return getValidTimeStart() != null && getValidTimeEnd() != null;
    }

    @Override
    public Set<RelatedFeatureEntity> getRelatedFeatures() {
        return relatedFeatures;
    }

    @Override
    public OfferingEntity setRelatedFeatures(Set<RelatedFeatureEntity> relatedFeatures) {
        this.relatedFeatures = relatedFeatures;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(getClass().getSimpleName())
                 .append(" [")
                 .append(" Domain id: ")
                 .append(getDomainId())
                 .append(" ]")
                 .toString();
    }

}