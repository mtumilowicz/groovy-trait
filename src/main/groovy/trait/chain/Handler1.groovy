package trait.chain

import groovy.transform.PackageScope

@PackageScope
trait Handler1 {
    def handle(String message) {
        if (message.contains("Handler1")) {
            return "Handler1"
        }
        return super.handle(message)
    }
}
