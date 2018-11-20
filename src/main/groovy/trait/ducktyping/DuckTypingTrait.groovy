package trait.ducktyping

/**
 * Created by mtumilowicz on 2018-11-20.
 */
trait DuckTypingTrait {
    def introduce() {
        "hi ${getName()}"
    }
}