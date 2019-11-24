package trait.sam

import groovy.transform.PackageScope

@PackageScope
trait SingleAbstractMethodTrait {
    def other() {}

    abstract def getName()
}