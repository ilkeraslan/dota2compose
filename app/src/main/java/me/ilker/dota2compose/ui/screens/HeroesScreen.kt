package me.ilker.dota2compose.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import me.ilker.dota2compose.HeroState
import me.ilker.dota2compose.MainViewModel
import me.ilker.dota2compose.R
import me.ilker.dota2compose.domain.Hero

@ExperimentalMaterialApi
@Composable
fun HeroesScreen(viewModel: MainViewModel, function: () -> Unit) {
    val state by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    viewModel.getHeroes()

    when (state) {
        is HeroState.Success -> LazyColumn(modifier = Modifier.padding(bottom = 60.dp)) {
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

@ExperimentalMaterialApi
@Composable
fun HeroCard(hero: Hero, modifier: Modifier = Modifier) {
    val painter = rememberCoilPainter(
        request = "https://api.opendota.com".plus(hero.img),
        requestBuilder = {
            transformations(CircleCropTransformation())
        }
    )
    Card(onClick = { Log.i("CLICK", "OK") }) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.background)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (painter.loadState) {
                is ImageLoadState.Loading -> CircularProgressIndicator(Modifier.align(CenterVertically))
                is ImageLoadState.Error -> Image(
                    painter = painterResource(R.drawable.ic_error),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                is ImageLoadState.Success -> Image(
                    painter = painter,
                    contentDescription = "Hero image"
                )
            }

            Column(
                modifier = modifier.padding(start = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = hero.localizedName ?: "",
                    fontWeight = FontWeight.Bold
                )
                Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Icon")
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = hero.primaryAttr ?: "",
                        style = TextStyle.Default
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun HeroCardPreview() {
    HeroCard(
        hero = Hero(
            primaryAttr = "Melee",
            localizedName = "Drow Ranger",
            attackType = "Melee",
            baseAgi = 3,
            baseArmor = null,
            name = "Drow Ranger",
            baseAttackMax = null,
            baseAttackMin = null,
            baseHealth = null,
            baseInt = null,
            baseMana = null,
            baseMr = null,
            baseStr = null,
            cmEnabled = false,
            heroId = 1,
            icon = null,
            id = 1,
            img = null,
            legs = 2,
            moveSpeed = 300,
            projectileSpeed = null,
            roles = listOf("Carry")
        )
    )
}
