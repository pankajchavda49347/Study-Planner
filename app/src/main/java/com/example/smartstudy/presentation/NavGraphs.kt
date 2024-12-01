package com.example.smartstudy.presentation

import com.example.smartstudy.presentation.destinations.DashboardScreenRouteDestination
import com.example.smartstudy.presentation.destinations.SessionScreenRouteDestination
import com.example.smartstudy.presentation.destinations.SubjectScreenRouteDestination
import com.example.smartstudy.presentation.destinations.TaskScreenRouteDestination
import com.example.smartstudy.presentation.destinations.*
import com.ramcosta.composedestinations.spec.*

/**
 * Class generated if any Composable is annotated with `@Destination`.
 * It aggregates all [TypedDestination]s in their [NavGraph]s.
 */
public object NavGraphs {

    public val root: NavGraph = NavGraph(
        route = "root",
        startRoute = DashboardScreenRouteDestination,
        destinations = listOf(
            DashboardScreenRouteDestination,
			SessionScreenRouteDestination,
			SubjectScreenRouteDestination,
			TaskScreenRouteDestination
        )
    )
}