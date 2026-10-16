package com.polskownia.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlWorkDayRepository extends WorkDayRepository, JpaRepository<WorkDay, Integer>
{
}
