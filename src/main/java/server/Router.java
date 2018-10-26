package server;

import controllers.HomeController;
import controllers.LoginController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();

		Spark.staticFiles.location("/public");
		
		Spark.get("/loginUser", LoginController::showUser, engine);
		Spark.post("/loginUser", LoginController::loginUser, engine);
		Spark.get("/logoutUser", LoginController::logoutUser, engine);
		
		Spark.get("/loginAdmin", LoginController::showAdmin, engine);
		Spark.post("/loginAdmin", LoginController::loginAdmin, engine);
		Spark.get("/logoutAdmin", LoginController::logoutAdmin, engine);
		
		Spark.get("/admin/:id", HomeController::homeAdmin, engine);		
		Spark.get("/admin/:id/reportes", HomeController::reportesAdmin, engine);		
		Spark.get("/admin/:id/dispositivos", HomeController::listarDispositivosAdmin, engine);
		Spark.post("/admin/:id/dispositivos", HomeController::crearDispositivoAdmin);
		
		Spark.get("/user/:id", HomeController::homeUser, engine);
		Spark.get("/user/:id/consumos", HomeController::consumosUser, engine);
		Spark.get("/user/:id/optimizador", HomeController::optimizadorUser, engine);
	}

}