package me.ilker.dota2compose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import me.ilker.dota2compose.HeroState
import me.ilker.dota2compose.MainViewModel
import me.ilker.dota2compose.R
import me.ilker.dota2compose.domain.Hero

@Composable
fun HeroesScreen(viewModel: MainViewModel, function: () -> Unit) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    viewModel.getHeroes()

    when (state) {
        is HeroState.Success -> LazyColumn(state = scrollState) {
            items((state as HeroState.Success).heroes) { hero ->
                HeroCard(hero = hero)
            }
        }
        is HeroState.Error -> Toast.makeText(
            LocalContext.current,
            (state as HeroState.Error).error.message,
            Toast.LENGTH_LONG
        ).show()
    }
}

@Composable
fun HeroCard(hero: Hero, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.background)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoilImage(
            data = "https://api.opendota.com".plus(hero.img),
            contentDescription = "image",
            contentScale = ContentScale.Fit,
            modifier = modifier
                .height(64.dp)
                .padding(8.dp),
            loading = {
                Box(modifier.matchParentSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            },
            error = {
                Image(
                    painter = painterResource(R.drawable.ic_error),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        )
        Column(
            modifier = modifier.padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = hero.localizedName,
                fontWeight = FontWeight.Bold
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = hero.primaryAttr,
                    style = TextStyle.Default
                )
            }
        }
    }
}
