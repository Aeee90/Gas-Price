package aeee.api.gasprice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Order(Ordered.LOWEST_PRECEDENCE)
public class PropertiesEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final List<String> CUSTOM_PROPERTIES = Arrays.asList("properties/infura.properties");

    private PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment,
                                       SpringApplication application) {
        MutablePropertySources mps = environment.getPropertySources();
        CUSTOM_PROPERTIES.forEach(path -> {
            for(PropertySource propertySource : loadProperties(new ClassPathResource(path)))
                mps.addLast(propertySource);
        });
    }

    private List<PropertySource<?>> loadProperties(Resource path) {
        if (!path.exists()) throw new IllegalArgumentException("Resource " + path + " does not exist");
        try {
            return loader.load("custom-resource", path);
        }
        catch (IOException ex) {
            throw new IllegalStateException("Failed to load yaml configuration from " + path, ex);
        }
    }
}
