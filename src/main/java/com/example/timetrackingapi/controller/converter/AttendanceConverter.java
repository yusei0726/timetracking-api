package com.example.timetrackingapi.controller.converter;

import com.example.timetrackingapi.controller.model.Attendances;
import com.example.timetrackingapi.domain.AttendanceEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.function.Function;

@Service
public class AttendanceConverter implements Function<AttendanceEntity, Attendances> {
    @Override
    public Attendances apply(AttendanceEntity attendanceEntity) {
        return new Attendances(
                attendanceEntity.getUserId(),
                attendanceEntity.getDate(),
                attendanceEntity.getTimeIn().atOffset(ZoneOffset.ofHours(9)),
                attendanceEntity.getTimeOut().atOffset(ZoneOffset.ofHours(9))
        );
    }
}
