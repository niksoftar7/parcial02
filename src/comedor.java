import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Comida {
    String nombre;
    String tipo;
    int disponibilidad;
    double precio;

    public Comida(String nombre, String tipo, int disponibilidad, double precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
        this.precio = precio;
    }

    public boolean servir() {
        if (disponibilidad > 0) {
            disponibilidad--;
            return true;
        }
        return false;
    }
}

class Estudiante {
    String nombre;
    String id;
    Comida comidaSeleccionada;

    public Estudiante(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }
}

class GestionComedor {
    List<Comida> comidas = new ArrayList<>();
    List<Estudiante> estudiantes = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void cargarMenus() {
        comidas.add(new Comida("Vegetariano", "Vegetariano", 50, 2400));
        comidas.add(new Comida("Vegano", "Vegano", 30, 2400));
        comidas.add(new Comida("Plato del Día", "Estándar", 100, 2400));
    }

    public void mostrarMenus() {
        System.out.println("Comidas disponibles:");
        for (int i = 0; i < comidas.size(); i++) {
            Comida comida = comidas.get(i);
            System.out.printf("%d. %s (%s) - Disponibles: %d\n", 
                i+1, comida.nombre, comida.tipo, comida.disponibilidad);
        }
    }

    public Comida seleccionarComida() {
        mostrarMenus();
        while (true) {
            System.out.print("Seleccione una comida (número): ");
            try {
                int opcion = scanner.nextInt();
                if (opcion > 0 && opcion <= comidas.size()) {
                    Comida comidaSeleccionada = comidas.get(opcion - 1);
                    if (comidaSeleccionada.disponibilidad > 0) {
                        return comidaSeleccionada;
                    } else {
                        System.out.println("Lo siento, esa comida no está disponible.");
                    }
                } else {
                    System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Introduzca un número.");
                scanner.nextLine(); // Limpiar buffer
            }
        }
    }

    public void realizarPedido() {
        System.out.print("Ingrese su nombre: ");
        scanner.nextLine(); // Limpiar buffer
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese su ID de estudiante: ");
        String id = scanner.nextLine();

        Estudiante estudiante = new Estudiante(nombre, id);
        
        Comida comidaElegida = seleccionarComida();
        estudiante.comidaSeleccionada = comidaElegida;
        
        if (comidaElegida.servir()) {
            System.out.println("Pedido realizado exitosamente:");
            System.out.printf("Estudiante: %s\nComida: %s\n", 
                estudiante.nombre, comidaElegida.nombre);
        } else {
            System.out.println("No se pudo procesar el pedido.");
        }
    }

    public static void main(String[] args) {
        GestionComedor comedor = new GestionComedor();
        comedor.cargarMenus();
        
        while (true) {
            comedor.realizarPedido();
            
            System.out.print("¿Desea realizar otro pedido? (s/n): ");
            String continuar = comedor.scanner.next();
            if (!continuar.toLowerCase().equals("s")) {
                break;
            }
        }
    }
}