package com.sopt.withsuhyeon.feature.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.feature.onboarding.FinishRoute
import com.sopt.withsuhyeon.feature.onboarding.GenderSelectRoute
import com.sopt.withsuhyeon.feature.onboarding.NickNameAuthenticationRoute
import com.sopt.withsuhyeon.feature.onboarding.PhoneNumberAuthenticationRoute
import com.sopt.withsuhyeon.feature.onboarding.SelectLocationRoute
import com.sopt.withsuhyeon.feature.onboarding.SelectProfileRoute
import com.sopt.withsuhyeon.feature.onboarding.TermsOfUseRoute
import com.sopt.withsuhyeon.feature.onboarding.YearOfBirthRoute
import com.sopt.withsuhyeon.core.navigation.Route.TermsOfUse as TermsOfUseRoute
import com.sopt.withsuhyeon.core.navigation.Route.PhoneNumberAuth as PhoneNumberAuthRoute
import com.sopt.withsuhyeon.core.navigation.Route.NickNameAuth as NickNameAuthRoute
import com.sopt.withsuhyeon.core.navigation.Route.SelectYearOfBirth as YearOfBirthRoute
import com.sopt.withsuhyeon.core.navigation.Route.SelectGender as SelectGenderRoute
import com.sopt.withsuhyeon.core.navigation.Route.PostProfileImage as PostProfileImageRoute
import com.sopt.withsuhyeon.core.navigation.Route.SelectLocation as SelectLocationRoute
import com.sopt.withsuhyeon.core.navigation.Route.OnboardingFinish as OnboardingFinishRoute
import com.sopt.withsuhyeon.core.navigation.MainTabRoute.Home as HomeRoute


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

fun NavController.navigateToPostProfileImage() {
    navigate(PostProfileImageRoute)
}

fun NavController.navigateToSelectLocation() {
    navigate(SelectLocationRoute)
}

fun NavController.navigateToOnboardingFinish() {
    navigate(OnboardingFinishRoute)
}

fun NavController.navigateToHome() {
    navigate(HomeRoute)
}

fun NavGraphBuilder.onBoardingNavGraph(
    padding: PaddingValues,
    onNavigateToPhoneNumberAuth: () -> Unit,
    onNavigateToNickNameAuth: () -> Unit,
    onNavigateToSelectYearOfBirth: () -> Unit,
    onNavigateToSelectGender: () -> Unit,
    onNavigateToPostProfileImage: () -> Unit,
    onNavigateToSelectLocation: () -> Unit,
    onNavigateToFinish: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    composable<TermsOfUseRoute> {
        TermsOfUseRoute(
            navigateToNext = onNavigateToPhoneNumberAuth,
            padding = padding
        )
    }
    composable<PhoneNumberAuthRoute> {
        PhoneNumberAuthenticationRoute(
            navigateToNext = onNavigateToNickNameAuth,
            padding = padding
        )
    }
    composable<NickNameAuthRoute> {
        NickNameAuthenticationRoute(
            navigateToNext = onNavigateToSelectYearOfBirth,
            padding = padding
        )
    }
    composable<YearOfBirthRoute> {
        YearOfBirthRoute(
            navigateToNext = onNavigateToSelectGender,
            padding = padding
        )
    }
    composable<SelectGenderRoute> {
        GenderSelectRoute(
            navigateToNext = onNavigateToPostProfileImage,
            padding = padding
        )
    }
    composable<PostProfileImageRoute> {
        SelectProfileRoute(
            navigateToNext = onNavigateToSelectLocation,
            padding = padding
        )
    }
    composable<SelectLocationRoute> {
        SelectLocationRoute(
            navigateToNext = onNavigateToFinish,
            padding = padding
        )
    }
    composable<OnboardingFinishRoute> {
        FinishRoute(
            navigateToNext = onNavigateToHome,
            padding = padding
        )
    }
}