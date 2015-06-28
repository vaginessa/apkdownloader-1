package ooO0;

import java.io.IOException;

public class MIAN {
	public static final String ACCOUT_STRING = "@gmail.com";
	public static final String PASSWOR_STRING = "";
	public static final String DEVICEID_STRING = "11";


	public static void main(String[] args) throws IOException {
		AcountRead acountRead = new AcountRead();
		StringBuilder sBuilder = acountRead.readpack();
		String strings = new String(sBuilder);
		String[] ssStrings = strings.split(" ");
		String[] ssidStrings = new String[ssStrings.length];
		GetGoogleId ggiId = new GetGoogleId();
		for (int k = 0; k < ssStrings.length; k++) {
			ssidStrings[k] = ggiId.GetPacId(ACCOUT_STRING, PASSWOR_STRING,
					DEVICEID_STRING, ssStrings[k]);
		}

		DownloadManager[] dl = new DownloadManager[ssStrings.length];
		for (int i = 0; i < ssStrings.length; i++) {
			dl[i] = new DownloadManager(ACCOUT_STRING, PASSWOR_STRING,
					DEVICEID_STRING, ssidStrings[i]);
		}
		Thread thread[] = new Thread[ssStrings.length];
		for (int j = 0; j < ssStrings.length; j++) {
			thread[j] = new Thread(dl[j]);
			thread[j].start();
		}

	}
}
