package trait.shadow

import groovy.transform.PackageScope

@PackageScope
class ClassWithField implements TraitWithField {
    int id = 5
}
