package com.example.timetrackingapi.controller.converter;

import com.example.timetrackingapi.controller.model.BreakRecord;
import com.example.timetrackingapi.domain.BreakEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Optional;
import java.util.function.Function;

@Service
public class BreakConverter implements Function<BreakEntity, BreakRecord> {

    @Override
    public BreakRecord apply(BreakEntity breakEntity) {
        if (breakEntity == null) {
            throw new IllegalArgumentException();
        }

        return new BreakRecord(
                Optional.ofNullable(
                        breakEntity.getBreakStart())
                        .map(breakStart -> breakStart.atOffset(ZoneOffset.ofHours(9)))
                        .orElse(null),
                Optional.ofNullable(
                        breakEntity.getBreakEnd())
                        .map(breakEnd -> breakEnd.atOffset(ZoneOffset.ofHours(9)))
                        .orElse(null)
        );
    }
}
