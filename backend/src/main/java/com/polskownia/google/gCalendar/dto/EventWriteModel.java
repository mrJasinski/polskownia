package com.polskownia.google.gCalendar.dto;

import java.time.OffsetDateTime;
import java.util.Set;

public record EventWriteModel
(
    String title
    , String description
    , OffsetDateTime start
    , OffsetDateTime end
    , Set<String> emails
)
{
}
