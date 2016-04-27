# annotated-nyt
Utilities for reading the [Annotated NYT corpus](https://catalog.ldc.upenn.edu/LDC2008T19).

![Maven Badges](https://maven-badges.herokuapp.com/maven-central/edu.jhu.hlt/annotated-nyt/badge.svg)
[![javadoc.io](https://javadocio-badges.herokuapp.com/edu.jhu.hlt/annotated-nyt/badge.svg)](http://www.javadoc.io/doc/edu.jhu.hlt/annotated-nyt/)

Latest Maven dependency
---
```xml
<dependency>
  <groupId>edu.jhu.hlt</groupId>
  <artifactId>annotated-nyt</artifactId>
  <version>1.1.4</version>
</dependency>
```

## Quick start
Create a `NYTCorpusDocumentParser` object:
```java
NYTCorpusDocumentParser parser = new NYTCorpusDocumentParser();
```

Read a single `.xml` document from the annotated NYT corpus:
```java
Path p = Paths.get("/your/path/.xml");
byte[] bytes = Files.readAllBytes(p);
NYTCorpusDocument ncd = parser.fromByteArray(bytes, false);
AnnotatedNYTDocument and = new AnnotatedNYTDocument(ncd);
```

## API
All fields in the `AnnotatedNYTDocument` objects are guaranteed to
be non-`null`.

Many of the fields in the corpus can be empty or `null` in the
documents themselves. These fields are represented in the wrapper
object, `AnnotatedNYTDocument`, as `Optional` fields.

Many convenience methods exist to convert naturally list-based items (e.g.,
the body as a `List` of paragraphs). Many of these sections, however,
can also be `null`. In these cases, the API will return an empty `List`
object. These lists will never be `null`.

## Running the integration test
The integration test can be executed with the following command:

```sh
mvn clean verify -Pitest -DanytDataPath=/path/to/your/LDC/corpus/data/dir
```

The `anyDataPath` property should point to your `data` directory
from the extracted ANYT corpus. This directory contains many folders
with numbers as names, representing years of annotated NYT data.
