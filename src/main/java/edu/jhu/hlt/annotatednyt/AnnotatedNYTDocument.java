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
    return nullListAsEmptyList(this.getOnlineDescriptors());
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
  
//  if (cDoc.getColumnNumber() != null)
//    ni.setColumnNumber(cDoc.getColumnNumber());
//  final Date dt = cDoc.getCorrectionDate();
//  if (dt != null)
//    ni.setCorrectionDate(dt.getTime());
//  ni.setCorrectionText(cDoc.getCorrectionText());
//  ni.setCredit(cDoc.getCredit());
//  
//  ni.setDayOfWeek(cDoc.getDayOfWeek());
//  if(cDoc.getDescriptors() != null && cDoc.getDescriptors().size() > 0) {
//    ni.setDescriptorList(cDoc.getDescriptors());
//  }
//  ni.setFeaturePage(cDoc.getFeaturePage());
//  if(cDoc.getGeneralOnlineDescriptors() != null && cDoc.getGeneralOnlineDescriptors().size() > 0) {
//    ni.setGeneralOnlineDescriptorList(cDoc.getGeneralOnlineDescriptors());
//  }
//  
//  ni.setGuid(cDoc.getGuid());
//  
//  ni.setKicker(cDoc.getKicker());
//  ni.setLeadParagraphList(cDoc.getLeadParagraphAsList());
//
//  if(cDoc.getLocations() != null && cDoc.getLocations().size() > 0) {
//    ni.setLocationList(cDoc.getLocations());
//  }
//  if(cDoc.getNames() != null && cDoc.getNames().size() > 0) {
//    ni.setNameList(cDoc.getNames());
//  }
//  ni.setNewsDesk(cDoc.getNewsDesk());
//  ni.setNormalizedByline(cDoc.getNormalizedByline());
//  if(cDoc.getOnlineDescriptors() != null && cDoc.getOnlineDescriptors().size() > 0) {
//    ni.setOnlineDescriptorList(cDoc.getOnlineDescriptors());
//  }
//  ni.setOnlineHeadline(cDoc.getOnlineHeadline());
//  ni.setOnlineLeadParagraph(cDoc.getOnlineLeadParagraph());
//  if(cDoc.getOnlineLocations() != null && cDoc.getOnlineLocations().size() > 0) {
//    ni.setOnlineLocationList(cDoc.getOnlineLocations());
//  }
//  if(cDoc.getOnlineOrganizations() != null && cDoc.getOnlineOrganizations().size() > 0) {
//    ni.setOnlineOrganizationList(cDoc.getOnlineOrganizations());
//  }
//  if(cDoc.getOnlinePeople() != null && cDoc.getOnlinePeople().size() > 0) {
//    ni.setOnlinePeople(cDoc.getOnlinePeople());
//  }
//  if(cDoc.getOnlineSection() != null && cDoc.getOnlineSection().size() > 0) {
//    ni.setOnlineSectionList(cDoc.getOnlineSection());
//  }
//  if(cDoc.getOnlineTitles() != null && cDoc.getOnlineTitles().size() > 0) {
//    ni.setOnlineTitleList(cDoc.getOnlineTitles());
//  }
//  if(cDoc.getOrganizations() != null && cDoc.getOrganizations().size() > 0) {
//    ni.setOrganizationList(cDoc.getOrganizations());
//  }
//  if (cDoc.getPage() != null) {
//    ni.setPage(cDoc.getPage());
//  }
//  if(cDoc.getPeople() != null && cDoc.getPeople().size() > 0) {
//    ni.setPeopleList(cDoc.getPeople());
//  }
//  {
//  final Date pdt = cDoc.getPublicationDate();
//  if (pdt != null)
//    ni.setPublicationDate(pdt.getTime());
//  }
//  // without this guard, autoboxing will throw a NPE
//  if(cDoc.getPublicationDayOfMonth() != null) {
//    ni.setPublicationDayOfMonth(cDoc.getPublicationDayOfMonth());
//  }
//  if(cDoc.getPublicationMonth() != null) {
//    ni.setPublicationMonth(cDoc.getPublicationMonth());
//  }
//  if(cDoc.getPublicationYear() != null) {
//    ni.setPublicationYear(cDoc.getPublicationYear());
//  }
//  ni.setSection(cDoc.getSection());
//  ni.setSeriesName(cDoc.getSeriesName());
//  ni.setSlug(cDoc.getSlug());
//
//  if(cDoc.getTaxonomicClassifiers() != null && cDoc.getTaxonomicClassifiers().size() > 0) {
//    ni.setTaxonomicClassifierList(cDoc.getTaxonomicClassifiers());
//  }
//  if(cDoc.getTitles() != null && cDoc.getTitles().size() > 0) {
//    ni.setTitleList(cDoc.getTitles());
//  }
//  if(cDoc.getTypesOfMaterial() != null && cDoc.getTypesOfMaterial().size() > 0) {
//    ni.setTypesOfMaterialList(cDoc.getTypesOfMaterial());
//  }
//  final URL url = cDoc.getUrl();
//  if (url != null) {
//    ni.setUrl(url.toString());
//  }
//  if(cDoc.getWordCount() != null) {
//    ni.setWordCount(cDoc.getWordCount());
//  }
}
