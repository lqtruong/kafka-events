package com.pycogroup.training.messaging.order;

import lombok.Data;

@Data
public class CheckedStatus {

  private Result status = Result.NOT_VERIFIED;
  private String reason;

  private CheckedStatus() {
  }

  private CheckedStatus(final Result status, final String reason) {
    this.status = status;
    this.reason = reason;
  }

  public static CheckedStatus from(final Result status, final String reason) {
    return new CheckedStatus(status, reason);
  }

  public static CheckedStatus newInstance() {
    return new CheckedStatus();
  }

  boolean isRejected() {
    return status.isFailed() || status.isNotVerified();
  }


  public static enum Result {
    NOT_VERIFIED, SUCCESSFUL, FAILED;

    public boolean isNotVerified() {
      return this == NOT_VERIFIED;
    }

    public boolean isSuccessful() {
      return this == SUCCESSFUL;
    }

    public boolean isFailed() {
      return this == FAILED;
    }

  }

}
