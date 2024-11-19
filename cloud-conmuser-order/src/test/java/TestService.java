import jakarta.annotation.Resource;
import org.example.OrderConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderConsumer.class)
public class TestService {

    @Resource
    private DiscoveryClient discoveryClient;

    @Test
    public void test01(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getHost() + ":" + instance.getPort());
        }
        System.out.println("----------------------");
        System.out.println(instances.get(0).getHost() + ":" + instances.get(0).getPort());
    }
}
