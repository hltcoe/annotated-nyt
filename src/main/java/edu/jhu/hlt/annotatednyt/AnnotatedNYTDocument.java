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
import java.util.regex.Pattern;

import com.nytlabs.corpus.NYTCorpusDocument;

/**
 * Wrapper for the {@link NYTCorpusDocument} object, providing some
 * extra utility.
 * <br><br>
 * In this wrapper, the semantics of possibly empty fields are represented by {@link Optional}
 * wrappers. Methods that return an {@link Optional} object represent fields that
 * can be <code>null</code> in the Annotated NYT corpus. Methods that return an object directly
 * (e.g., {@link String}, {@link Integer} etc.) are guaranteed to be non-null across the corpus.
 * <br><br>
 * This wrapper also provides {@link List} objects, for convenience, for many fields.
 * The semantics of these lists is as follows: the list is guaranteed to be non-null,
 * but the list might be empty if the underlying corpus document does not have content
 * for the field specified.
 */
public class AnnotatedNYTDocument {

  // Useful pattern for removing non-breaking whitespace
  // that is found in some online lead paragraphs.
  private static final Pattern NBS_PATTERN = Pattern.compile("\\p{Zs}");

  private final NYTCorpusDocument nytdoc;

  /**
   * Wrap an {@link NYTCorpusDocument} object.
   *
   * @param nytdoc the {@link NYTCorpusDocument} to wrap
   */
  public AnnotatedNYTDocument(final NYTCorpusDocument nytdoc) {
    this.nytdoc = nytdoc;
  }

  /**
   * @return the guid of the underlying document as an {@link Integer}. Guaranteed non-null.
   */
  public Integer getGuid() {
    return this.nytdoc.getGuid();
  }

  /**
   * @return the online section field as a list. It is split by the <code>;</code> delimeter.
   */
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

  /**
   * @return the lead paragraph field as a list. Split by *nix newlines.
   */
  public List<String> getLeadParagraphAsList() {
    return getOptionalStringFieldAsList(this.nytdoc.getLeadParagraph());
  }

  /**
   * @return the online lead paragraph field as a list, split by *nix newlines.
   */
  public List<String> getOnlineLeadParagraphAsList() {
    return getOptionalStringFieldAsList(this.nytdoc.getOnlineLeadParagraph());
  }

  /**
   *
   * @return the body field as a list, split by *nix newlines.
   */
  public List<String> getBodyAsList() {
    return getOptionalStringFieldAsList(this.nytdoc.getBody());
  }

  /**
   * @param fieldStr possibly <code>null</code> {@link String} to split
   * @return a {@link List} of {@link String} objects; the list is empty if <code>fieldStr</code>
   * is null, otherwise, it will have all elements of calling {@link String#split(String)} with
   * <code>\n</code> as the parameter.
   */
  private static final List<String> getOptionalStringFieldAsList(final String fieldStr) {
    // the list could have only spaces - in that case, just return an empty list.
    return removeNonBreakingSpaceChars(fieldStr)
        .map(AnnotatedNYTDocument::unixNewlineStringToList)
        .orElse(new ArrayList<String>());
  }

  /**
   * @param unixNewlineStr a string to split via a *nix newline
   * @return a {@link List} of {@link String} objects from the string split
   */
  private static final List<String> unixNewlineStringToList(final String unixNewlineStr) {
    List<String> strList = new ArrayList<String>();
    String[] split = unixNewlineStr.split("\n");
    for (String s : split)
      strList.add(s);
    return strList;
  }

  /**
   * Generic utility method to take a possibly <code>null</code> list and
   * return either an empty list of the same type, or the list itself
   * if it is not empty.
   *
   * @param stuff a {@link List}, which may be <code>null</code>
   * @return an empty {@link List}, or the list itself if not null.
   */
  private static final <T> List<T> nullListAsEmptyList(List<T> stuff) {
    if (stuff == null)
      return new ArrayList<T>();
    else
      return stuff;
  }

  /**
   * @return the headline
   */
  public Optional<String> getHeadline() {
    return Optional.ofNullable(this.nytdoc.getHeadline());
  }

  /**
   * @return the online headline
   */
  public Optional<String> getOnlineHeadline() {
    return Optional.ofNullable(this.nytdoc.getOnlineHeadline());
  }

  /**
   * @return the byline
   */
  public Optional<String> getByline() {
    return Optional.ofNullable(this.nytdoc.getByline());
  }

  /**
   * @return the dateline as a string
   */
  public Optional<String> getDateline() {
    return Optional.ofNullable(this.nytdoc.getDateline());
  }

  /**
   *
   * @return the article abstract
   */
  public Optional<String> getArticleAbstract() {
    return Optional.ofNullable(this.nytdoc.getArticleAbstract());
  }

  /**
   *
   * @return the lead paragraph
   */
  public Optional<String> getLeadParagraph() {
    return Optional.ofNullable(this.nytdoc.getLeadParagraph());
  }

  /**
   *
   * @return the online lead paragraph
   */
  public Optional<String> getOnlineLeadParagraph() {
    final String olp = this.nytdoc.getOnlineLeadParagraph();
    return removeNonBreakingSpaceChars(olp);
  }

  /**
   *
   * @return the correction text
   */
  public Optional<String> getCorrectionText() {
    return Optional.ofNullable(this.nytdoc.getCorrectionText());
  }

  /**
   *
   * @return the kicker
   */
  public Optional<String> getKicker() {
    return Optional.ofNullable(this.nytdoc.getKicker());
  }

  /**
   *
   * @return the alternate url, as a {@link URL} object.
   */
  public Optional<URL> getAlternateURL() {
    return Optional.ofNullable(this.nytdoc.getAlternateURL());
  }

  /**
   *
   * @return the descriptors
   */
  public List<String> getDescriptors() {
    return nullListAsEmptyList(this.nytdoc.getDescriptors());
  }

  /**
   *
   * @return the author biography
   */
  public Optional<String> getAuthorBiography() {
    return Optional.ofNullable(this.nytdoc.getAuthorBiography());
  }

  /**
   *
   * @return the banner
   */
  public Optional<String> getBanner() {
    return Optional.ofNullable(this.nytdoc.getBanner());
  }

  /**
   *
   * @return the biographical categories, as a list. May be empty.
   */
  public List<String> getBiographicalCategories() {
    return nullListAsEmptyList(this.nytdoc.getBiographicalCategories());
  }

  /**
   *
   * @return the column name
   */
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

  private static final Optional<String> removeNonBreakingSpaceChars(final String in) {
    return Optional.ofNullable(in)
        .map(s -> s.replaceAll(NBS_PATTERN.toString(), " "))
        .map(String::trim)
        .filter(s -> !s.isEmpty());
  }
}
