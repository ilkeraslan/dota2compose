package me.ilker.dota2compose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import me.ilker.dota2compose.DOTA_API
import me.ilker.dota2compose.R
import me.ilker.dota2compose.model.domain.Hero
import me.ilker.dota2compose.presenter.HeroesState

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun HeroesScreen(
    heroState: HeroesState = HeroesState.Empty,
    requestReload: () -> Unit
) {
    when (heroState) {
        HeroesState.Empty -> requestReload()

        is HeroesState.Error -> Toast.makeText(
            LocalContext.current,
            heroState.error.message,
            Toast.LENGTH_LONG
        ).show()

        is HeroesState.Loaded -> LazyColumn(
            modifier = Modifier
        ) {
            items(heroState.heroes) { hero ->
                HeroCard(hero = hero)
            }
        }

        HeroesState.Loading -> Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun HeroCard(
    modifier: Modifier = Modifier,
    hero: Hero,
    painter: AsyncImagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data = DOTA_API.plus(hero.img))
            .crossfade(true)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.logo)
            .size(coil.size.Size(128, 128))
            .build()
    )
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp)
            .background(Color.LightGray, RoundedCornerShape(16.dp)),
        backgroundColor = MaterialTheme.colors.background,
        onClick = { /* no-op */ }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            when (painter.state) {
                AsyncImagePainter.State.Empty -> Image(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    painter = painter,
                    contentDescription = "Empty hero image"
                )

                is AsyncImagePainter.State.Loading -> CircularProgressIndicator(
                    Modifier.align(
                        CenterVertically
                    )
                )

                is AsyncImagePainter.State.Success -> Image(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    painter = painter,
                    contentDescription = "Hero image"
                )

                is AsyncImagePainter.State.Error -> Image(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    painter = painterResource(R.drawable.ic_error),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Error image"
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = hero.localizedName ?: "",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = hero.primaryAttr ?: "",
                    style = TextStyle.Default
                )
            }
        }
    }
}

@ExperimentalCoilApi
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
