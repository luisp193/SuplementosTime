# Reglas del proyecto

## Numeración de versiones (obligatoria)
- El número de versión es el **número de compilación de GitHub Actions** (`github.run_number`): ejecución #7 = **V7**.
- El workflow pasa ese número a Gradle con `-PappVersion=${{ github.run_number }}`; `app/build.gradle.kts` lo usa en
  `versionCode` y `versionName = "V<n>"`.
- El APK y el artefacto se llaman siempre **`<NombreApp>_V<n>`** (aquí `SuplementosTime_V7.apk`).
- No pongas números de versión a mano ni inventes otro esquema (1.1.1, etc.).
- Cada app Android nueva de Luis debe llevar esta misma regla desde el primer commit.
