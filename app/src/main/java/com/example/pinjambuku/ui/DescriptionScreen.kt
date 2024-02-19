package com.example.pinjambuku.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pinjambuku.R
import com.example.pinjambuku.data.Book
import com.example.pinjambuku.data.DataSource
import com.example.pinjambuku.ui.theme.PinjamBukuTheme

@Composable
fun DescriptionScreen(
    idBuku: Int, // ID buku yang akan ditampilkan deskripsinya
    onCancelButtonClicked: () -> Unit = {}, // Fungsi yang dipanggil saat tombol "Cancel" ditekan
    onNextButtonClicked: () -> Unit = {}, // Fungsi yang dipanggil saat tombol "Next" ditekan
    modifier: Modifier = Modifier // Modifier untuk mengatur tata letak
) {
    // Mencari buku berdasarkan ID
    val book: Book? = DataSource.bookOptions.find { it.id == idBuku }

    Column(
        modifier = modifier, // Mengatur modifier untuk tata letak
    ) {
        if (book != null) {
            Spacer(modifier = Modifier.height(40.dp)) // Spasi vertikal sebelum gambar buku
            Image(
                painter = painterResource(id = book.imageId), // Gambar buku
                contentDescription = "Book cover", // Deskripsi konten gambar
                modifier = Modifier
                    .width(180.dp)
                    .height(240.dp)
                    .align(Alignment.CenterHorizontally) // Mengatur gambar di tengah secara horizontal
            )
            // Spacer
            Spacer(modifier = Modifier.height(20.dp)) // Spasi vertikal setelah gambar

            // Menampilkan judul buku
            Text(
                text = book.title, // Judul buku
                style = MaterialTheme.typography.bodyLarge, // Gaya teks untuk judul
                color = Color.Black.copy(alpha = 0.8f), // Warna teks
                fontWeight = FontWeight.Bold, // Berat teks
                modifier = Modifier.padding(
                    horizontal = 30.dp,
                    vertical = 6.dp
                ), // Padding untuk judul
            )
            // Menampilkan penulis buku
            Text(
                text = "Penulis : ${book.author}", // Informasi penulis buku
                style = MaterialTheme.typography.bodyMedium, // Gaya teks untuk penulis
                color = Color.Black.copy(alpha = 0.8f), // Warna teks
                modifier = Modifier.padding(
                    horizontal = 30.dp,
                    vertical = 4.dp
                ), // Padding untuk informasi penulis
            )
            // Menampilkan label "Deskripsi"
            Text(
                text = "Deskripsi :", // Label untuk deskripsi
                style = MaterialTheme.typography.bodySmall, // Gaya teks untuk label
                color = Color.Black.copy(alpha = 0.8f), // Warna teks
                fontWeight = FontWeight.Bold, // Berat teks
                modifier = Modifier.padding(
                    horizontal = 30.dp,
                    vertical = 4.dp
                ), // Padding untuk label
            )
            // Menampilkan deskripsi buku dengan paragraf rata kanan kiri
            Text(
                text = buildAnnotatedString {
                    withStyle(style = ParagraphStyle(textAlign = TextAlign.Justify)) {
                        append("   " + book.description) // Deskripsi buku
                    }
                },
                style = MaterialTheme.typography.bodySmall, // Gaya teks untuk deskripsi
                color = Color.Black.copy(alpha = 0.8f), // Warna teks
                modifier = Modifier.padding(
                    horizontal = 30.dp,
                    vertical = 4.dp,
                ) // Padding untuk deskripsi
            )

            Spacer(modifier = Modifier.height(20.dp)) // Spasi vertikal setelah deskripsi
        } else {
            // Menampilkan pesan jika buku tidak ditemukan berdasarkan ID
            Text(text = "Buku dengan ID $idBuku tidak ditemukan.")
        }
    }
    // Tombol "Cancel" dan "Next" di bagian paling bawah
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium)), // Mengatur padding untuk tombol
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)), // Mengatur jarak antar tombol
        verticalAlignment = Alignment.Bottom // Mengatur posisi tombol secara vertikal
    ) {
        OutlinedButton(
            modifier = Modifier.weight(1f), // Mengatur berat tombol
            onClick = onCancelButtonClicked // Menentukan fungsi yang dipanggil saat tombol "Cancel" ditekan
        ) {
            Text(stringResource(R.string.cancel)) // Teks untuk tombol "Cancel"
        }
        Button(
            modifier = Modifier.weight(1f), // Mengatur berat tombol
            onClick = onNextButtonClicked // Menentukan fungsi yang dipanggil saat tombol "Next" ditekan
        ) {
            Text(stringResource(R.string.next)) // Teks untuk tombol "Next"
        }
    }
}


@Preview(showBackground = true, widthDp = 420, heightDp = 1000)
@Composable
fun SelectOptionPreview() {
    PinjamBukuTheme {
        DescriptionScreen(
            idBuku = 1,
        )
    }
}