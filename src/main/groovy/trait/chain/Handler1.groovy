package trait.chain

/**
 * Created by mtumilowicz on 2018-11-20.
 */
trait Handler1 {
    def handle(String message) {
        if (message.contains("Handler1")) {
            return "Handler1"
        }
        return super.handle(message)
    }
}
