package com.evoting.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @NotNull(message = "name은 필수 값입니다.")
    private String name;
    @NotNull(message = "pwd는 필수 값입니다.")
    private String pwd;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "writer")
    private List<Agenda> agendas =new ArrayList<>();

    @Builder
    public Member(Long id, String name, String pwd, Role role, List<Agenda> agendas) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.role = role;
        this.agendas = agendas;
    }
}
