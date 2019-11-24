package trait.ducktyping

import spock.lang.Specification

class DuckTypingTest extends Specification {
    def "test getName"() {
        expect:
        new Named(name: "name").name == "name"
    }

    def "test introduce"() {
        expect:
        new Named(name: "name").introduce() == "hi name"
    }
}
