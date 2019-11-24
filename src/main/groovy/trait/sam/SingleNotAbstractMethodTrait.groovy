package trait.sam

import groovy.transform.PackageScope

@PackageScope
trait SingleNotAbstractMethodTrait {
    def getName() {
        "name"
    }
}
