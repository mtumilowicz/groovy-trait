package trait.conflict

import spock.lang.Specification

class CustomClassTest extends Specification {
    
    def "get method is taken from trait"() {
        expect:
        new CustomClass().get() == "Trait1"
    }
}
