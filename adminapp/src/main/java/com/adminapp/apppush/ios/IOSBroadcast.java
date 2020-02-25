package com.adminapp.apppush.ios;


import com.adminapp.apppush.IOSNotification;

public class IOSBroadcast extends IOSNotification {
	public IOSBroadcast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "broadcast");

	}
}
