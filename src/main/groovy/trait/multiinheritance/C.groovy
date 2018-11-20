package trait.multiinheritance

/**
 * Created by mtumilowicz on 2018-11-20.
 */
class C implements T1, T2 {
    def getFromT1() {
        T1.super.get()
    }

    def getFromT2() {
        T2.super.get()
    }
}
