package com.example.pinjambuku.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pinjambuku.R
import com.example.pinjambuku.data.Book
import com.example.pinjambuku.data.DataSource
import com.example.pinjambuku.ui.theme.PinjamBukuTheme

// Komponen untuk menampilkan layar memulai pesanan
@Composable
fun StartOrderScreen(
    bookOptions: List<Book>, // Opsi buku yang tersedia untuk dipilih
    onNextButtonClicked: (Int) -> Unit, // Fungsi yang dipanggil saat tombol lanjut ditekan dengan ID buku terpilih
    modifier: Modifier = Modifier
) {
    // Column sebagai kontainer utama untuk tata letak
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween // Menata antar elemen secara vertikal dengan ruang di antara mereka
    ) {
        // Column untuk bagian header
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceBetween // Menata antar elemen secara vertikal dengan ruang di antara mereka
        ) {
            // Column untuk label pemilihan buku
            Column(
                modifier = Modifier.fillMaxWidth(), // Mengisi lebar maksimum
                horizontalAlignment = Alignment.CenterHorizontally, // Pusatkan elemen secara horizontal
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)) // Menata antar elemen secara vertikal dengan jarak yang ditentukan
            ) {
                Text(text = "Pilih buku:") // Teks label pemilihan buku
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small))) // Spasi vertikal
            }
            // Column untuk daftar buku yang tersedia
            Column(
                modifier = Modifier.fillMaxWidth(), // Mengisi lebar maksimum
                horizontalAlignment = Alignment.CenterHorizontally, // Pusatkan elemen secara horizontal
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)) // Menata antar elemen secara vertikal dengan jarak yang ditentukan
            ) {
                // Iterasi melalui opsi buku dan tampilkan tombol untuk setiap buku
                bookOptions.forEach { book ->
                    SelectBookButton(
                        title =  book.title, // Judul buku
                        author= book.author, // Penulis buku
                        imageId =  book.imageId, // ID gambar buku
                        onClick = { onNextButtonClicked(book.id) }, // Fungsi yang dipanggil saat tombol buku dipilih
                        modifier = Modifier.fillMaxWidth(), // Mengisi lebar maksimum
                    )
                }
            }
        }
    }
}

// Komponen tombol untuk memilih buku
@Composable
fun SelectBookButton(
    title: String, // Judul buku
    author: String, // Penulis buku
    imageId: Int, // ID gambar buku
    onClick: () -> Unit, // Fungsi yang dipanggil saat tombol ditekan
    modifier: Modifier = Modifier
) {
    // Tombol untuk memilih buku dengan gambar, judul, dan penulis
    Button(
        onClick = onClick, // Fungsi yang dipanggil saat tombol ditekan
        modifier = modifier.widthIn(min = 250.dp), // Lebar tombol dengan batas minimum
        shape = RoundedCornerShape(8.dp), // Bentuk tombol dengan sudut melengkung
    ) {
        // Baris untuk menampilkan gambar buku, judul, dan penulis
        Row(
            verticalAlignment = Alignment.CenterVertically, // Pusatkan elemen secara vertikal
            modifier = Modifier.padding(16.dp) // Padding untuk jarak dari tepi tombol
        ) {
            // Gambar buku
            Image(
                painter = painterResource(id = imageId), // Gambar buku berdasarkan ID
                contentDescription = "Book cover", // Deskripsi konten gambar
                modifier = Modifier.size(80.dp) // Ukuran gambar
            )
            Spacer(modifier = Modifier.width(16.dp)) // Spasi horizontal
            Column {
                // Judul buku dengan gaya teks bold
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                )
                // Penulis buku dengan gaya teks medium dan warna abu-abu
                Text(
                    text = author,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                )
            }
        }
    }
}



@Preview(showBackground = true, widthDp = 420, heightDp = 1000)
@Composable
fun StartOrderPreview() {
//    PinjamBukuTheme {
//        StartOrderScreen(
//            bookOptions = DataSource.bookOptions,
//            onNextButtonClicked = {},
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(dimensionResource(R.dimen.padding_medium))
//        )
//    }
}
