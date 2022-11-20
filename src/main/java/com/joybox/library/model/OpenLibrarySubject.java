package com.joybox.library.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class OpenLibrarySubject {
  private String key;
  private String name;

  @JsonProperty("subject_type")
  private String subjectType;

  @JsonProperty("work_count")
  private int workCount;

  private List<HashMap<String, Object>> works;

  public OpenLibrarySubject() {
  }

  public OpenLibrarySubject(String key, String name, String subjectType, int workCount, List<HashMap<String, Object>> works) {
    this.key = key;
    this.name = name;
    this.subjectType = subjectType;
    this.workCount = workCount;
    this.works = works;
  }
}
