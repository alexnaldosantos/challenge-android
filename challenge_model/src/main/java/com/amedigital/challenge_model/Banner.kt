package com.amedigital.challenge_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Banner(
    val id: Long,
    val urlImagem: String,
    val linkUrl: String
) : Parcelable

val fakeBanners = listOf(
    Banner(
        id = 1,
        urlImagem = "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png",
        linkUrl = "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png"
    ),
    Banner(
        id = 2,
        urlImagem = "https://images-submarino.b2w.io/spacey/2017/02/06/DESTAQUE_FULL_CARTAO_CASA_FEV.png",
        linkUrl = "https://images-submarino.b2w.io/spacey/2017/02/06/DESTAQUE_FULL_CARTAO_CASA_FEV.png"
    ),
    Banner(
        id = 3,
        urlImagem = "https://images-submarino.b2w.io/spacey/2017/02/03/sub-home-dest-full-655x328-touch-play.png",
        linkUrl = "https://images-submarino.b2w.io/spacey/2017/02/03/sub-home-dest-full-655x328-touch-play.png"
    )
)