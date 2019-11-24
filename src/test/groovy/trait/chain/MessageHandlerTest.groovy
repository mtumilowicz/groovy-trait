package trait.chain

import spock.lang.Specification

class MessageHandlerTest extends Specification {
    def "test handle - message contains Handle1"() {
        given:
        def handler = new MessageHandler()

        expect:
        handler.handle("message for Handler1") == "Handler1"
    }

    def "test handle - message contains Handle2"() {
        given:
        def handler = new MessageHandler()

        expect:
        handler.handle("message for Handler2") == "Handler2"
    }

    def "test handle - message contains Handle3"() {
        given:
        def handler = new MessageHandler()

        expect:
        handler.handle("message for Handler3") == "Handler3"
    }
}
