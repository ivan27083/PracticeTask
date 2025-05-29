import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.*;
import org.junit.platform.launcher.core.*;
import org.junit.platform.launcher.listeners.*;

public class TestRunner {
    public static void runTests() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        DiscoverySelectors.selectClass(LinearHashMapTest.class),
                        DiscoverySelectors.selectClass(ChainHashMapTest.class),
                        DiscoverySelectors.selectClass(AVLTreeTest.class)
                )
                .build();

        Launcher launcher = LauncherFactory.create();

        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();

        System.out.println("\n========= РЕЗУЛЬТАТЫ ТЕСТОВ =========");
        System.out.println("Всего тестов: " + summary.getTestsFoundCount());
        System.out.println("Пройдено успешно: " + summary.getTestsSucceededCount());
        System.out.println("Провалено: " + summary.getTestsFailedCount());
        System.out.println("Пропущено: " + summary.getTestsSkippedCount());

        if (!summary.getFailures().isEmpty()) {
            System.out.println("\n--- Ошибки ---");
            for (TestExecutionSummary.Failure failure : summary.getFailures()) {
                System.out.println(failure.getTestIdentifier().getDisplayName());
                System.out.println("→ " + failure.getException());
            }
        }
    }
}
