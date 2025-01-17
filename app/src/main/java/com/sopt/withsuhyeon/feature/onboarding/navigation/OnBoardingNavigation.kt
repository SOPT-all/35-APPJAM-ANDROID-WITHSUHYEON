package com.sopt.withsuhyeon.feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.feature.onboarding.PhoneNumberAuthenticationRoute
import com.sopt.withsuhyeon.feature.onboarding.TermsOfUseRoute
import com.sopt.withsuhyeon.core.navigation.Route.TermsOfUse as TermsOfUseRoute
import com.sopt.withsuhyeon.core.navigation.Route.PhoneNumberAuth as PhoneNumberAuthRoute
import com.sopt.withsuhyeon.core.navigation.Route.NickNameAuth as NickNameAuthRoute

fun NavController.navigateToTermsOfUse() {
    navigate(TermsOfUseRoute)
}
fun NavController.navigateToPhoneNumberAuth() {
    navigate(PhoneNumberAuthRoute)
}

fun NavController.navigateToNickNameAuth() {
    navigate(NickNameAuthRoute)
}

fun NavGraphBuilder.onBoardingNavGraph(
    onNavigateToPhoneNumberAuth: () -> Unit,
    onNavigateToNickNameAuth: () -> Unit
) {
    composable<TermsOfUseRoute> {
        TermsOfUseRoute(
            navigateToNext = onNavigateToPhoneNumberAuth
        )
    }
    composable<PhoneNumberAuthRoute> {
        PhoneNumberAuthenticationRoute(
            navigateToNext = onNavigateToNickNameAuth
        )
    }
}