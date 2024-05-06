package com.example.timetrackingapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "attendances", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "date"})
})
public class AttendanceEntity extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id", nullable = false)
    private Integer attendanceId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time_in")
    private LocalDateTime timeIn;

    @Column(name = "time_out")
    private LocalDateTime timeOut;
}
