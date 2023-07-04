package ContextoProblema;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilitarios {
    public static boolean solicitarBooleano(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        boolean valorBooleano;

        do {
            System.out.print(mensaje);
            try {
                valorBooleano = scanner.nextBoolean();
                scanner.nextLine(); // Consumir el salto de línea pendiente después de nextBoolean()
                break; // Salir del bucle si se ingresó un valor booleano válido
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar 'true' o 'false'. Intente nuevamente.");
                scanner.nextLine(); // Consumir la entrada no válida
            }
        } while (true);

        return valorBooleano;
    }
    public static int leerEntero() {          //usar:  int numero.Utilitarios = leerEntero()
        Scanner scanner = new Scanner(System.in);

        try {

            int numero = scanner.nextInt();
            return numero;
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero.");
            return leerEntero();
        }
    }
}
