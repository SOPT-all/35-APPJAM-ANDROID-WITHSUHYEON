package com.sopt.withsuhyeon.feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.feature.onboarding.NickNameAuthenticationRoute
import com.sopt.withsuhyeon.feature.onboarding.PhoneNumberAuthenticationRoute
import com.sopt.withsuhyeon.feature.onboarding.TermsOfUseRoute
import com.sopt.withsuhyeon.feature.onboarding.YearOfBirthRoute
import com.sopt.withsuhyeon.core.navigation.Route.TermsOfUse as TermsOfUseRoute
import com.sopt.withsuhyeon.core.navigation.Route.PhoneNumberAuth as PhoneNumberAuthRoute
import com.sopt.withsuhyeon.core.navigation.Route.NickNameAuth as NickNameAuthRoute
import com.sopt.withsuhyeon.core.navigation.Route.SelectYearOfBirth as YearOfBirthRoute
import com.sopt.withsuhyeon.core.navigation.Route.SelectGender as SelectGenderRoute


fun NavController.navigateToTermsOfUse() {
    navigate(TermsOfUseRoute)
}

fun NavController.navigateToPhoneNumberAuth() {
    navigate(PhoneNumberAuthRoute)
}

fun NavController.navigateToNickNameAuth() {
    navigate(NickNameAuthRoute)
}

fun NavController.navigateToSelectYearOfBirth() {
    navigate(YearOfBirthRoute)
}

fun NavController.navigateToSelectGender() {
    navigate(SelectGenderRoute)
}

fun NavGraphBuilder.onBoardingNavGraph(
    onNavigateToPhoneNumberAuth: () -> Unit,
    onNavigateToNickNameAuth: () -> Unit,
    onNavigateToSelectYearOfBirth: () -> Unit,
    onNavigateToSelectGender: () -> Unit
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
    composable<NickNameAuthRoute> {
        NickNameAuthenticationRoute(
            navigateToNext = onNavigateToSelectYearOfBirth
        )
    }
    composable<YearOfBirthRoute> {
        YearOfBirthRoute(
            navigateToNext = onNavigateToSelectYearOfBirth
        )
    }
}