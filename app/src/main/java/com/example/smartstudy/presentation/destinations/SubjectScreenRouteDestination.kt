package com.example.smartstudy.presentation.destinations

import SubjectScreenNavArgs
import SubjectScreenRoute
import android.os.Bundle
import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
//import com.example.smartstudy.presentation.subject.SubjectScreenNavArgs
//import com.example.smartstudy.presentation.subject.SubjectScreenRoute
import com.ramcosta.composedestinations.navargs.primitives.DestinationsIntNavType
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.DestinationDependenciesContainer
import com.ramcosta.composedestinations.scope.DestinationScope
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.DestinationStyle
import com.ramcosta.composedestinations.spec.Direction
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

public object SubjectScreenRouteDestination : TypedDestination<SubjectScreenNavArgs> {
    
    override fun invoke(navArgs: SubjectScreenNavArgs): Direction = with(navArgs) {
        invoke(subjectId)
    }
     
    public operator fun invoke(
		subjectId: Int,
    ): Direction {
        return Direction(
            route = "$baseRoute" + 
					"/${DestinationsIntNavType.serializeValue(subjectId)}"
        )
    }
    
    @get:RestrictTo(RestrictTo.Scope.SUBCLASSES)
    override val baseRoute: String = "subject_screen_route"

    override val route: String = "$baseRoute/{subjectId}"
    
	override val arguments: List<NamedNavArgument> get() = listOf(
		navArgument("subjectId") {
			type = DestinationsIntNavType
		}
	)

    @Composable
    override fun DestinationScope<SubjectScreenNavArgs>.Content() {
		SubjectScreenRoute(
			navigator = destinationsNavigator
		)
    }
                    
	override fun argsFrom(bundle: Bundle?): SubjectScreenNavArgs {
	    return SubjectScreenNavArgs(
			subjectId = DestinationsIntNavType.safeGet(bundle, "subjectId") ?: throw RuntimeException("'subjectId' argument is mandatory, but was not present!"),
	    )
	}
                
	override fun argsFrom(savedStateHandle: SavedStateHandle): SubjectScreenNavArgs {
	    return SubjectScreenNavArgs(
			subjectId = DestinationsIntNavType.get(savedStateHandle, "subjectId") ?: throw RuntimeException("'subjectId' argument is mandatory, but was not present!"),
	    )
	}
}