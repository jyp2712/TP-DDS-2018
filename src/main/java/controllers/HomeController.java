package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tp0.modelo.Cliente;
import tp0.modelo.dispositivo.Dispositivo;
import tp0.modelo.hogar.Hogar;
import tp0.modelo.reportes.ReporteConsumoCliente;
import tp0.modelo.repositorios.RepositoriosReportes;
import tp0.modelo.repositorios.RepositoriosUsuarios;

public class HomeController {
	
	protected static Cliente cliente;

	public static ModelAndView homeUser(Request req, Response res){
		Map<String, Object> model = new HashMap<>();

		cliente = RepositoriosUsuarios.findCliente(req.session().attribute("user"));
		
		ReporteConsumoCliente reporte = RepositoriosReportes.findReporteConsumoCliente(cliente.getDocumento());
		
		model.put("reporte", reporte);
		model.put("cliente", cliente);
		return new ModelAndView(model, "home/homeUser.hbs");
	}

	public static ModelAndView consumosUser(Request req, Response res){
		Map<String, Object> model = new HashMap<>();
		
		List<ReporteConsumoCliente> reportes = RepositoriosReportes.repositorioReporteConsumoCliente.todos();
		
		model.put("cliente", cliente);
		model.put("reportes", reportes);

		if (req.queryParams("fecha") != null) {
			ReporteConsumoCliente reporte = reportes.stream().filter(rep -> rep.getFechaInicio().equals(req.queryParams("fecha"))).findFirst().get();
			model.put("reporte", reporte);
			return new ModelAndView(model, "home/consumosUserIndividual.hbs");
		}
		
		return new ModelAndView(model, "home/consumosUser.hbs");
	}
	
	public static ModelAndView optimizadorUser(Request req, Response res){
		Map<String, Object> model = new HashMap<>();

		List<Dispositivo> dispositivos = cliente.getDispositivos()
				.stream().filter(d -> d.optimizable()).collect(Collectors.toList());
		
		Hogar hogar = new Hogar();
		hogar.optimizar(dispositivos);
		
		model.put("resultados", hogar.getResultados());
		return new ModelAndView(model, "home/optimizadorUser.hbs");
	}


}
