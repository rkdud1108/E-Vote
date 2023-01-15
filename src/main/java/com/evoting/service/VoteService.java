package com.evoting.service;

import com.evoting.controller.dto.response.ResponseVoteInterface;
import com.evoting.controller.dto.VoteDto;
import com.evoting.controller.dto.response.VoteResponseDto;
import com.evoting.domain.Agenda;
import com.evoting.domain.Member;
import com.evoting.domain.Vote;
import com.evoting.domain.enums.AgendaStatus;
import com.evoting.domain.enums.AgendaType;
import com.evoting.repository.AgendaRepository;
import com.evoting.repository.MemberRepository;
import com.evoting.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "안건이 종료된 상태입니다.");
        }

        //회원의 안건수에 따라 제한걸기
        //해당 안건에 몇개 투표했는지 가져온다.
        Member member = memberRepository.findByName(voteDto.getMemberName());
        Integer voteLimit = member.getVoteCount();

        Integer voteSum =  voteRepository.findMemberCntAgendaIdResult(member.getId(),agenda.getId());
        Integer voteReq = voteDto.getCount();

        if(voteLimit<=voteSum){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 회원의 의결권이 없습니다.");
        }

        //요청 들어온 값이 크다면
        if(voteLimit-voteSum<voteReq){
            voteReq = voteLimit-voteSum;
            voteDto.setCount(voteReq);
        }

        //FREE, LIMITED에 따라 투표 방식 달라짐
        AgendaType nowType = agenda.getAgendaType();
        //제한 경쟁이라면 agenda에서 max_count 따져줘야한다.
        if(nowType==AgendaType.LIMITED){
            Integer agendaLimit = agenda.getMaxCount();
            if(agendaLimit<voteReq){
                voteReq = agendaLimit;
                voteDto.setCount(voteReq);

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

        return entity.getId();
    }

    //안건 id에 따라 전체 투표결과 가져오기
    public List<ResponseVoteInterface> findAgendaIdResult(Long id){
        List<ResponseVoteInterface> result = voteRepository.findAgendaIdResult(id);
        return result;
    }

    //안건 id에 따라 전체 투표결과 가져오기(관리자)
    public List<VoteResponseDto> findAdminAgendaIdResult(Long id){
        List<VoteResponseDto> result = voteRepository.findAdminAgendaIdResult(id).stream().map(VoteResponseDto::new).collect(Collectors.toList());
        return result;
    }

}
