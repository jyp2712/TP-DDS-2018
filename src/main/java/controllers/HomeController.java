package controllers;

import java.util.HashMap;

import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tp0.modelo.Cliente;
import tp0.modelo.hogar.Hogar;
import tp0.modelo.reportes.ReporteConsumoCliente;
import tp0.modelo.repositorios.RepositoriosReportes;
import tp0.modelo.repositorios.RepositoriosUsuarios;

public class HomeController {
	
	protected static Cliente cliente;

	public static ModelAndView homeUser(Request req, Response res){
		if(req.session().attribute("user") == null) {
			res.redirect("/loginUser");
		}
		Map<String, Object> model = new HashMap<>();

		cliente = RepositoriosUsuarios.findCliente(req.session().attribute("user"));
		
		ReporteConsumoCliente reporte = RepositoriosReportes.findReporteConsumoCliente(cliente.getDocumento());
		
		model.put("reporte", reporte);
		model.put("cliente", cliente);
		return new ModelAndView(model, "home/homeUser.hbs");
	}

	public static ModelAndView consumosUser(Request req, Response res){
		if(req.session().attribute("user") == null) {
			res.redirect("/loginUser");
		}
		Map<String, Object> model = new HashMap<>();
		
		model.put("cliente", cliente);
		model.put("reportes", RepositoriosReportes.repositorioReporteConsumoCliente.todos());

		if (req.queryParams("fecha") != null) {
			ReporteConsumoCliente reporte = RepositoriosReportes.repositorioReporteConsumoCliente
					.encontrar(rep -> rep.getFechaInicio().equals(req.queryParams("fecha")));			
			model.put("reporte", reporte);
			return new ModelAndView(model, "home/consumosUserIndividual.hbs");
		}
		
		return new ModelAndView(model, "home/consumosUser.hbs");
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
		return new ModelAndView(model, "home/optimizadorUser.hbs");
	}

}
