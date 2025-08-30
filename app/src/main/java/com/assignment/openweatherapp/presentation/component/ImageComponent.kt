package presentations.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
public fun ImageComponent(modifier: Modifier, imageRes: Int, colorFilter: ColorFilter? = null, contentScale: ContentScale = ContentScale.Crop) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "Image Component",
            contentScale = contentScale,
            modifier = modifier,
            colorFilter = colorFilter
        )
}