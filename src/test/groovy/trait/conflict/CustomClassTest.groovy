package trait.conflict

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-11-19.
 */
class CustomClassTest extends Specification {
    
    def "get method is taken from trait"() {
        expect:
        new CustomClass().get() == "Trait1"
    }
}
