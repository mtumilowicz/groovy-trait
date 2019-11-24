package trait.superclass

import spock.lang.Specification

class ChildTest extends Specification {
    def 'abc'() {
        def a = new Child()

        expect:
        a.x == 'from Trait'
    }
}
