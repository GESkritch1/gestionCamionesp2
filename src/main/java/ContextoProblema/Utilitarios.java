package ContextoProblema;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilitarios {
    public static int leerEntero() {          //usar:  int numero.Utilitarios = leerEntero()
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingresa un número entero: ");
            int numero = scanner.nextInt();
            return numero;
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero.");
            return leerEntero();
        }
    }
}
