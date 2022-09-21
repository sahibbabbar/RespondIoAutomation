package Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

public class Verify extends Assertion {
    private static final Logger LOGGER = LoggerFactory.getLogger(Verify.class);

    public void onAssertSuccess(IAssert<?> assertCommand) {
        String suffix = String.format("Expected [%s] and found [%s]", assertCommand.getExpected().toString(), assertCommand.getActual().toString());
        LOGGER.info("[PASSED] - " + assertCommand.getMessage() + ". " + suffix);
    }

    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        String suffix = String.format("Expected [%s] but found [%s]", assertCommand.getExpected().toString(), assertCommand.getActual().toString());
        LOGGER.error("[FAILED] - " + assertCommand.getMessage() + ". " + suffix);
    }
}