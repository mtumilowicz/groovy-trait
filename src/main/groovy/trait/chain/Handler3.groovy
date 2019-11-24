package trait.chain

trait Handler3 {
    def handle(String message) {
        if (message.contains("Handler3")) {
            return "Handler3"
        }
        return super.handle(message)
    }
}
