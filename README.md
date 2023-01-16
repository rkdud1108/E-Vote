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
![ERD_날짜추가](/uploads/ab6214c30b7a3a1dd1a4af520bceff42/ERD_날짜추가.png)

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

## Feedback
- REST API 설계 규칙
- toEntity : MapStruct 이용한 Entity <-> DTO 변환, ModelMapper

## 개선점
- 동시성 구현
- 예외 처리 : null, Bad Request 등...
