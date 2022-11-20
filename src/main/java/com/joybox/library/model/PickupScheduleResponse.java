package com.joybox.library.model;

import com.joybox.library.model.common.HourMinute;
import com.joybox.library.model.common.MonthDayYear;
import lombok.Data;

@Data
public class PickupScheduleResponse extends PickupSchedule {
  private Book book;

  public PickupScheduleResponse(Book book, MonthDayYear pickupDate, HourMinute pickupTime) {
    super(pickupDate, pickupTime);
    this.book = book;
  }
}
