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

package org.n52.series.db.beans.ereporting;

import org.n52.series.db.beans.AbstractRelationEntity;

public class RelatedDatasetEntity extends AbstractRelationEntity {

    public static final String SERIES_ID = "dataset";

    private static final long serialVersionUID = 280165490758286290L;

    private long dataset;

    private EReportingDatasetEntity relatedDataset;

    /**
     * @return the dataset
     */
    public long getDataset() {
        return dataset;
    }

    /**
     * @param dataset
     *            the dataset to set
     */
    public void setDataset(long dataset) {
        this.dataset = dataset;
    }

    /**
     * @return the relatedDataset
     */
    public EReportingDatasetEntity getRelatedDataset() {
        return relatedDataset;
    }

    /**
     * @param relatedDataset
     *            the relatedDataset to set
     */
    public void setRelatedDataset(EReportingDatasetEntity relatedDataset) {
        this.relatedDataset = relatedDataset;
    }

    public boolean isSetRelatedDataset() {
        return getRelatedDataset() != null;
    }

}