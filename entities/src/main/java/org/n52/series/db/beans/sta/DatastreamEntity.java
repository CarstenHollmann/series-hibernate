/*
 * Copyright 2015-2019 52°North Initiative for Geospatial Open Source
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
package org.n52.series.db.beans.sta;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.locationtech.jts.geom.Geometry;
import org.n52.series.db.beans.DatasetEntity;
import org.n52.series.db.beans.FormatEntity;
import org.n52.series.db.beans.GeometryEntity;
import org.n52.series.db.beans.HibernateRelations.HasGeometry;
import org.n52.series.db.beans.HibernateRelations.HasObservableProperty;
import org.n52.series.db.beans.HibernateRelations.HasObservationType;
import org.n52.series.db.beans.HibernateRelations.HasPhenomenonTime;
import org.n52.series.db.beans.HibernateRelations.HasProcedure;
import org.n52.series.db.beans.HibernateRelations.HasUnit;
import org.n52.series.db.beans.PhenomenonEntity;
import org.n52.series.db.beans.PlatformEntity;
import org.n52.series.db.beans.ProcedureEntity;
import org.n52.series.db.beans.UnitEntity;
import org.n52.series.db.common.Utils;

/**
 *
 * @author <a href="mailto:s.drost@52north.org">Sebastian Drost</a>
 */
public class DatastreamEntity extends AbstractNameDescriptionStaEntity
        implements Serializable, HasObservationType<DatastreamEntity>, HasObservableProperty<DatastreamEntity>,
        HasPhenomenonTime, HasUnit, HasProcedure<DatastreamEntity>, HasGeometry {

    public static final String PROPERTY_NAME = NAME;
    public static final String PROPERTY_DESCRIPTION = DESCRIPTION;
    public static final String PROPERTY_OBSERVATION_TYPE = "observationType";
    public static final String PROPERTY_UNIT = "unitOfMeasurement";
    public static final String PROPERTY_OBSERVED_AREA = "observedArea";
    public static final String PROPERTY_SAMPLING_TIME_START = "samplingTimeStart";
    public static final String PROPERTY_SAMPLING_TIME_END = "samplingTimeEnd";
    public static final String PROPERTY_RESULT_TIME_START = "resultTimeStart";
    public static final String PROPERTY_RESULT_TIME_END = "resultTimeEnd";
    public static final String PROPERTY_THING = "thing";
    public static final String PROPERTY_PROCEDURE = "procedure";
    public static final String PROPERTY_PHENOMENON = "phenomenon";

    private static final long serialVersionUID = -9067390076853876658L;

    private GeometryEntity geometryEntity;
    private Date samplingTimeStart;
    private Date samplingTimeEnd;
    private Date resultTimeStart;
    private Date resultTimeEnd;

    private FormatEntity observationType;
    private UnitEntity unitOfMeasurement;
    private PlatformEntity thing;
    private ProcedureEntity procedure;
    private PhenomenonEntity observableProperty;

    private Set<DatasetEntity> datasets;
    private Set<StaDataEntity> observations;

    @Override
    public Date getSamplingTimeStart() {
        return Utils.createUnmutableTimestamp(samplingTimeStart);
    }

    @Override
    public void setSamplingTimeStart(Date samplingTimeStart) {
        this.samplingTimeStart = Utils.createUnmutableTimestamp(samplingTimeStart);
    }

    @Override
    public Date getSamplingTimeEnd() {
        return Utils.createUnmutableTimestamp(samplingTimeEnd);
    }

    @Override
    public void setSamplingTimeEnd(Date samplingTimeEnd) {
        this.samplingTimeEnd = Utils.createUnmutableTimestamp(samplingTimeEnd);
    }

    public Date getResultTimeStart() {
        return Utils.createUnmutableTimestamp(resultTimeStart);
    }

    public void setResultTimeStart(Date resultTimeStart) {
        this.resultTimeStart = Utils.createUnmutableTimestamp(resultTimeStart);
    }

    public boolean hasResultTimeStart() {
        return getResultTimeStart() != null;
    }

    public Date getResultTimeEnd() {
        return Utils.createUnmutableTimestamp(resultTimeEnd);
    }

    public void setResultTimeEnd(Date resultTimeEnd) {
        this.resultTimeEnd = Utils.createUnmutableTimestamp(resultTimeEnd);
    }

    public boolean hasResultTimeEnd() {
        return getResultTimeEnd() != null;
    }

    @Override
    public UnitEntity getUnit() {
        return unitOfMeasurement;
    }

    @Override
    public void setUnit(UnitEntity unit) {
        this.unitOfMeasurement = unit;
    }

    @Override
    public boolean isSetUnit() {
        return getUnitOfMeasurement() != null && getUnitOfMeasurement().isSetIdentifier();
    }

    public Set<DatasetEntity> getDatasets() {
        return datasets;
    }

    public void setDatasets(Set<DatasetEntity> datasets) {
        this.datasets = datasets;
    }

    public void addDataset(DatasetEntity dataset) {
        if (datasets == null) {
            datasets = new LinkedHashSet<>();
        }
        datasets.add(dataset);
    }

    @Override
    public FormatEntity getObservationType() {
        return observationType;
    }

    @Override
    public DatastreamEntity setObservationType(FormatEntity observationType) {
        this.observationType = observationType;
        return this;
    }

    @Override
    public boolean isSetObservationType() {
        return this.observationType != null;
    }

    public PlatformEntity getThing() {
        return thing;
    }

    public void setThing(PlatformEntity thing) {
        this.thing = thing;
    }

    @Override
    public DatastreamEntity setProcedure(ProcedureEntity procedure) {
        this.procedure = procedure;
        return this;
    }

    @Override
    public ProcedureEntity getProcedure() {
        return procedure;
    }

    public UnitEntity getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitEntity unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    @Override
    public GeometryEntity getGeometryEntity() {
        return geometryEntity;
    }

    @Override
    public DatastreamEntity setGeometry(Geometry geometry) {
        this.geometryEntity = new GeometryEntity();
        this.geometryEntity.setGeometry(geometry);
        this.geometryEntity.setSrid(geometry.getSRID());
        return this;
    }

    @Override
    public DatastreamEntity setGeometryEntity(GeometryEntity geometryEntity) {
        this.geometryEntity = geometryEntity;
        return this;
    }

    @Override
    public DatastreamEntity setObservableProperty(PhenomenonEntity observableProperty) {
        this.observableProperty = observableProperty;
        return this;
    }

    @Override
    public PhenomenonEntity getObservableProperty() {
        return observableProperty;
    }

    public void setObservations(Set<StaDataEntity> observations) {
        this.observations = observations;
    }

    public Set<StaDataEntity> getObservations() {
        return observations;
    }

    public boolean hasObservations() {
        return getObservations() != null && !getObservations().isEmpty();
    }

}