package trait.sam

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-11-20.
 */
class SingleAbstractMethodTraitTest extends Specification {
    def "test getName"() {
        when:
        SingleAbstractMethodTrait t = { "name" }
        
        then:
        t.getName() == "name"
    }
}
