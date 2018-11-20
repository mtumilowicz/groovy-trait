package trait.field

import spock.lang.Specification
import trait.shadow.ClassWithField

/**
 * Created by mtumilowicz on 2018-11-20.
 */
class ShadowingFieldTest extends Specification {
    def "field is taken from trait"() {
        given:
        def classWithField = new ClassWithField()
        
        expect:
        classWithField.id == 5
        classWithField.id() == 1
    }
}
