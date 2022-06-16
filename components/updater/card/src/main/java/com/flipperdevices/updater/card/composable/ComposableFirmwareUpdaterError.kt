package com.flipperdevices.updater.card.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flipperdevices.core.ui.ktx.painterResourceByKey
import com.flipperdevices.core.ui.res.R as DesignSystem
import com.flipperdevices.updater.card.R
import com.flipperdevices.updater.model.UpdateErrorType

@Composable
fun ComposableFirmwareUpdaterError(
    typeError: UpdateErrorType,
    retryUpdate: () -> Unit = {}
) {
    val title = stringResource(id = getTitleByUpdateError(typeError))
    val description = stringResource(id = getDescriptionByUpdateError(typeError))
    val image = painterResourceByKey(id = getImageByUpdateError(typeError))

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = title
        )
        Text(
            modifier = Modifier.padding(top = 4.dp, start = 12.dp, end = 12.dp),
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            color = colorResource(DesignSystem.color.black_100),
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(top = 2.dp, start = 12.dp, end = 12.dp),
            text = description,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            color = colorResource(DesignSystem.color.black_30),
            textAlign = TextAlign.Center
        )
    }

    Text(
        modifier = Modifier
            .clickable(
                indication = rememberRipple(),
                onClick = retryUpdate,
                interactionSource = remember { MutableInteractionSource() }
            )
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 8.dp),
        text = stringResource(R.string.updater_card_updater_error_retry),
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        color = colorResource(DesignSystem.color.accent_secondary)
    )
}

@StringRes
fun getTitleByUpdateError(type: UpdateErrorType): Int {
    return when (type) {
        UpdateErrorType.NO_INTERNET -> R.string.update_card_error_no_internet_title
        UpdateErrorType.UNABLE_TO_SERVER -> R.string.update_card_error_server_request_title
        UpdateErrorType.NO_SD_CARD -> R.string.update_card_error_no_sd_title
    }
}

@StringRes
fun getDescriptionByUpdateError(type: UpdateErrorType): Int {
    return when (type) {
        UpdateErrorType.NO_INTERNET -> R.string.update_card_error_no_internet_desc
        UpdateErrorType.UNABLE_TO_SERVER -> R.string.update_card_error_server_request_desc
        UpdateErrorType.NO_SD_CARD -> R.string.update_card_error_no_sd_desc
    }
}

@DrawableRes
fun getImageByUpdateError(type: UpdateErrorType): Int {
    return when (type) {
        UpdateErrorType.NO_INTERNET -> R.drawable.ic_no_internet
        UpdateErrorType.UNABLE_TO_SERVER -> R.drawable.ic_server_error
        UpdateErrorType.NO_SD_CARD -> R.drawable.ic_no_sd
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ComposableFirmwareUpdaterErrorPreview() {
    Column {
        ComposableFirmwareUpdaterError(UpdateErrorType.NO_INTERNET)
        ComposableFirmwareUpdaterError(UpdateErrorType.UNABLE_TO_SERVER)
        ComposableFirmwareUpdaterError(UpdateErrorType.NO_SD_CARD)
    }
}