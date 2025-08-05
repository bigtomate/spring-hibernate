package com.springboot_hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class FootBallMatch {

    @Id
    @GeneratedValue
    private long id;
    private String team;
    @Column(name="\"year\"")
    private Integer year;
    private Integer goal;
    private Integer draw;

    @CreationTimestamp
    private LocalDateTime inserted_at;

    public FootBallMatch(String team, Integer year, Integer goal, Integer draw) {
        this.team = team;
        this.year = year;
        this.goal = goal;
        this.draw = draw;
    }
}
