package comDay0314;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "testClass")
public class TestClass {
    @Value(value = "泉水姐姐超级粉丝")
    private String str;
    public TestClass(){System.out.println("调用了构造方法");}
    public void sayHello(){System.out.println("Hello everyone");}
    public String getStr(){return this.str;}
}
