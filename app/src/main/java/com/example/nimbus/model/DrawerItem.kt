package com.example.nimbus.model

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.nimbus.R

data class DrawerItem(
    val title: String,
    val selectedIcon: @Composable () -> Unit,
    val unselectedIcon: @Composable () -> Unit,
    val onClick: (() -> Unit?)? = null
)

@Composable
fun getDrawerItems(): List<DrawerItem> {
    return listOf(
        // home
        DrawerItem(
            title = stringResource(id = R.string.home),
            selectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.house_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.orange_500),
                    modifier = Modifier.size(40.dp)
                )
            },
            unselectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.house_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.gray_400),
                    modifier = Modifier.size(40.dp)
                )
            }

        ),

        // notificações
        DrawerItem(
            title = stringResource(id = R.string.notification),
            selectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.bell_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.orange_500),
                    modifier = Modifier.size(40.dp)
                )
            },
            unselectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.bell_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.gray_400),
                    modifier = Modifier.size(40.dp)
                )
            }

        ),

        // time
        DrawerItem(
            title = stringResource(id = R.string.team),
            selectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.team_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.orange_500),
                    modifier = Modifier.size(40.dp)
                )
            },
            unselectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.team_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.gray_400),
                    modifier = Modifier.size(40.dp)
                )
            }

        ),

        // eventos
        DrawerItem(
            title = stringResource(id = R.string.events),
            selectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.calender_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.orange_500),
                    modifier = Modifier.size(40.dp)
                )
            },
            unselectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.calender_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.gray_400),
                    modifier = Modifier.size(40.dp)
                )
            }

        ),

        // chat
        DrawerItem(
            title = stringResource(id = R.string.chat),
            selectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.chat_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.orange_500),
                    modifier = Modifier.size(40.dp)
                )
            },
            unselectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.chat_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.gray_400),
                    modifier = Modifier.size(40.dp)
                )
            }

        ),

        // trocar time
        DrawerItem(
            title = stringResource(id = R.string.change_team),
            selectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.change_team_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.orange_500),
                    modifier = Modifier.size(40.dp)
                )
            },
            unselectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.change_team_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.gray_400),
                    modifier = Modifier.size(40.dp)
                )
            }

        ),

        // conta
        DrawerItem(
            title = stringResource(id = R.string.account),
            selectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.user_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.orange_500),
                    modifier = Modifier.size(40.dp)
                )
            },
            unselectedIcon =
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.user_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.gray_400),
                    modifier = Modifier.size(40.dp)
                )
            }

        )
    )
}
