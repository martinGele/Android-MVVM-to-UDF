package com.martin.samplecompose.feature.detailpokemon

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.coil.rememberCoilPainter
import com.martin.catchemall.data.remote.response.Pokemon
import com.martin.samplecompose.R
import com.martin.samplecompose.ui.CircularProgressBar
import com.martin.samplecompose.ui.ErrorScreen
import com.martin.samplecompose.util.Resource
import java.util.*

@Composable
fun PokemonDetailScreen(pokemonName: String, viewModel: DetailPokemonViewModel = hiltViewModel()) {

    val pokemonInfoState = produceState<Resource<Pokemon>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemonInfo(pokemonName.lowercase())
    }

    val pokemonInfo by remember{
      pokemonInfoState
    }

    when (pokemonInfo) {
        is Resource.Success -> {
            if (pokemonInfo.data != null) {
                DetailScreen(pokemonInfo.data!!)
            }
        }
        is Resource.Loading -> {
            CircularProgressBar()
        }

        is Resource.Error -> {
            ErrorScreen()
        }
    }

}

@Composable
private fun DetailScreen(data: Pokemon) {
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    DetailHeader(
                        pokemon = data,
                        containerHeight = this@BoxWithConstraints.maxHeight
                    )
                    DetailContent(pokemon = data, this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
    Row(modifier = Modifier.fillMaxSize()) {

    }
}

@Composable
private fun DetailHeader(
    pokemon: Pokemon,
    containerHeight: Dp
) {
    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth(),
        painter = rememberCoilPainter(
            request = pokemon.sprites.frontShiny,
            previewPlaceholder = R.drawable.image_placeholder
        ),
        contentScale = ContentScale.Crop,
        contentDescription = null,

        )
}

@Composable
private fun DetailContent(pokemon: Pokemon, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Name(pokemon)
        Text(
            text = stringResource(R.string.abilities),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = pokemon.abilities,
                itemContent = {
                    ProfileProperty(it.ability.name)
                }
            )
        }
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun Name(
    pokemon: Pokemon
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Name(
            pokemon = pokemon,
            modifier = Modifier.height(32.dp)
        )
    }
}

@Composable
private fun Name(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Text(
        text = pokemon.name.replaceFirstChar { it.uppercaseChar() },
        modifier = modifier,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold,

    )
}

@Composable
fun ProfileProperty(value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Divider()
        val style = if (isLink) {
            MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary)
        } else {
            MaterialTheme.typography.body1
        }
        Text(
            text = value,
            modifier = Modifier.height(24.dp),
            style = style
        )
    }
}

