package com.karsoft.weatherappwithcompose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.karsoft.weatherappwithcompose.R
import com.karsoft.weatherappwithcompose.data.WeatherModel
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun MainCard() {
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.Magenta,
            elevation = 0.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                        text = "23 August 2023 17:20",
                        style = TextStyle(fontSize = 15.sp),
                        color = Color.White
                    )
                    AsyncImage(
                        model = "https://cdn.weatherapi.com/weather/64x64/day/113.png",
                        contentDescription = "im2",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(top = 3.dp, end = 8.dp)
                    )
                }
                Text(
                    text = "Nukus",
                    style = TextStyle(fontSize = 24.sp),
                    color = Color.White
                )
                Text(
                    text = "23°",
                    style = TextStyle(fontSize = 64.sp),
                    color = Color.White
                )
                Text(
                    text = "Sunny",
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "im3",
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "23°C/12ºC",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.White
                    )
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sync),
                            contentDescription = "im4",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout() {
    val tabList = listOf("HOURS", "DAYS")
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier
        .padding(5.dp)
        .clip(RoundedCornerShape(5.dp))) {
        TabRow(
            selectedTabIndex = 0,
            indicator = { pos ->
                        TabRowDefaults.Indicator(
                            Modifier.pagerTabIndicatorOffset(pagerState, pos)
                        )
            },
            backgroundColor = Color.Magenta,
            contentColor = Color.White
        ) {
            tabList.forEachIndexed { index, text ->
                Tab(
                    selected = false,
                    onClick = {
                              coroutineScope.launch {
                                  pagerState.animateScrollToPage(index)
                              }
                    },
                    text = {
                        Text(text = text, color = Color.White)
                    }
                )
            }
        }
        HorizontalPager(
            count = tabList.size,
        state = pagerState,
        modifier = Modifier.weight(1.0f)) {
            index ->
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(
                    listOf(
                        WeatherModel(
                            "Nukus",
                            "10:22",
                            "28ºC",
                            "Sunny",
                            "//cdn.weatherapi.com/weather/64x64/day/113.png",
                            "36ºC",
                            "9ºC",
                            "11:00"
                        ),
                        WeatherModel(
                            "Nukus",
                            "10:22",
                            "40ºC",
                            "Sunny",
                            "//cdn.weatherapi.com/weather/64x64/day/113.png",
                            "45ºC",
                            "9ºC",
                            "19:00"
                        ),
                        WeatherModel(
                            "Nukus",
                            "10:22",
                            "",
                            "Sunny",
                            "//cdn.weatherapi.com/weather/64x64/day/113.png",
                            "36ºC",
                            "9ºC",
                            "24.08.2023"
                        )
                    )
                ){
                    _, item -> ListItem(item = item)
                }
            }
        }
    }
}