package com.example.timetrackingapi.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "breaks")
public class BreakEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "break_id")
    private Integer breakId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_id", nullable = false)
    private AttendanceEntity attendance;

    @Column(name = "break_start")
    private LocalDateTime breakStart;

    @Column(name = "break_end")
    private LocalDateTime breakEnd;
}
