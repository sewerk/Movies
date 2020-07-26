# Movie directory sample application
based on <https://www.omdbapi.com/> REST API .

## This project showcase integration of following libraries:
- Jetpack [Architecture Components](https://developer.android.com/topic/libraries/architecture/): ViewModel, LiveData, DataBinding, Navigation, Compose, Room, Paging, Store
- [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html) + Flow
- [Hilt](https://dagger.dev/hilt/)
- Retrofit 2 + Moshi
- Glide
- Timber
- [Junit5](https://junit.org/junit5) + Mockito Kotlin + Kluent
- Gradle Kotlin DSL

## Features:
- allows to search movies by title (saves state)
- displays movies details
- works offline, database is sync in background

## License

    Copyright 2020 Kamil Seweryn

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
