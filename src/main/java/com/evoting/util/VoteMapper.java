package com.evoting.util;

import com.evoting.controller.dto.VoteDto;
import com.evoting.domain.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VoteMapper{

    VoteMapper INSTANCE = Mappers.getMapper(VoteMapper.class);

    Vote voteDtoToEntity(VoteDto voteDto);

    @Mapping(target = "memberName", expression = "java(vote.getMember().getName())")
    @Mapping(target = "agendaId", expression = "java(vote.getAgenda().getId())")
    VoteDto voteToDto(Vote vote);
}
