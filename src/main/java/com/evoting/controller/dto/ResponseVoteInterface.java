package com.evoting.controller.dto;

import com.evoting.domain.enums.VoteType;

public interface ResponseVoteInterface {
    String getType();
    Integer getCount();
}
