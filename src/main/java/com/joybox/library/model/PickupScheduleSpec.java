package com.joybox.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PickupScheduleSpec extends PickupSchedule {
  private String bookTitle;
}
