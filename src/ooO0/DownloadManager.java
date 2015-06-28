package ooO0;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.gc.android.market.api.MarketSession;
import com.gc.android.market.api.model.Market.GetAssetResponse.InstallAsset;

public class DownloadManager implements Runnable {
	String lg = null;
	String pw = null;
	String android = null;
	String asset = null;

	public DownloadManager(String login, String password, String androidId,
			String assetId) throws IOException {
		lg = login;
		pw = password;
		android = androidId;
		asset = assetId;

	}

	// public void downloading() throws IOException {

	@Override
	public void run() {
		try {

			MarketSession session = new MarketSession(false);
			session.login(lg, pw, android);
			System.out.println("Login success");
			System.out.println(session.queryGetAssetRequest(asset).getInstallAsset(0));
			System.exit(0);
			InstallAsset ia = session.queryGetAssetRequest(asset).getInstallAsset(0);
			String cookieName = ia.getDownloadAuthCookieName();
			String cookieValue = ia.getDownloadAuthCookieValue();
			URL url = new URL(ia.getBlobUrl());
			System.out.print(ia.getBlobUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent",
					"Android-Market/2 (sapphire PLAT-RC33); gzip");
			conn.setRequestProperty("Cookie", cookieName + "=" + cookieValue);
			String[] assetStrings = asset.split(":");

			java.io.InputStream inputstream = conn.getInputStream();
			String fileToSave = assetStrings[1] + ".apk";
			System.out.println("Downloading " + fileToSave);
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(fileToSave));
			byte buf[] = new byte[1024];
			int k = 0;
			for (long l = 0L; (k = inputstream.read(buf)) != -1; l += k)
				stream.write(buf, 0, k);
			inputstream.close();
			stream.close();
			System.out.println("download " + fileToSave + " success");

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("download " + asset + " failed");
			File failName = new File("failed.txt");
			try {
				RandomAccessFile rAccessFile = new RandomAccessFile(failName,
						"rw");
				long fileLength = rAccessFile.length();
				// 将写文件指针移到文件尾。
				rAccessFile.seek(fileLength);
				rAccessFile.writeBytes(asset + "\r\n");
				rAccessFile.close();

			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				System.out.println(e1);
			}

			// TODO: handle exception
		}

	}

	// TODO 自动生成的方法存根

}
