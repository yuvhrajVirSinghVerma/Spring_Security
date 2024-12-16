package com.example.demo.services;

import com.example.demo.entities.SessionEntity;
import com.example.demo.entities.User;
import com.example.demo.repository.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class SessionService {


    @Autowired
    SessionRepo sessionRepo;

    private int SESSION_LIMIT=2;

    public boolean isSessionLimitExceeded(User user){
        List<SessionEntity> allSessions=sessionRepo.findByUser(user);
        System.out.println("allSessions "+allSessions);
       return allSessions.size()==SESSION_LIMIT;
    }
    public void generateSession(User user,String refreshToken){


        if(isSessionLimitExceeded(user)){
            List<SessionEntity> allSessions=sessionRepo.findByUser(user);
            allSessions.sort(Comparator.comparing(session->session.getLastUsedAt()));
            SessionEntity sessionentity=allSessions.getFirst();
            sessionRepo.delete(sessionentity);
        }

        SessionEntity newSession=SessionEntity.builder().refreshToken(refreshToken).user(user).build();
        sessionRepo.save(newSession);
    }

   public boolean validateSession(String refreshToken){
        SessionEntity sessionEntity=sessionRepo.
                findByRefreshToken(refreshToken).
                orElseThrow(()->new SessionAuthenticationException("Session Not Found for RefreshToken : "+refreshToken));

        //if refreshToken is valid then update its last used time
        sessionEntity.setLastUsedAt(LocalDateTime.now());
        sessionRepo.save(sessionEntity);
        return sessionEntity!=null;
    }
}
