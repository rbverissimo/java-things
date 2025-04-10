package br.com.coltran.farmacinhapp.security.domain.interfaces;

import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;
import br.com.coltran.farmacinhapp.security.domain.User;

import java.time.ZonedDateTime;
import java.util.UUID;

public interface TableToken {

    User getUser();
    void setUser(User user);

    String getToken();
    void setToken(String token);

    ZonedDateTime getExpiredAt();
    void setExpiredAt(ZonedDateTime expirationDateTime);

    ZonedDateTime getVerifiedAt();
    void setVerifiedAt(ZonedDateTime verifiedAt);

    default void issueTo(User user, TableToken t, ZonedDateTime now, Integer expirationLimitHours){
        UUID token = UUID.randomUUID();
        t.setToken(token.toString());
        t.setUser(user);
        t.setExpiredAt(now.plusHours(expirationLimitHours));
    }



}
