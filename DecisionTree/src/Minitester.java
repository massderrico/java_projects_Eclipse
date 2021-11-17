//
//
//import java.util.Arrays;
//
//
//class ReadFile {
//    public static String localDir = System.getProperty("user.dir");
//    //You might need to change these based on your operating system.
//    public static String base = localDir + "/src/data/";
//    //You might need to change these based on your operating system.
//    public static String basedb = localDir + "/src/data/db/";
//
//    public static DecisionTree getDTFromFile(String filename) {
//        DecisionTree dt = DataReader.readSerializedTree(base + filename);
//        if (dt == null) throw new AssertionError("[ERROR] Could not read DT from file.");
//        return dt;
//    }
//
//    public static DataReader getCSVDataReader(String filename) {
//        DataReader dr = new DataReader();
//        try {
//            dr.read_data(basedb + filename);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new AssertionError("[ERROR] Could not read csv file.");
//        }
//        return dr;
//    }
//}
//
//
//class DecisionTree_classify1 implements Runnable{
//
//    @Override
//    public void run() {
//        boolean verbose = false;
//
//        DataReader dr = ReadFile.getCSVDataReader("data_minimal_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree dt = ReadFile.getDTFromFile("data_minimal_overlap/thresh1.ser");
//
//        int counter=0;
//        int total = dr.trainData.size();
//        for (int i = 0; i < total; i++)
//        {
//            double[] attrs = dr.trainData.get(i).x;
//            int correctLabel = dr.trainData.get(i).y;
//            int classifiedAs = dt.classify(attrs);
//
//            if (verbose) {
//                System.out.println("Attributes: " + Arrays.toString(attrs));
//                System.out.println("Correct label: " + correctLabel + ", Your classification :" + classifiedAs);
//            }
//            if (correctLabel == classifiedAs)
//            {
//                counter++;
//            }
//        }
//        System.out.println("Number of correct outputs : " + counter + " out of " + total);
//
//        if (counter != total) {
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class DecisionTree_classify2 implements Runnable{
//
//    @Override
//    public void run() {
//        boolean verbose = false;
//
//        DataReader dr = ReadFile.getCSVDataReader("data_partial_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree dt = ReadFile.getDTFromFile("data_partial_overlap/thresh1.ser");
//
//        int counter=0;
//        int total = dr.trainData.size();
//        for (int i = 0; i < total; i++)
//        {
//            double[] attrs = dr.trainData.get(i).x;
//            int correctLabel = dr.trainData.get(i).y;
//            int classifiedAs = dt.classify(attrs);
//
//            if (verbose) {
//                System.out.println("Attributes: " + Arrays.toString(attrs));
//                System.out.println("Correct label: " + correctLabel + ", Your classification :" + classifiedAs);
//            }
//            if (correctLabel == classifiedAs)
//            {
//                counter++;
//            }
//        }
//        System.out.println("Number of correct outputs : " + counter + " out of " + total);
//
//        if (counter != total) {
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class DecisionTree_classify3 implements Runnable{
//
//    @Override
//    public void run() {
//        boolean verbose = false;
//
//        DataReader dr = ReadFile.getCSVDataReader("data_high_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree dt = ReadFile.getDTFromFile("data_high_overlap/thresh1.ser");
//
//        int counter=0;
//        int total = dr.trainData.size();
//        for (int i = 0; i < total; i++)
//        {
//            double[] attrs = dr.trainData.get(i).x;
//            int correctLabel = dr.trainData.get(i).y;
//            int classifiedAs = dt.classify(attrs);
//
//            if (verbose) {
//                System.out.println("Attributes: " + Arrays.toString(attrs));
//                System.out.println("Correct label: " + correctLabel + ", Your classification :" + classifiedAs);
//            }
//            if (correctLabel == classifiedAs)
//            {
//                counter++;
//            }
//        }
//        System.out.println("Number of correct outputs : " + counter + " out of " + total);
//
//        if (counter != total) {
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class DecisionTree_classify4 implements Runnable{
//
//    @Override
//    public void run() {
//        boolean verbose = false;
//
//        DataReader dr = ReadFile.getCSVDataReader("data_minimal_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree dt = ReadFile.getDTFromFile("data_minimal_overlap/thresh2.ser");
//
//        int counter=0;
//        int total = dr.trainData.size();
//        for (int i = 0; i < total; i++)
//        {
//            double[] attrs = dr.trainData.get(i).x;
//            int correctLabel = dr.trainData.get(i).y;
//            int classifiedAs = dt.classify(attrs);
//
//            if (verbose) {
//                System.out.println("Attributes: " + Arrays.toString(attrs));
//                System.out.println("Correct label: " + correctLabel + ", Your classification :" + classifiedAs);
//            }
//            if (correctLabel == classifiedAs)
//            {
//                counter++;
//            }
//        }
//        System.out.println("Number of correct outputs : " + counter + " out of " + total);
//
//        if (counter != total) {
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class Equals_HighDiffFiles implements Runnable{
//
//    @Override
//    public void run() {
//        DecisionTree dt1 = ReadFile.getDTFromFile("data_high_overlap/thresh4.ser");
//        DecisionTree dt2 = ReadFile.getDTFromFile("data_high_overlap/thresh1.ser");
//        if (DecisionTree.equals(dt1, dt2)) {
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class Equals_HighSameFiles implements Runnable{
//
//    @Override
//    public void run() {
//        DecisionTree dt1 = ReadFile.getDTFromFile("data_high_overlap/thresh1.ser");
//        DecisionTree dt2 = ReadFile.getDTFromFile("data_high_overlap/thresh1.ser");
//        if (!DecisionTree.equals(dt1, dt2)) {
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class Equals_MinDiffFiles implements Runnable{
//
//    @Override
//    public void run() {
//        DecisionTree dt1 = ReadFile.getDTFromFile("data_minimal_overlap/thresh8.ser");
//        DecisionTree dt2 = ReadFile.getDTFromFile("data_minimal_overlap/thresh1.ser");
//        if (DecisionTree.equals(dt1, dt2)) {
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class Equals_MinSameFiles implements Runnable{
//
//    @Override
//    public void run() {
//        DecisionTree dt1 = ReadFile.getDTFromFile("data_minimal_overlap/thresh4.ser");
//        DecisionTree dt2 = ReadFile.getDTFromFile("data_minimal_overlap/thresh4.ser");
//        if (!DecisionTree.equals(dt1, dt2)) {
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class Equals_PartialDiffFiles implements Runnable{
//
//    @Override
//    public void run() {
//        DecisionTree dt1 = ReadFile.getDTFromFile("data_partial_overlap/thresh4.ser");
//        DecisionTree dt2 = ReadFile.getDTFromFile("data_partial_overlap/thresh1.ser");
//        if (DecisionTree.equals(dt1, dt2)) {
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class Equals_PartialSameFiles implements Runnable{
//
//    @Override
//    public void run() {
//        DecisionTree dt1 = ReadFile.getDTFromFile("data_high_overlap/thresh1.ser");
//        DecisionTree dt2 = ReadFile.getDTFromFile("data_high_overlap/thresh1.ser");
//        if (!DecisionTree.equals(dt1, dt2)) {
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class FillDTNode_High1 implements Runnable{
//    @Override
//    public void run() {
//        int threshold = 1;
//        DataReader dr = ReadFile.getCSVDataReader("data_high_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree serdt = ReadFile.getDTFromFile("data_high_overlap/thresh" + threshold +".ser");
//        DecisionTree dt = new DecisionTree(dr.trainData , threshold);
//
//        if (!DecisionTree.equals(serdt,dt)){
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class FillDTNode_High2 implements Runnable{
//    @Override
//    public void run() {
//        int threshold = 4;
//        DataReader dr = ReadFile.getCSVDataReader("data_high_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree serdt = ReadFile.getDTFromFile("data_high_overlap/thresh" + threshold +".ser");
//        DecisionTree dt = new DecisionTree(dr.trainData , threshold);
//
//        if (!DecisionTree.equals(serdt,dt)){
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class FillDTNode_High3 implements Runnable{
//    @Override
//    public void run() {
//        int threshold = 32;
//        DataReader dr = ReadFile.getCSVDataReader("data_high_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree serdt = ReadFile.getDTFromFile("data_high_overlap/thresh" + threshold +".ser");
//        DecisionTree dt = new DecisionTree(dr.trainData , threshold);
//
//        if (!DecisionTree.equals(serdt,dt)){
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class FillDTNode_Min1 implements Runnable{
//    @Override
//    public void run() {
//        int threshold = 1;
//        DataReader dr = ReadFile.getCSVDataReader("data_minimal_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree serdt = ReadFile.getDTFromFile("data_minimal_overlap/thresh" + threshold +".ser");
//        DecisionTree dt = new DecisionTree(dr.trainData , threshold);
//
//        if (!DecisionTree.equals(serdt,dt)){
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class FillDTNode_Min2 implements Runnable{
//    @Override
//    public void run() {
//        int threshold = 4;
//        DataReader dr = ReadFile.getCSVDataReader("data_minimal_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree serdt = ReadFile.getDTFromFile("data_minimal_overlap/thresh" + threshold +".ser");
//        DecisionTree dt = new DecisionTree(dr.trainData , threshold);
//
//        if (!DecisionTree.equals(serdt,dt)){
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class FillDTNode_Min3 implements Runnable{
//    @Override
//    public void run() {
//        int threshold = 64;
//        DataReader dr = ReadFile.getCSVDataReader("data_minimal_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree serdt = ReadFile.getDTFromFile("data_minimal_overlap/thresh" + threshold +".ser");
//        DecisionTree dt = new DecisionTree(dr.trainData , threshold);
//
//        if (!DecisionTree.equals(serdt,dt)){
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class FillDTNode_Min4 implements Runnable{
//    @Override
//    public void run() {
//        int threshold = 128;
//        DataReader dr = ReadFile.getCSVDataReader("data_minimal_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree serdt = ReadFile.getDTFromFile("data_minimal_overlap/thresh" + threshold +".ser");
//        DecisionTree dt = new DecisionTree(dr.trainData , threshold);
//
//        if (!DecisionTree.equals(serdt,dt)){
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class FillDTNode_Partial1 implements Runnable{
//    @Override
//    public void run() {
//        int threshold = 1;
//        DataReader dr = ReadFile.getCSVDataReader("data_partial_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree serdt = ReadFile.getDTFromFile("data_partial_overlap/thresh" + threshold +".ser");
//        DecisionTree dt = new DecisionTree(dr.trainData , threshold);
//
//        if (!DecisionTree.equals(serdt,dt)){
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class FillDTNode_Partial2 implements Runnable{
//    @Override
//    public void run() {
//        int threshold = 8;
//        DataReader dr = ReadFile.getCSVDataReader("data_partial_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree serdt = ReadFile.getDTFromFile("data_partial_overlap/thresh" + threshold +".ser");
//        DecisionTree dt = new DecisionTree(dr.trainData , threshold);
//
//        if (!DecisionTree.equals(serdt,dt)){
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//class FillDTNode_Partial3 implements Runnable{
//    @Override
//    public void run() {
//        int threshold = 16;
//        DataReader dr = ReadFile.getCSVDataReader("data_partial_overlap.csv");
//        dr.splitTrainTestData(.5);
//
//        DecisionTree serdt = ReadFile.getDTFromFile("data_partial_overlap/thresh" + threshold +".ser");
//        DecisionTree dt = new DecisionTree(dr.trainData , threshold);
//
//        if (!DecisionTree.equals(serdt,dt)){
//            throw new AssertionError("Test failed.");
//        }
//        System.out.println("Test passed.");
//    }
//}
//
//
//public class Minitester {
//    // To skip running some tests, just comment them out below.
//    static String[] tests = {
//        "DecisionTree_classify1",
//	"DecisionTree_classify2",
//	"DecisionTree_classify3",
//	"DecisionTree_classify4",
//	"Equals_HighDiffFiles",
//	"Equals_HighSameFiles",
//	"Equals_MinDiffFiles",
//	"Equals_MinSameFiles",
//	"Equals_PartialDiffFiles",
//	"Equals_PartialSameFiles",
//	"FillDTNode_High1",
//	"FillDTNode_High2",
//	"FillDTNode_High3",
//	"FillDTNode_Min1",
//	"FillDTNode_Min2",
//	"FillDTNode_Min3",
//	"FillDTNode_Min4",
//	"FillDTNode_Partial1",
//	"FillDTNode_Partial2",
//	"FillDTNode_Partial3"
//    };
//    public static void main(String[] args) {
//        int numPassed = 0;
//        for(String className: tests)    {
//            System.out.printf("%n======= %s =======%n", className);
//            System.out.flush();
//            try {
//                Runnable testCase = (Runnable) Class.forName(className).getDeclaredConstructor().newInstance();
//                testCase.run();
//                numPassed++;
//            } catch (AssertionError e) {
//                System.out.println(e);
//            } catch (Exception e) {
//                e.printStackTrace(System.out);
//            }
//        }
//        System.out.printf("%n%n%d of %d tests passed.%n", numPassed, tests.length);
//    }
//}
