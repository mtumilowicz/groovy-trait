package trait.shadow

import groovy.transform.PackageScope

@PackageScope
trait TraitWithField {
    int id = 1
    
    def id(){
        id
    }
}