package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tp0.modelo.dispositivo.Dispositivo;
import tp0.modelo.repositorios.RepositoriosDispositivos;

public class HomeController {
	
	public static ModelAndView homeUser(Request req, Response res){
		Map<String, List<Dispositivo>> model = new HashMap<>();
		List<Dispositivo> dispositivos = RepositoriosDispositivos.instancia.listar(req.params("id"));

		model.put("dispositivos", dispositivos);
		return new ModelAndView(model, "home/homeUser.hbs");
	}
	
}
