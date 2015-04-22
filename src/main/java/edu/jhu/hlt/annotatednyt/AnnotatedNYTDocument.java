/*
 * Copyright 2012-2015 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.annotatednyt;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nytlabs.corpus.NYTCorpusDocument;

/**
 * Wrapper for the {@link NYTCorpusDocument} object, providing some
 * extra utility.
 */
public class AnnotatedNYTDocument {

  private final NYTCorpusDocument nytdoc;

  public AnnotatedNYTDocument(final NYTCorpusDocument nytdoc) {
    this.nytdoc = nytdoc;
  }

  public Integer getGuid() {
    return this.nytdoc.getGuid();
  }

  public List<String> getOnlineSectionAsList() {
    List<String> onlineSectionList = new ArrayList<String>();
    Optional<String> online = Optional.ofNullable(this.nytdoc.getOnlineSection());
    online.ifPresent(str -> {
      String[] split = str.split(";");
      for (String s : split)
        onlineSectionList.add(s.trim());
    });

    return onlineSectionList;
  }

  public List<String> getLeadParagraphAsList() {
    return getOptionalStringFieldAsList(this.nytdoc.getLeadParagraph());
  }

  public List<String> getOnlineLeadParagraphAsList() {
    return getOptionalStringFieldAsList(this.nytdoc.getOnlineLeadParagraph());
  }

  public List<String> getBodyAsList() {
    return getOptionalStringFieldAsList(this.nytdoc.getBody());
  }

  private static final List<String> getOptionalStringFieldAsList(final String fieldStr) {
    final Optional<String> field = Optional.ofNullable(fieldStr);
    List<String> toRet = field.isPresent() ? unixNewlineStringToList(field.get()) : new ArrayList<String>();
    return toRet;
  }

  private static final List<String> unixNewlineStringToList(final String unixNewlineStr) {
    List<String> strList = new ArrayList<String>();
    String[] split = unixNewlineStr.split("\n");
    for (String s : split)
      strList.add(s);
    return strList;
  }

  private static final <T> List<T> nullListAsEmptyList(List<T> stuff) {
    if (stuff == null)
      return new ArrayList<T>();
    else
      return stuff;
  }

  public Optional<String> getHeadline() {
    return Optional.ofNullable(this.nytdoc.getHeadline());
  }

  public Optional<String> getOnlineHeadline() {
    return Optional.ofNullable(this.nytdoc.getOnlineHeadline());
  }

  public Optional<String> getByline() {
    return Optional.ofNullable(this.nytdoc.getByline());
  }

  public Optional<String> getDateline() {
    return Optional.ofNullable(this.nytdoc.getDateline());
  }

  public Optional<String> getArticleAbstract() {
    return Optional.ofNullable(this.nytdoc.getArticleAbstract());
  }

  public Optional<String> getLeadParagraph() {
    return Optional.ofNullable(this.nytdoc.getLeadParagraph());
  }

  public Optional<String> getOnlineLeadParagraph() {
    return Optional.ofNullable(this.nytdoc.getOnlineLeadParagraph());
  }

  public Optional<String> getCorrectionText() {
    return Optional.ofNullable(this.nytdoc.getCorrectionText());
  }

  public Optional<String> getKicker() {
    return Optional.ofNullable(this.nytdoc.getKicker());
  }

  public Optional<URL> getAlternateURL() {
    return Optional.ofNullable(this.nytdoc.getAlternateURL());
  }

  public List<String> getDescriptors() {
    return nullListAsEmptyList(this.nytdoc.getDescriptors());
  }

  public Optional<String> getAuthorBiography() {
    return Optional.ofNullable(this.nytdoc.getAuthorBiography());
  }

  public Optional<String> getBanner() {
    return Optional.ofNullable(this.nytdoc.getBanner());
  }

  public List<String> getBiographicalCategories() {
    return nullListAsEmptyList(this.nytdoc.getBiographicalCategories());
  }

  public Optional<String> getColumnName() {
    return Optional.ofNullable(this.nytdoc.getColumnName());
  }

  /**
   * Accessor for the columnNumber property.
   *
   * @return the columnNumber
   */
  public Optional<Integer> getColumnNumber() {
    return Optional.ofNullable(this.nytdoc.getColumnNumber());
  }

  /**
   * Accessor for the correctionDate property.
   *
   * @return the correctionDate
   */
  public Optional<Date> getCorrectionDate() {
    return Optional.ofNullable(this.nytdoc.getCorrectionDate());
  }

  /**
   * Accessor for the credit property.
   *
   * @return the credit
   */
  public String getCredit() {
    return this.nytdoc.getCredit();
  }

  /**
   * Accessor for the dayOfWeek property.
   *
   * @return the dayOfWeek
   */
  public Optional<String> getDayOfWeek() {
    return Optional.ofNullable(this.nytdoc.getDayOfWeek());
  }

  /**
   * Accessor for the featurePage property.
   *
   * @return the featurePage
   */
  public Optional<String> getFeaturePage() {
    return Optional.ofNullable(this.nytdoc.getFeaturePage());
  }

  /**
   * Accessor for the generalOnlineDescriptors property.
   *
   * @return the generalOnlineDescriptors
   */
  public List<String> getGeneralOnlineDescriptors() {
    return nullListAsEmptyList(this.nytdoc.getGeneralOnlineDescriptors());
  }

  /**
   * Accessor for the locations property.
   *
   * @return the locations
   */
  public List<String> getLocations() {
    return nullListAsEmptyList(this.nytdoc.getLocations());
  }

  /**
   * Accessor for the names property.
   *
   * @return the names
   */
  public List<String> getNames() {
    return nullListAsEmptyList(this.nytdoc.getNames());
  }

  /**
   * Accessor for the newsDesk property.
   *
   * @return the newsDesk
   */
  public Optional<String> getNewsDesk() {
    return Optional.ofNullable(this.nytdoc.getNewsDesk());
  }

  /**
   * Accessor for the normalizedByline property.
   *
   * @return the normalizedByline
   */
  public Optional<String> getNormalizedByline() {
    return Optional.ofNullable(this.nytdoc.getNormalizedByline());
  }

  /**
   * Accessor for the onlineDescriptors property.
   *
   * @return the onlineDescriptors
   */
  public List<String> getOnlineDescriptors() {
    return nullListAsEmptyList(this.nytdoc.getOnlineDescriptors());
  }

  /**
   * Accessor for the onlineLocations property.
   *
   * @return the onlineLocations
   */
  public List<String> getOnlineLocations() {
    return nullListAsEmptyList(this.nytdoc.getOnlineLocations());
  }

  /**
   * Accessor for the onlineOrganizations property.
   *
   * @return the onlineOrganizations
   */
  public List<String> getOnlineOrganizations() {
    return nullListAsEmptyList(this.nytdoc.getOnlineOrganizations());
  }

  /**
   * Accessor for the onlinePeople property.
   *
   * @return the onlinePeople
   */
  public List<String> getOnlinePeople() {
    return nullListAsEmptyList(this.nytdoc.getOnlinePeople());
  }

  /**
   * Accessor for the onlineSection property.
   *
   * @return the onlineSection
   */
  public Optional<String> getOnlineSection() {
    return Optional.ofNullable(this.nytdoc.getOnlineSection());
  }

  /**
   * Accessor for the onlineTitles property.
   *
   * @return the onlineTitles
   */
  public List<String> getOnlineTitles() {
    return nullListAsEmptyList(this.nytdoc.getOnlineTitles());
  }

  /**
   * Accessor for the organizations property.
   *
   * @return the organizations
   */
  public List<String> getOrganizations() {
    return nullListAsEmptyList(this.nytdoc.getOrganizations());
  }

  /**
   * Accessor for the page property.
   *
   * @return the page
   */
  public Optional<Integer> getPage() {
    return Optional.ofNullable(this.nytdoc.getPage());
  }

  /**
   * Accessor for the people property.
   *
   * @return the people
   */
  public List<String> getPeople() {
    return nullListAsEmptyList(this.nytdoc.getPeople());
  }

  /**
   * Accessor for the publicationDate property.
   *
   * @return the publicationDate
   */
  public Optional<Date> getPublicationDate() {
    return Optional.ofNullable(this.nytdoc.getPublicationDate());
  }

  /**
   * Accessor for the publicationDayOfMonth property.
   *
   * @return the publicationDayOfMonth
   */
  public Optional<Integer> getPublicationDayOfMonth() {
    return Optional.ofNullable(this.nytdoc.getPublicationDayOfMonth());
  }

  /**
   * Accessor for the publicationMonth property.
   *
   * @return the publicationMonth
   */
  public Optional<Integer> getPublicationMonth() {
    return Optional.ofNullable(this.nytdoc.getPublicationMonth());
  }

  /**
   * Accessor for the publicationYear property.
   *
   * @return the publicationYear
   */
  public Optional<Integer> getPublicationYear() {
    return Optional.ofNullable(this.nytdoc.getPublicationYear());
  }

  /**
   * Accessor for the section property.
   *
   * @return the section
   */
  public Optional<String> getSection() {
    return Optional.ofNullable(this.nytdoc.getSection());
  }

  /**
   * Accessor for the seriesName property.
   *
   * @return the seriesName
   */
  public Optional<String> getSeriesName() {
    return Optional.ofNullable(this.nytdoc.getSeriesName());
  }

  /**
   * Accessor for the slug property.
   *
   * @return the slug
   */
  public Optional<String> getSlug() {
    return Optional.ofNullable(this.nytdoc.getSlug());
  }

  /**
   * Accessor for the sourceFile property.
   *
   * @return the sourceFile
   */
  public Optional<Path> getSourcePath() {
    File f = this.nytdoc.getSourceFile();
    return f == null ? Optional.empty() : Optional.of(f.toPath());
  }

  /**
   * Accessor for the taxonomicClassifiers property.
   *
   * @return the taxonomicClassifiers
   */
  public List<String> getTaxonomicClassifiers() {
    return nullListAsEmptyList(this.nytdoc.getTaxonomicClassifiers());
  }

  /**
   * Accessor for the titles property.
   *
   * @return the titles
   */
  public List<String> getTitles() {
    return nullListAsEmptyList(this.nytdoc.getTitles());
  }

  /**
   * Accessor for the typesOfMaterial property.
   *
   * @return the typesOfMaterial
   */
  public List<String> getTypesOfMaterial() {
    return nullListAsEmptyList(this.nytdoc.getTypesOfMaterial());
  }

  /**
   * Accessor for the url property.
   *
   * @return the url
   */
  public Optional<URL> getUrl() {
    URL u = this.nytdoc.getUrl();
    return Optional.ofNullable(u);
  }

  /**
   * Accessor for the wordCount property.
   *
   * @return the wordCount
   */
  public Optional<Integer> getWordCount() {
    return Optional.ofNullable(this.nytdoc.getWordCount());
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    final int maxLen = 3;
    StringBuilder builder = new StringBuilder();
    builder.append("AnnotatedNYTDocument [getGuid()=");
    builder.append(getGuid());
    builder.append(", getOnlineSectionAsList()=");
    builder.append(getOnlineSectionAsList() != null ? getOnlineSectionAsList().subList(0, Math.min(getOnlineSectionAsList().size(), maxLen)) : null);
    builder.append(", getLeadParagraphAsList()=");
    builder.append(getLeadParagraphAsList() != null ? getLeadParagraphAsList().subList(0, Math.min(getLeadParagraphAsList().size(), maxLen)) : null);
    builder.append(", getOnlineLeadParagraphAsList()=");
    builder.append(getOnlineLeadParagraphAsList() != null ? getOnlineLeadParagraphAsList().subList(0, Math.min(getOnlineLeadParagraphAsList().size(), maxLen))
        : null);
    builder.append(", getBodyAsList()=");
    builder.append(getBodyAsList() != null ? getBodyAsList().subList(0, Math.min(getBodyAsList().size(), maxLen)) : null);
    builder.append(", getHeadline()=");
    builder.append(getHeadline());
    builder.append(", getOnlineHeadline()=");
    builder.append(getOnlineHeadline());
    builder.append(", getByline()=");
    builder.append(getByline());
    builder.append(", getDateline()=");
    builder.append(getDateline());
    builder.append(", getArticleAbstract()=");
    builder.append(getArticleAbstract());
    builder.append(", getLeadParagraph()=");
    builder.append(getLeadParagraph());
    builder.append(", getOnlineLeadParagraph()=");
    builder.append(getOnlineLeadParagraph());
    builder.append(", getCorrectionText()=");
    builder.append(getCorrectionText());
    builder.append(", getKicker()=");
    builder.append(getKicker());
    builder.append(", getAlternateURL()=");
    builder.append(getAlternateURL());
    builder.append(", getDescriptors()=");
    builder.append(getDescriptors() != null ? getDescriptors().subList(0, Math.min(getDescriptors().size(), maxLen)) : null);
    builder.append(", getAuthorBiography()=");
    builder.append(getAuthorBiography());
    builder.append(", getBanner()=");
    builder.append(getBanner());
    builder.append(", getBiographicalCategories()=");
    builder.append(getBiographicalCategories() != null ? getBiographicalCategories().subList(0, Math.min(getBiographicalCategories().size(), maxLen)) : null);
    builder.append(", getColumnName()=");
    builder.append(getColumnName());
    builder.append(", getColumnNumber()=");
    builder.append(getColumnNumber());
    builder.append(", getCorrectionDate()=");
    builder.append(getCorrectionDate());
    builder.append(", getCredit()=");
    builder.append(getCredit());
    builder.append(", getDayOfWeek()=");
    builder.append(getDayOfWeek());
    builder.append(", getFeaturePage()=");
    builder.append(getFeaturePage());
    builder.append(", getGeneralOnlineDescriptors()=");
    builder.append(getGeneralOnlineDescriptors() != null ? getGeneralOnlineDescriptors().subList(0, Math.min(getGeneralOnlineDescriptors().size(), maxLen))
        : null);
    builder.append(", getLocations()=");
    builder.append(getLocations() != null ? getLocations().subList(0, Math.min(getLocations().size(), maxLen)) : null);
    builder.append(", getNames()=");
    builder.append(getNames() != null ? getNames().subList(0, Math.min(getNames().size(), maxLen)) : null);
    builder.append(", getNewsDesk()=");
    builder.append(getNewsDesk());
    builder.append(", getNormalizedByline()=");
    builder.append(getNormalizedByline());
    builder.append(", getOnlineDescriptors()=");
    builder.append(getOnlineDescriptors() != null ? getOnlineDescriptors().subList(0, Math.min(getOnlineDescriptors().size(), maxLen)) : null);
    builder.append(", getOnlineLocations()=");
    builder.append(getOnlineLocations() != null ? getOnlineLocations().subList(0, Math.min(getOnlineLocations().size(), maxLen)) : null);
    builder.append(", getOnlineOrganizations()=");
    builder.append(getOnlineOrganizations() != null ? getOnlineOrganizations().subList(0, Math.min(getOnlineOrganizations().size(), maxLen)) : null);
    builder.append(", getOnlinePeople()=");
    builder.append(getOnlinePeople() != null ? getOnlinePeople().subList(0, Math.min(getOnlinePeople().size(), maxLen)) : null);
    builder.append(", getOnlineSection()=");
    builder.append(getOnlineSection());
    builder.append(", getOnlineTitles()=");
    builder.append(getOnlineTitles() != null ? getOnlineTitles().subList(0, Math.min(getOnlineTitles().size(), maxLen)) : null);
    builder.append(", getOrganizations()=");
    builder.append(getOrganizations() != null ? getOrganizations().subList(0, Math.min(getOrganizations().size(), maxLen)) : null);
    builder.append(", getPage()=");
    builder.append(getPage());
    builder.append(", getPeople()=");
    builder.append(getPeople() != null ? getPeople().subList(0, Math.min(getPeople().size(), maxLen)) : null);
    builder.append(", getPublicationDate()=");
    builder.append(getPublicationDate());
    builder.append(", getPublicationDayOfMonth()=");
    builder.append(getPublicationDayOfMonth());
    builder.append(", getPublicationMonth()=");
    builder.append(getPublicationMonth());
    builder.append(", getPublicationYear()=");
    builder.append(getPublicationYear());
    builder.append(", getSection()=");
    builder.append(getSection());
    builder.append(", getSeriesName()=");
    builder.append(getSeriesName());
    builder.append(", getSlug()=");
    builder.append(getSlug());
    builder.append(", getSourcePath()=");
    builder.append(getSourcePath());
    builder.append(", getTaxonomicClassifiers()=");
    builder.append(getTaxonomicClassifiers() != null ? getTaxonomicClassifiers().subList(0, Math.min(getTaxonomicClassifiers().size(), maxLen)) : null);
    builder.append(", getTitles()=");
    builder.append(getTitles() != null ? getTitles().subList(0, Math.min(getTitles().size(), maxLen)) : null);
    builder.append(", getTypesOfMaterial()=");
    builder.append(getTypesOfMaterial() != null ? getTypesOfMaterial().subList(0, Math.min(getTypesOfMaterial().size(), maxLen)) : null);
    builder.append(", getUrl()=");
    builder.append(getUrl());
    builder.append(", getWordCount()=");
    builder.append(getWordCount());
    builder.append("]");
    return builder.toString();
  }
}
