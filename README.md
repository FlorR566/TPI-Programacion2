# Food Store – Sistema de Gestión de Pedidos

Trabajo Práctico Integrador – Programación 2  
Tecnicatura Universitaria en Programación a Distancia – UTN

---

## Descripción

Aplicación de consola desarrollada en Java 21 que permite gestionar categorías, productos, usuarios y pedidos mediante un menú interactivo.
Toda la información se almacena en memoria usando Colecciones durante la ejecución del programa.

---

## Estudiantes

**Aldana, Lara Agustina** – Legajo XXXXX  
**Miglioranza, Manuel Agustín** – Legajo XXXXX  
**Pavic, Lucelia Ines** – Legajo 35935  
**Rodriguez, Florencia Mabel** – Legajo XXXXX

---

## Video demostrativo

[Enlace al video](#) _( )_

## Informe PDF

[Enlace al informe PDF](#) _( )_

---

## Tecnologías

- Java 21
- Colecciones (`ArrayList`)
- POO: herencia, interfaces, polimorfismo, encapsulamiento
- Manejo de excepciones propias

---

## Estructura del proyecto

```
src/
└── integrado/prog2/
    ├── Main.java
    ├── config/
    ├── entities/
    ├── enums/
    ├── exception/
    ├── service/
    └── ui/
```

---

## Requisitos previos

- JDK 21 instalado
- Variable de entorno `JAVA_HOME` configurada

---

## Cómo ejecutar

**Opción 1 – Desde un IDE (Apache NetBeans / IntelliJ IDEA / Eclipse)**

1. Clonar el repositorio
2. Importar como proyecto Java
3. Ejecutar la clase `Main.java`

**Opción 2 – Desde la terminal**

```bash
# Compilar
javac -d out src/integrado/prog2/**/*.java

# Ejecutar
java -cp out integrado.prog2.Main
```

---

## Funcionalidades

| Módulo     | Operaciones disponibles                                                             |
| ---------- | ----------------------------------------------------------------------------------- |
| Categorías | Listar, Crear, Editar, Eliminar (baja lógica)                                       |
| Productos  | Listar, Crear, Editar, Eliminar (baja lógica)                                       |
| Usuarios   | Listar, Crear, Editar, Eliminar (baja lógica)                                       |
| Pedidos    | Listar, Crear con detalles, Actualizar estado/forma de pago, Eliminar (baja lógica) |
