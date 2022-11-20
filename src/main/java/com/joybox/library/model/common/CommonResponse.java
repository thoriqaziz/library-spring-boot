package com.joybox.library.model.common;

import lombok.Data;

@Data
public class CommonResponse {
  private boolean success;
  private String messageResponse;

  public CommonResponse(boolean success, String messageResponse) {
    this.success = success;
    this.messageResponse = messageResponse;
  }
}
