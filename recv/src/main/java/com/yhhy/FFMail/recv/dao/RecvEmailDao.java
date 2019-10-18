package com.yhhy.FFMail.recv.dao;

import com.yhhy.FFMail.recv.domain.RecvEmail;

public interface RecvEmailDao {
    public void saveRecvEmail(RecvEmail recvemail);

    public boolean uidIsExists(String emailAddress, String uid);
}
