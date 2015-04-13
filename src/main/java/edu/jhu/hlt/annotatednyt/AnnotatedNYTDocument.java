/*
 * Copyright 2012-2015 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.annotatednyt;

import java.util.ArrayList;
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
    List<String> strList = new ArrayList<String>();
    // TODO
    return strList;
  }
  
  public List<String> getBodyAsList() {
    List<String> strList = new ArrayList<String>();
    // TODO
    return strList;    
  }
}
