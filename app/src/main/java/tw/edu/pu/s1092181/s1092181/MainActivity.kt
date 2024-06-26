package tw.edu.pu.s1092181.s1092181

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tw.edu.pu.s1092181.s1092181.ui.theme.S1092181Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S1092181Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val context = LocalContext.current  // 取得App的運行環境
    var buttonText by remember { mutableStateOf("作者: 資管系余汶芯") }

    Column(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                text = "簡介",
                color = Color.Blue,
                modifier = modifier
            )
            Image(
                painter = painterResource(id = R.drawable.service),
                contentDescription = null
            )
            Button(
                onClick = { buttonText = "作者: 資管系余汶芯" },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = buttonText)
            }
        }
    }
}


@Composable
fun SecondScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "主要機構",
            color = Color.Red,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                // Handle navigation to 台中愛心家園
            }) {
                Text(text = "台中愛心家園")
            }

            Button(onClick = {
                // Handle navigation to 瑪麗亞學園
            }) {
                Text(text = "瑪麗亞學園")
            }
        }

        Text(
            text = "「瑪利亞學園」提供重度以及極重度多重障礙者日間照顧服務，以健康照護為基礎，支持生活多面向參與及學習概念，輔助發展重度身心障礙者自我概念為最終服務目標。",
            color = Color.Black
        )
        Text(
            text = "\n"
        )
        Text(
            text = "雙擊以下圖片，可以觀看瑪利亞學園地圖",
            color = Color.Blue
        )
        Image(
            painter = painterResource(id = R.drawable.campus),
            contentDescription = null
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val navController = rememberNavController()
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }

    Column {
        androidx.compose.material3.TopAppBar(
            title = { Image(
                painter = painterResource(id = R.drawable.maria),
                contentDescription = null
            ) },
            navigationIcon = {
                IconButton(onClick = {
                    Toast.makeText(context, "您點選了導覽圖示", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Default.Menu, contentDescription = "Navigation icon")
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        Toast.makeText(context, "作者：余汶芯", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Icon(Icons.Rounded.AccountBox, contentDescription = "Author")
                }
                IconButton(
                    onClick = { showMenu = true }
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                }
                DropdownMenu(
                    expanded = showMenu, onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("簡介") },
                        onClick = {
                            showMenu = false
                            navController.navigate("JumpFirst")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("主要機構") },
                        onClick = {
                            showMenu = false
                            navController.navigate("JumpSecond")
                        }
                    )
                }
            }
        )

        NavHost(navController = navController, startDestination = "JumpFirst") {
            composable("JumpFirst") {
                FirstScreen(navController = navController)
            }
            composable("JumpSecond") {
                SecondScreen(navController = navController)
            }
        }
    }
}
