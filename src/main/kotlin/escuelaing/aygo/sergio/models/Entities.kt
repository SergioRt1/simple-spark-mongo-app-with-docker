package escuelaing.aygo.sergio.models

import escuelaing.aygo.sergio.annotations.NoArgsConstructor
import java.util.*

@NoArgsConstructor
data class Record(
    var word: String,
    var date: Date
)