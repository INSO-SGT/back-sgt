package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.dto.SessionDto.ListSession;
import com.plenamente.sgt.domain.dto.SessionDto.RegisterSession;
import com.plenamente.sgt.domain.dto.SessionDto.UpdateSession;
import com.plenamente.sgt.domain.entity.Session;

import java.time.LocalDate;
import java.util.List;

public interface SessionService {
    Session createSession(RegisterSession dto);
    Session updateSession(UpdateSession dto);
    List<ListSession> getSessionsByDate(LocalDate date);
    List<ListSession> getSessionsByTherapist(Long therapistId);
}