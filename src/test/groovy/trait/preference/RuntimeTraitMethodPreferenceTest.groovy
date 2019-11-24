package trait.preference

import spock.lang.Specification 

class RuntimeTraitMethodPreferenceTest extends Specification {
    def "runtime trait preference"() {
        given:
        def c = new C()

        when:
        def newC = c as RuntimeTrait1

        then:
        newC.get() == "RuntimeTrait1"
    }
}
