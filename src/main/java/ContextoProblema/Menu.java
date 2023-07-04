package ContextoProblema;
import java.util.Scanner;
public class Menu {
static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args){
		lanzador();
	}

	private static void lanzador() {
		Admin admin = new Admin();
		opcionesAdmin(admin);
	}

	private static void opcionesAdmin(Admin admin) {
		ElegirOpcionesAdmin();
		int opcionElejida = 0;
		int opcionseguir = 0;
		opcionElejida = Utilitarios.leerEntero();
	switch (opcionElejida) {
		case 1:
			admin.opcionesPedido();
			System.out.println("desea otra opcion o desea salir (1 seguir, cualquiero otra cosa para salir)");
			opcionseguir = Utilitarios.leerEntero();
			if (opcionseguir==1){
				opcionesAdmin(admin);
			}
			break;
		case 2: admin.opcionesCamiones();
			System.out.println("desea otra opcion o desea salir (1 seguir, cualquiero otra cosa para salir)");
			opcionseguir = Utilitarios.leerEntero();
			if (opcionseguir==1){
				opcionesAdmin(admin);
			}
			break;
		case 3: admin.opcionesChoferes();System.out.println("desea otra opcion o desea salir (1 seguir, cualquiero otra cosa para salir)");
			opcionseguir = Utilitarios.leerEntero();
			if (opcionseguir==1){
				opcionesAdmin(admin);
			}
			break;
		default:
			System.out.println("esa opcion no existe, elija otra vez");
			opcionesAdmin(admin);
		}
	}

	private static void ElegirOpcionesAdmin(){
		System.out.println("Elija la opcion que desee digitando el numero de la opcion");
		System.out.println("(1) Pedidos");
		System.out.println("(2) Camiones");
		System.out.println("(3) Choferes");
	}


}