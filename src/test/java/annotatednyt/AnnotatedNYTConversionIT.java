/*
 * Copyright 2012-2015 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package annotatednyt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nytlabs.corpus.NYTCorpusDocument;
import com.nytlabs.corpus.NYTCorpusDocumentParser;

import edu.jhu.hlt.acute.iterators.tar.TarGzArchiveEntryByteIterator;
import edu.jhu.hlt.annotatednyt.AnnotatedNYTDocument;

/**
 *
 */
public class AnnotatedNYTConversionIT {

  private static final Logger LOGGER = LoggerFactory.getLogger(AnnotatedNYTConversionIT.class);

  Path nytData = Paths.get(System.getProperty("anytDataPath")).resolve("data");

  @Test
  public void corpusTest() throws IOException {
    final NYTCorpusDocumentParser parser = new NYTCorpusDocumentParser();
    Files.list(this.nytData)
        .flatMap(p -> {
          try {
            return Files.list(p);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        })
        .filter(p -> p.toString().endsWith(".tgz"))
        .forEach(p -> {
          try (InputStream is = Files.newInputStream(p);
              BufferedInputStream bin = new BufferedInputStream(is, 1024 * 8 * 16);
              TarGzArchiveEntryByteIterator iter = new TarGzArchiveEntryByteIterator(bin);) {
            while (iter.hasNext()) {
              byte[] n = iter.next();
              NYTCorpusDocument nytd = parser.fromByteArray(n, false);
              LOGGER.info("Got NYT representation of document: {}", nytd.getGuid());
              AnnotatedNYTDocument anytd = new AnnotatedNYTDocument(nytd);
              LOGGER.info("ANYT representation: {}", anytd.toString());
            }
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
  }
}
