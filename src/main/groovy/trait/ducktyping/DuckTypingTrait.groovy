package trait.ducktyping

import groovy.transform.PackageScope

@PackageScope
trait DuckTypingTrait {
    def introduce() {
        "hi ${getName()}"
    }
}