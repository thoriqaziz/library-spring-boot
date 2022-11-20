package com.joybox.library.model;

import com.joybox.library.model.common.HourMinute;
import com.joybox.library.model.common.MonthDayYear;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PickupSchedule {
  private MonthDayYear pickupDate;
  private HourMinute pickupTime;
}
