package com.example.smartstudy.presentation.destinations

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
import com.example.smartstudy.presentation.task.TaskScreenNavArgs
import com.example.smartstudy.presentation.task.TaskScreenRoute
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

public object TaskScreenRouteDestination : TypedDestination<TaskScreenNavArgs> {
    
    override fun invoke(navArgs: TaskScreenNavArgs): Direction = with(navArgs) {
        invoke(taskId, subjectId)
    }
     
    public operator fun invoke(
		taskId: Int?,
		subjectId: Int?,
    ): Direction {
        return Direction(
            route = "$baseRoute" + 
					"?taskId=${DestinationsIntNavType.serializeValue(taskId)}" + 
					"&subjectId=${DestinationsIntNavType.serializeValue(subjectId)}"
        )
    }
    
    @get:RestrictTo(RestrictTo.Scope.SUBCLASSES)
    override val baseRoute: String = "task_screen_route"

    override val route: String = "$baseRoute?taskId={taskId}&subjectId={subjectId}"
    
	override val arguments: List<NamedNavArgument> get() = listOf(
		navArgument("taskId") {
			type = DestinationsIntNavType
			nullable = true
		},
		navArgument("subjectId") {
			type = DestinationsIntNavType
			nullable = true
		}
	)

    @Composable
    override fun DestinationScope<TaskScreenNavArgs>.Content() {
		TaskScreenRoute(
			navigator = destinationsNavigator
		)
    }
                    
	override fun argsFrom(bundle: Bundle?): TaskScreenNavArgs {
	    return TaskScreenNavArgs(
			taskId = DestinationsIntNavType.safeGet(bundle, "taskId"),
			subjectId = DestinationsIntNavType.safeGet(bundle, "subjectId"),
	    )
	}
                
	override fun argsFrom(savedStateHandle: SavedStateHandle): TaskScreenNavArgs {
	    return TaskScreenNavArgs(
			taskId = DestinationsIntNavType.get(savedStateHandle, "taskId"),
			subjectId = DestinationsIntNavType.get(savedStateHandle, "subjectId"),
	    )
	}
}