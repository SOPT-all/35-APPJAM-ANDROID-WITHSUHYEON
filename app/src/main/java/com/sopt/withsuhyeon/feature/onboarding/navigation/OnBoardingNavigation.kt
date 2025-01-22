package com.sopt.withsuhyeon.feature.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.feature.onboarding.FinishRoute
import com.sopt.withsuhyeon.feature.onboarding.GenderSelectRoute
import com.sopt.withsuhyeon.feature.onboarding.LoginRoute
import com.sopt.withsuhyeon.feature.onboarding.NickNameAuthenticationRoute
import com.sopt.withsuhyeon.feature.onboarding.OnBoardingRoute
import com.sopt.withsuhyeon.feature.onboarding.PhoneNumberAuthenticationRoute
import com.sopt.withsuhyeon.feature.onboarding.SelectLocationRoute
import com.sopt.withsuhyeon.feature.onboarding.SelectProfileRoute
import com.sopt.withsuhyeon.feature.onboarding.TermsOfUseRoute
import com.sopt.withsuhyeon.feature.onboarding.YearOfBirthRoute
import com.sopt.withsuhyeon.feature.onboarding.viewmodel.SignUpViewModel
import com.sopt.withsuhyeon.core.navigation.Route.OnBoarding as OnBoardingRoute
import com.sopt.withsuhyeon.core.navigation.Route.TermsOfUse as TermsOfUseRoute
import com.sopt.withsuhyeon.core.navigation.Route.PhoneNumberAuth as PhoneNumberAuthRoute
import com.sopt.withsuhyeon.core.navigation.Route.NickNameAuth as NickNameAuthRoute
import com.sopt.withsuhyeon.core.navigation.Route.SelectYearOfBirth as YearOfBirthRoute
import com.sopt.withsuhyeon.core.navigation.Route.SelectGender as SelectGenderRoute
import com.sopt.withsuhyeon.core.navigation.Route.PostProfileImage as PostProfileImageRoute
import com.sopt.withsuhyeon.core.navigation.Route.SelectLocation as SelectLocationRoute
import com.sopt.withsuhyeon.core.navigation.Route.OnboardingFinish as OnboardingFinishRoute
import com.sopt.withsuhyeon.core.navigation.MainTabRoute.Home as HomeRoute
import com.sopt.withsuhyeon.core.navigation.Route.Login as LoginRoute

fun NavController.navigateToSignUp() {
    navigate(TermsOfUseRoute)
}

fun NavController.navigateToLogin() {
    navigate(LoginRoute)
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
    onBoardingPadding: PaddingValues,
    padding: PaddingValues,
    onNavigateToLogin: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onNavigateToPhoneNumberAuth: () -> Unit,
    onNavigateToNickNameAuth: () -> Unit,
    onNavigateToSelectYearOfBirth: () -> Unit,
    onNavigateToSelectGender: () -> Unit,
    onNavigateToPostProfileImage: () -> Unit,
    onNavigateToSelectLocation: () -> Unit,
    onNavigateToFinish: () -> Unit,
    onNavigateToHome: () -> Unit,
    getBackStackUploadViewModel: @Composable (NavBackStackEntry) -> SignUpViewModel
) {
    composable<OnBoardingRoute> {
        OnBoardingRoute(
            padding = onBoardingPadding,
            navigateToSignUp = onNavigateToSignUp,
            navigateToLogin = onNavigateToLogin,
        )
    }

    composable<TermsOfUseRoute> {
        TermsOfUseRoute(
            navigateToNext = onNavigateToPhoneNumberAuth,
            padding = padding,
            viewModel = hiltViewModel()
        )
    }
    composable<PhoneNumberAuthRoute> {
        PhoneNumberAuthenticationRoute(
            navigateToNext = onNavigateToNickNameAuth,
            padding = padding,
            viewModel = getBackStackUploadViewModel(it)
        )
    }
    composable<NickNameAuthRoute> {
        NickNameAuthenticationRoute(
            navigateToNext = onNavigateToSelectYearOfBirth,
            padding = padding,
            viewModel = getBackStackUploadViewModel(it)
        )
    }
    composable<YearOfBirthRoute> {
        YearOfBirthRoute(
            navigateToNext = onNavigateToSelectGender,
            padding = padding,
            viewModel = getBackStackUploadViewModel(it)
        )
    }
    composable<SelectGenderRoute> {
        GenderSelectRoute(
            navigateToNext = onNavigateToPostProfileImage,
            padding = padding,
            viewModel = getBackStackUploadViewModel(it)
        )
    }
    composable<PostProfileImageRoute> {
        SelectProfileRoute(
            navigateToNext = onNavigateToSelectLocation,
            padding = padding,
            viewModel = getBackStackUploadViewModel(it)
        )
    }
    composable<SelectLocationRoute> {
        SelectLocationRoute(
            navigateToNext = onNavigateToFinish,
            padding = padding,
            viewModel = getBackStackUploadViewModel(it)
        )
    }
    composable<OnboardingFinishRoute> {
        FinishRoute(
            navigateToNext = onNavigateToHome,
            padding = padding,
            viewModel = getBackStackUploadViewModel(it)
        )
    }
    composable<LoginRoute> {
        LoginRoute(
            navigateToLoginFinish = onNavigateToFinish,
            padding = padding
        )
    }
}