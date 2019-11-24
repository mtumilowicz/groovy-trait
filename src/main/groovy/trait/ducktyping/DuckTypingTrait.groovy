package trait.ducktyping

trait DuckTypingTrait {
    def introduce() {
        "hi ${getName()}"
    }
}