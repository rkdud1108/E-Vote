package com.evoting.service;

import com.evoting.controller.dto.AgendaDto;
import com.evoting.controller.dto.VoteDto;
import com.evoting.controller.dto.VoteResponseDto;
import com.evoting.domain.Agenda;
import com.evoting.domain.Member;
import com.evoting.domain.Vote;
import com.evoting.domain.enums.AgendaStatus;
import com.evoting.repository.AgendaRepository;
import com.evoting.repository.MemberRepository;
import com.evoting.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;

    private final AgendaRepository agendaRepository;
    private final MemberRepository memberRepository;

    //안건 id에 따라 투표하기
    //자유 경쟁만 구현
    @Transactional
    public Long voteToAgenda(VoteDto voteDto){

        Agenda agenda = agendaRepository.findById(voteDto.getAgendaId()).orElseThrow(IllegalArgumentException::new);

        //안건이 끝난상태인지 체크
        if(agenda.getAgendaStatus().equals(AgendaStatus.END)){
            throw new IllegalStateException("안건이 종료된 상태입니다.");
        }

        //회원의 안건수에 따라 제한걸기
        Member member = memberRepository.findByName(voteDto.getMemberName());
        Long voteLimit = member.getVoteCount();
        Long voteReq = voteDto.getCount();

        //요청 들어온 값이 크다면
        if(voteLimit<voteReq){
            voteDto.setCount(voteLimit);
            voteReq = voteLimit;
        }

        //FREE, LIMIT에 따라 투표 방식 달라짐
        String nowType = agenda.getAgendaType().toString();
        //제한 경쟁이라면 agenda에서 max_count 따져줘야한다.
        if(nowType.equals("LIMIT")){
            Long agendaLimit = agenda.getMaxCount();
            if(agendaLimit<voteReq){
                voteDto.setCount(agendaLimit);
                voteReq = agendaLimit;

                //안건 끝난상태로 update
                agenda.setAgendaStatus(AgendaStatus.END);
                voteDto.setAgenda(agenda);
            }
        }

        //agenda_id setting
        //memeber_id setting
        voteDto.setAgenda(agenda);
        voteDto.setMember(member);

        Vote entity = voteRepository.save(voteDto.toEntity());

        //쓴 의결권만큼 member cnt값 update
        memberRepository.updateAfterVote(voteDto.getMemberName(),voteLimit);

        return entity.getId();
    }

    //안건 id에 따라 전체 투표결과 가져오기
    public List<VoteResponseDto> findAgendaIdResult(Long id){
        List<VoteResponseDto> result = voteRepository.findAgendaIdResult(id).stream().map(VoteResponseDto::new).collect(Collectors.toList());

        return result;
    }

}
