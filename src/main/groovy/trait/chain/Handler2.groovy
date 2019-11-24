package trait.chain

import groovy.transform.PackageScope

@PackageScope
trait Handler2 {
    def handle(String message) {
        if (message.contains("Handler2")) {
            return "Handler2"
        }
        return super.handle(message)
    }
}
