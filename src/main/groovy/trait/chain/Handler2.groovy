package trait.chain

/**
 * Created by mtumilowicz on 2018-11-20.
 */
trait Handler2 {
    def handle(String message) {
        if (message.contains("Handler2")) {
            return "Handler2"
        }
        return super.handle(message)
    }
}
