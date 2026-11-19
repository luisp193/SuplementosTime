@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.suplementostime.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suplementostime.app.data.RefTable
import com.suplementostime.app.data.References

@Composable
fun ReferenceScreen() {
    var idx by rememberSaveable { mutableStateOf(0) }
    var filter by rememberSaveable { mutableStateOf("") }
    val table = References.tables[idx]

    Column(Modifier.verticalScroll(rememberScrollState()).padding(16.dp)) {
        Text("Tablas de referencia", fontSize = 24.sp, fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary)
        Text("Datos de instituciones oficiales y de alto rendimiento deportivo para contrastar cifras.",
            fontSize = 13.sp, modifier = Modifier.padding(vertical = 8.dp))
        Row(Modifier.horizontalScroll(rememberScrollState())) {
            References.tables.forEachIndexed { i, t ->
                FilterChip(selected = i == idx, onClick = { idx = i; filter = "" },
                    label = { Text(t.chip) }, modifier = Modifier.padding(end = 8.dp))
            }
        }
        Text(table.title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 12.dp))
        Text(table.org, fontSize = 12.sp)
        OutlinedTextField(
            value = filter, onValueChange = { filter = it }, singleLine = true,
            label = { Text("Filtrar filas") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        )
        RefTableView(table, filter.trim())
        Text(table.note, fontSize = 11.sp, modifier = Modifier.padding(top = 8.dp))
        Text("Valores orientativos para adultos sanos. Consulta a un médico o dietista-nutricionista " +
            "deportivo antes de cambiar tu suplementación.", fontSize = 11.sp, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
private fun RefTableView(t: RefTable, filter: String) {
    val rows = t.rows.filter { filter.isEmpty() || it[0].contains(filter, ignoreCase = true) }
    Column(Modifier.horizontalScroll(rememberScrollState())) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("", Modifier.width(t.labelWidthDp.dp))
            t.columns.forEach {
                Text(it, Modifier.width(t.colWidthDp.dp).padding(4.dp), fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }
        if (rows.isEmpty()) Text("Sin resultados.", Modifier.padding(4.dp))
        rows.forEachIndexed { i, row ->
            val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            else androidx.compose.ui.graphics.Color.Transparent
            Row(Modifier.background(bg), verticalAlignment = Alignment.CenterVertically) {
                Text(row[0], Modifier.width(t.labelWidthDp.dp).padding(4.dp), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                row.drop(1).forEach {
                    Text(it, Modifier.width(t.colWidthDp.dp).padding(4.dp), fontSize = 12.sp)
                }
            }
        }
    }
}
