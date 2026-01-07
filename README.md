# LiterAlura
![Fecha](https://img.shields.io/badge/Release%20date-January%202026-yellow)
![Status](https://img.shields.io/badge/Status-completado-green)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-orange)


## 📖 Descripción del Proyecto
**Literalura** es un catálogo de libros interactivo diseñado para los amantes de la lectura. La aplicación consume datos en tiempo real de la API [Gutendex](https://gutendex.com/), procesa la información y permite al usuario gestionar su propia biblioteca digital local. 

El sistema cuenta con una arquitectura robusta que garantiza la integridad de los datos, evitando duplicados tanto en libros como en autores a través de una base de datos relacional.

---

## 🚀 Funcionalidades
El sistema ofrece un menú interactivo por consola con las siguientes capacidades:

1.  **Buscar libro por título:** Consulta la API externa, mapea los datos y los guarda en la base de datos (incluyendo la vinculación automática con su autor).
2.  **Ver libros registrados:** Muestra un listado detallado de todas las obras almacenadas localmente.
3.  **Ver autores registrados:** Lista los autores almacenados, mostrando sus fechas de nacimiento/fallecimiento y las obras que tienen vinculadas.
4.  **Filtrar autores vivos en un determinado año:** Búsqueda avanzada mediante **JPQL** para identificar autores cuya cronología coincida con el año solicitado.
5.  **Filtrar libros por idioma:** Permite listar obras filtrando por códigos internacionales (es, en, fr, pt).

---
## 🛠️ Tecnologías Utilizadas
- Java 25

- Spring Boot 4.0.1

- Spring Data JPA (Persistencia y relaciones @ManyToOne / @OneToMany)

- PostgreSQL (Base de datos relacional)

- Jackson (Manipulación de JSON/Records)

- Maven (Gestión de dependencias)

---
## 🛠️ Instalación y Configuración

### 1. Requisitos previos
* **Java JDK 17** o superior.
* **Maven** (Gestor de dependencias).
* **PostgreSQL** (Servidor de base de datos activo).

### 2. Clonar el repositorio
```bash
git clone [https://github.com/tu-usuario/literalura.git](https://github.com/tu-usuario/literalura.git)
cd literalura
```
### 3. Configurar la Base de Datos
Crea una base de datos en tu PostgreSQL con el nombre que prefieras. Luego, localiza el archivo `src/main/resources/application.properties` y configura tus credenciales:
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_de_tu_bd
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

# Configuración de Hibernate para creación automática de tablas
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 4. Ejecución
Puedes ejecutar el proyecto desde tu IDE favorito (como IntelliJ o Eclipse) o mediante la terminal usando Maven:
```bash
mvn spring-boot:run
```
## 💻Cómo utilizar la aplicación desde la consola
Una vez iniciada la aplicación, interactuarás con ella a través de la terminal:

- Navegación del Menú: El sistema presentará una lista de opciones numeradas del 0 al 5. Escribe el número de la acción que deseas realizar y presiona Enter.

- Búsqueda de Libros: Al seleccionar la opción 1, se te pedirá el nombre del libro. El sistema buscará coincidencias en la API de Gutendex. Si el libro ya existe en tu base de datos local, se te notificará para evitar registros duplicados.

- Listado de Libros: Muestra todos los libros que has guardado previamente en tu base de datos. Verás el título, autor, idioma y número de descargas con un formato limpio y organizado.

- Listado de Autores: Despliega la información de todos los autores registrados. Incluye su nombre, año de nacimiento, año de fallecimiento y una lista de todos los libros de ese autor que tienes en tu catálogo.

- Filtro de Autores por Año: En la opción 4, ingresa un año de cuatro dígitos (ej: 1850). El sistema filtrará a los autores que estaban vivos en ese periodo según los registros en tu base de datos.

- Filtro de Idiomas: Para la opción 5, deberás ingresar el código de dos letras correspondiente al idioma. La aplicación acepta:

  - es - Español

  - en - Inglés

  - pt - Portugués

- Salir: En la opción 0, la aplicación se cerrará

- Tratamiento de Errores: La aplicación está diseñada para ser robusta. Si ingresas letras en lugar de números o valores fuera de rango, el sistema capturará la excepción y te permitirá intentar de nuevo sin cerrarse

  ### Demostración de la Aplicación ✓
  Uso de la opción 1(búsqueda de libro y su registro en la base de datos)
  
  ![Demostracin-LiterAlura-ezgif com-crop](https://github.com/user-attachments/assets/1fb52ed5-a657-4be4-acd0-6780a5b5778a)

## ✨Posibles Mejoras
Para fortalecer este proyecto en el futuro, se contemplan las siguientes actualizaciones:

- 📊 Estadísticas de Descargas: Implementar una función para mostrar el Top 10 de los libros más descargados en la base de datos local.

- 🔎 Búsqueda por Autor: Agregar una opción de búsqueda para filtrar libros de un autor específico directamente por su nombre.

- 📑 Generación de Reportes: Permitir al usuario exportar los datos de libros y autores a un archivo externo (formato .txt o .csv).

- 🌍 Internacionalización: Ampliar la validación de idiomas para admitir más códigos internacionales y mejorar la precisión de los resultados.

### 📝 Contribuciones
Este proyecto fue desarrollado como parte de un desafío de programación. Si encuentras un error o tienes sugerencias de mejora, ¡no dudes en abrir un issue!

---

## ✒️ Autor
**JOSUÉ BADILLA MADRIGAL**
