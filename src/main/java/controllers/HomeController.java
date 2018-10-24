package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.ArrayUtils;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tp0.modelo.Cliente;
import tp0.modelo.dispositivo.Dispositivo;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.hogar.Hogar;
import tp0.modelo.reportes.ReporteConsumoCliente;
import tp0.modelo.repositorios.RepositoriosDispositivos;
import tp0.modelo.repositorios.RepositoriosUsuarios;

public class HomeController {
	
	private static final RepositoriosUsuarios repositorioUsuarios = new RepositoriosUsuarios();
	
	public static ModelAndView homeUser(Request req, Response res){
		Map<String, Object> model = new HashMap<>();

		repositorioUsuarios.cargarClientes();
		Cliente cliente = repositorioUsuarios.findCliente(req.session().attribute("user"));
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
;
		
		@SuppressWarnings("unchecked")
		List<ReporteConsumoCliente> reportes = entityManager
					.createQuery("from ReporteConsumoCliente where dni="+cliente.getDocumento()+"ORDER BY id DESC")
					.getResultList();
		
		ReporteConsumoCliente reporte = null;
		
		if(!reportes.isEmpty()) reporte = reportes.get(0);
		
		model.put("cliente", cliente);
		model.put("reporte", reporte);
		return new ModelAndView(model, "home/homeUser.hbs");
	}

	public static ModelAndView consumosUser(Request req, Response res){
		Map<String, Object> model = new HashMap<>();

		List<Dispositivo> dispositivos = RepositoriosDispositivos.instancia.listar(req.params("id"));

		model.put("dispositivos", dispositivos);
		return new ModelAndView(model, "home/consumosUser.hbs");
	}
	
	public static ModelAndView optimizadorUser(Request req, Response res){
		Map<String, Object> model = new HashMap<>();

		List<Dispositivo> dispositivos = RepositoriosDispositivos.instancia.listar(req.params("id"))
				.stream().filter(d -> d.optimizable()).collect(Collectors.toList());
		
		Hogar hogar = new Hogar();
		hogar.optimizar(dispositivos);
		
		model.put("resultados", hogar.getResultados());
		return new ModelAndView(model, "home/optimizadorUser.hbs");
	}


}
