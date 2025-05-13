# AnimalsApp

Aplicación Android nativa desarrollada en Kotlin y Jetpack Compose que consume una API REST para mostrar información de animales y sus ambientes de forma dinámica.

---

## 📌 Características principales

* **Listado de Animales:** Pantalla principal con imágenes circulares y nombres.
* **Detalle de Animal:** Descripción, hechos interesantes y galería de imágenes.
* **Listado de Ambientes:** Muestra ambientes y permite navegar a su detalle.
* **Detalle de Ambiente:** Información del ambiente y lista de animales filtrados.
* **Navegación:** Jetpack Navigation Compose para rutas limpias y consistentes.
* **Arquitectura MVVM:** ViewModel + StateFlow para estados de carga, éxito y error.
* **Networking:** Retrofit + OkHttp (con interceptor de logging) para consumir API.
* **Carga de Imágenes:** Coil Compose, optimizado para carga y cache.
* **UI & Theming:** Material 3 con esquema de colores personalizado y tema dark/light.
* **Pruebas:** JUnit y AndroidX Test (Espresso & Compose Test).

---

## 🗂️ Estructura del proyecto

```
jalcon00-animalsapp/
├─ build.gradle.kts            # Configuración general de Gradle
├─ settings.gradle.kts         # Nombre y módulos del proyecto
├─ app/
│  ├─ build.gradle.kts         # Configuración específica del módulo app
│  ├─ proguard-rules.pro       # Reglas de ofuscación
│  └─ src/
│     ├─ main/
│     │  ├─ AndroidManifest.xml
│     │  ├─ java/com/example/animalsapp/
│     │  │  ├─ AnimalsApp.kt
│     │  │  ├─ MainActivity.kt
│     │  │  ├─ di/                # Dependencias e inyección (Hilt-ready)
│     │  │  ├─ models/            # Data classes (AnimalsItem, EnviromentItem)
│     │  │  ├─ network/           # RetrofitInstance
│     │  │  ├─ repository/        # Repositorios de datos
│     │  │  ├─ services/          # Interfaces Retrofit
│     │  │  ├─ viewmodel/         # ViewModels MVVM
│     │  │  └─ ui/                # Componentes, pantallas y navegación
│     │  └─ res/                  # Recursos: layouts, drawables, valores
│     └─ androidTest/            # Tests instrumentados
└─ gradle/                      # Wrapper y versión de plugins
```

---

## ⚙️ Tech Stack & Dependencias

* **Lenguaje:** Kotlin 1.8+
* **Compilación:** Android SDK 35, minSdk 29
* **UI:** Jetpack Compose, Material 3
* **Navegación:** androidx.navigation\:navigation-compose:2.8.9
* **Red:** retrofit2:2.11.0, okhttp3:4.11.0, gson
* **Imágenes:** io.coil-kt\:coil-compose:2.4.0
* **Coroutines & Flow:** kotlinx-coroutines, StateFlow
* **Logging:** okhttp3-logging-interceptor
* **Testing:** junit, androidx.test.espresso, androidx.compose.ui.test

---

## 🚀 Instalación & Ejecución

1. **Clonar repositorio**

   ```bash
   git clone https://github.com/tu-usuario/AnimalsApp.git
   cd AnimalsApp
   ```
2. **Abrir en Android Studio**

   * Importar proyecto Gradle existente.
   * Sincronizar dependencias.
3. **Ejecutar**

   * Seleccionar un dispositivo/emulador con Android 10+.
   * Presionar ▶️ Run.

> **Tip:** Asegura conexión a internet para consumir la API.

---

## 📈 Flujo de Trabajo

1. **Lista de Animales:** `AnimalsListViewModel` carga datos via `AnimalsRepository`.
2. **Detalle de Animal:** `AnimalDetailViewModel` obtiene elemento por ID.
3. **Ambientes:** `EnvironmentsListViewModel` y `EnvDetailViewModel` gestionan datos de ambientes y su relación con animales.
4. **UI en Compose:** Estados representados con `UiState` (Loading, Success, Error).

---

## 🤝 Contribuciones

1. Crear un *fork* del repositorio.
2. Abrir una rama (`git checkout -b feature/nombre-feature`).
3. Implementar cambios y añadir tests.
4. Hacer commit con mensaje claro.
5. Enviar Pull Request describiendo la mejora.

---

