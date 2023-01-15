# G사 주주총회 전자 투표 시스템


## 개발 환경
- Java 11
- Spring boot 2.4.1
- MySQL 8.0.27

## Dependencies
- Spring Web
- Spring Data JPA
- Spring Security
    - jsonwebtoken(JWT)

## ERD 
![ERD_수정](/uploads/414babe198a57a56e7e8f85cbd0f07b6/ERD_수정.png)

## 도메인 세부 설명
- **Authority**
    - authority_name(권한 이름)
        - ROLE_USER : 이용자
        - ROLE_ADMIN : 관리자

- **Agenda**
    - Agenda_Type(안건 타입)
        - LIMETED(제한 경쟁)
        - FREE(자유 경쟁)

    - Agenda_Status(안건 상태)
        - ON(안건 진행중)
        - END(안건 종료)
        - WAIT(안건 대기중)
    
- **Vote**
    - Vote_Type(투표 타입)
        - AGREE(찬성)
        - DISAGREE(반대)
        - ABSTENTION(기권)

        

## API 명세서
- [API 명세서](https://documenter.getpostman.com/view/25239459/2s8ZDSd5Zz)

***

```
cd existing_repo
git remote add origin http://mentoring-gitlab.gabia.com/mentee/mentee_2023.01/sandbox/jenna_e-voting.git
git branch -M main
git push -uf origin main
```

## Integrate with your tools

- [ ] [Set up project integrations](http://mentoring-gitlab.gabia.com/mentee/mentee_2023.01/sandbox/jenna_e-voting/-/settings/integrations)


***

# Editing this README

When you're ready to make this README your own, just edit this file and use the handy template below (or feel free to structure it however you want - this is just a starting point!). Thank you to [makeareadme.com](https://www.makeareadme.com/) for this template.
