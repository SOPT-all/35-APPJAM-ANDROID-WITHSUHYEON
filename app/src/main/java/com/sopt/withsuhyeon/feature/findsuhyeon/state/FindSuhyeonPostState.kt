package com.sopt.withsuhyeon.feature.findsuhyeon.state

import com.sopt.withsuhyeon.domain.entity.ChatRoomInfoModel
import com.sopt.withsuhyeon.domain.entity.FindSuhyeonPostDetailModel
import com.sopt.withsuhyeon.domain.entity.PostDetailInfoModel

data class FindSuhyeonPostState(
    val postDetailData: FindSuhyeonPostDetailModel =
        FindSuhyeonPostDetailModel(
            title = "",
            content = "",
            nickname = "",
            createdAt = "",
            profileImage = "",
            price = 0,
            owner = true,
            matching = true,
            isExpired = true,
            postDetailInfo = PostDetailInfoModel(
                region = "",
                gender = true,
                age = "",
                date = "",
                requests = emptyList()
            ),
            chatRoomInfoPost = ChatRoomInfoModel(
                postId = 0,
                ownerId = 0,
                writerId = 0,
                ownerChatRoomId = "",
                peerChatRoomId = ""
            )
        ),
    val isDeleteBottomSheetVisible: Boolean = false,
    val isDeleteAlertModalVisible: Boolean = false
)