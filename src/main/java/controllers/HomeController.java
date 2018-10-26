package controllers;

import java.util.HashMap;

import java.util.Map;
import java.util.stream.Collectors;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tp0.modelo.Administrador;
import tp0.modelo.Cliente;
import tp0.modelo.dispositivo.DispositivoConcreto;
import tp0.modelo.hogar.Hogar;
import tp0.modelo.reportes.ReporteConsumoCliente;
import tp0.modelo.repositorios.RepositoriosDispositivosGenericos;
import tp0.modelo.repositorios.RepositoriosReportes;
import tp0.modelo.repositorios.RepositoriosUsuarios;

public class HomeController {
	
	protected static Cliente cliente;
	protected static Administrador admin;

	public static ModelAndView homeUser(Request req, Response res){
		if(req.session().attribute("user") == null) {
			res.redirect("/loginUser");
		}
		Map<String, Object> model = new HashMap<>();

		cliente = RepositoriosUsuarios.findCliente(req.session().attribute("user"));
		
		model.put("reporte", RepositoriosReportes.findReporteConsumoCliente(cliente.getDocumento()));
		model.put("cliente", cliente);
		return new ModelAndView(model, "user/homeUser.hbs");
	}

	public static ModelAndView homeAdmin(Request req, Response res){
		if(req.session().attribute("admin") == null) {
			res.redirect("/loginAdmin");
		}

		Map<String, Object> model = new HashMap<>();

		RepositoriosUsuarios.cargarClientes();

		admin = RepositoriosUsuarios.findAdmin(req.session().attribute("admin"));
		
		model.put("clientes", RepositoriosUsuarios.repositorioClientes.todos());
		model.put("administrador", admin);
		return new ModelAndView(model, "admin/homeAdmin.hbs");
	}

	public static ModelAndView reportesAdmin(Request req, Response res){
		if(req.session().attribute("admin") == null) {
			res.redirect("/loginAdmin");
		}
		
		Map<String, Object> model = new HashMap<>();

		RepositoriosReportes.cargarReportes();

		model.put("administrador", admin);
		model.put("reportes", RepositoriosReportes.repositorioReporteConsumoCliente.todos());
		
		return new ModelAndView(model, "admin/consumosUser.hbs");
	}
	
	public static ModelAndView consumosUser(Request req, Response res){
		if(req.session().attribute("user") == null) {
			res.redirect("/loginUser");
		}
		Map<String, Object> model = new HashMap<>();
		
		model.put("cliente", cliente);
		model.put("reportes", 
				RepositoriosReportes.repositorioReporteConsumoCliente.todos()
				.stream().filter(r -> r.getDNI().equals(cliente.getDocumento())).collect(Collectors.toList()));

		if (req.queryParams("fecha") != null) {
			ReporteConsumoCliente reporte = RepositoriosReportes.repositorioReporteConsumoCliente
					.encontrar(rep -> rep.getFechaInicio().equals(req.queryParams("fecha")));			
			model.put("reporte", reporte);
			return new ModelAndView(model, "user/consumosUserIndividual.hbs");
		}
		
		return new ModelAndView(model, "user/consumosUserTodos.hbs");
	}
	
	public static ModelAndView optimizadorUser(Request req, Response res){
		if(req.session().attribute("user") == null) {
			res.redirect("/loginUser");
		}
		
		Map<String, Object> model = new HashMap<>();

		Hogar hogar = new Hogar();
		hogar.optimizar(cliente.getDispositivos());
		
		model.put("cliente", cliente);
		model.put("resultados", hogar.getResultados());
		return new ModelAndView(model, "user/optimizadorUser.hbs");
	}

	public static ModelAndView listarDispositivosAdmin(Request req, Response res){
		if(req.session().attribute("admin") == null) {
			res.redirect("/loginAdmin");
		}
		
		Map<String, Object> model = new HashMap<>();

		RepositoriosDispositivosGenericos.cargarDispositivos();
		
		model.put("administrador", admin);
		model.put("dispositivos", RepositoriosDispositivosGenericos.repositorioDispositivos.todos());
		return new ModelAndView(model, "admin/newDispositivo.hbs");
	}

	public static Void crearDispositivoAdmin(Request req, Response res){
		if(req.session().attribute("admin") == null) {
			res.redirect("/loginAdmin");
		}
		
		DispositivoConcreto dispositivoNuevo = new DispositivoConcreto(
				req.queryParams("nombreGenerico"), 
				Double.parseDouble(req.queryParams("coeficiente")), 
				Double.parseDouble(req.queryParams("usoMinimo")), 
				Double.parseDouble(req.queryParams("usoMaximo")), 
				Boolean.parseBoolean(req.queryParams("optimizable")));
		
		if(RepositoriosDispositivosGenericos.findDispositivo(dispositivoNuevo.getNombreGenerico()) == null){
			RepositoriosDispositivosGenericos.agregar(dispositivoNuevo);
		}
		
		res.redirect(req.url());
		return null;
	}

}
