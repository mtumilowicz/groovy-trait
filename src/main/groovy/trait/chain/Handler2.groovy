package trait.chain

trait Handler2 {
    def handle(String message) {
        if (message.contains("Handler2")) {
            return "Handler2"
        }
        return super.handle(message)
    }
}
