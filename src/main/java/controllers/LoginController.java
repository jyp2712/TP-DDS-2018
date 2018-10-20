package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {

	private static final String SESSION_USER = "user";
	private static final String SESSION_ADMIN = "admin";
	
	public static ModelAndView show(Request req, Response res, String path, String usuario, String hbs){
		if(req.session().attribute(usuario) != null) {
			res.redirect("/"+path);
			return null;			
		}else {
			return new ModelAndView(null, hbs);	
		}
	}

	public static ModelAndView showUser(Request req, Response res){
		return show(req, res, "User", SESSION_USER, "home/loginUser.hbs");
	}
	
	public static ModelAndView showAdmin(Request req, Response res){
		return show(req, res, "Admin", SESSION_ADMIN, "home/loginAdmin.hbs");
	}
	
	public static ModelAndView loginUser(Request req, Response res) {
		String user = req.queryParams("user");
		String pass = req.queryParams("password");
		//buscar el usuario en el repo
		req.session().attribute(SESSION_USER, user);
		res.redirect("/");
		return null;
	}

	public static ModelAndView loginAdmin(Request req, Response res) {
		String user = req.queryParams("user");
		String pass = req.queryParams("password");
		//buscar el usuario en el repo
		req.session().attribute(SESSION_ADMIN, user);
		res.redirect("/");
		return null;
	}
	
	public static ModelAndView logout(Request req, Response res, String path, String usuario) {
		req.session().removeAttribute(usuario);
		res.redirect("/"+path);
		return null;
	}
	
	public static ModelAndView logoutUser(Request req, Response res) {
		return logout(req, res, "loginUser", SESSION_USER);
	}
	
	public static ModelAndView logoutAdmin(Request req, Response res) {
		return logout(req, res, "loginAdmin", SESSION_ADMIN);
	}
}
