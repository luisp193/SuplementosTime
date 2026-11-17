package com.suplementostime.app.data

private fun hsn(url: String, label: String = "HSN") = Source(label, url)
private fun nih(slug: String) = Source("NIH Oficina de Suplementos Dietéticos (ODS)", "https://ods.od.nih.gov/factsheets/$slug-HealthProfessional/")
private val EFSA_VITD = Source("EFSA – Límite superior de vitamina D (2023)", "https://www.efsa.europa.eu/en/efsajournal/pub/8145")
private val EFSA_CAF = Source("EFSA – Seguridad de la cafeína (2015)", "https://www.efsa.europa.eu/en/press/news/150527")
private val PROZIS = Source("Prozis", "https://www.prozis.com/es/es/nutricion-deportiva")
private val ISSN_CREA = Source("ISSN – Posición sobre creatina (2017)", "https://jissn.biomedcentral.com/articles/10.1186/s12970-017-0173-z")

object Catalog {
    const val DISCLAIMER =
        "Información orientativa y educativa, basada en fuentes oficiales y tiendas especializadas. " +
        "No sustituye el consejo médico. Si estás embarazada, das el pecho, tomas medicación o tienes " +
        "una enfermedad, consulta antes con tu médico o farmacéutico. Sigue siempre la etiqueta del producto."

    private val core: List<Supplement> = listOf(
        Supplement(
            id = "creatina", name = "Creatina monohidrato", category = "Rendimiento deportivo",
            aliases = listOf("creatina", "creatine", "monohidrato", "kreatina", "fuerza", "musculo"),
            summary = "El suplemento deportivo con más evidencia para fuerza y potencia.",
            properties = listOf(
                "Mejora el rendimiento en esfuerzos cortos e intensos (pesas, sprints).",
                "Ayuda a aumentar la masa muscular junto con el entrenamiento de fuerza.",
                "Es una de las más estudiadas y se considera segura en personas sanas.",
            ),
            dose = "3–5 g al día (HSN indica unos 3,5 g). Fase de carga opcional: 20 g/día durante 5–7 días (4 tomas de 5 g), después 3–5 g.",
            howToTake = listOf(
                "Mezcla un cacito (≈3,5 g) en 200–250 ml de agua, zumo o batido.",
                "Tómala todos los días, también los de descanso.",
                "No necesita ciclos de descanso. Bebe agua a lo largo del día.",
            ),
            timing = "A cualquier hora; lo importante es la constancia. Puedes tomarla tras entrenar o con una comida.",
            cautions = listOf(
                "Consulta con tu médico si tienes enfermedad renal.",
                "Puede aumentar algo el peso por retención de agua intramuscular.",
                "La creatinina en sangre puede subir sin que haya daño renal; avísalo si te haces análisis.",
            ),
            sources = listOf(
                hsn("https://www.hsnstore.com/blog/suplementos/rendimiento-deportivo/creatina/cuanta-tomar-al-dia-y-durante-cuanto-tiempo/", "HSN – Cuánta creatina tomar"),
                ISSN_CREA, nih("ExerciseAndAthleticPerformance"),
            ),
        ),
        Supplement(
            id = "whey", name = "Proteína whey (suero de leche)", category = "Proteínas",
            aliases = listOf("proteina", "whey", "suero", "protein", "batido", "isolate", "aislada", "wei"),
            summary = "Proteína de alta calidad y rápida digestión para completar la ingesta diaria.",
            properties = listOf(
                "Aporta todos los aminoácidos esenciales, rica en leucina.",
                "Ayuda a la recuperación y al mantenimiento/ganancia de masa muscular.",
                "Cómoda para alcanzar tu objetivo diario de proteína.",
            ),
            dose = "20–40 g por toma, 1–2 veces al día, hasta completar unos 1,4–2,0 g de proteína por kg de peso al día (deportistas) contando la comida.",
            howToTake = listOf(
                "Disuelve 1 cacito (≈30 g) en 200–250 ml de agua, leche o bebida vegetal.",
                "Agita en coctelera. Se puede añadir a avena, yogur o batidos.",
                "Es un complemento: no sustituye una dieta variada.",
            ),
            timing = "Tras entrenar o entre comidas, o cuando necesites completar proteína. El momento exacto importa menos que el total diario.",
            cautions = listOf(
                "Contiene lactosa (el aislado menos). Si eres intolerante prueba aislado o hidrolizado.",
                "Alergia a las proteínas de la leche: no usar.",
                "Con enfermedad renal, consulta antes.",
            ),
            sources = listOf(PROZIS, hsn("https://www.hsnstore.com/blog/", "HSN – Blog de suplementación"), nih("ExerciseAndAthleticPerformance")),
        ),
        Supplement(
            id = "magnesio", name = "Magnesio", category = "Minerales",
            aliases = listOf("magnesium", "citrato de magnesio", "glicinato", "bisglicinato", "calambres", "sueno", "relajar"),
            summary = "Mineral implicado en más de 300 reacciones: músculo, nervios y energía.",
            properties = listOf(
                "Contribuye al funcionamiento normal de músculos y sistema nervioso.",
                "Contribuye a reducir el cansancio y la fatiga.",
                "Aporta al equilibrio electrolítico y a la salud ósea.",
            ),
            dose = "Si la dieta no llega: 200–350 mg de magnesio elemental al día. Límite superior NIH para suplementos: 350 mg/día (la EFSA propone 250 mg/día).",
            howToTake = listOf(
                "Elige formas bien toleradas: citrato, bisglicinato/glicinato.",
                "Tómalo con comida si te sienta mal al estómago.",
                "Fija la dosis por el magnesio elemental que indica la etiqueta, no por el peso de la sal.",
            ),
            timing = "Por la noche o con la cena suele resultar cómodo; también con una comida.",
            cautions = listOf(
                "Dosis altas pueden causar diarrea y malestar.",
                "Con enfermedad renal o ciertos fármacos (antibióticos, bifosfonatos, diuréticos) consulta antes.",
            ),
            sources = listOf(nih("Magnesium"), PROZIS, hsn("https://www.hsnstore.com/blog/", "HSN – Blog de suplementación")),
        ),
        Supplement(
            id = "vitd", name = "Vitamina D3", category = "Vitaminas",
            aliases = listOf("vitamina d", "vit d", "colecalciferol", "d3", "sol", "huesos", "defensas"),
            summary = "Vitamina liposoluble clave para huesos, músculos y sistema inmune.",
            properties = listOf(
                "Contribuye al mantenimiento normal de huesos, dientes y músculos.",
                "Ayuda a la absorción de calcio y fósforo.",
                "Contribuye al funcionamiento normal del sistema inmunitario.",
            ),
            dose = "Ingesta recomendada adultos: 600 UI (15 µg)/día, 800 UI (20 µg) a partir de 70 años. Suplementos habituales 400–2000 UI/día. Límite superior seguro: 4000 UI (100 µg)/día (EFSA y NIH).",
            howToTake = listOf(
                "Tómala con una comida que contenga grasa (se absorbe mejor).",
                "Prefiere la forma D3 (colecalciferol).",
                "Una dosis diaria baja es tan válida como una semanal más alta.",
            ),
            timing = "Con la comida principal, siempre a la misma hora.",
            cautions = listOf(
                "No superes 4000 UI/día sin control médico: riesgo de exceso de calcio.",
                "Si dudas de tu nivel, un análisis de 25(OH)D te lo dirá.",
            ),
            sources = listOf(nih("VitaminD"), EFSA_VITD, hsn("https://www.hsnstore.com/blog/", "HSN – Blog de suplementación")),
        ),
        Supplement(
            id = "omega3", name = "Omega-3 (EPA y DHA)", category = "Ácidos grasos",
            aliases = listOf("omega 3", "omega3", "aceite de pescado", "fish oil", "dha", "epa", "krill", "corazon", "cerebro"),
            summary = "Grasas esenciales de pescado y algas para corazón, cerebro y visión.",
            properties = listOf(
                "EPA y DHA contribuyen al funcionamiento normal del corazón (250 mg/día).",
                "El DHA contribuye al mantenimiento de la función cerebral y la visión (250 mg/día).",
                "Pueden ayudar a mantener triglicéridos en rangos normales con dosis mayores y supervisión.",
            ),
            dose = "250–1000 mg de EPA+DHA al día para mantenimiento. La EFSA considera seguro hasta 5 g/día de EPA+DHA en suplementos; la FDA, 3 g/día.",
            howToTake = listOf(
                "Mira cuánto EPA+DHA aporta la cápsula, no los mg totales de aceite.",
                "Tómalo con una comida con grasa para reducir regurgitaciones.",
                "Guárdalo en lugar fresco; si huele rancio, descártalo.",
            ),
            timing = "Con comida, comida principal o cena.",
            cautions = listOf(
                "Con anticoagulantes o antes de una cirugía, consulta a tu médico.",
                "Alergia al pescado: elige versión de algas.",
            ),
            sources = listOf(nih("Omega3FattyAcids"), PROZIS, hsn("https://www.hsnstore.com/blog/", "HSN – Blog de suplementación")),
        ),
        Supplement(
            id = "cafeina", name = "Cafeína", category = "Rendimiento deportivo",
            aliases = listOf("caffeine", "cafe", "preentreno", "pre workout", "energia", "estimulante"),
            summary = "Estimulante que mejora el rendimiento y reduce la sensación de esfuerzo.",
            properties = listOf(
                "Mejora la resistencia y el rendimiento en ejercicio.",
                "Aumenta el estado de alerta y la concentración.",
            ),
            dose = "3 mg/kg de peso, 30–60 min antes de entrenar (hasta 6 mg/kg en estudios, aunque no hace falta). Máximo 200 mg por toma y 400 mg/día para adultos sanos (EFSA). Embarazo: 200 mg/día.",
            howToTake = listOf(
                "Suma también el café, té, refrescos y preentrenos del día.",
                "Empieza por la dosis más baja para ver tu tolerancia.",
                "Tómala en cápsula, chicle o en polvo con agua.",
            ),
            timing = "30–60 minutos antes de entrenar. Evítala en las 6 horas previas a dormir.",
            cautions = listOf(
                "Puede causar insomnio, nerviosismo y taquicardia.",
                "Si tienes hipertensión, ansiedad o problemas cardíacos, consulta antes.",
                "No recomendada en menores de edad.",
            ),
            sources = listOf(EFSA_CAF, nih("ExerciseAndAthleticPerformance"), PROZIS),
        ),
        Supplement(
            id = "vitc", name = "Vitamina C", category = "Vitaminas",
            aliases = listOf("vitamina c", "vit c", "acido ascorbico", "defensas", "resfriado", "antioxidante"),
            summary = "Antioxidante hidrosoluble implicado en el colágeno y el sistema inmune.",
            properties = listOf(
                "Contribuye al funcionamiento normal del sistema inmunitario.",
                "Contribuye a la formación normal de colágeno.",
                "Mejora la absorción del hierro de origen vegetal.",
            ),
            dose = "Ingesta recomendada: 90 mg/día (hombres) y 75 mg/día (mujeres). Suplementos habituales: 100–500 mg/día. Límite superior NIH: 2000 mg/día.",
            howToTake = listOf(
                "Divide en 2 tomas si tomas más de 500 mg.",
                "Con o sin comida; con comida se tolera mejor.",
            ),
            timing = "A cualquier hora. Junto a comidas con hierro vegetal mejora su absorción.",
            cautions = listOf(
                "Dosis altas pueden dar diarrea y molestias gástricas.",
                "Antecedentes de cálculos renales de oxalato: consulta antes.",
            ),
            sources = listOf(nih("VitaminC"), hsn("https://www.hsnstore.com/blog/", "HSN – Blog de suplementación")),
        ),
        Supplement(
            id = "zinc", name = "Zinc", category = "Minerales",
            aliases = listOf("zink", "cinc", "defensas", "piel", "testosterona"),
            summary = "Mineral para inmunidad, piel y metabolismo de proteínas.",
            properties = listOf(
                "Contribuye al funcionamiento normal del sistema inmunitario.",
                "Contribuye al mantenimiento de piel, cabello y uñas.",
                "Participa en el metabolismo de macronutrientes.",
            ),
            dose = "Ingesta recomendada: 11 mg/día (hombres), 8 mg/día (mujeres). Suplemento habitual 5–15 mg. Límite superior: 40 mg/día (NIH); EFSA 25 mg/día.",
            howToTake = listOf(
                "Tómalo con comida para evitar náuseas.",
                "No lo combines a la vez con calcio o hierro en dosis altas.",
            ),
            timing = "Con una comida, por ejemplo la cena.",
            cautions = listOf(
                "Tomar más de 25–40 mg durante semanas puede reducir el cobre.",
                "Puede causar náuseas en ayunas.",
            ),
            sources = listOf(nih("Zinc"), hsn("https://www.hsnstore.com/blog/", "HSN – Blog de suplementación")),
        ),
        Supplement(
            id = "colageno", name = "Colágeno hidrolizado", category = "Articulaciones y piel",
            aliases = listOf("colageno", "collagen", "articulaciones", "tendones", "piel", "kolageno"),
            summary = "Proteína de tejido conectivo, usada para articulaciones y piel.",
            properties = listOf(
                "Aporta glicina, prolina e hidroxiprolina.",
                "Estudios sugieren beneficios en articulaciones y elasticidad de la piel.",
                "La evidencia es moderada y variable según estudio.",
            ),
            dose = "2,5–15 g al día de colágeno hidrolizado según el producto y el objetivo.",
            howToTake = listOf(
                "Mézclalo en agua, café, té o un batido.",
                "Combínalo con vitamina C para favorecer la síntesis.",
                "Constancia: se evalúa tras 8–12 semanas.",
            ),
            timing = "A cualquier hora; si lo usas para tendones, 30–60 min antes de entrenar.",
            cautions = listOf(
                "No contiene todos los aminoácidos esenciales: no sustituye a la proteína completa.",
                "Cuidado con alergias a pescado o vacuno según el origen.",
            ),
            sources = listOf(hsn("https://www.hsnstore.com/blog/", "HSN – Blog de suplementación"), PROZIS),
        ),
        Supplement(
            id = "ashwagandha", name = "Ashwagandha", category = "Plantas y adaptógenos",
            aliases = listOf("ashwaganda", "asuagandha", "withania", "estres", "ansiedad", "cortisol", "adaptogeno"),
            summary = "Planta adaptógena estudiada para el estrés y el descanso.",
            properties = listOf(
                "Algunos estudios indican reducción del estrés percibido y del cortisol.",
                "Puede mejorar la calidad del sueño en algunas personas.",
            ),
            dose = "300–600 mg de extracto estandarizado al día (según etiqueta, p. ej. 5 % withanólidos).",
            howToTake = listOf(
                "Tómalo con comida.",
                "Prueba 4–8 semanas y valora el efecto; haz descansos si lo usas mucho tiempo.",
            ),
            timing = "Por la mañana o por la noche según te siente; con comida.",
            cautions = listOf(
                "No usar en embarazo ni lactancia.",
                "Se han descrito casos raros de daño hepático: suspende si notas molestias.",
                "Evítalo con trastornos tiroideos o inmunosupresores sin consultar.",
            ),
            sources = listOf(hsn("https://www.hsnstore.com/blog/", "HSN – Blog de suplementación"), PROZIS),
        ),
        Supplement(
            id = "melatonina", name = "Melatonina", category = "Descanso",
            aliases = listOf("melatonin", "sueno", "dormir", "insomnio", "jet lag"),
            summary = "Hormona del ciclo sueño-vigilia usada para conciliar el sueño.",
            properties = listOf(
                "1 mg antes de dormir reduce el tiempo en conciliar el sueño (declaración EFSA).",
                "Ayuda a aliviar el desfase horario (jet lag).",
            ),
            dose = "0,5–1 mg suele bastar; más no suele dar más efecto. Máximo habitual 1–2 mg en suplementos sin receta.",
            howToTake = listOf(
                "Tómala 30–60 minutos antes de acostarte.",
                "Úsala de forma puntual y reevalúa con un profesional si dura semanas.",
            ),
            timing = "Por la noche, antes de dormir, a la misma hora.",
            cautions = listOf(
                "Puede causar somnolencia: no conducir tras tomarla.",
                "Embarazo, lactancia y menores: consulta médica.",
                "Interacciona con anticoagulantes y fármacos sedantes.",
            ),
            sources = listOf(hsn("https://www.hsnstore.com/blog/", "HSN – Blog de suplementación"), nih("Melatonin")),
        ),
        Supplement(
            id = "betaalanina", name = "Beta-alanina", category = "Rendimiento deportivo",
            aliases = listOf("beta alanina", "betaalanina", "beta-alanine", "carnosina", "resistencia"),
            summary = "Aminoácido que aumenta la carnosina muscular y retrasa la fatiga.",
            properties = listOf(
                "Mejora el rendimiento en esfuerzos de 1–4 minutos.",
                "Su efecto es acumulativo: se necesitan semanas.",
            ),
            dose = "3,2–6,4 g al día en tomas de ≤1,6 g para reducir el hormigueo (ISSN).",
            howToTake = listOf(
                "Reparte en 2–4 tomas con comida.",
                "El cosquilleo (parestesia) es inofensivo y disminuye con dosis pequeñas.",
                "Mantén al menos 4 semanas.",
            ),
            timing = "A cualquier hora, repartida; no depende del entrenamiento.",
            cautions = listOf("Puede causar hormigueo en cara y manos."),
            sources = listOf(hsn("https://www.hsnstore.com/blog/", "HSN – Blog de suplementación"), Source("ISSN – Beta-alanina (2015)", "https://jissn.biomedcentral.com/articles/10.1186/s12970-015-0090-y")),
        ),
        Supplement(
            id = "citrulina", name = "Citrulina malato", category = "Rendimiento deportivo",
            aliases = listOf("citrulina", "citruline", "citrulline", "bombeo", "pump", "preentreno"),
            summary = "Aminoácido popular en preentrenos por su efecto sobre el óxido nítrico.",
            properties = listOf(
                "Puede mejorar la resistencia muscular y reducir la fatiga.",
                "La evidencia es mixta; la respuesta varía entre personas.",
            ),
            dose = "6–8 g de citrulina malato (2:1) antes de entrenar.",
            howToTake = listOf(
                "Disuelve en 250–300 ml de agua.",
                "Si usas L-citrulina pura, la dosis equivalente es menor (≈3–6 g).",
            ),
            timing = "30–60 minutos antes de entrenar.",
            cautions = listOf(
                "Puede dar molestias gastrointestinales en ayunas.",
                "Con medicación para tensión arterial o erección, consulta antes.",
            ),
            sources = listOf(hsn("https://www.hsnstore.com/blog/", "HSN – Blog de suplementación"), PROZIS),
        ),
        Supplement(
            id = "b12", name = "Vitamina B12", category = "Vitaminas",
            aliases = listOf("vitamina b12", "b12", "cobalamina", "vegano", "vegetariano", "energia"),
            summary = "Esencial para el sistema nervioso y la formación de glóbulos rojos.",
            properties = listOf(
                "Contribuye al metabolismo energético y a reducir el cansancio.",
                "Los veganos necesitan suplementarla: no está en alimentos vegetales.",
            ),
            dose = "Ingesta recomendada: 2,4 µg/día. Suplemento habitual para veganos: 25–250 µg diarios o 2000 µg a la semana.",
            howToTake = listOf(
                "Elige cianocobalamina o metilcobalamina.",
                "Puedes tomarla en comprimidos, sublingual o spray.",
            ),
            timing = "Por la mañana, con o sin comida.",
            cautions = listOf("En general segura; si tienes anemia o síntomas neurológicos, consulta al médico."),
            sources = listOf(nih("VitaminB12"), hsn("https://www.hsnstore.com/blog/", "HSN – Blog de suplementación")),
        ),
    )

    val all: List<Supplement> by lazy { core + CatalogMore.items + CatalogExtra.items + CatalogAminos.items + CatalogPlants.items }
}
