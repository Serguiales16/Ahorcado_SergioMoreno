# 🪓 Ahorcado – App Android

**Aplicación Android** del clásico juego del ahorcado, desarrollada en **Kotlin** con Android Studio. El proyecto se centra en el aprendizaje de la **lógica de juego**, el **control de estado** y la gestión de eventos en el desarrollo Android nativo.



---

## 🎯 Características Principales

* **Juego Completo:** Implementación del juego del ahorcado.
* **Vocabulario:** Palabras de **5 letras** en español.
* **Sistema Visual:** **Imágenes progresivas** del ahorcado que avanzan con cada error.
* **Estado:** Detección automática de **victoria** (🎉) o **derrota** (💀).
* **Control:** Opción de **reinicio manual** del juego.
* **Interfaz:** **Sencilla** y **clara**, centrada en la jugabilidad.
* **Lógica:** Implementada totalmente en Kotlin, **sin librerías externas**.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** **Kotlin**.
* **IDE:** **Android Studio**.
* **UI:** **XML** para el diseño de la interfaz.
* **Enlace de Vistas:** **View Binding** para acceso seguro a los elementos.
* **Compatibilidad:** Android **SDK** (nativo).
* **Arquitectura:** **Activity única** (`MainActivity`) para toda la lógica.

---

## 🧩 Funcionamiento del Juego

1.  **Inicio:** Al iniciar o reiniciar, se elige una **palabra aleatoria** de un *array* predefinido.
2.  **Ocultación:** Las letras de la palabra objetivo se muestran como guiones bajos o puntos.
3.  **Input:** El usuario introduce una única letra en el campo de texto y pulsa el botón de verificación.
4.  **Verificación:**
    * ✅ **Correcta:** La letra se **revela** en la palabra oculta.
    * ❌ **Incorrecta:** Se **incrementa el contador** de errores y la imagen del ahorcado avanza.
5.  **Fin del Juego:**
    * **Victoria:** Se revelan todas las letras de la palabra.
    * **Derrota:** Se alcanza el número máximo de errores.

---

## 🧠 Lógica Destacada y Estructura del Código

El juego se gestiona completamente dentro de la clase `MainActivity`, utilizando contadores y manejo de eventos.

### Estructura de Clases y Funciones

| Componente | Rol | Funcionalidad Clave |
| :--- | :--- | :--- |
| **`MainActivity`** | **Controlador** | Centraliza la **lógica**, maneja **eventos** de botones y **actualiza** la interfaz (imágenes/texto). |
| **`asignacion()`** | **Inicialización** | **Reinicia el estado** del juego (contadores a cero) y **selecciona una nueva palabra** aleatoria. |
| **`verificar()`** | **Comprobación** | Procesa la letra introducida, gestionando si es **correcta** o **incorrecta** (error/progreso visual). |
| **`ViewBinding`** | **Acceso a Vistas** | Garantiza un **acceso seguro y eficiente** a todos los elementos del diseño XML. |

### Control de Estado

* **Arrays:** Se utilizan para almacenar la **lista de palabras** y las **referencias a las imágenes** de los diferentes estados del ahorcado.
* **Eventos:** El flujo se controla mediante **`setOnClickListener`**.
* **Temporización:** Uso de **`Handler`** para introducir un pequeño **retraso** tras la victoria o derrota antes de un posible reinicio.
