package com.passgen.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.passgen.ui.ui.theme.PassGenUITheme
import com.passgen.ui.ui.theme.SansFonts
import com.passgen.ui.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PassGenUITheme {
                Column {
                    HeaderUI()
                    FieldView()
                }
            }
        }
    }

    @Preview
    @Composable
    fun HeaderUI() {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
        ) {
            Icon(
                Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(8.dp)
                    .size(28.dp)
            )
            Text(
                text = "Password Generator",
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(
                    fontFamily = SansFonts,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            )
        }
    }

    @Preview
    @Composable
    fun FieldView() {
        Card(
            shape = Shapes.large,
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier.padding(16.dp)
        ) {
            ConstraintLayout {
                val (passwordText, line, timeText, btnGen, btnCopy) = createRefs()
                Text(
                    text = "PASSWORD5477!@",
                    color = MaterialTheme.colors.onSecondary,
                    style = TextStyle(
                        fontFamily = SansFonts,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 60.dp,
                            bottom = 60.dp
                        )
                        .fillMaxWidth()
                        .constrainAs(passwordText) {
                            top.linkTo(parent.top)
                        },
                    textAlign = TextAlign.Center
                )
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier
                        .constrainAs(line) {
                            top.linkTo(passwordText.bottom)
                        }
                        .fillMaxWidth()
                )
                Text(
                    text = "It will take 5 months to crack!",
                    color = Color.Gray,
                    style = TextStyle(
                        fontFamily = SansFonts,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .padding(14.dp)
                        .constrainAs(timeText) {
                            top.linkTo(line.bottom)
                        }
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 12.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    ),
                    modifier = Modifier.constrainAs(btnGen){
                        top.linkTo(timeText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(btnCopy.start)
                        width = Dimension.fillToConstraints
                    }.padding(8.dp),
                    shape = Shapes.medium
                ) {
                    Icon(
                        Icons.Rounded.Build,
                        contentDescription = "Generate",
                        Modifier.size(24.dp)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        text = "Generate",
                        style = TextStyle(
                            fontFamily = SansFonts,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        ),
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 12.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    ),
                    modifier = Modifier.constrainAs(btnCopy){
                        top.linkTo(btnGen.top)
                        start.linkTo(btnGen.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }.padding(8.dp),
                    shape = Shapes.medium
                ) {
                    Icon(
                        Icons.Rounded.Clear,
                        contentDescription = "Copy",
                        Modifier.size(24.dp)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        text = "Copy",
                        style = TextStyle(
                            fontFamily = SansFonts,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        ),
                    )
                }
            }
        }
    }
}