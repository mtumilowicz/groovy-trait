package trait.sam

import spock.lang.Specification

class SingleAbstractMethodTraitTest extends Specification {
    def "test getName"() {
        when:
        SingleAbstractMethodTrait t = { "name" }
        
        then:
        t.getName() == "name"
    }
}
