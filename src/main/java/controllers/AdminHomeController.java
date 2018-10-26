package controllers;

import java.io.Console;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tp0.modelo.Administrador;
import tp0.modelo.Cliente;
import tp0.modelo.dispositivo.Dispositivo;
import tp0.modelo.dispositivo.DispositivoConcreto;
import tp0.modelo.reportes.ReporteConsumoCliente;
import tp0.modelo.repositorios.RepositorioDispositivosConcretos;
import tp0.modelo.repositorios.RepositorioReporteClientes;
import tp0.modelo.repositorios.RepositoriosDispositivos;
import tp0.modelo.repositorios.RepositoriosUsuarios;

public class AdminHomeController implements WithGlobalEntityManager, TransactionalOps {
	public static AdminHomeController instancia = new AdminHomeController();
	private Administrador admin;

	private void ValidateAdmin(Request req, Response res) {
//		admin = RepositoriosUsuarios.findAdmin(req.session().attribute("user"));
//		if (admin == null) {
//			res.redirect("/loginAdmin");
//		}
	}

	public ModelAndView home(Request req, Response res) {
		ValidateAdmin(req, res);
		Map<String, Object> model = new HashMap<>();
		List<Cliente> clientes = RepositoriosUsuarios.listarClientes();
		admin = new Administrador(1, "asd", "asd", "asd", "13082018");
		model.put("clientes", clientes);
		model.put("admin", admin);
		return new ModelAndView(model, "admin/adminHome.hbs");
	}

	public ModelAndView reporte(Request req, Response res) {
		ValidateAdmin(req, res);
		List<ReporteConsumoCliente> reportes = RepositorioReporteClientes.instancia.listar();
		Map<String, List<ReporteConsumoCliente>> model = new HashMap<>();
		model.put("reportes", reportes);
		return new ModelAndView(model, "admin/reporte.hbs");
	}
	
	public ModelAndView altaDispositivo(Request req, Response res) {
		ValidateAdmin(req, res);
		Map<String, Administrador> model = new HashMap<	>();
		model.put("admin", admin);
		return new ModelAndView(model, "admin/altaDispositivo.hbs");
	}
	
	public ModelAndView grabarDispositivo(Request req, Response res) {
		DispositivoConcreto disp = new DispositivoConcreto(
				req.queryParams("nombre"), 
				Double.parseDouble(req.queryParams("coeficiente")),
				Double.parseDouble(req.queryParams("usoMinimo")),
				Double.parseDouble(req.queryParams("usoMaximo")),
				Boolean.parseBoolean(req.queryParams("optimizable")));
		withTransaction(() ->{
			RepositorioDispositivosConcretos.instancia.agregar(disp);
		});
		return new ModelAndView(null, "admin/altaDispositivoExito.hbs");
	}
}
