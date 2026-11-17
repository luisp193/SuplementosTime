package com.suplementostime.app.data

/**
 * Dosis orientadas a deportistas de alto rendimiento, según protocolos de nutrición deportiva
 * (ISSN, consenso del COI 2018 sobre suplementos). Nunca superan los límites de seguridad:
 * en vitaminas y minerales "más" no mejora el rendimiento y puede ser tóxico.
 */
object SportDoses {
    private const val SAME_AS_GENERAL =
        "Para deportistas la dosis óptima es la misma que la general: tomar más no mejora el rendimiento " +
            "y, por encima del límite superior, puede ser tóxico. Ajusta solo si un análisis muestra déficit."

    private val map: Map<String, String> = mapOf(
        "creatina" to "0,1 g por kg de peso al día (≈ 7–10 g en deportistas de 70–100 kg). Carga rápida: 0,3 g/kg/día " +
            "(≈ 20–30 g) en 4 tomas durante 5–7 días. Tómala con hidratos y proteína para mejorar la captación muscular.",
        "whey" to "Proteína total: 1,6–2,2 g/kg/día (hasta 2,3–3,1 g/kg de masa magra en fase de definición). " +
            "Reparte 0,3–0,4 g/kg por toma (≈ 25–40 g) cada 3–4 h, y 30–40 g de caseína antes de dormir.",
        "magnesio" to "Deportistas con mucho sudor: 350–500 mg/día de magnesio elemental repartidos en 2 tomas. " +
            "Por encima de 350 mg en suplementos aumenta la probabilidad de diarrea; usa bisglicinato.",
        "vitd" to "2000–4000 UI (50–100 µg) al día, sobre todo en invierno o si entrenas en interior. " +
            "Objetivo en análisis: 25(OH)D de 30–50 ng/ml. Dosis mayores solo con déficit confirmado y control médico.",
        "omega3" to "2–3 g de EPA+DHA al día para recuperación, inflamación y salud cardiovascular (hasta 4 g en estudios). " +
            "La EFSA considera seguro hasta 5 g/día.",
        "cafeina" to "3–6 mg/kg 30–60 min antes de competir o de sesiones clave (≈ 210–420 mg en 70 kg). " +
            "Dosis bajas (1–2 mg/kg) al final de pruebas largas también funcionan. Por encima de 9 mg/kg no hay más beneficio " +
            "y sí más efectos secundarios. Ojo: 6 mg/kg en una persona de 80 kg (480 mg) supera los 400 mg/día de la EFSA.",
        "vitc" to "250–1000 mg/día en periodos de mucha carga o viajes para apoyar las defensas. Evita tomar de forma " +
            "continuada más de 1 g junto al entrenamiento: puede reducir parte de las adaptaciones.",
        "zinc" to "15–30 mg/día en deportistas con mucho sudor o dieta vegetal. No superes 40 mg/día; si pasas de 25 mg " +
            "durante semanas, añade 1–2 mg de cobre.",
        "colageno" to "15 g de colágeno hidrolizado o gelatina + 50 mg de vitamina C, 30–60 min antes de entrenar o " +
            "de los ejercicios de rehabilitación de tendones y ligamentos.",
        "ashwagandha" to "600 mg/día de extracto estandarizado (p. ej. KSM-66) en 2 tomas de 300 mg, durante 8–12 semanas. " +
            "Se ha asociado a mejoras de fuerza, VO2máx y recuperación.",
        "melatonina" to "Jet lag o cambios de horario en competición: 1–5 mg 30–60 min antes de dormir en la hora de destino. " +
            "Empieza por 1 mg; en España sin receta se venden dosis de hasta 1,9 mg.",
        "betaalanina" to "4–6,4 g/día repartidos en tomas de 0,8–1,6 g (o de liberación sostenida) durante al menos " +
            "4 semanas; los mejores resultados aparecen a las 8–12 semanas.",
        "citrulina" to "8 g de citrulina malato (2:1) o 6 g de L-citrulina pura 60 minutos antes de entrenar " +
            "(hasta 10 g de malato en algunos protocolos).",
        "b12" to "Igual que la dosis general salvo en veganos: 250–1000 µg al día. " + SAME_AS_GENERAL,
        "nac" to "1200 mg/día (2 tomas de 600 mg) en bloques de mucha carga. Las dosis agudas altas (70 mg/kg) dan muchas " +
            "molestias digestivas. Tomarla siempre de forma crónica puede frenar adaptaciones al entrenamiento: úsala por ciclos.",
        "glutation" to "500–1000 mg/día de glutatión liposomal en periodos de carga alta.",
        "ala" to "600 mg/día en ayunas, en ciclos de 8–12 semanas.",
        "coq10" to "200–300 mg/día de ubiquinol con comida grasa, en 2 tomas.",
        "carnitina" to "2 g/día de L-carnitina L-tartrato con 60–80 g de hidratos (la insulina favorece su entrada al músculo), " +
            "durante al menos 12 semanas.",
        "taurina" to "1–3 g 60–90 min antes de entrenar o competir (hasta 6 g/día, límite seguro EFSA).",
        "glicina" to "3–5 g antes de dormir para mejorar el descanso.",
        "teanina" to "200 mg junto a 100–200 mg de cafeína antes de entrenar o competir para mejorar la concentración " +
            "sin nerviosismo.",
        "glutamina" to "5–10 g/día. Para proteger el intestino en pruebas de resistencia con calor: 0,25–0,3 g/kg " +
            "1–2 horas antes.",
        "eaa" to "10–15 g de EAA con al menos 3 g de leucina por toma, durante o tras entrenar; útiles si entrenas en ayunas.",
        "hmb" to "3 g/día (≈ 38 mg/kg) en 3 tomas; la forma ácido libre (HMB-FA), 1–2 g 30–60 min antes de entrenar.",
        "electrolitos" to "Sudoración intensa: 500–1000 mg de sodio por hora de ejercicio. Tras entrenar, bebe " +
            "1,25–1,5 litros por cada kg de peso perdido.",
        "curcuma" to "1000–1500 mg/día de curcuminoides de alta absorción para reducir las agujetas y el dolor muscular.",
        "rhodiola" to "200–600 mg de extracto 60 minutos antes de entrenar o competir.",
        "quercetina" to "1000 mg/día durante 1–2 semanas en bloques de mucha carga (efecto pequeño en resistencia).",
        "hierro" to "Frecuente déficit en deportistas de resistencia y mujeres. SOLO con análisis (ferritina baja) y control " +
            "médico: el protocolo habitual es 60–100 mg de hierro elemental en días alternos por la mañana, con vitamina C.",
        "calcio" to "1000–1500 mg/día entre dieta y suplementos en deportistas con riesgo de fracturas por estrés o con " +
            "baja disponibilidad energética. No superes 500 mg por toma.",
        "probioticos" to "Deportistas de resistencia que viajan o compiten mucho: ≥ 10 000 millones de UFC/día de cepas con " +
            "estudios (p. ej. Lactobacillus) para reducir resfriados y molestias intestinales.",
        "arginina" to "0,15 g/kg (≈ 10–12 g) 60–90 min antes, según algunos estudios; en entrenados el efecto es escaso " +
            "y la diarrea frecuente. Mejor opción: citrulina.",
        "leucina" to "3 g por comida (≈ 0,05 g/kg) en 4–5 comidas al día, sobre todo con proteínas vegetales o en déficit calórico.",
        "tirosina" to "100–150 mg/kg (≈ 7–10 g en 70 kg) 60 min antes, en situaciones de estrés, calor o falta de sueño. " +
            "Empieza por 2 g para valorar la tolerancia.",
        "triptofano" to "1–2 g antes de dormir en periodos de competición o viajes. No mejora el rendimiento directamente.",
        "htp" to "Igual que la dosis general (50–300 mg): no hay protocolos deportivos y dosis mayores aumentan los riesgos.",
        "gaba" to "100–300 mg antes de dormir. Las dosis de 3–5 g para subir la hormona del crecimiento no han mostrado " +
            "más músculo ni rendimiento.",
        "ornitina" to "0,1 g/kg (≈ 7 g en 70 kg) en pruebas de resistencia larga para reducir la fatiga; o 2–6 g antes de dormir.",
        "lisina" to "Igual que la dosis general (1–3 g/día). Las dosis altas con arginina para la hormona del crecimiento " +
            "no tienen efecto real en el músculo.",
        "betaina" to "2,5 g/día (≈ 1,25 g dos veces) durante al menos 2–6 semanas, con efecto pequeño en potencia y composición corporal.",
        "agmatina" to "1–2,5 g 30–60 min antes de entrenar; hay muy poca investigación en deportistas.",
        "daa" to "3 g/día en ciclos de 12–14 días. No subas a 6 g: en entrenados puede bajar la testosterona.",
        "carnosina" to "No hay protocolo eficaz con carnosina oral: usa beta-alanina (4–6,4 g/día) para subir la carnosina muscular.",
        "whey_hidro" to "0,3–0,4 g/kg (≈ 25–40 g) justo tras entrenar; en pruebas largas, 10–20 g por hora junto a los hidratos.",
        "caseina" to "40 g (≈ 0,5 g/kg) 30 min antes de dormir para maximizar la síntesis de músculo durante la noche.",
        "colageno_pep" to "Tendón/ligamento: 5 g de Tendoforte o 15 g de colágeno + 50 mg de vitamina C 30–60 min antes de la " +
            "carga o rehabilitación. Articulación: 5 g de Fortigel. Hueso: 5 g de Fortibone.",
        "calostro" to "20 g/día durante 4–8 semanas antes de bloques de competición, en ayunas. Recuerda el aviso de la AMA.",
        "peptidos_iny" to "No hay dosis: están PROHIBIDOS por la AMA para deportistas, en competición y fuera de ella. " +
            "Su uso puede suponer una sanción y riesgos para la salud.",
    )

    private val safetyFirstCategories = setOf("Vitaminas", "Minerales", "Multivitamínicos")

    private const val SLEEP_PLANTS =
        "No mejoran el rendimiento. Úsalas solo por la noche y nunca antes de competir o de un entrenamiento técnico: " +
            "pueden dar somnolencia y alargar el tiempo de reacción. La dosis es la misma que la general."

    private const val HERBAL =
        "La dosis es la misma que la general: no hay beneficio deportivo en tomar más. Si pasas controles antidopaje, " +
            "elige productos con certificado (p. ej. Informed Sport o el sello del CELAD): las plantas pueden ir contaminadas."

    fun of(s: Supplement): String? = map[s.id] ?: when {
        s.category in safetyFirstCategories -> SAME_AS_GENERAL
        s.category == SUENO -> SLEEP_PLANTS
        s.category == MEDICINALES -> HERBAL
        else -> null
    }
}
