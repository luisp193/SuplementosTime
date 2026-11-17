# Suplementos Time

App Android nativa (Kotlin + Jetpack Compose), en español.

- **Buscador con lupa** tolerante a errores (sin tildes, fonética, letras de más/menos/intercambiadas).
- Ficha por suplemento: propiedades, cantidad diaria, cómo y cuándo tomarlo, precauciones y fuentes.
- Catálogo: vitaminas, minerales, omega-3, rendimiento deportivo, multivitamínicos, aminoácidos, proteínas y péptidos,
  antioxidantes (NAC…), plantas para dormir y plantas medicinales para problemas comunes (se puede buscar por síntoma: "tos", "cistitis", "dormir"…).
  Los péptidos inyectables (BPC-157, TB-500…) solo aparecen como aviso: no son suplementos y están prohibidos por la AMA.
- **Comparador** de productos (multivitamínicos, multiminerales, omega-3): introduces la tabla de la etiqueta y se compara cantidad y % VRN (UE) por elemento.
- **Pantalla de inicio** de 2 s con imágenes de suplementos que se funden y un acorde muy suave (no suena con el móvil en silencio o vibración).
- **Imagen de cada suplemento** (ilustración dibujada por la app según su forma: polvo, cápsula, perla, comprimido o gotas).
  Para usar una foto real, añade `app/src/main/res/drawable/foto_<id>.jpg` (p. ej. `foto_creatina.jpg`, `foto_nac.jpg`); la app la usará automáticamente.
- **Dosis para deportistas de alto rendimiento** (ISSN, consenso COI), sin superar nunca los límites de seguridad.
- Fuentes: NIH ODS, EFSA, ISSN, HSN, Prozis. Información orientativa, no sustituye consejo médico.

## Compilar
GitHub Actions (`.github/workflows/build-apk.yml`) ejecuta tests y `assembleDebug` y sube el APK como artefacto `SuplementosTime-debug-apk`.
Local: `gradle testDebugUnitTest assembleDebug` (Gradle 8.9, JDK 17, Android SDK 34).
