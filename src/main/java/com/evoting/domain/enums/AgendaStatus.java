package com.evoting.domain.enums;

public enum AgendaStatus {
    ON(1),
    END(2),
    WAIT(3);

    private final int val;

    AgendaStatus(int val) {
        this.val=val;
    }
}
