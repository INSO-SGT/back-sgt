package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.dto.SessionDTO.DetailsSession;
import com.plenamente.sgt.domain.dto.SessionDTO.RegisterSession;
import com.plenamente.sgt.domain.entity.Session;

import java.util.List;

public interface SesionService {


    String registerSession(RegisterSession registerSession);
    List<Session> getAllSessions();
    DetailsSession getSessionDetails(Long id);
}
