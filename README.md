# Food Store – Sistema de Gestión de Pedidos

Trabajo Práctico Integrador – Programación 2  
Tecnicatura Universitaria en Programación a Distancia – UTN

---

## Descripción

Aplicación de consola desarrollada en Java 21 que permite gestionar categorías, productos, usuarios y pedidos mediante un menú interactivo.
Toda la información se almacena en memoria usando Colecciones durante la ejecución del programa.

---

## Estudiantes

Grupo 22

- Aldana, Lara Agustina - Comisión 6
- Miglioranza, Manuel Agustín - Comisión 8
- Pavic, Lucelia Ines - Comisión 2
- Rodriguez, Florencia Mabel - Comisión 1

---

## Video demostrativo

[Ver video en YouTube](https://www.youtube.com/watch?v=evJ12b_Eqz4 "Haga clic para ver el video")

---

## Tecnologías

- Java 21
- NetBeans
- Git
- GitHub

---

## Estructura del proyecto

```
src/
└── foodstore/
    ├── Main.java
    ├── entities/
    ├── enums/
    ├── interfaces/
    └── utils/
```

---

## Requisitos previos

- JDK 21 instalado
- Variable de entorno `JAVA_HOME` configurada
- NetBeans instalado (opcional) u otro IDE compatible con Java 21.

---

## Cómo ejecutar

**Opción 1 – Desde un IDE (Apache NetBeans / IntelliJ IDEA / Eclipse)**

1. Clonar el repositorio
2. Importar como proyecto Java (Ant)
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
