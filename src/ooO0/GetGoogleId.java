package ooO0;

import com.gc.android.market.api.MarketSession;
import com.gc.android.market.api.MarketSession.Callback;
import com.gc.android.market.api.model.Market.AppsRequest;
import com.gc.android.market.api.model.Market.AppsResponse;
import com.gc.android.market.api.model.Market.RequestContext;
import com.gc.android.market.api.model.Market.ResponseContext;

public class GetGoogleId {
	String pacIdString = null;
	String authSubToken = null;
	RequestContext.Builder context = RequestContext.newBuilder();

	public String getPacIdString() {
		return pacIdString;
	}

	public void setPacIdString(String pacIdString) {
		this.pacIdString = pacIdString;
	}

	String GetPacId(String ACCOUT_STRING, String PASSWOR_STRING,
			String DEVICEID_STRING, String s) {

		MarketSession session = new MarketSession(false);
		String login = ACCOUT_STRING;
		String password = PASSWOR_STRING;
		String androidId = DEVICEID_STRING;
		System.out.println("Login...");
		String query = s;
		session.login(login, password, androidId);
		AppsRequest appsRequest = AppsRequest.newBuilder().setQuery(query)
				.setStartIndex(0).setEntriesCount(10)
				.setWithExtendedInfo(false).build();

		Callback<AppsResponse> callback = new Callback<AppsResponse>() {
			public void onResult(ResponseContext context, AppsResponse response) {
				String PacId = response.getApp(0).getId();
				setPacIdString(PacId);
				System.out.println(PacId);

			}
		};
		session.append(appsRequest, callback);
		session.flush();
		System.out.println("_______________");
		return getPacIdString();
	}



}
