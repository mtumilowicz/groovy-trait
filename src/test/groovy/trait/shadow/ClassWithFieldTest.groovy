package trait.shadow

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-11-20.
 */
class ClassWithFieldTest extends Specification {
    def "field is taken from trait"() {
        given:
        def classWithField = new ClassWithField()
        
        expect:
        classWithField.id == 5
        classWithField.id() == 1
    }
}
