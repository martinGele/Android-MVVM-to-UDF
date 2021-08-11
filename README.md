# Refactoring an MVVM based project with Fragments into an UDF architecture with Composables

The example of this application is cut in three parts which are switched trhu branches(master, initial_refactoring_to_compose, fully_refactoring_to_compose). 

On the master branch the initial state of the applicaiton is presented with MVMM architecture and with use of Hilt, Retrofit, JetpackNavigation.

The second branch is "initial_refactoring_to_compose" here used cases like Fragments and JetpackNavigation with navigation graph is still in use onyl the layouts are not in used for the fragment as they are replaced with composables.

And the last branch fully_refactoring_to_compsoe Fragments are no longer in use they are replaced with composables and the navigation with navigation graph is no longer in use.
