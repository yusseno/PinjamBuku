package com.example.pinjambuku.ui

import androidx.lifecycle.ViewModel
import com.example.pinjambuku.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OrderViewModel : ViewModel() {

    // StateFlow yang menyimpan status UI dari pesanan
    private val _uiState = MutableStateFlow(OrderUiState(takeOptions = takeOptions()))
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    // Fungsi untuk mengatur ID buku pada status UI
    fun setIdBook(id: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                idBuku = id, // Mengubah ID buku pada status UI
            )
        }
    }

    // Fungsi untuk mengatur tanggal pada status UI
    fun setDate(takepDate: String) {
        _uiState.update { currentState ->
            currentState.copy(
                date = takepDate, // Mengubah tanggal pada status UI
            )
        }
    }

    // Fungsi untuk mengatur ulang pesanan (mengembalikan ke nilai awal)
    fun resetOrder() {
        _uiState.value = OrderUiState(takeOptions = takeOptions()) // Mengatur ulang status UI
    }

    // Fungsi untuk menghasilkan opsi tanggal pengambilan
    private fun takeOptions(): List<String> {
        val dateOptions = mutableListOf<String>()
        val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID")) // Format tanggal
        val calendar = Calendar.getInstance() // Instance dari kalendar

        // Tambahkan tanggal saat ini.
        calendar.add(Calendar.DATE, 7) // Menambahkan 7 hari untuk melompati seminggu.
        dateOptions.add(formatter.format(calendar.time) + " (1 minggu)")

        // Tambahkan 3 minggu berikutnya (total 4 minggu).
        repeat(3) {
            calendar.add(Calendar.DATE, 7) // Menambahkan 7 hari untuk melompati seminggu.
            dateOptions.add(formatter.format(calendar.time) + " (${it + 2} minggu)")
        }

        return dateOptions // Mengembalikan opsi tanggal
    }
}