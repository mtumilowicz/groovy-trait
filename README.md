[![Build Status](https://travis-ci.com/mtumilowicz/groovy-trait.svg?branch=master)](https://travis-ci.com/mtumilowicz/groovy-trait)

# groovy-trait
Overview of groovy trait.

_Reference_: http://docs.groovy-lang.org/next/html/documentation/core-traits.html  
_Reference_: [Using Traits, Mixins and Monads in JVM Languages](https://www.youtube.com/watch?v=NsZoFno8Mfk)

# preface
* **traits** can be seen as interfaces carrying both default 
implementations and state
    * trait is defined using the trait keyword:
        ```
        trait Swimmer {
            def swim() {
                "swimming"
            }
        }
        ```
        then it can be used like a normal interface using 
        the implements keyword:
        ```
        class Penguin implements Swimmer {
        }
        ```
        * **example**: package `birds`, tests: `TraitTest`
* **traits** can be seen as a delegation pattern build into language
    * explicit delegation
        ```
        interface X { 
            void a()
        }
        class XImpl implements X {
            public void a() {} // suppose this method is used in many classes, we don't want copy-paste so we abstract it
        }
        class Y implements X {
            XImpl x
      
            public void a() {
                x.a()
            }
        }
        ```
    * becomes traits (in fact - compiler changes it to something very similar as above `explicit delegation`)
        ```
        trait X { 
            void a() {}
        }
        class Y implements X {
        }
        ```
    * it gets more sense when fields (state) come to play
* it could be shipped on a class level and an instance level as well

# details

## method
* methods declared in traits:
    * could be public and private methods (neither protected 
    nor package private scopes are supported)
    * could be abstract
    * cannot be final (you might consider creating a base 
    class with final implementation of the desired trait(s))
    * could be overridden in the implementing class
    * could be MOP methods like `methodMissing` 
    or `propertyMissing`
    
* if we have a class implementing a trait, implementations 
are woven directly into the class (there is no base class)
* if some trait defines a method with the same signature as 
a method in another trait - last declared trait in the 
implements clause wins, but you can also explicitly choose 
which method to call using the `Trait.super.foo` syntax
    ```
    trait T1 {
        def get() {
            "T1"
        }
    }
    trait T2 {
        def get() {
            "T2"
        }
    }
    ```
    ```
    class C implements T1, T2 {
        def getFromT1() {
            T1.super.get()
        }
    
        def getFromT2() {
            T2.super.get()
        }
    }
    ```
    and tests:
    ```
    expect:
    new C().getFromT1() == "T1"
    new C().getFromT2() == "T2"
    ```

**example**: package `superclass`, tests: `CustomClassTest`

## fields
* fields defined in traits:
    * could be final
    * could have all access levels
    * in order to avoid the diamond problem, field names are 
    remapped in the implementing class - if the type of the 
    field is `String`, the name of the package is `my.package`, 
    the name of the trait is `Foo` and the name of the field 
    is `bar`, in the implementing class, the public field will 
    appear as: `String my_package_Foo__bar`
        ```
        trait TraitWithField {
            int id = 5
        }
        ```
        ```
        class C implements TraitWithField {
        }
        ```
        and test:
        ```
        expect:
        new C().trait_field_TraitWithField__id == 5
        new C().id == 5
        ```

* fields from traits are shadowed:
    ```
    trait TraitWithField {
        int id = 1
        
        def id(){
            id
        }
    }
    ```
    ```
    class ClassWithField implements TraitWithField {
        int id = 5
    }
    ```
    and test:
    ```
    given:
    def classWithField = new ClassWithField()
    
    expect:
    classWithField.id == 5
    classWithField.id() == 1
    ```

**example**: package `field`, tests: `TraitFieldTest`, `ShadowingFieldTest`

## duck typing
_Reference_: https://en.wikipedia.org/wiki/Duck_typing

With normal typing, suitability is determined by an 
object's type. In **duck typing**, an object's suitability 
is determined by the presence of certain methods and 
properties, rather than the type of the object itself.

Traits can call any dynamic code, like a normal Groovy class
(you can, in the body of a method, call methods which are 
supposed to exist in an implementing class, without having 
to explicitly declare them in an interface).
```
trait DuckTypingTrait {
    def introduce() {
        "hi ${getName()}"
    }
}
```
```
class Named implements DuckTypingTrait {
    String name
}
```
and test:
```
expect:
new Named(name: "name").name == "name"
new Named(name: "name").introduce() == "hi name"
```

**example**: package `ducktyping`, tests: `DuckTypingTest`

## implementing traits dynamically
* Groovy supports implementing traits dynamically at 
runtime
    ```
    trait Extra {
        String extra() { "I'm an extra method" }            
    }
    class Something {                                       
        String doSomething() { 'Something' }                
    }
    ```
    single trait at runtime: `obj as trait1`, multiple traits at runtime: `obj.withTraits trait1, trait2, trait3`
    ```
    def s = new Something() as Extra // when you need to bind extra behaviour rather to the instance than the class
    s.extra()
    s.doSomething()
    ```

* When coercing an object to a trait, the result of the 
operation is not the same instance. It is guaranteed 
that the coerced object will implement both the trait 
and the interfaces that the original object implements, 
but the result will not be an instance of the original 
class.
    * `interface I1 {}`
    * `trait RuntimeTrait1 {}`
    * `trait RuntimeTrait2 {}`
    * `class C implements I1 {}`
    
    and tests:
    ```
    given:
    def c = new C()
    
    when:
    def newC = c as RuntimeTrait1
    
    then:
    newC instanceof I1
    newC instanceof RuntimeTrait1
    !(newC instanceof C)    
    ```

* when you want to implement several traits at once, 
you can use the `withTraits` method instead of the 
`as` keyword
    ```
    given:
    def c = new C()
    
    when:
    def newC = c.withTraits RuntimeTrait1, RuntimeTrait2
    
    then:
    newC instanceof RuntimeTrait1
    newC instanceof RuntimeTrait2
    ```

*  if you use runtime traits, the methods from the 
trait are always preferred to those of the proxied 
object
    ```
    class C {
        def get() {
            "C"
        }
    }
    ```
    ```
    trait RuntimeTrait1 {
        def get() {
            "RuntimeTrait1"
        }
    }
    ```
    and test
    ```
    given:
    def c = new C()
    
    when:
    def newC = c as RuntimeTrait1
    
    then:
    newC.get() == "RuntimeTrait1"
    ```

**example**: `runtime.RuntimeTraitTypeTest`, `preference.RuntimeTraitMethodPreferenceTest`

## chains of responsibility
* it is possible to easily compose chains of responsibility 
with traits
    ```
    class MessageHandler implements Handler1, Handler2, Handler3 {}
    ```
    ```
    trait Handler1 {
        def handle(String message) {
            if (message.contains("Handler1")) {
                return "Handler1"
            }
            return super.handle(message)
        }
    }
    trait Handler2 {
        def handle(String message) {
            if (message.contains("Handler2")) {
                return "Handler2"
            }
            return super.handle(message)
        }
    }
    trait Handler3 {
        def handle(String message) {
            if (message.contains("Handler3")) {
                return "Handler3"
            }
            return super.handle(message)
        }
    }
    ```
    and tests:
    ```
    given:
    def handler = new MessageHandler()
    
    expect:
    handler.handle("message for Handler1") == "Handler1"
    handler.handle("message for Handler2") == "Handler2"
    handler.handle("message for Handler3") == "Handler3"
    ```
* `super`:
    * if the class implements another trait, the call 
    delegates to the next trait in the chain
    * if there isn’t any trait left in the chain, super 
    refers to the super class of the implementing 
    class (`this`)

**example**: package `chain`, tests: `MessageHandlerTest`

## SAM
* SAM (Single Abstract Method) type coercion for traits 
with single abstract method 
    * single abstract method
        ```
        trait SingleAbstractMethodTrait {
            def other() {
            }
            abstract def getName()
        }
        ```
        and test:
        ```
        when:
        SingleAbstractMethodTrait t = { "name" }
        
        then:
        t.getName() == "name"
        ```
    * single NOT abstract method - `GroovyCastException`
        ```
        trait SingleNotAbstractMethodTrait {
            def getName() {
                "name"
            }
        }
        ```
        and test
        ```
        when:
        SingleNotAbstractMethodTrait a = { "A" }
        
        then:
        thrown(GroovyCastException)
        ```

**example**: package `sam`, tests: `SingleAbstractMethodTraitTest`, `SingleNotAbstractMethodTraitTest`

# vs java 8
* note that
    ```
    trait X {
        String a
  
        int b() {}
    }
    ```
    could be seen as Java's interface
    ```
    interface X {
        String getA();
        int b() {};
    }
    ```
* but interfaces in java do not have the concept of chaining
* interfaces cannot be attached per instance 
* if a class does not provide the implementation - the implementation 
from the trait is always used if the class declares the trait in its 
interface list
    * feature particular useful when you don’t have access to the super class source code
    * example in package `trait.superclass`

# meaning of this
* `this` represents the implementing instance
* Sometimes you will want to write a trait that 
can only be applied to some type. In order to 
make this contract explicit we use `@SelfType`:
    * it let you declare the types that a class 
    that implements this trait must inherit or implement
    * throw a compile time error if those type 
    constraints are not satisfied
    
    ```
    @SelfType(Device)
    @CompileStatic
    trait Communicating {
        void sendMessage(Device to, String message) {
            SecurityService.check(this)
            CommunicationService.sendMessage(id, to.id, message)
        }
    }
    ```
    ```
    class MyDevice implements Communicating {} // forgot to extend Device
    ```
    then compile time error:
    
    `class 'MyDevice' implements trait 'Communicating' but does not extend self type class 'Device'`

## inheritance
* traits may implement interfaces
* traits may extend another trait (`extends` keyword)
* traits may extend multiple traits (`implements` keyword)

## additional info
* prefix and postfix operations are not allowed if they update a field of the trait
* static member support is work in progress and still experimental
