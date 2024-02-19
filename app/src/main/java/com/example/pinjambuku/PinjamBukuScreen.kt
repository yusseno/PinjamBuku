package com.example.pinjambuku

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pinjambuku.data.DataSource
import com.example.pinjambuku.ui.DescriptionScreen
import com.example.pinjambuku.ui.OrderViewModel
import com.example.pinjambuku.ui.StartOrderScreen
import com.example.pinjambuku.ui.DateScreen
import com.example.pinjambuku.ui.SummaryScreen

// Enum untuk menentukan layar aplikasi dan judulnya
enum class PinjamBukuScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name), // Layar Awal
    Description(title = R.string.description_book), // Layar Deskripsi Buku
    Date(title = R.string.date), // Layar Pemilihan Tanggal
    Summary(title = R.string.summary) // Layar Ringkasan
}

// Komponen AppBar aplikasi "PinjamBuku"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinjamBukuAppBar(
    currentScreen: PinjamBukuScreen, // Layar saat ini
    canNavigateBack: Boolean, // Menunjukkan apakah navigasi ke belakang dimungkinkan
    navigateUp: () -> Unit, // Fungsi untuk menavigasi ke layar sebelumnya
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) }, // Judul AppBar
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer // Warna latar belakang
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button) // Deskripsi ikon kembali
                    )
                }
            }
        }
    )
}

// Komponen utama aplikasi "PinjamBuku"
@Composable
fun PinjamBukuApp(
    viewModel: OrderViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = PinjamBukuScreen.valueOf(
        backStackEntry?.destination?.route ?: PinjamBukuScreen.Start.name
    )

    // Menggunakan Scaffold untuk menyediakan layout dasar aplikasi
    Scaffold(
        topBar = {
            // Menampilkan AppBar dengan judul yang sesuai dengan layar saat ini
            PinjamBukuAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        // Menggunakan NavHost untuk menangani navigasi antar layar
        NavHost(
            navController = navController,
            startDestination = PinjamBukuScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = PinjamBukuScreen.Start.name) {
                // Menampilkan layar awal untuk memulai pesanan baru
                StartOrderScreen(
                    bookOptions = DataSource.bookOptions,
                    onNextButtonClicked = {
                        viewModel.setIdBook(it)
                        navController.navigate(PinjamBukuScreen.Description.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = PinjamBukuScreen.Description.name) {
                // Menampilkan layar deskripsi buku
                DescriptionScreen(
                    idBuku = uiState.idBuku,
                    onNextButtonClicked = { navController.navigate(PinjamBukuScreen.Date.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                )
            }
            composable(route = PinjamBukuScreen.Date.name) {
                // Menampilkan layar untuk pemilihan tanggal peminjaman
                DateScreen(
                    onNextButtonClicked = { navController.navigate(PinjamBukuScreen.Summary.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    options = uiState.takeOptions,
                    onSelectionChanged = { viewModel.setDate(it) },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = PinjamBukuScreen.Summary.name) {
                val context = LocalContext.current
                // Menampilkan layar ringkasan peminjaman
                SummaryScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    onSendButtonClicked = { subject: String, summary: String ->
                        shareOrder(context, subject = subject, summary = summary)
                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}

// Fungsi untuk membatalkan pesanan dan kembali ke layar awal
private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(PinjamBukuScreen.Start.name, inclusive = false)
}

// Fungsi untuk berbagi pesanan menggunakan ACTION_SEND
private fun shareOrder(context: Context, subject: String, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.peminjaman_buku_baru)
        )
    )
}
