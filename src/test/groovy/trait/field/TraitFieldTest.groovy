package trait.field

import spock.lang.Specification

class TraitFieldTest extends Specification {
    def "get field from trait"() {
        expect:
        new C().trait_field_TraitWithField__id == 5
        new C().id == 5
    }
}
