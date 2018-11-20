package trait.multiinheritance

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-11-20.
 */
class CTest extends Specification {
    def "test getFromT1"() {
        expect:
        new C().getFromT1() == "T1"
    }

    def "test getFromT2"() {
        expect:
        new C().getFromT2() == "T2"
    }
}
