package trait.runtime.preference

import spock.lang.Specification 
/**
 * Created by mtumilowicz on 2018-11-20.
 */
class CTest extends Specification {
    def "runtime trait preference"() {
        given:
        def c = new C()

        when:
        def newC = c as RuntimeTrait1

        then:
        newC.get() == "RuntimeTrait1"
    }
}
