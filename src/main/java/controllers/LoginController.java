package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tp0.modelo.Administrador;
import tp0.modelo.Cliente;
import tp0.modelo.repositorios.RepositoriosReportes;
import tp0.modelo.repositorios.RepositoriosUsuarios;

public class LoginController {

	private static final String SESSION_USER = "user";
	private static final String SESSION_ADMIN = "admin";
	
	public static ModelAndView show(Request req, Response res, String usuario, String hbs){
		return new ModelAndView(null, hbs);	
	}

	public static ModelAndView showUser(Request req, Response res){
		if(req.session().attribute(SESSION_USER) != null) {
			Cliente cliente = RepositoriosUsuarios.findCliente(req.session().attribute("user"));
			res.redirect("/user/"+cliente.getId());
		}
		
		RepositoriosUsuarios.cargarClientes();
		RepositoriosReportes.cargarReportes();

		return show(req, res, "user", "login/loginUser.hbs");	
	}
	
	public static ModelAndView showAdmin(Request req, Response res){
		if(req.session().attribute(SESSION_ADMIN) != null) {
			Administrador admin = RepositoriosUsuarios.findAdmin(req.session().attribute("admin"));
			res.redirect("/admin/"+admin.getId());
		}
		
		RepositoriosUsuarios.cargarAdministradores();
		
		return show(req, res, SESSION_ADMIN, "login/loginAdmin.hbs");
	}
	
	public static ModelAndView loginUser(Request req, Response res) {
		String user = req.queryParams("user");
		String pass = req.queryParams("password");
		Cliente cliente = RepositoriosUsuarios.findCliente(user);

		if(cliente == null) {
			return show(req, res, SESSION_USER, "login/loginUserErrorUser.hbs");	
		}
		if(!cliente.getPass().equals(pass)) {
			return show(req, res, SESSION_USER, "login/loginUserErrorPass.hbs");
		}
		
		req.session().attribute(SESSION_USER, user);
		res.redirect("/user/"+cliente.getId());
		return null;
	}

	public static ModelAndView loginAdmin(Request req, Response res) {
		String user = req.queryParams("user");
		String pass = req.queryParams("password");

		Administrador admin = RepositoriosUsuarios.findAdmin(user);

		if(admin == null) {
			return show(req, res, SESSION_ADMIN, "login/loginAdminErrorUser.hbs");	
		}
		if(!admin.getPass().equals(pass)) {
			return show(req, res, SESSION_ADMIN, "login/loginAdminErrorPass.hbs");
		}

		req.session().attribute(SESSION_ADMIN, user);
		res.redirect("/admin/"+admin.getId());
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
