package trait.sam

import org.codehaus.groovy.runtime.typehandling.GroovyCastException
import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-11-20.
 */
class SingleNotAbstractMethodTraitTest extends Specification {
    def "test getName"() {
        when:
        SingleNotAbstractMethodTrait a = { "A" }
        
        then:
        thrown(GroovyCastException)
    }
}
