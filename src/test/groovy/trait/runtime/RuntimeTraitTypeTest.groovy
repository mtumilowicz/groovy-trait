package trait.runtime

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-11-20.
 */
class RuntimeTraitTypeTest extends Specification {
    def "runtime trait - type"() {
        given:
        def c = new C()
        
        when:
        def newC = c as RuntimeTrait1
        
        then:
        newC instanceof I1
        newC instanceof RuntimeTrait1
        !(newC instanceof C)
    }

    def "runtime multiple traits"() {
        given:
        def c = new C()

        when:
        def newC = c.withTraits RuntimeTrait1, RuntimeTrait2

        then:
        newC instanceof RuntimeTrait1
        newC instanceof RuntimeTrait2
    }
}
