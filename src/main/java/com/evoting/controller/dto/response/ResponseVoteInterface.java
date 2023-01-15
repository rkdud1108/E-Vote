package com.evoting.controller.dto.response;

import com.evoting.domain.enums.VoteType;

public interface ResponseVoteInterface {
    String getType();
    Integer getCount();
}
