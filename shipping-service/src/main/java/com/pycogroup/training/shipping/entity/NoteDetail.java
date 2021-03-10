package com.pycogroup.training.shipping.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pycogroup.training.messaging.date.DatePatterns;

@Getter
@Setter
@ToString
public class NoteDetail {

  private String notes;
  @CreatedBy
  private String notedBy;

  @CreatedDate
  @JsonFormat(pattern = DatePatterns.DATE_TIME_PATTERN)
  private LocalDateTime created;

  @LastModifiedDate
  @JsonFormat(pattern = DatePatterns.DATE_TIME_PATTERN)
  private LocalDateTime lastModified;

  public static NoteDetail of(final String message) {
    final NoteDetail note = new NoteDetail();
    note.setNotes(message);
    return note;
  }
}
