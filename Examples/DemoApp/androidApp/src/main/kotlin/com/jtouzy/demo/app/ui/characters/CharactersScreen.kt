package com.jtouzy.demo.app.ui.characters

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
import androidx.ui.core.Clip
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.res.stringResource
import com.jtouzy.demo.app.R
import com.jtouzy.demo.app.ui.NavigationManager
import com.jtouzy.demo.app.ui.Screen
import com.jtouzy.demo.app.ui.common.ErrorScreen
import com.jtouzy.demo.app.ui.common.LoadingScreen
import com.jtouzy.demo.app.ui.common.VectorImage
import com.jtouzy.demo.app.ui.common.image
import com.jtouzy.demo.ui.characters.CharactersViewState
import com.jtouzy.demo.ui.model.Character

class CharactersScreen(
    private val navigationManager: NavigationManager,
    private val viewModel: CharactersViewModel
) {

    @Composable
    fun MainScreen() {
        viewModel.loadCharacters()
        Column {
            TopAppBar(title = { Text(text = +stringResource(R.string.app_name)) })
            Crossfade(viewModel.viewState) { state ->
                when (state) {
                    CharactersViewState.Loading -> LoadingScreen()
                    is CharactersViewState.Content -> CharacterList(state.characters)
                    CharactersViewState.Error -> ErrorScreen()
                }
            }
        }
    }

    @Composable
    private fun CharacterList(characters: List<Character>) {
        VerticalScroller {
            Column {
                characters.forEach { CharacterItem(it) }
            }
        }
    }

    @Composable
    private fun CharacterItem(character: Character) {
        Ripple(bounded = true) {
            Clickable(onClick = {
                navigationManager.navigateTo(Screen.Quote(character))
            }) {
                Column {
                    Row {
                        Padding(16.dp) {
                            Container(width = 32.dp, height = 32.dp) {
                                Clip(shape = RoundedCornerShape(16.dp)) {
                                    val image = +image(character.imageUrl)
                                    if (null != image) {
                                        DrawImage(image)
                                    } else {
                                        VectorImage(id = R.drawable.ic_baseline_tag_faces_24, tint = Color.Black)
                                    }
                                }
                            }
                        }
                        Text(modifier = Gravity.Center, text = character.name)
                    }
                    Divider(color = (+MaterialTheme.colors()).onBackground)
                }
            }
        }
    }
}
