package com.example.timetrackingapi.controller.converter;

import com.example.timetrackingapi.controller.model.BreakRecord;
import com.example.timetrackingapi.domain.BreakEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.function.Function;

@Service
public class BreakConverter implements Function<BreakEntity, BreakRecord> {

    @Override
    public BreakRecord apply(BreakEntity breakEntity) {
        return new BreakRecord(
                breakEntity.getBreakStart().atOffset(ZoneOffset.ofHours(9)),
                breakEntity.getBreakEnd().atOffset(ZoneOffset.ofHours(9))
        );
    }
}
