package com.example.marvelheros

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter


data class Hero(
    val name: String,
    val logo: String,
    val picture: String,
    val comment: String)

public var HeroList = listOf(Hero("DeadPool", "https://w0.peakpx.com/wallpaper/136/513/HD-wallpaper-deadpool-logo-marvel.jpg",
    "https://w0.peakpx.com/wallpaper/903/975/HD-wallpaper-deadpool-hero-man-thumbnail.jpg", "With great power, comes great irresponsibility.")
    ,Hero("Iron Man", "https://w0.peakpx.com/wallpaper/233/583/HD-wallpaper-the-ironman-iron-man-logo-marvel.jpg",
        "https://w0.peakpx.com/wallpaper/933/680/HD-wallpaper-iron-man-iron-man-avengers-endgame-marvel-comics-marvel-superheroes-superheroes-marvel-infinity-gauntlet-thumbnail.jpg",
        "I'm Iron Man"),
    Hero("Spider Man", "https://w0.peakpx.com/wallpaper/51/380/HD-wallpaper-spiderman-logo-spider.jpg",
        "https://w0.peakpx.com/wallpaper/977/682/HD-wallpaper-spiderman-spider-man-marvel-movie-film-hero-thumbnail.jpg",
        "No matter what I do... no matter how hard I try... the ones I love will always be the ones who pay"),
    Hero("Captain America", "https://w0.peakpx.com/wallpaper/545/793/HD-wallpaper-captain-america.jpg",
        "https://w0.peakpx.com/wallpaper/976/366/HD-wallpaper-captain-america-art-avengers-civil-war-fantasy-hero-marvel.jpg",
        "I can do this all day"),
    Hero("Black Panther", "https://w0.peakpx.com/wallpaper/28/243/HD-wallpaper-black-panther-avengers-dark-infinity-logo-marvel-war.jpg",
        "https://w0.peakpx.com/wallpaper/435/396/HD-wallpaper-black-panther-7-avengers-black-hero-marvellous-panther-super.jpg", "Wakanda Forever!"))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }

    }


    @SuppressLint("UnrememberedMutableState")
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ListOfHerose() {
        val LazyState = rememberLazyListState()
        val snapBehaviour = rememberSnapFlingBehavior(lazyListState = LazyState)

        LazyRow(state = LazyState, flingBehavior = snapBehaviour) {
            items(HeroList) { hero ->
                Column(
                    Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    var isClicked by mutableStateOf(false)
                    Box(Modifier.size(700.dp), contentAlignment = Alignment.BottomCenter) {
                        IconButton(onClick = {isClicked = true}, Modifier.fillMaxSize()) {
                            if (isClicked) {
                                setContent {
                                    HeroCard(hero = hero)
                                }
                                isClicked = false }
                            }
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = hero.logo, imageLoader = ImageLoader(
                                        LocalContext.current
                                    )
                                ),
                                contentDescription = "hero logo",
                                modifier = Modifier.size(700.dp), alignment = Alignment.Center
                            )
                        }
                        Text(
                            text = hero.name, fontFamily = FontFamily.Monospace,
                            fontSize = 32.sp, color = Color.White, textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

    @Composable
    fun MainScreen() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.untitled),
                contentDescription = "Marvel LOGO"
            )
            Text(
                text = "CHOOSE YOUR HERO",
                color = Color.White,
                fontSize = 38.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            ListOfHerose()
        }
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun HeroCard(hero: Hero) {
        var isClicked by mutableStateOf(false)
        Image(
            painter = rememberAsyncImagePainter(
                model = hero.picture, imageLoader = ImageLoader(
                    LocalContext.current
                )
            ),
            contentDescription = "hero image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(), alignment = Alignment.Center)

        IconButton(onClick = { isClicked = true }, modifier = Modifier.size(50.dp)) {
            Icon(Icons.Default.Close, contentDescription = "", tint = Color.White)
            if (isClicked) {
                setContent {
                    MainScreen()
                }
                isClicked = false
            }
        }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
            Column {
                    Text(
                        text = hero.name, fontFamily = FontFamily.Monospace,
                        fontSize = 32.sp, color = Color.White, fontWeight = FontWeight.Black
                    )
                    Text(
                        text = hero.comment, fontFamily = FontFamily.Monospace,
                        fontSize = 25.sp, color = Color.White, fontWeight = FontWeight.Black
                )
            }

        }
    }
}