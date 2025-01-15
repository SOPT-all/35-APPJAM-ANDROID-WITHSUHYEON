package com.sopt.withsuhyeon.feature.findsuhyeon.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.KeyStorage.PHONE_CALL
import com.sopt.withsuhyeon.core.util.KeyStorage.TAKE_A_PHOTO
import com.sopt.withsuhyeon.core.util.KeyStorage.VIDEO_CALL

@Composable
fun MultiSelectRequirements(
    modifier: Modifier = Modifier,
    selectList: MutableList<String> = mutableListOf()
) {
    var isSelectPhoto by remember { mutableStateOf(false) }
    var isSelectVideoCall by remember { mutableStateOf(false) }
    var isSelectPhoneCall by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        MultiSelectChip(
            text = TAKE_A_PHOTO,
            isSelected = isSelectPhoto,
            image =  painterResource(R.drawable.dummy_ellipse),
            onClick = {
                isSelectPhoto = !isSelectPhoto
                if(isSelectPhoto)
                    selectList.add(TAKE_A_PHOTO)
                else
                    selectList.remove(TAKE_A_PHOTO)
            }
        )
        MultiSelectChip(
            text = VIDEO_CALL,
            isSelected = isSelectVideoCall,
            image =  painterResource(R.drawable.dummy_ellipse),
            onClick = {
                isSelectVideoCall = !isSelectVideoCall
                if(isSelectPhoto)
                    selectList.add(VIDEO_CALL)
                else
                    selectList.remove(VIDEO_CALL)
            }
        )
        MultiSelectChip(
            text = PHONE_CALL,
            isSelected = isSelectPhoneCall,
            image =  painterResource(R.drawable.dummy_ellipse),
            onClick = {
                isSelectPhoneCall = !isSelectPhoneCall
                if(isSelectPhoto)
                    selectList.add(PHONE_CALL)
                else
                    selectList.remove(PHONE_CALL)
            }
        )
    }
}

@Composable
@Preview
fun PreviewMultiSelectRequirements() {
    MultiSelectRequirements()
}