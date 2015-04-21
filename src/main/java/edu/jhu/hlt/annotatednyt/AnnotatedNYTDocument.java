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
    Optional<String> online = Optional.ofNullable(this.nytdoc.getSection());
    online.ifPresent(str -> {
      String[] split = str.split(";");
      for (String s : split)
        onlineSectionList.add(s);
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

  public String getHeadline() {
    return this.nytdoc.getHeadline();
  }

  public String getOnlineHeadline() {
    return this.nytdoc.getOnlineHeadline();
  }

  public String getByline() {
    return this.nytdoc.getByline();
  }

  public String getDateline() {
    return this.nytdoc.getDateline();
  }

  public String getArticleAbstract() {
    return this.nytdoc.getArticleAbstract();
  }

  public String getLeadParagraph() {
    return this.nytdoc.getLeadParagraph();
  }

  public String getOnlineLeadParagraph() {
    return this.nytdoc.getOnlineLeadParagraph();
  }

  public String getCorrectionText() {
    return this.nytdoc.getCorrectionText();
  }

  public String getKicker() {
    return this.nytdoc.getKicker();
  }

  public Optional<URL> getAlternateURL() {
    return Optional.ofNullable(this.nytdoc.getAlternateURL());
  }

  public List<String> getDescriptors() {
    return nullListAsEmptyList(this.nytdoc.getDescriptors());
  }

  public String getAuthorBiography() {
    return this.nytdoc.getAuthorBiography();
  }

  public String getBanner() {
    return this.nytdoc.getBanner();
  }

  public List<String> getBiographicalCategories() {
    return nullListAsEmptyList(this.nytdoc.getBiographicalCategories());
  }

  public String getColumnName() {
    return this.nytdoc.getColumnName();
  }

  /**
   * Accessor for the columnNumber property.
   *
   * @return the columnNumber
   */
  public Integer getColumnNumber() {
    return this.nytdoc.getColumnNumber();
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
  public String getDayOfWeek() {
    return this.nytdoc.getDayOfWeek();
  }

  /**
   * Accessor for the featurePage property.
   *
   * @return the featurePage
   */
  public String getFeaturePage() {
    return this.nytdoc.getFeaturePage();
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
  public String getNewsDesk() {
    return this.nytdoc.getNewsDesk();
  }

  /**
   * Accessor for the normalizedByline property.
   *
   * @return the normalizedByline
   */
  public String getNormalizedByline() {
    return this.nytdoc.getNormalizedByline();
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
  public String getOnlineSection() {
    return this.nytdoc.getOnlineSection();
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
  public Integer getPage() {
    return this.nytdoc.getPage();
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
  public Integer getPublicationDayOfMonth() {
    return this.nytdoc.getPublicationDayOfMonth();
  }

  /**
   * Accessor for the publicationMonth property.
   *
   * @return the publicationMonth
   */
  public Integer getPublicationMonth() {
    return this.nytdoc.getPublicationMonth();
  }

  /**
   * Accessor for the publicationYear property.
   *
   * @return the publicationYear
   */
  public Integer getPublicationYear() {
    return this.nytdoc.getPublicationYear();
  }

  /**
   * Accessor for the section property.
   *
   * @return the section
   */
  public String getSection() {
    return this.nytdoc.getSection();
  }

  /**
   * Accessor for the seriesName property.
   *
   * @return the seriesName
   */
  public String getSeriesName() {
    return this.nytdoc.getSeriesName();
  }

  /**
   * Accessor for the slug property.
   *
   * @return the slug
   */
  public String getSlug() {
    return this.nytdoc.getSlug();
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
  public Integer getWordCount() {
    return this.nytdoc.getWordCount();
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("AnnotatedNYTDocument [getGuid()=");
    builder.append(getGuid());
    builder.append(", getOnlineSectionAsList()=");
    builder.append(getOnlineSectionAsList());
    builder.append(", getLeadParagraphAsList()=");
    builder.append(getLeadParagraphAsList());
    builder.append(", getOnlineLeadParagraphAsList()=");
    builder.append(getOnlineLeadParagraphAsList());
    builder.append(", getBodyAsList()=");
    builder.append(getBodyAsList());
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
    builder.append(getDescriptors());
    builder.append(", getAuthorBiography()=");
    builder.append(getAuthorBiography());
    builder.append(", getBanner()=");
    builder.append(getBanner());
    builder.append(", getBiographicalCategories()=");
    builder.append(getBiographicalCategories());
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
    builder.append(getGeneralOnlineDescriptors());
    builder.append(", getLocations()=");
    builder.append(getLocations());
    builder.append(", getNames()=");
    builder.append(getNames());
    builder.append(", getNewsDesk()=");
    builder.append(getNewsDesk());
    builder.append(", getNormalizedByline()=");
    builder.append(getNormalizedByline());
    builder.append(", getOnlineDescriptors()=");
    builder.append(getOnlineDescriptors());
    builder.append(", getOnlineLocations()=");
    builder.append(getOnlineLocations());
    builder.append(", getOnlineOrganizations()=");
    builder.append(getOnlineOrganizations());
    builder.append(", getOnlinePeople()=");
    builder.append(getOnlinePeople());
    builder.append(", getOnlineSection()=");
    builder.append(getOnlineSection());
    builder.append(", getOnlineTitles()=");
    builder.append(getOnlineTitles());
    builder.append(", getOrganizations()=");
    builder.append(getOrganizations());
    builder.append(", getPage()=");
    builder.append(getPage());
    builder.append(", getPeople()=");
    builder.append(getPeople());
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
    builder.append(getTaxonomicClassifiers());
    builder.append(", getTitles()=");
    builder.append(getTitles());
    builder.append(", getTypesOfMaterial()=");
    builder.append(getTypesOfMaterial());
    builder.append(", getUrl()=");
    builder.append(getUrl());
    builder.append(", getWordCount()=");
    builder.append(getWordCount());
    builder.append("]");
    return builder.toString();
  }
}
