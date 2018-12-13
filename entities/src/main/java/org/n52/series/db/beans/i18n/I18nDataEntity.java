/*
 * Copyright 2015-2018 52°North Initiative for Geospatial Open Source
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

package org.n52.series.db.beans.i18n;

import org.n52.series.db.beans.DataEntity;

public class I18nDataEntity extends I18nEntity<DataEntity<?>> {

    private static final long serialVersionUID = 6049859871182587108L;

    private String valueName;

    private String valueDescription;

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String shortName) {
        this.valueName = shortName;
    }

    public String getValueDescription() {
        return valueDescription;
    }

    public void setValueDescription(String longName) {
        this.valueDescription = longName;
    }

    public boolean hasValueDescription() {
        return getValueDescription() != null && !getValueDescription().isEmpty();
    }

    public boolean hasValueName() {
        return getValueName() != null && !getValueName().isEmpty();
    }
}
