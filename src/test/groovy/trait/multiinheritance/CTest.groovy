package trait.multiinheritance

import spock.lang.Specification

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
