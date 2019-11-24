package trait.superclass

import groovy.transform.PackageScope

@PackageScope
trait Trait {
    def getX() {
        "from Trait"
    }
}