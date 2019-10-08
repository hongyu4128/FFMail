package com.yhhy.FFMail.setting.dao.user;

import com.yhhy.FFMail.setting.domain.user.RecvEmailInfo;

public interface RecvEmailInfoDao {
	public void saveRecvEmailInfo(RecvEmailInfo recvemail);

	boolean uidIsExists(String emailAddress, long uid);
}
