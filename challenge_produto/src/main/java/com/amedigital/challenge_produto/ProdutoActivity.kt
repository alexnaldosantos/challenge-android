package com.amedigital.challenge_produto

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.amedigital.challenge_model.Produto
import com.amedigital.challenge_produto.widgets.TextValorDe
import com.amedigital.challenge_produto.widgets.TextValorPor
import com.amedigital.coreui.R
import com.amedigital.coreui.views.BaseActivity
import com.amedigital.coreui.widgets.TopBarNavigator

class ProdutoActivity : BaseActivity() {

    companion object {
        const val PARAM_PRODUTO = "produto"

        fun gotoProduto(context: Context, produto: Produto) {
            val intent = Intent(context, ProdutoActivity::class.java)
                .putExtra(PARAM_PRODUTO, produto)
            context.startActivity(intent)
        }
    }

    private val produto: Produto
        get() = intent.getParcelableExtra(PARAM_PRODUTO)!!

    @Composable
    override fun activityContent() {
        val scrollState = rememberScrollState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(256.dp),
                model = produto.urlImagem,
                contentDescription = produto.nome,
                fallback = painterResource(R.drawable.ic_broken_image),
            )
            Text(
                text = produto.nome,
                modifier = Modifier
                    .padding(8.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Divider()
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                TextValorDe(produto.precoDe)
                Spacer(modifier = Modifier.width(16.dp))
                TextValorPor(produto.precoPor)
            }
            Divider()
            Text(
                text = produto.descricao,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }

    @Composable
    override fun topBar() {
        return TopBarNavigator(
            title = "",
            backgroundColor = MaterialTheme.colors.background
        ) {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    @Composable
    override fun floatingActionButton() {
        val reservedDialogState = remember { mutableStateOf(false) }
        FloatingActionButton(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            onClick = { reserveProduto(reservedDialogState) }
        ) {
            Icon(painterResource(id = R.drawable.check_button), "")
        }
        showProdutoReserved(reservedDialogState = reservedDialogState)
    }

    private fun reserveProduto(reservedDialogState: MutableState<Boolean>) {
        //TODO: Reservar o produto
        reservedDialogState.value = true
    }

    @Composable
    private fun showProdutoReserved(reservedDialogState: MutableState<Boolean>) {
        if (reservedDialogState.value) {
            AlertDialog(
                onDismissRequest = {
                    reservedDialogState.value = false
                },
                text = {
                    Text(text = "Produto reservado com sucesso!")
                },
                buttons = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Text(
                            "OK",
                            modifier = Modifier
                                .padding(end = 32.dp, bottom = 16.dp)
                                .clickable { reservedDialogState.value = false },
                            color = MaterialTheme.colors.primaryVariant,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    }
}