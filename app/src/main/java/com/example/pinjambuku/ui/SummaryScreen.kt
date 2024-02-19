package com.example.pinjambuku.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pinjambuku.R
import com.example.pinjambuku.data.Book
import com.example.pinjambuku.data.DataSource
import com.example.pinjambuku.data.OrderUiState
import com.example.pinjambuku.ui.theme.PinjamBukuTheme

@Composable
fun SummaryScreen(
    orderUiState: OrderUiState, // State dari pesanan yang ditampilkan pada layar
    onCancelButtonClicked: () -> Unit, // Fungsi yang dipanggil saat tombol "Cancel" ditekan
    onSendButtonClicked: (String, String) -> Unit, // Fungsi yang dipanggil saat tombol "Send" ditekan dengan pesan dan ringkasan pesanan sebagai parameter
    modifier: Modifier = Modifier // Modifier untuk mengatur tata letak
) {
    var newOrder by remember { mutableStateOf("") } // Variabel untuk menyimpan pesanan baru
    var orderSummary by remember { mutableStateOf("") } // Variabel untuk menyimpan ringkasan pesanan

    Column(
        modifier = modifier, // Mengatur modifier untuk tata letak
        verticalArrangement = Arrangement.SpaceBetween // Mengatur penataan vertikal
    ) {
        val book: Book? = DataSource.bookOptions.find { it.id == orderUiState.idBuku } // Mencari buku yang sesuai dengan ID pesanan

        Column {
            Spacer(modifier = Modifier.height(30.dp)) // Spasi vertikal antara elemen

            Text(
                text = "Judul Buku", // Label untuk judul buku
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 24.dp, end = 24.dp) // Mengatur padding untuk teks judul buku
            )

            if (book != null) {
                Text(
                    text = book.title, // Menampilkan judul buku
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                    ),
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp) // Mengatur padding untuk teks judul buku
                )
            }
            Spacer(modifier = Modifier.height(30.dp)) // Spasi vertikal antara elemen

            Text(
                text = "Durasi Peminjaman", // Label untuk durasi peminjaman
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 24.dp, end = 24.dp) // Mengatur padding untuk teks durasi peminjaman
            )

            Text(
                text = orderUiState.date, // Menampilkan durasi peminjaman
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(start = 24.dp, end = 24.dp) // Mengatur padding untuk teks durasi peminjaman
            )
        }

        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)) // Mengatur padding untuk baris tombol
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)) // Mengatur jarak antara tombol secara vertikal
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(), // Mengatur lebar tombol
                    onClick = {
                        if (book != null) {
                            orderSummary = book.title + ", " + orderUiState.date // Menyiapkan ringkasan pesanan
                        }
                        onSendButtonClicked(newOrder, orderSummary) // Memanggil fungsi saat tombol "Send" ditekan dengan pesan dan ringkasan pesanan sebagai parameter
                    }
                ) {
                    Text(stringResource(R.string.send)) // Teks untuk tombol "Send"
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(), // Mengatur lebar tombol
                    onClick = onCancelButtonClicked // Menentukan fungsi yang dipanggil saat tombol "Cancel" ditekan
                ) {
                    Text(stringResource(R.string.cancel)) // Teks untuk tombol "Cancel"
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 420, heightDp = 1000)
@Composable
fun SummaryScreenPreview() {
    PinjamBukuTheme {
        SummaryScreen(
            orderUiState = OrderUiState(1, "Test"),
            onSendButtonClicked = { subject: String, summary: String -> },
            onCancelButtonClicked = {},
            modifier = Modifier.fillMaxHeight()
        )
    }
}