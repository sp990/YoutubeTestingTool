public class App {

    public void run() {
        MetamorphicTestRunner[] tests = generateTests();
        int testsPassed = 0;
        int testsFailed = 0;

        for(MetamorphicTestRunner test : tests){
            test.runTest();
            if(test.getResult())
                testsPassed++;
            else
                testsFailed++;
        }

        System.out.println("Tests Passed " + testsPassed + " Test Failed: " + testsFailed);
    }

    public MetamorphicTestRunner[] generateTests() {
        MetamorphicTestRunner tests[] =
                {
                        new CompleteRunner(),
                        new DifferenceRunner(),
                        new DisjointRunner(),
                        new EqualityRunner(),
                        new EquivalenceRunner(),
                        new SubsetRunner()
                };

        return tests;
    }

    public static void main(String[] args) {
        new App().run();
    }
}
