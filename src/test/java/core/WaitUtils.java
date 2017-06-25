package core;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by kotvertolet on 6/24/2017.
 */
public class WaitUtils {

    public static FluentWait<Integer> getFluentWait(int timeout, int pollingTimeout, String errorMessage) {
        return new FluentWait<>(0).withTimeout(timeout, TimeUnit.SECONDS).pollingEvery(pollingTimeout, TimeUnit.MILLISECONDS)
                .withMessage(errorMessage);
    }

    public void waitUntilVideoStartToPlay(int timeoutSecs, int pollingTimeout, SelenideElement element) {
        getFluentWait(timeoutSecs, pollingTimeout, String.format("Video did't started to play after ", timeoutSecs))
                .until(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(Integer integer) {
                        return !element.getAttribute("class").contains("buffering-mode")
                                && !element.getAttribute("class").contains("paused-mode")
                                && element.getAttribute("class").contains("playing-mode");
                    }
                });
    }

    public void waitUntilCollectionFilled(int timeoutSecs, int pollingTimeout, ElementsCollection elementsCollection) {
        getFluentWait(timeoutSecs, pollingTimeout, String.format("Collection wasn't filled in ", timeoutSecs))
                .until(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(Integer integer) {
                        return elementsCollection.size() > 0;
                    }
                });
    }

}
