package cz.kibo.astrology.service;

import static spark.Spark.get;
import static spark.Spark.internalServerError;
import static spark.Spark.notFound;
import static spark.Spark.post;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

import astro.api.AstroInfo;
import astro.api.ConfigBean;
import spark.Request;
import spark.Response;
import spark.Route;

public class API {

	public static final String API_CONTEXT = "/api/v1";

	public API() {
		setupEndpoints();
	}

	public void setupEndpoints() {

		// For test
		post(API_CONTEXT + "/status", (req, res) -> {
			res.type("application/json");
			return "{\"status\":\"running\"}";
		});

		get("/status", (req, res) -> {
			res.type("application/json");
			return "{\"status\":\"running\"}";
		});

		get(API_CONTEXT + "/calc", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {
				SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
				double longitude = 0;
				double latitude = 0;

				String lngstr = request.queryParams("longitude");
				if (lngstr != null) {
					longitude = Double.parseDouble(lngstr);
				}
				String latStr = request.queryParams("latitude");

				if (latStr != null) {
					latitude = Double.parseDouble(latStr);
				}

				String address = request.queryParams("address");
				if (address != null) {
					longitude = ConfigBean.getLongitude(address);
					latitude = ConfigBean.getLatitude(address);
				}
				Date birthday = null;
				try {
					String queryParams = request.queryParams("birthday") + ":00";
					birthday = f.parse(queryParams);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				response.status(200);
				response.type("application/json");
				// response.header("Content-Encoding", "UTF-8");

			
				String json = new Gson().toJson(new AstroInfo().parserAstroData(longitude, latitude, birthday));
				return new String(json.getBytes("UTF-8"));
			}
		});


		// code 404
		notFound((req, res) -> {
			res.status(404);
			res.type("application/json");
			return "{\"message\":\"404 - Not Found\"}";
		});

		// code 500
		internalServerError((req, res) -> {
			res.status(500);
			res.type("application/json");
			return "{\"message\":\"500 - Server Error\"}";
		});
	}
}
