package trait.birds

import groovy.transform.PackageScope

@PackageScope
trait Flyer {
    def fly() {
        "flying"
    }
}