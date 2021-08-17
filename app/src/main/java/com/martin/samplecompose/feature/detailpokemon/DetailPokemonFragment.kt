package com.martin.samplecompose.feature.detailpokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.fragment.navArgs
import com.google.accompanist.coil.rememberCoilPainter
import com.martin.catchemall.data.remote.response.Pokemon
import com.martin.samplecompose.R
import com.martin.samplecompose.ui.CircularProgressBar
import com.martin.samplecompose.ui.ErrorScreen
import com.martin.samplecompose.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPokemonFragment : Fragment(R.layout.detail_pokemon_fragment) {

    private val args: DetailPokemonFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext())
        view.apply {
            setContent {
                PokemonDetailScreen(pokemonName = args.pokemonName)
            }
            return view
        }
    }
}

@Composable
fun PokemonDetailScreen(pokemonName: String, viewModel: DetailPokemonViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()

    viewModel.loadSinglePokemon(pokemonName = pokemonName.lowercase())
    /**
     * here we are converting LiveData to State through observeAsState
     */
    val pokemonInfoState = viewModel.getSinglePokemon().observeAsState(initial = Resource.Loading())
    val pokemonInfo by remember {
        pokemonInfoState
    }

    when (pokemonInfo) {
        is Resource.Success -> {
            if (pokemonInfo.data != null) {
                DetailScreen(scrollState, pokemonInfo.data!!)
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
private fun DetailScreen(scrollState: ScrollState, data: Pokemon) {
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    DetailHeader(
                        scrollState = scrollState,
                        pokemon = data,
                        containerHeight = this@BoxWithConstraints.maxHeight
                    )
                    DetailContent(pokemon = data, this@BoxWithConstraints.maxHeight)
                }

            }
        }
    }
}

@Composable
private fun DetailHeader(
    scrollState: ScrollState,
    pokemon: Pokemon,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
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
        pokemon.abilities.forEach {
            ProfileProperty(stringResource(R.string.abilities), it.ability.name)
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
        text = pokemon.name,
        modifier = modifier,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ProfileProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier.height(24.dp),
                style = MaterialTheme.typography.caption,
            )
        }
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
