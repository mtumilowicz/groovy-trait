package trait.ducktyping

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-11-20.
 */
class NamedTest extends Specification {
    def "test getName"() {
        expect:
        new Named(name: "name").name == "name"
    }

    def "test introduce"() {
        expect:
        new Named(name: "name").introduce() == "hi name"
    }
}
