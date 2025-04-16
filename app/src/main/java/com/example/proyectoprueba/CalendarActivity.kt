package com.example.proyectoprueba

import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.jakewharton.threetenabp.AndroidThreeTen
import org.threeten.bp.LocalDate
import java.util.HashMap

class CalendarActivity : AppCompatActivity() {

    private lateinit var calendarView: MaterialCalendarView
    private lateinit var eventsListView: ListView
    private lateinit var selectedDateText: TextView

    // Almacén en memoria de actividades por fecha
    private val eventsMap = HashMap<LocalDate, MutableList<String>>()
    private var selectedDate: LocalDate = LocalDate.now()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Inicializa ThreeTenABP para soportar LocalDate en minSdk < 26
        AndroidThreeTen.init(this)

        setContentView(R.layout.activity_calendar)

        // Vincula las vistas
        calendarView = findViewById(R.id.calendarView)
        eventsListView = findViewById(R.id.eventsListView)
        selectedDateText = findViewById(R.id.selectedDateText)
        val addEventButton = findViewById<Button>(R.id.addEventButton)

        // Adaptador de lista
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        eventsListView.adapter = adapter

        // Muestra eventos iniciales
        updateEventList()

        // Cuando se selecciona una fecha en el calendario
        calendarView.setOnDateChangedListener { _, date, _ ->
            selectedDate = LocalDate.of(date.year, date.month + 1, date.day)
            updateEventList()
        }

        // Cuando se hace clic en "Añadir actividad"
        addEventButton.setOnClickListener {
            showAddEventDialog()
        }
    }

    private fun updateEventList() {
        selectedDateText.text = "Actividades del día: $selectedDate"
        val events = eventsMap[selectedDate] ?: emptyList()
        adapter.clear()
        adapter.addAll(events)
        adapter.notifyDataSetChanged()
    }

    private fun showAddEventDialog() {
        val input = EditText(this)
        input.hint = "Escribe la actividad"

        AlertDialog.Builder(this)
            .setTitle("Nueva actividad")
            .setView(input)
            .setPositiveButton("Guardar") { _, _ ->
                val text = input.text.toString()
                if (text.isNotBlank()) {
                    val list = eventsMap.getOrPut(selectedDate) { mutableListOf() }
                    list.add(text)
                    updateEventList()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}