@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.suplementostime.app.ui

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suplementostime.app.data.*

@Composable
fun App(context: Context) {
    var splash by rememberSaveable { mutableStateOf(true) }
    if (splash) SplashScreen { splash = false } else MainContent(context)
}

@Composable
private fun MainContent(context: Context) {
    var tab by remember { mutableIntStateOf(0) }
    var selected by remember { mutableStateOf<Supplement?>(null) }
    BackHandler(enabled = selected != null) { selected = null }

    Scaffold(
        bottomBar = {
            if (selected == null) NavigationBar {
                NavigationBarItem(selected = tab == 0, onClick = { tab = 0 },
                    icon = { Icon(Icons.Default.Search, null) }, label = { Text("Suplementos") })
                NavigationBarItem(selected = tab == 1, onClick = { tab = 1 },
                    icon = { Text("⚖", fontSize = 20.sp) }, label = { Text("Comparar") })
            }
        },
    ) { pad ->
        Box(Modifier.padding(pad).fillMaxSize()) {
            val s = selected
            when {
                s != null -> DetailScreen(s) { selected = null }
                tab == 0 -> SearchScreen { selected = it }
                else -> CompareScreen(context)
            }
        }
    }
}

@Composable
fun SearchScreen(onOpen: (Supplement) -> Unit) {
    var query by remember { mutableStateOf("") }
    val results = remember(query) { Search.search(query, Catalog.all) }
    Column(Modifier.fillMaxSize()) {
        Text("Suplementos Time", fontSize = 26.sp, fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 8.dp))
        OutlinedTextField(
            value = query, onValueChange = { query = it }, singleLine = true,
            placeholder = { Text("Busca: creatina, magnesio, vitamina D…") },
            leadingIcon = { Icon(Icons.Default.Search, "Buscar") },
            trailingIcon = { if (query.isNotEmpty()) IconButton({ query = "" }) { Icon(Icons.Default.Clear, "Borrar") } },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        )
        if (results.isEmpty()) {
            Text("No encontramos nada parecido a \"$query\". Prueba con otra palabra.", Modifier.padding(16.dp))
        }
        LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(results, key = { it.id }) { s ->
                Card(Modifier.fillMaxWidth().clickable { onOpen(s) }) {
                    Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        SupplementImage(s, Modifier.size(64.dp), corner = 12.dp)
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text(s.name, fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
                            Text(s.category, color = MaterialTheme.colorScheme.secondary, fontSize = 12.sp)
                            Spacer(Modifier.height(4.dp))
                            Text(s.summary, fontSize = 14.sp)
                        }
                    }
                }
            }
            item { Text(Catalog.DISCLAIMER, fontSize = 11.sp, modifier = Modifier.padding(top = 8.dp)) }
        }
    }
}

@Composable
private fun Section(title: String, items: List<String>) {
    Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp))
    items.forEach { Text("•  $it", modifier = Modifier.padding(vertical = 2.dp)) }
}

@Composable
fun DetailScreen(s: Supplement, onBack: () -> Unit) {
    val uri = LocalUriHandler.current
    Column(Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(s.name) },
            navigationIcon = { IconButton(onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver") } })
        Column(Modifier.verticalScroll(rememberScrollState()).padding(16.dp)) {
            SupplementImage(s, Modifier.fillMaxWidth().height(220.dp), corner = 20.dp)
            Text(s.category, color = MaterialTheme.colorScheme.secondary, fontSize = 13.sp,
                modifier = Modifier.padding(top = 10.dp, bottom = 4.dp))
            Text(s.summary, fontSize = 16.sp)
            SportDoses.of(s)?.let { sport ->
                Card(
                    Modifier.fillMaxWidth().padding(top = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                ) {
                    Column(Modifier.padding(14.dp)) {
                        Text("🏆 Dosis para deportistas de alto rendimiento", fontWeight = FontWeight.Bold, fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Spacer(Modifier.height(6.dp))
                        Text(sport, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    }
                }
            }
            Section("Propiedades", s.properties)
            Section("Cantidad diaria general y límites", listOf(s.dose))
            Section("Cómo tomarlo", s.howToTake)
            Section("Cuándo", listOf(s.timing))
            Section("Precauciones", s.cautions)
            Text("Fuentes", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 16.dp))
            s.sources.forEach {
                TextButton({ runCatching { uri.openUri(it.url) } }) { Text(it.label) }
            }
            Text(Catalog.DISCLAIMER, fontSize = 11.sp, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
fun CompareScreen(context: Context) {
    val store = remember { ProductStore(context) }
    var products by remember { mutableStateOf(store.load()) }
    var picked by remember { mutableStateOf(setOf<String>()) }
    var adding by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.verticalScroll(rememberScrollState()).padding(16.dp).padding(bottom = 72.dp)) {
            Text("Comparar productos", fontSize = 24.sp, fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary)
            Text("Añade multivitamínicos, multiminerales u omega-3 copiando la tabla nutricional de la etiqueta " +
                "(cantidad por dosis diaria, p. ej. HSN o Prozis) y compara cada elemento con el VRN europeo.",
                fontSize = 13.sp, modifier = Modifier.padding(vertical = 8.dp))
            if (products.isEmpty()) Text("Aún no has añadido productos. Pulsa + para empezar.")
            products.forEach { p ->
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = p.id in picked, onCheckedChange = {
                        picked = if (it) picked + p.id else picked - p.id
                    })
                    Column(Modifier.weight(1f)) {
                        Text(p.name, fontWeight = FontWeight.SemiBold)
                        Text(p.brand, fontSize = 12.sp)
                    }
                    TextButton({ products = products.filter { it.id != p.id }; picked = picked - p.id; store.save(products) }) {
                        Text("Borrar")
                    }
                }
            }
            val chosen = products.filter { it.id in picked }
            if (chosen.size >= 2) CompareTable(chosen)
            else if (products.size >= 2) Text("Marca 2 o más productos para compararlos.", Modifier.padding(top = 12.dp))
        }
        FloatingActionButton({ adding = true }, Modifier.align(Alignment.BottomEnd).padding(16.dp)) {
            Icon(Icons.Default.Add, "Añadir producto")
        }
    }
    if (adding) AddProductDialog(onDismiss = { adding = false }) { p ->
        products = products + p; store.save(products); adding = false
    }
}

@Composable
private fun CompareTable(chosen: List<Product>) {
    val rows = Compare.rows(chosen)
    val cellW = 110.dp
    Column(Modifier.padding(top = 16.dp)) {
        Text("Cantidad por dosis y % VRN", fontWeight = FontWeight.Bold)
        Text("En verde, el producto que más aporta de cada elemento.", fontSize = 12.sp)
        Column(Modifier.horizontalScroll(rememberScrollState()).padding(top = 8.dp)) {
            Row {
                Text("", Modifier.width(130.dp))
                chosen.forEach { Text(it.name, Modifier.width(cellW).padding(2.dp), fontWeight = FontWeight.Bold, fontSize = 12.sp, maxLines = 3) }
            }
            rows.forEach { n ->
                val vals = chosen.map { it.amounts[n.key] ?: 0.0 }
                val max = vals.max()
                Row(Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("${n.name} (${n.unit})", Modifier.width(130.dp), fontSize = 12.sp)
                    vals.forEach { v ->
                        val best = v > 0 && v == max
                        Text(
                            if (v > 0) "${Compare.fmt(v)} · ${Compare.fmt(Math.round(Compare.percent(n, v)).toDouble())}%" else "—",
                            Modifier.width(cellW).padding(2.dp)
                                .background(if (best) MaterialTheme.colorScheme.primaryContainer else androidx.compose.ui.graphics.Color.Transparent),
                            fontSize = 12.sp,
                        )
                    }
                }
            }
            Row(Modifier.padding(top = 8.dp)) {
                Text("Nutrientes entre 50–200 % VRN", Modifier.width(130.dp), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                chosen.forEach { Text("${Compare.balanced(it)}", Modifier.width(cellW).padding(2.dp), fontWeight = FontWeight.Bold) }
            }
        }
        chosen.forEach { p ->
            val over = Compare.overLimit(p)
            if (over.isNotEmpty()) Text("⚠ ${p.name} supera el límite superior orientativo en: " +
                over.joinToString { it.name }, fontSize = 12.sp, modifier = Modifier.padding(top = 6.dp))
        }
        Text("VRN = valor de referencia de nutrientes (Reglamento UE 1169/2011). Más no siempre es mejor: " +
            "busca cantidades cercanas al 100 % y por debajo de los límites superiores.",
            fontSize = 11.sp, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
private fun AddProductDialog(onDismiss: () -> Unit, onSave: (Product) -> Unit) {
    var brand by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    val values = remember { mutableStateMapOf<String, String>() }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nuevo producto") },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                OutlinedTextField(brand, { brand = it }, label = { Text("Marca (HSN, Prozis…)") }, singleLine = true)
                OutlinedTextField(name, { name = it }, label = { Text("Nombre del producto") }, singleLine = true)
                Text("Cantidad por dosis diaria (vacío = 0)", fontSize = 12.sp, modifier = Modifier.padding(top = 8.dp))
                Nutrients.all.forEach { n ->
                    OutlinedTextField(
                        value = values[n.key] ?: "", onValueChange = { values[n.key] = it },
                        label = { Text("${n.name} (${n.unit})") }, singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val am = values.mapNotNull { (k, v) -> v.replace(',', '.').toDoubleOrNull()?.takeIf { it > 0 }?.let { k to it } }.toMap()
                onSave(Product(System.currentTimeMillis().toString(), brand.trim().ifEmpty { "Sin marca" },
                    name.trim().ifEmpty { "Producto" }, am))
            }) { Text("Guardar") }
        },
        dismissButton = { TextButton(onDismiss) { Text("Cancelar") } },
    )
}
