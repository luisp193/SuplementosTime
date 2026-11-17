# Suplementos Time

App Android nativa (Kotlin + Jetpack Compose), en español.

- **Buscador con lupa** tolerante a errores (sin tildes, fonética, letras de más/menos/intercambiadas).
- Ficha por suplemento: propiedades, cantidad diaria, cómo y cuándo tomarlo, precauciones y fuentes.
- Catálogo: vitaminas, minerales, omega-3, rendimiento deportivo y multivitamínicos.
- **Comparador** de productos (multivitamínicos, multiminerales, omega-3): introduces la tabla de la etiqueta y se compara cantidad y % VRN (UE) por elemento.
- Fuentes: NIH ODS, EFSA, ISSN, HSN, Prozis. Información orientativa, no sustituye consejo médico.

## Compilar
GitHub Actions (`.github/workflows/build-apk.yml`) ejecuta tests y `assembleDebug` y sube el APK como artefacto `SuplementosTime-debug-apk`.
Local: `gradle testDebugUnitTest assembleDebug` (Gradle 8.9, JDK 17, Android SDK 34).
