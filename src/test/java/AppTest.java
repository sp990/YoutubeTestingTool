import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void generateTests_CallMethod_ArrayReturnedWithOneOfEachTestType() {
        MetamorphicTestRunner[] expectedResult =
                {
                    new CompleteRunner(),
                    new DifferenceRunner(),
                    new DisjointRunner(),
                    new EqualityRunner(),
                    new EquivalenceRunner(),
                    new SubsetRunner()
                };

        MetamorphicTestRunner[] actualResult = new App().generateTests();

        for(int i = 0; i < actualResult.length; i++)
            Assert.assertEquals(expectedResult[i].getClass(), actualResult[i].getClass());
    }


}
