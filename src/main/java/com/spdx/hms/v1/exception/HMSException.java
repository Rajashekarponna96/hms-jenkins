package com.spdx.hms.v1.exception;

import lombok.Getter;

@Getter
public class HMSException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String debugMessage;
    private final HMSError error;
    private final ErrorCode code;

    public HMSException(
            final ErrorCode code,
            final String message,
            final String debugMessage,
            final Exception e,
            final HMSError error) {
        super(message, e);
        this.debugMessage = debugMessage;
        this.error = error;
        this.code = code;
    }


    public HMSException(
            final ErrorCode code,
            final String message,
            final String debugMessage,
            final Exception e) {
        this(code, message, debugMessage, e, null);
    }

    public HMSException(final ErrorCode code, final String message) {
        this(code, message, null, null);
    }


    public HMSException(final HMSError error) {
        this(null, error.getErrorCode(), null, null, error);
    }
}
