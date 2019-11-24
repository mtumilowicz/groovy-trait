package trait.birds

import groovy.transform.PackageScope

@PackageScope
trait Swimmer {
    def swim() {
        "swimming"
    }
}