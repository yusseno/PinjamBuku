package com.example.pinjambuku.data

data class OrderUiState(
    val idBuku: Int = 0,        // ID buku yang dipilih untuk pemesanan (default: 0)
    val date: String = "",       // Tanggal pemesanan buku
    val takeOptions: List<String> = listOf()  // Opsi tanggal pemesanan yang tersedia
)
