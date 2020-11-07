package fitr.common;

import org.junit.jupiter.api.Test;

import static fitr.common.ResourceManager.loadResource;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResourceManagerTest {
    @Test
    public void resourceManagerTest_fail() {
        assertThrows(IllegalArgumentException.class, () -> {
            loadResource("exerciseList.txt"); });
    }
}
