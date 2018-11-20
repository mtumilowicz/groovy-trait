package trait.field

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-11-20.
 */
class TraitFieldTest extends Specification {
    def "get field from trait"() {
        expect:
        new C().trait_field_TraitWithField__id == 5
        new C().id == 5
    }
}
