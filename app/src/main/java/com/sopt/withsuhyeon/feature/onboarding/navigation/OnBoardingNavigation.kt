package com.sopt.withsuhyeon.feature.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.feature.onboarding.FinishLoginRoute
import com.sopt.withsuhyeon.feature.onboarding.FinishSignUpRoute
import com.sopt.withsuhyeon.feature.onboarding.GenderSelectRoute
import com.sopt.withsuhyeon.feature.onboarding.LoginRoute
import com.sopt.withsuhyeon.feature.onboarding.NickNameAuthenticationRoute
import com.sopt.withsuhyeon.feature.onboarding.OnBoardingRoute
import com.sopt.withsuhyeon.feature.onboarding.PhoneNumberAuthenticationRoute
import com.sopt.withsuhyeon.feature.onboarding.SelectLocationRoute
import com.sopt.withsuhyeon.feature.onboarding.SelectProfileRoute
import com.sopt.withsuhyeon.feature.onboarding.TermsOfUseRoute
import com.sopt.withsuhyeon.feature.onboarding.YearOfBirthRoute
import com.sopt.withsuhyeon.feature.onboarding.viewmodel.LoginViewModel
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

fun NavController.navigateToLoginFinish() {
    navigate(Route.LoginFinish)
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
    onNavigateToSignUpFinish: () -> Unit,
    onNavigateToLoginFinish: () -> Unit,
    onNavigateToHome: () -> Unit,
    getBackStackSignUpViewModel: @Composable (NavBackStackEntry) -> SignUpViewModel,
    getBackStackLoginViewModel: @Composable (NavBackStackEntry) -> LoginViewModel
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
            viewModel = getBackStackSignUpViewModel(it)
        )
    }
    composable<PhoneNumberAuthRoute> {
        PhoneNumberAuthenticationRoute(
            navigateToNext = onNavigateToNickNameAuth,
            padding = padding,
            viewModel = getBackStackSignUpViewModel(it)
        )
    }
    composable<NickNameAuthRoute> {
        NickNameAuthenticationRoute(
            navigateToNext = onNavigateToSelectYearOfBirth,
            padding = padding,
            viewModel = getBackStackSignUpViewModel(it)
        )
    }
    composable<YearOfBirthRoute> {
        YearOfBirthRoute(
            navigateToNext = onNavigateToSelectGender,
            padding = padding,
            viewModel = getBackStackSignUpViewModel(it)
        )
    }
    composable<SelectGenderRoute> {
        GenderSelectRoute(
            navigateToNext = onNavigateToPostProfileImage,
            padding = padding,
            viewModel = getBackStackSignUpViewModel(it)
        )
    }
    composable<PostProfileImageRoute> {
        SelectProfileRoute(
            navigateToNext = onNavigateToSelectLocation,
            padding = padding,
            viewModel = getBackStackSignUpViewModel(it)
        )
    }
    composable<SelectLocationRoute> {
        SelectLocationRoute(
            navigateToNext = onNavigateToSignUpFinish,
            padding = padding,
            viewModel = getBackStackSignUpViewModel(it)
        )
    }
    composable<OnboardingFinishRoute> {
        FinishSignUpRoute(
            navigateToOnLogin = onNavigateToLogin,
            padding = padding,
            viewModel = getBackStackSignUpViewModel(it)
        )
    }
    composable<LoginRoute> {
        LoginRoute(
            navigateToLoginFinish = onNavigateToLoginFinish,
            padding = padding,
        )
    }
    composable<Route.LoginFinish> {
        FinishLoginRoute(
            padding = padding,
            navigateToNext = onNavigateToHome,
            viewModel = getBackStackLoginViewModel(it)
        )
    }
}