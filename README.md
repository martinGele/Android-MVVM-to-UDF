# Refactoring an MVVM based project with Fragments into a UDF architecture with Compostables

The example of this application is cut in three parts which are switched thru branches(master, initial_refactoring_to_compose, fully_refactoring_to_compose). 

## first branch

On the **master** branch the initial state of the application is presented with MVMM architecture and with the use of Hilt, Retrofit, JetpackNavigation.

## second branch

The second branch is **initial_refactoring_to_compose** here used cases like Fragments and JetpackNavigation with navigation graph are still in use only the layouts are not in use for the fragment as they are replaced with composable.

## third branch

And the last branch **fully_refactoring_to_compose** Fragments are no longer in use they are replaced with composable and the navigation with navigation graph is no longer in use.

## The classes and files that are no longer in use after fully refactoring:

- ListPokemonFragment.kt
- DetailPokemonFragment.kt
- PokemonAdapter.kt
- PokemonViewHolder.kt
- activity_main.xml
- detail_pokemon_fragment.xml
- list_pokemon_fragment.xml
- pokemon_item.xml
- navigation.xml

