package com.example.timetrackingapi.controller.converter;

import com.example.timetrackingapi.controller.model.Attendances;
import com.example.timetrackingapi.domain.AttendanceEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Optional;
import java.util.function.Function;

@Service
public class AttendanceConverter implements Function<AttendanceEntity, Attendances> {
    @Override
    public Attendances apply(AttendanceEntity attendanceEntity) {
        return new Attendances(
                attendanceEntity.getUserId(),
                attendanceEntity.getDate(),
                Optional.ofNullable(attendanceEntity.getTimeIn())
                        .map(timeIn -> timeIn.atOffset(ZoneOffset.ofHours(9)))
                        .orElse(null)
                ,
                Optional.ofNullable(attendanceEntity.getTimeOut())
                        .map(timeOut -> timeOut.atOffset(ZoneOffset.ofHours(9)))
                        .orElse(null)
        );
    }
}
