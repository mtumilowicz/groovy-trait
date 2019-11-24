package trait.ducktyping

import groovy.transform.PackageScope

@PackageScope
class Named implements DuckTypingTrait {
    String name
}
