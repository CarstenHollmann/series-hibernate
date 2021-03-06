/*
 * Copyright 2015-2020 52°North Initiative for Geospatial Open Source
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

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Locale.LanguageRange;
import java.util.stream.Collectors;

import org.n52.series.db.beans.HibernateRelations.HasId;
import org.n52.series.db.beans.HibernateRelations.HasParameters;
import org.n52.series.db.beans.i18n.I18nEntity;
import org.n52.series.db.common.LocaleHelper;

public interface Describable extends IdentifierNameDescriptionEntity, HasId, HasParameters {

    String LOCALE_REGEX = "[-_# ]";

    ServiceEntity getService();

    DescribableEntity setService(ServiceEntity service);

    Set<I18nEntity<? extends Describable>> getTranslations();

    void setTranslations(Set<I18nEntity<? extends Describable>> translations);

    default boolean hasTranslations() {
        return getTranslations() != null && !getTranslations().isEmpty();
    }

    default boolean hasService() {
        return getService() != null;
    }

    default Set<Map<String, Object>> getMappedParameters(String locale) {
        return hasParameters() ? getParameters().stream().map(e -> e.toValueMap(locale)).collect(Collectors.toSet())
                : null;
    }

    default String getLabelFrom(String locale) {
        if (isi18nNameAvailable(locale)) {
            return getNameI18n(locale);
        } else if (isNameAvailable()) {
            return getName();
        } else if (isDomainAvailable()) {
            return getDomain();
        } else {
            // absolute fallback
            return Long.toString(getId());
        }
    }

    default boolean isNameAvailable() {
        return getName() != null && !getName().isEmpty();
    }

    default boolean isDomainAvailable() {
        return getDomain() != null && !getDomain().isEmpty();
    }

    default boolean isi18nNameAvailable(String locale) {
        return getNameI18n(locale) != null && !getNameI18n(locale).isEmpty();
    }

    default boolean noTranslationAvailable(String locale) {
        return getTranslations() == null || locale == null || getTranslations().isEmpty() || locale.isEmpty();
    }

    default String getNameI18n(String locale) {
        if (noTranslationAvailable(locale)) {
            return getName();
        }
        I18nEntity<? extends Describable> translation = getTranslation(locale);
        return translation != null ? translation.getName() : getName();
    }

    default String getDescriptionI18n(String locale) {
        if (noTranslationAvailable(locale)) {
            return getDescription();
        }
        I18nEntity<? extends Describable> translation = getTranslation(locale);
        return translation != null ? translation.getDescription() : getDescription();
    }

    default String getCountryCode(String locale) {
        if (locale != null) {
            return locale.split(LOCALE_REGEX).length > 1 ? locale.split(LOCALE_REGEX)[1]
                    : locale.split(LOCALE_REGEX)[0];
        }
        return "";
    }

    default I18nEntity<? extends Describable> getTranslation(String locale) {
        if (!noTranslationAvailable(locale)) {
            String countryCode = getCountryCode(locale);
            Locale matchingLocale = getMatchingLocale(getTranslations(), locale);
            for (I18nEntity<? extends Describable> translation : getTranslations()) {
                Locale translatedLocale = LocaleHelper.decode(translation.getLocale());
                if (translatedLocale.equals(matchingLocale)
                        || translatedLocale.getCountry().equalsIgnoreCase(countryCode)) {
                    return translation;
                }
            }
        }
        return null;
    }

    default Locale getMatchingLocale(Set<I18nEntity<? extends Describable>> translations, String queriedLocale) {
        List<LanguageRange> localeRange = Locale.LanguageRange.parse(queriedLocale);
        return Locale.lookup(localeRange,
                translations.stream().map(t -> LocaleHelper.decode(t.getLocale())).collect(Collectors.toSet()));
    }

}
