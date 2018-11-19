# groovy-trait
Overview of groovy trait.

_Reference_: http://docs.groovy-lang.org/next/html/documentation/core-traits.html

# preface
**Traits** can be seen as interfaces carrying both default 
implementations and state. A trait is defined using the 
trait keyword:
```
trait Swimmer {
    def swim() {
        "swimming"
    }
}
```
Then it can be used like a normal interface using 
the implements keyword:
```
class Penguin implements Swimmer {
}
```
* methods declared in traits could be:
    * public
    * abstract
    * private
    * cannot be final
    * it is possible to override them in the implementing class
    Traits only support public and private methods. 
    Neither protected nor package private scopes are 
    supported.
    
    If we have a class implementing a trait, conceptually implementations from the trait methods are "inherited" into the class. But, in reality, there is no base class containing such implementations. Rather, they are woven directly into the class.
    
    You might consider creating a base class which implements the desired trait(s) if you want trait implementation methods that can’t be overridden.
* `this` represents the implementing instance
* traits may implement interfaces
* trait may define properties
    * final fields
    * all access levels
    *  in order to avoid the diamond problem, field names 
    are remapped in the implementing class:
    example:
    if the type of the field is String, the name of the package is my.package, the name of the trait is Foo and the name of the field is bar, in the implementing class, the public field will appear as:
    
    `String my_package_Foo__bar`
* Traits may extend another trait (`extends` keyword)
* trait may extend multiple traits (`implements` keyword)
* duck typing: Traits can call any dynamic code, like a normal Groovy class
(you can, in the body of a method, call methods which are supposed to exist in an implementing class, without having to explicitly declare them in an interface
)
* could implement MOP methods like `methodMissing` 
or `propertyMissing`
*  If some trait defines a method with the same signature as a method in another trait
last declared trait in the implements clause wins.
* you can explicitly choose which method to call using the Trait.super.foo syntax
    ```
    class C implements A,B {
        String exec() { A.super.exec() }    
    }
    def c = new C()
    assert c.exec() == 'A'  
    ```
* groovy also supports implementing traits dynamically at 
runtime
```
trait Extra {
    String extra() { "I'm an extra method" }            
}
class Something {                                       
    String doSomething() { 'Something' }                
}
```
```
def s = new Something() as Extra
s.extra()
s.doSomething()
```

When coercing an object to a trait, the result of the operation is not the same instance. It is guaranteed that the coerced object will implement both the trait and the interfaces that the original object implements, but the result will not be an instance of the original class.

* when you want to implement several traits at once, you can use the withTraits method instead of the as keyword
* it is possible to easily compose chains of responsibility with traits
* `super`:
    * if the class implements another trait, the call delegates to the next trait in the chain
    * if there isn’t any trait left in the chain, super refers to the super class of the implementing class (this)
* SAM (Single Abstract Method) type coercion for traits with single abstract method (WHAT IF NOT ABSTRACT BUT SINGLE)
* 

# differences to java 8 default methods
