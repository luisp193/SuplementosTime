package com.suplementostime.app.data

/**
 * Tablas de referencia de instituciones oficiales y de alto rendimiento deportivo.
 * La primera celda de cada fila es el nombre; el resto va en el mismo orden que [columns].
 * Son valores orientativos para adultos sanos: no sustituyen a un análisis ni a un profesional.
 */
data class RefTable(
    val chip: String,
    val title: String,
    val org: String,
    val note: String,
    val columns: List<String>,
    val rows: List<List<String>>,
    val labelWidthDp: Int = 130,
    val colWidthDp: Int = 100,
)

object References {
    private fun r(label: String, vararg v: String) = listOf(label) + v.toList()

    private val nih = RefTable(
        chip = "NIH (EE. UU.)",
        title = "Vitaminas y minerales · RDA/AI y límite superior",
        org = "NIH Office of Dietary Supplements / National Academies (DRI), adultos 19–50 años",
        note = "RDA = ingesta recomendada; con * es AI (ingesta adecuada). UL = límite superior diario. " +
            "En Mg el UL se refiere solo a suplementos; en vitamina A, a retinol; en B3 y B9, a formas sintéticas.",
        columns = listOf("Hombre", "Mujer", "UL"),
        rows = listOf(
            r("Vitamina A (µg)", "900", "700", "3000"),
            r("Vitamina C (mg)", "90", "75", "2000"),
            r("Vitamina D (µg)", "15", "15", "100"),
            r("Vitamina E (mg)", "15", "15", "1000"),
            r("Vitamina K (µg)", "120*", "90*", "—"),
            r("B1 Tiamina (mg)", "1,2", "1,1", "—"),
            r("B2 Riboflavina (mg)", "1,3", "1,1", "—"),
            r("B3 Niacina (mg)", "16", "14", "35"),
            r("B5 Pantoténico (mg)", "5*", "5*", "—"),
            r("B6 (mg)", "1,3", "1,3", "100"),
            r("B7 Biotina (µg)", "30*", "30*", "—"),
            r("B9 Folato (µg)", "400", "400", "1000"),
            r("B12 (µg)", "2,4", "2,4", "—"),
            r("Calcio (mg)", "1000", "1000", "2500"),
            r("Hierro (mg)", "8", "18", "45"),
            r("Magnesio (mg)", "400–420", "310–320", "350"),
            r("Zinc (mg)", "11", "8", "40"),
            r("Cobre (µg)", "900", "900", "10000"),
            r("Manganeso (mg)", "2,3*", "1,8*", "11"),
            r("Selenio (µg)", "55", "55", "400"),
            r("Cromo (µg)", "35*", "25*", "—"),
            r("Molibdeno (µg)", "45", "45", "2000"),
            r("Yodo (µg)", "150", "150", "1100"),
            r("Potasio (mg)", "3400*", "2600*", "—"),
            r("Fósforo (mg)", "700", "700", "4000"),
        ),
    )

    private val efsa = RefTable(
        chip = "EFSA (UE)",
        title = "Vitaminas y minerales · valores dietéticos de referencia",
        org = "Autoridad Europea de Seguridad Alimentaria (EFSA), adultos",
        note = "PRI = ingesta de referencia de la población; con * es AI (ingesta adecuada). UL = límite superior diario. " +
            "Las cifras de EFSA son distintas de las de EE. UU.: úsalas para contrastar, no para elegir la más alta.",
        columns = listOf("Hombre", "Mujer", "UL"),
        rows = listOf(
            r("Vitamina A (µg)", "750", "650", "3000"),
            r("Vitamina C (mg)", "110", "95", "—"),
            r("Vitamina D (µg)", "15*", "15*", "100"),
            r("Vitamina E (mg)", "13*", "11*", "300"),
            r("Vitamina K (µg)", "70*", "70*", "—"),
            r("B1 Tiamina", "0,1 mg/MJ", "0,1 mg/MJ", "—"),
            r("B2 Riboflavina (mg)", "1,6", "1,6", "—"),
            r("B3 Niacina", "1,6 mg/MJ", "1,6 mg/MJ", "—"),
            r("B5 Pantoténico (mg)", "5*", "5*", "—"),
            r("B6 (mg)", "1,7", "1,6", "12"),
            r("B7 Biotina (µg)", "40*", "40*", "—"),
            r("B9 Folato (µg)", "330", "330", "1000"),
            r("B12 (µg)", "4*", "4*", "—"),
            r("Calcio (mg)", "950", "950", "2500"),
            r("Hierro (mg)", "11", "16", "—"),
            r("Magnesio (mg)", "350*", "300*", "250"),
            r("Zinc (mg)", "9,4–16,3", "7,5–12,7", "25"),
            r("Cobre (mg)", "1,6*", "1,3*", "5"),
            r("Manganeso (mg)", "3*", "3*", "8"),
            r("Selenio (µg)", "70*", "70*", "255"),
            r("Molibdeno (µg)", "65*", "65*", "600"),
            r("Yodo (µg)", "150*", "150*", "600"),
            r("Potasio (mg)", "3500*", "3500*", "—"),
            r("Fósforo (mg)", "550*", "550*", "—"),
        ),
    )

    private val macros = RefTable(
        chip = "Deporte: energía",
        title = "Deportistas · hidratos, proteína, grasa e hidratación",
        org = "ACSM / Academia de Nutrición y Dietética / Dietistas de Canadá (2016) y Consenso del COI (2018)",
        note = "Cifras por kg de peso corporal; se ajustan según el volumen de entrenamiento, el objetivo y el periodo.",
        columns = listOf("Pauta", "Fuente"),
        rows = listOf(
            r("Hidratos · entreno ligero", "3–5 g/kg/día", "ACSM 2016"),
            r("Hidratos · 1 h/día", "5–7 g/kg/día", "ACSM 2016"),
            r("Hidratos · 1–3 h/día", "6–10 g/kg/día", "ACSM 2016"),
            r("Hidratos · 4–5 h/día", "8–12 g/kg/día", "ACSM 2016"),
            r("Hidratos durante (1–2,5 h)", "30–60 g/h", "ACSM 2016 / COI"),
            r("Hidratos durante (>2,5 h)", "hasta 90 g/h (glucosa + fructosa)", "COI 2018"),
            r("Recuperación inmediata", "1–1,2 g/kg/h, primeras 4 h", "ACSM 2016"),
            r("Proteína diaria", "1,2–2,0 g/kg/día", "ACSM 2016"),
            r("Proteína por toma", "0,25–0,3 g/kg (20–40 g)", "ACSM 2016"),
            r("Grasa", "20–35 % de la energía", "ACSM 2016"),
            r("Pérdida de peso por sudor", "no superar el 2 % del peso", "ACSM"),
            r("Sodio en el sudor", "0,2–2 g/L (media ≈ 1 g/L)", "Gatorade Sports Science Institute"),
            r("Sodio en bebidas (>2 h)", "0,5–0,7 g/L", "ACSM"),
        ),
        labelWidthDp = 150,
        colWidthDp = 190,
    )

    private val micros = RefTable(
        chip = "Deporte: micronutrientes",
        title = "Deportistas · vitaminas y minerales de atención",
        org = "Consenso del COI (2018), National Academies (DRI) y organismos de alto rendimiento",
        note = "Los micronutrientes no mejoran el rendimiento si no hay déficit. Mide antes de suplementar " +
            "(ferritina, 25(OH)D, B12) y no superes los límites de las otras tablas.",
        columns = listOf("Pauta", "Fuente"),
        rows = listOf(
            r("Hierro · resistencia", "necesidades un 30–70 % mayores que la RDA", "National Academies"),
            r("Hierro · control", "analítica de ferritina al menos 1 vez al año", "Consenso COI 2018"),
            r("Vitamina D · objetivo", "25(OH)D ≥ 30 ng/ml (75 nmol/L)", "Consenso COI 2018"),
            r("Vitamina D · invierno", "1000–2000 UI/día si hay poca exposición solar", "Institutos de alto rendimiento"),
            r("Calcio · baja energía", "1500 mg/día entre dieta y suplemento", "ACSM (Tríada de la deportista)"),
            r("Vitamina C y E", "dosis altas continuas pueden frenar adaptaciones", "Consenso COI 2018"),
            r("Magnesio", "cubrir la RDA con dieta; suplemento solo si hay déficit", "NIH ODS"),
            r("Vitamina B12 · veganos", "suplementar siempre (≥ 4 µg/día o 2000 µg/semana)", "EFSA / ISSN"),
        ),
        labelWidthDp = 150,
        colWidthDp = 190,
    )

    private val ergogenic = RefTable(
        chip = "Deporte: suplementos",
        title = "Deportistas · suplementos con evidencia",
        org = "AIS Sports Supplement Framework (Grupo A), ISSN y Consenso del COI (2018)",
        note = "Dosis para adultos sanos. Antes de competir, prueba cada producto en entrenamiento " +
            "y comprueba que tenga sello antidopaje (Informed Sport, NSF Certified for Sport).",
        columns = listOf("Dosis", "Fuente"),
        rows = listOf(
            r("Cafeína", "3–6 mg/kg, 30–60 min antes", "AIS / COI"),
            r("Creatina monohidrato", "3–5 g/día, de forma continuada", "AIS / ISSN"),
            r("Beta-alanina", "4–6 g/día en tomas de ≤ 2 g", "ISSN"),
            r("Bicarbonato sódico", "0,2–0,4 g/kg, 60–180 min antes", "AIS / COI"),
            r("Nitrato (remolacha)", "≈ 300–600 mg de nitrato, 2–3 h antes", "AIS / COI"),
            r("Vitamina D e hierro", "solo si el análisis lo indica", "AIS (Grupo A)"),
            r("Whey / proteína en polvo", "20–40 g por toma", "AIS / ISSN"),
            r("Geles y bebidas deportivas", "30–90 g de hidratos/h según duración", "AIS / ACSM"),
        ),
        labelWidthDp = 150,
        colWidthDp = 190,
    )

    val tables: List<RefTable> = listOf(nih, efsa, macros, micros, ergogenic)
}
