package com.seed.trace.resources.submission.types;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExecutionSessionStatus {
    CREATING_CONTAINER("CREATING_CONTAINER"),

    PROVISIONING_CONTAINER("PROVISIONING_CONTAINER"),

    PENDING_CONTAINER("PENDING_CONTAINER"),

    RUNNING_CONTAINER("RUNNING_CONTAINER"),

    LIVE_CONTAINER("LIVE_CONTAINER"),

    FAILED_TO_LAUNCH("FAILED_TO_LAUNCH");

    private final String value;

    ExecutionSessionStatus(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
