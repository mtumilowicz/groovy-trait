package trait.sam

import org.codehaus.groovy.runtime.typehandling.GroovyCastException
import spock.lang.Specification

class SingleNotAbstractMethodTraitTest extends Specification {
    def "test getName"() {
        when:
        SingleNotAbstractMethodTrait a = { "A" }
        
        then:
        thrown(GroovyCastException)
    }
}
