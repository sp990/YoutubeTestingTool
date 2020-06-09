public class App {

    public void run() {
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
