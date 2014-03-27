package controllers;

import play.*;
import play.mvc.*;
import play.libs.WS;
import play.mvc.Result;

import static play.libs.F.Function;
import static play.libs.F.Promise;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render("Your place for a new home"));
    }
	
	public static Promise<Result> getHomes(String postcode) {
		String feedUrl = "http://slickindustries.co.uk/homes/" + postcode;
		final Promise<Result> resultPromise = WS.url(feedUrl).get().map(
				new Function<WS.Response, Result>() {
					public Result apply(WS.Response response) {
						return ok(response.asJson());
					}
				}
		);
		return resultPromise;
	}
}
