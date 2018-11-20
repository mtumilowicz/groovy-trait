package trait.chain

/**
 * Created by mtumilowicz on 2018-11-20.
 */
trait Handler3 {
    def handle(String message) {
        if (message.contains("Handler3")) {
            return "Handler3"
        }
        return super.handle(message)
    }
}
