package com.tayyabbsse51f22s087re.nexusfood


import androidx.navigation.NavOptions
import androidx.navigation.navOptions



object NavigationUtils {
    val fadeNavOptions = navOptions {
        anim {
            enter = R.anim.fade_in
            exit = R.anim.fade_out
            popEnter = R.anim.fade_in
            popExit = R.anim.fade_out
        }
        // Optional: only pop splash on onboarding flows
        // popUpTo(R.id.splashFragment) { inclusive = true }
    }


    val slideNavOptions = navOptions {
        anim {
            enter = R.anim.enter
            exit = R.anim.exit
            popEnter = R.anim.enter
            popExit = R.anim.exit
        }
    }

    fun createFadeOptionsWithPopUp(popUpToFragmentId: Int, inclusive: Boolean = true): NavOptions {
        return navOptions {
            anim {
                enter = R.anim.fade_in
                exit = R.anim.fade_out
                popEnter = R.anim.fade_in
                popExit = R.anim.fade_out
            }
            popUpTo(popUpToFragmentId) { this.inclusive = inclusive }
        }
    }
}