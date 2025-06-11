package com.denis.api.modules.transaction.util;

import com.denis.api.modules.transaction.domain.Transaction;
import com.denis.api.modules.user.domain.User;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static com.denis.api.modules.transaction.error.TransactionErrorMessage.USER_ARE_NOT_THE_OWNER;
import static org.springframework.http.HttpStatus.FORBIDDEN;

public class CheckIfUserIsOwnerUtil {
    public static void check(User user, Transaction transaction){
        if(!Objects.equals(user.getId(), transaction.getUser().getId()))
            throw new ResponseStatusException(FORBIDDEN, USER_ARE_NOT_THE_OWNER.getMessage());
    }
}
